package eg.edu.alexu.csd.oop.game.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import eg.edu.alexu.csd.oop.game.COP;
import eg.edu.alexu.csd.oop.game.model.Quality;
import eg.edu.alexu.csd.oop.game.model.object.ImagesVisitor;

/**
 * @dPattern Singleton
 */
@SuppressWarnings("serial")
class SettingsFrame extends COPFrame {

	private static SettingsFrame single_instance = null;

	private SettingsFrame() {
		addObserver(ImagesVisitor.ImagesManger);
		pointerToMySelf = this;
		initiateFrame();
		layoutButtons();
		updateRATIO();
	}

	private void updateRATIO() {
		COP.RATIO = getScreenSize().getWidth() / GameWindowSize.getWidth();
	}

	protected static SettingsFrame getInstance() {
		if (single_instance == null)
			single_instance = new SettingsFrame();

		return single_instance;
	}

	protected static Dimension GameWindowSize = new Dimension((int) (getScreenSize().width / 1.5),
			(int) (getScreenSize().height / 1.5));

	private boolean fullScreenActive = false;

	private JLabel windowsize = new JLabel("");
	private JLabel fullwindow = new JLabel("");

	private Observable notifier = new Observable();
	private Quality picQuality = Quality.MEDIUM;

	private void addObserver(Observer o) {
		notifier.addObserver(o);
	}

	public void showFrame() {
		logr.info("Setting frame opened");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	protected void initiateFrame() {
		setTitle("Setting");
		ImageIcon image = new ImageIcon(getRelativePakagePath("icon.png"));
		setIconImage(image.getImage());
		removeBackgroundColor();
		setUpBackground();
	}

	private void setUpBackground() {
		ImageIcon background = new ImageIcon(getRelativePakagePath("settingmenu.png"));
		setSize(608, 845);
		setLocationRelativeTo(null);
		Image image = background.getImage();
		Image newimg = image.getScaledInstance(getWidth() / 2, getHeight() / 2, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newimg);
		setContentPane(new JLabel(background));
	}

	protected void layoutButtons() {
		addQualityButton();
		addMuteButton();
		addFullScreenButton();
		addWindowSizeButton();
		addBackButton();
	}

	private void addMuteButton() {
		JLabel mutebtn = new JLabel("");
		if (GamePanel.soundclicked % 2 == 0) {
			mutebtn.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("unmute.png")).getImage()
					.getScaledInstance(283 / 3, 232 / 3, Image.SCALE_DEFAULT)));

		} else {

			mutebtn.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("mute.png")).getImage()
					.getScaledInstance(283 / 3, 232 / 3, Image.SCALE_DEFAULT)));
		}
		Dimension size = mutebtn.getPreferredSize();
		mutebtn.setBounds(285, 505, size.width, size.height);
		mutebtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GamePanel.soundclicked++;
				if (GamePanel.soundclicked % 2 == 0) {
					logr.info("Sound muted from setting in start");
					COPFrame.soundClip.stop();
					mutebtn.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("unmute.png"))
							.getImage().getScaledInstance(283 / 3, 232 / 3, Image.SCALE_DEFAULT)));
				} else {
					logr.info("Sound unmuted from setting in start");
					COPFrame.soundClip.setFramePosition(0);
					COPFrame.soundClip.loop(Clip.LOOP_CONTINUOUSLY);
					mutebtn.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("mute.png")).getImage()
							.getScaledInstance(283 / 3, 232 / 3, Image.SCALE_DEFAULT)));
				}
			}

		});
		add(mutebtn);
	}

	private void turnFullScreenON() {
		fullwindow.setIcon(new ImageIcon(ToggleButtons.FULL_SCREEN.ON));
		windowsize.setIcon(new ImageIcon(ToggleButtons.WINDOW_SIZE.OFF));
		GameWindowSize = new Dimension(getScreenSize().width, getScreenSize().height);
	}

	private void turnFullScreenOFF() {
		fullwindow.setIcon(new ImageIcon(ToggleButtons.FULL_SCREEN.OFF));
		windowsize.setIcon(new ImageIcon(ToggleButtons.WINDOW_SIZE.ON));
		GameWindowSize = new Dimension((int) (getScreenSize().width / 1.5), (int) (getScreenSize().height / 1.5));
	}

	private void addFullScreenButton() {
		if (fullScreenActive)
			fullwindow.setIcon(new ImageIcon(ToggleButtons.FULL_SCREEN.ON));
		else
			fullwindow.setIcon(new ImageIcon(ToggleButtons.FULL_SCREEN.OFF));

		Dimension size = fullwindow.getPreferredSize();
		fullwindow.setBounds(225, 440, size.width, size.height);

		fullwindow.addMouseListener((MouseListener) new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				fullScreenActive = !fullScreenActive;

				if (!fullScreenActive) {
					logr.info("full screen off");
					turnFullScreenOFF();
				}
					
				else {
					logr.info("full screen on");
					turnFullScreenON();
				}
					

				updateRATIO();

			}

		});

		add(fullwindow);
	}

	private void addWindowSizeButton() {

		if (!fullScreenActive)
			windowsize.setIcon(new ImageIcon(ToggleButtons.WINDOW_SIZE.ON));
		else
			windowsize.setIcon(new ImageIcon(ToggleButtons.WINDOW_SIZE.OFF));

		Dimension size = windowsize.getPreferredSize();
		windowsize.setBounds(225, 370, size.width, size.height);

		windowsize.addMouseListener((MouseListener) new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				logr.info("Window size changed");
				Dimension[] values = getValidScreenSizes();

				if (fullScreenActive) {
					if (JOptionPane.showConfirmDialog(pointerToMySelf, "Disable Full Screen Mode ", "Full Screen",
							JOptionPane.CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						fullScreenActive = false;
						turnFullScreenOFF();
					}
				}

				// this if is accessible if full screen mode is disabled
				if (!fullScreenActive) {

					GameWindowSize = (Dimension) JOptionPane.showInputDialog(null, "Select Size", "Window Size",
							JOptionPane.DEFAULT_OPTION, null, values, GameWindowSize);

					if (GameWindowSize == null)
						GameWindowSize = new Dimension((int) (getScreenSize().width / 1.5),
								(int) (getScreenSize().height / 1.5));

					updateRATIO();

				}

			}

		});
		add(windowsize);
	}

	private void addBackButton() {
		JLabel backbtn = new JLabel("");
		backbtn.setIcon(new ImageIcon(new ImageIcon(getRelativePakagePath("Settingback.png")).getImage()
				.getScaledInstance(182 / 3, 191 / 3, Image.SCALE_DEFAULT)));
		Dimension size = backbtn.getPreferredSize();
		backbtn.setBounds(225, 515, size.width, size.height);
		backbtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logr.warning("Setting is closed");
				pointerToMySelf.dispose();
			}

		});

		add(backbtn);
	}

	private void addQualityButton() {
		JLabel quiltybtn = new JLabel("");
		quiltybtn.setIcon(new ImageIcon(new ImageIcon(getRelativePakagePath("quality.png")).getImage()
				.getScaledInstance(427 / 3, 190 / 3, Image.SCALE_DEFAULT)));
		Dimension size = quiltybtn.getPreferredSize();
		quiltybtn.setBounds(225, 300, size.width, size.height);
		quiltybtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Quality qualities[] = Quality.values();
				picQuality = (Quality) JOptionPane.showInputDialog(null, "Select Quality", "Background Quality",
						JOptionPane.DEFAULT_OPTION, null, qualities, picQuality);

				if (picQuality == null) {
					picQuality = Quality.MEDIUM;
				}

				notifier.notifyObservers(picQuality);

			}

		});

		add(quiltybtn);
	}

	private COPDimension[] getValidScreenSizes() {

		COPDimension[] defaultValues = { /* new COPDimension(640, 360), new COPDimension(854, 480), */
				new COPDimension(1280, 720), new COPDimension(1366, 768), new COPDimension(1600, 900),
				new COPDimension(1920, 1080) };

		int acceptedDims = 0;

		for (int i = 0; i < defaultValues.length; i++) {
			if (defaultValues[i].width > getScreenSize().width || defaultValues[i].height > getScreenSize().height)
				break;
			else
				acceptedDims++;
		}

		return Arrays.copyOf(defaultValues, acceptedDims);
	}

	private class COPDimension extends Dimension {
		private COPDimension(int width, int height) {
			super(width, height);
		}

		public String toString() {
			return width + " X " + height;
		}

	}

	enum ToggleButtons {

		FULL_SCREEN(
				new ImageIcon(getRelativePakagePath("fullscreenbutton.png")).getImage().getScaledInstance(427 / 3,
						190 / 3, Image.SCALE_DEFAULT),
				new ImageIcon(getRelativePakagePath("fullscreenbuttondisabled.png")).getImage()
						.getScaledInstance(427 / 3, 190 / 3, Image.SCALE_DEFAULT)),

		WINDOW_SIZE(
				new ImageIcon(getRelativePakagePath("windowsize.png")).getImage().getScaledInstance(427 / 3, 190 / 3,
						Image.SCALE_DEFAULT),
				new ImageIcon(getRelativePakagePath("windowsizedisabled.png")).getImage().getScaledInstance(427 / 3,
						190 / 3, Image.SCALE_DEFAULT));

		private Image ON;
		private Image OFF;

		ToggleButtons(Image on, Image off) {
			this.ON = on;
			this.OFF = off;
		}

	}

}