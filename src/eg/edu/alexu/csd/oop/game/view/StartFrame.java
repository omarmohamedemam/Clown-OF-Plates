package eg.edu.alexu.csd.oop.game.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import eg.edu.alexu.csd.oop.game.LoggingManger;

@SuppressWarnings({ "serial" })
public class StartFrame extends COPFrame {

	private static StartFrame single_instance = null;
	
	private StartFrame() {
		pointerToMySelf = this;
		initiateFrame();
		layoutButtons();
		
		addWindowListener(new COPFrame.DisposeOthersWhenActive() {
			public void windowActivated(WindowEvent e) {
				for(JFrame window : subFrames)
					if(window.isVisible())
						window.dispose();
			}
		});
	}

	public static StartFrame getInstance() {
		if (single_instance == null)
			single_instance = new StartFrame();

		return single_instance;
	}
	
	private JFrame[] subFrames = { SettingsFrame.getInstance(), LevelsFrame.getInstance() };

	public void showFrame() {
		logr.info("Opening Start frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	protected void initiateFrame() {
		setTitle("Aliens VS Plates");
		ImageIcon image = new ImageIcon(getRelativePakagePath("icon.png"));
		pointerToMySelf.setIconImage(image.getImage());
		setUpBackground();
	}

	private void setUpBackground() {
		Icon background = new ImageIcon(getRelativePakagePath("o.gif"));
		setSize((int) (getScreenSize().width / 1.5), (int) (getScreenSize().height / 1.5));
		setLocationRelativeTo(null);
		setContentPane(new JLabel(background));
	}

	protected void layoutButtons() {
		addStartButton();
		addSettingButton();
		addHelpButton();
		addExitButton();
		addtext();
	}

	private void addStartButton() {
		JLabel startbtn = new JLabel("");
		startbtn.setIcon(new ImageIcon(new ImageIcon(getRelativePakagePath("playbtn.png")).getImage()
				.getScaledInstance(472 / 2, 190 / 2, Image.SCALE_DEFAULT)));
		startbtn.setBounds(20, (getHeight()) - 150, 472 / 2, 190 / 2);
		startbtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logr.info("Start Button Clicked");
				LevelsFrame.getInstance().showFrame();
			}
		});
		getContentPane().add(startbtn);
	}

	private void addSettingButton() {
		JLabel settingbtn = new JLabel("");
		settingbtn.setIcon(new ImageIcon(new ImageIcon(getRelativePakagePath("setting.png")).getImage()
				.getScaledInstance(182 / 2, 190 / 2, Image.SCALE_DEFAULT)));
		settingbtn.setBounds((getWidth()) - 110, (getHeight() / 2) + 120, 182 / 2, 190 / 2);
		settingbtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logr.info("Setting Button Clicked");
				SettingsFrame.getInstance().showFrame();
			}
		});
		getContentPane().add(settingbtn);
	}

	private void addHelpButton() {
		JLabel helpbtn = new JLabel("");
		helpbtn.setIcon(new ImageIcon(new ImageIcon(getRelativePakagePath("HELP.png")).getImage()
				.getScaledInstance(182 / 2, 190 / 2, Image.SCALE_DEFAULT)));
		helpbtn.setBounds((getWidth()) - 110, (getHeight() / 2) + 220, 182 / 2, 190 / 2);
		helpbtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logr.info("Help Button Clicked");
				String inst = new String();
				inst = ("Welcome To Game Instructions:\n1-How To Play?\nUse your keyboard to move the clown and collect plates\n"
						+ "Every three consecutive plates having the same color equal one point\nCollect as mush as you can before time is up.\n"
						+ "2-Setting??\nYou can adjust window size, the settings menu through has alot of options\n"
						+ ",you can choose graphics quality also you can mute the sound.\n"
						+ "Authors:\n - Abd Elrahmen Adel \n - Ahmed Waleed \n - Mouhamed Zidan \n - Omar Emam");
				ImageIcon icon = new ImageIcon("src/eg/edu/alexu/csd/oop/game/view/helpicon.png");
				Image img = icon.getImage();
				Image newimg = img.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
				ImageIcon newIcon = new ImageIcon(newimg);
				JOptionPane.showConfirmDialog(pointerToMySelf, inst, "  Help", JOptionPane.CLOSED_OPTION,
						JOptionPane.PLAIN_MESSAGE, newIcon);
			}
		});
		getContentPane().add(helpbtn);
	}

	private void addExitButton() {
		JLabel exitbtn = new JLabel("");
		exitbtn.setIcon(new ImageIcon(new ImageIcon(getRelativePakagePath("EXIT.png")).getImage()
				.getScaledInstance(182 / 2, 190 / 2, Image.SCALE_DEFAULT)));
		exitbtn.setBounds((getWidth()) - 110, 20, 182 / 2, 190 / 2);
		exitbtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logr.info("Exit Button Clicked");
				if (JOptionPane.showConfirmDialog(pointerToMySelf, "Confirm if you Want to Exit", "Exit!",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					logr.warning("Exit Game");
					System.exit(0);
				}
					
			}
		});
		getContentPane().add(exitbtn);
	}

	private void addtext() {
		JLabel aliens = new JLabel("");
		aliens.setIcon(new ImageIcon(new ImageIcon(getRelativePakagePath("cover.png")).getImage()
				.getScaledInstance(800 / 2, 310 / 2, Image.SCALE_DEFAULT)));
		Dimension alienssize = aliens.getPreferredSize();
		aliens.setBounds(20, 20, alienssize.width, alienssize.height);

		getContentPane().add(aliens);

	}

}
