package eg.edu.alexu.csd.oop.game.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import eg.edu.alexu.csd.oop.game.model.object.ImagesVisitor;

/**
 * @dPattern Singleton
 */
@SuppressWarnings({ "serial" })
public class LevelsFrame extends COPFrame {

	private static LevelsFrame single_instance = null;

	private LevelsFrame() {
		pointerToMySelf = this;
		initiateFrame();
		layoutButtons();

		addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowClosing(WindowEvent e) {
			}

			// dispose()
			public void windowClosed(WindowEvent e) {
				levelselected = false;
			}

			// requestFocus()
			public void windowActivated(WindowEvent e) {
				resetAllButtons();
			}

			private void resetAllButtons() {
				easybtn.setIcon(new ImageIcon(ToggleButtons.EASY.OFF));
				meduimbtn.setIcon(new ImageIcon(ToggleButtons.MEDUIM.OFF));
				hardbtn.setIcon(new ImageIcon(ToggleButtons.HARD.OFF));
				playbtn.setIcon(new ImageIcon(ToggleButtons.PLAY.OFF));
			}

		});

	}

	protected static LevelsFrame getInstance() {
		if (single_instance == null)
			single_instance = new LevelsFrame();

		return single_instance;
	}

	JLabel easybtn = new JLabel("");
	JLabel meduimbtn = new JLabel("");
	JLabel hardbtn = new JLabel("");
	JLabel playbtn = new JLabel("");
	static protected String mode = new String("Easy");

	static protected boolean levelselected = false;

	public void showFrame() {
		logr.info("Opening levels Frame");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	protected void initiateFrame() {
		setTitle("Select Level");
		ImageIcon image = new ImageIcon(getRelativePakagePath("icon.png"));
		pointerToMySelf.setIconImage(image.getImage());
		removeBackgroundColor();
		setUpBackground();

	}

	private void setUpBackground() {
		ImageIcon background = new ImageIcon(getRelativePakagePath("levlespane.png"));
		setSize(603, 688);
		setLocationRelativeTo(null);
		Image image = background.getImage();
		Image newimg = image.getScaledInstance(getWidth() / 2, getHeight() / 2, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newimg);
		setContentPane(new JLabel(background));
	}

	protected void layoutButtons() {
		addEasyButton();
		addMeduimButton();
		addHardButton();
		addHomeButton();
		addPlayButton();
	}

	private void addEasyButton() {

		easybtn.setIcon(new ImageIcon(ToggleButtons.EASY.OFF));
		Dimension size = easybtn.getPreferredSize();
		easybtn.setBounds(220, 200, size.width, size.height);
		easybtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logr.info("Easy mode is Chosen");
				mode = "Easy";
				levelselected = true;
				easybtn.setIcon(new ImageIcon(ToggleButtons.EASY.ON));
				meduimbtn.setIcon(new ImageIcon(ToggleButtons.MEDUIM.OFF));
				hardbtn.setIcon(new ImageIcon(ToggleButtons.HARD.OFF));
				playbtn.setIcon(new ImageIcon(ToggleButtons.PLAY.ON));
			}
		});
		add(easybtn);
	}

	private void addMeduimButton() {

		meduimbtn.setIcon(new ImageIcon(ToggleButtons.MEDUIM.OFF));
		Dimension size = meduimbtn.getPreferredSize();
		meduimbtn.setBounds(220, 270, size.width, size.height);
		meduimbtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logr.info("Medium mode is Chosen");
				levelselected = true;
				mode = "Meduim";
				easybtn.setIcon(new ImageIcon(ToggleButtons.EASY.OFF));
				meduimbtn.setIcon(new ImageIcon(ToggleButtons.MEDUIM.ON));
				hardbtn.setIcon(new ImageIcon(ToggleButtons.HARD.OFF));
				playbtn.setIcon(new ImageIcon(ToggleButtons.PLAY.ON));
			}
		});
		add(meduimbtn);
	}

	private void addHardButton() {

		hardbtn.setIcon(new ImageIcon(ToggleButtons.HARD.OFF));
		Dimension size = hardbtn.getPreferredSize();
		hardbtn.setBounds(220, 340, size.width, size.height);
		hardbtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logr.info("Hard mode is Chosen");
				levelselected = true;
				mode = "Hard";
				easybtn.setIcon(new ImageIcon(ToggleButtons.EASY.OFF));
				meduimbtn.setIcon(new ImageIcon(ToggleButtons.MEDUIM.OFF));
				hardbtn.setIcon(new ImageIcon(ToggleButtons.HARD.ON));
				playbtn.setIcon(new ImageIcon(ToggleButtons.PLAY.ON));
			}
		});
		add(hardbtn);
	}

	private void addHomeButton() {
		JLabel homebtn = new JLabel("");
		homebtn.setIcon(new ImageIcon(new ImageIcon(getRelativePakagePath("home.png")).getImage()
				.getScaledInstance(182 / 3, 191 / 3, Image.SCALE_DEFAULT)));
		Dimension size = homebtn.getPreferredSize();
		homebtn.setBounds(220, 410, size.width, size.height);
		homebtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logr.warning("Backing home from levels");
				pointerToMySelf.dispose();
			}
		});
		add(homebtn);
	}

	private void addPlayButton() {

		playbtn.setIcon(new ImageIcon(ToggleButtons.PLAY.OFF));
		Dimension size = playbtn.getPreferredSize();
		playbtn.setBounds(320, 410, size.width, size.height);

		playbtn.addMouseListener((MouseListener) new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				logr.info("Moving to the game");
				if (levelselected == true) {
					LoadingFrame.getInstance().showFrame();
					pointerToMySelf.dispose();
					StartFrame.getInstance().dispose();

					Timer delayTimer = new Timer();

					TimerTask refresh = new TimerTask() {

						public void run() {

							ImagesVisitor.ImagesManger.readThemAll();
							new GamePanel();
							LoadingFrame.getInstance().close();
							cancel();

						} // https://stackoverflow.com/questions/1409116/how-to-stop-the-task-scheduled-in-java-util-timer-class
					};

					delayTimer.schedule(refresh,1);

				} else {
					JOptionPane.showConfirmDialog(pointerToMySelf, "Select Level First", "Level Selection",
							JOptionPane.DEFAULT_OPTION);
				}

			}

		});
		add(playbtn);

	}

	enum ToggleButtons {

		EASY(new ImageIcon(getRelativePakagePath("easy.png")).getImage().getScaledInstance(472 / 3, 190 / 3,
				Image.SCALE_DEFAULT),
				new ImageIcon(getRelativePakagePath("easydisabled.png")).getImage().getScaledInstance(472 / 3, 190 / 3,
						Image.SCALE_DEFAULT)),

		MEDUIM(new ImageIcon(getRelativePakagePath("medium.png")).getImage().getScaledInstance(472 / 3, 190 / 3,
				Image.SCALE_DEFAULT),
				new ImageIcon(getRelativePakagePath("mediumdisabled.png")).getImage().getScaledInstance(472 / 3,
						190 / 3, Image.SCALE_DEFAULT)),

		HARD(new ImageIcon(getRelativePakagePath("hard.png")).getImage().getScaledInstance(472 / 3, 190 / 3,
				Image.SCALE_DEFAULT),
				new ImageIcon(getRelativePakagePath("harddisabled.png")).getImage().getScaledInstance(472 / 3, 190 / 3,
						Image.SCALE_DEFAULT)),

		PLAY(new ImageIcon(getRelativePakagePath("play.png")).getImage().getScaledInstance(182 / 3, 191 / 3,
				Image.SCALE_DEFAULT),
				new ImageIcon(getRelativePakagePath("playdisabled.png")).getImage().getScaledInstance(182 / 3, 191 / 3,
						Image.SCALE_DEFAULT));

		private Image ON;
		private Image OFF;

		ToggleButtons(Image on, Image off) {
			this.ON = on;
			this.OFF = off;
		}

	}

}
