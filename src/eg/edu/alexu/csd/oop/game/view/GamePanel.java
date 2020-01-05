package eg.edu.alexu.csd.oop.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import eg.edu.alexu.csd.oop.game.COPEngineFactory;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.game.model.world.AbstractWorldModel;

@SuppressWarnings({ "serial" })
public class GamePanel extends JPanel {

	static public int soundclicked = 1;

	private Dimension scoreboardsize;
	private static JFrame containerWindow;

	static GameController gameController;

	public GamePanel() {

		constractPanel();
		Image gif = sendGIF();

		containerWindow = new JFrame();

		JFrame[] subFrames = { PausedFrame.getInstance() };

		containerWindow.addWindowListener(new WindowListener() {

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

			public void windowClosed(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
				for (JFrame window : subFrames)
					if (window.isVisible())
						window.dispose();
				GamePanel.gameController.resume();
			}

		});

		scoreboardsize = new Dimension();

		AbstractWorldModel world = new eg.edu.alexu.csd.oop.game.examples.world.DummyWorld(SettingsFrame.GameWindowSize.width,
				SettingsFrame.GameWindowSize.height);
		
		if (SettingsFrame.GameWindowSize.width == SettingsFrame.getScreenSize().width) {
			gameController = new COPEngineFactory().getEngine(containerWindow, this, gif,
					world);
		} else {
			containerWindow.dispose();
			gameController = new COPEngineFactory().getEngine(world);
		}
		
	}

	private Image sendGIF() {
		return Toolkit.getDefaultToolkit().createImage(StartFrame.getRelativePakagePath("game.gif")).getScaledInstance(
				SettingsFrame.GameWindowSize.width, SettingsFrame.GameWindowSize.height, Image.SCALE_DEFAULT);
	}

	private void constractPanel() {
		COPFrame.logr.info("Game is opened");
		setLayout(null);
		setSize(SettingsFrame.GameWindowSize);
		putscore();
		puttime();
		putlevel();
		puthomebutton();
		putpausebutton();
		putsoundbutton();

	}

	private void putscore() {
		// ************************** Score Text*****************************//
		String CurrentScore = new String("000");
		JLabel Score = new JLabel(CurrentScore);
		Score.setForeground(Color.WHITE);
		Score.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 30));
		Dimension scoretext = Score.getPreferredSize();
		Score.setBounds(138, 7, scoretext.width, scoretext.height);

		Timer timerforscore = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				COPFrame.logr.info("Score Changed");
				Score.setText("5");// get score from its function
				Score.repaint();
			}
		});
		timerforscore.start();
		add(Score);
		// ************************** Score Board *****************************//
		JLabel ScoreBoard = new JLabel("");
		ScoreBoard.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("score.png")).getImage()
				.getScaledInstance(395 / 2, 56 / 2, Image.SCALE_DEFAULT)));
		scoreboardsize = ScoreBoard.getPreferredSize();
		ScoreBoard.setBounds(10, 10, scoreboardsize.width, scoreboardsize.height);
		add(ScoreBoard);
	}

	private void puttime() {
		/************************** TimeText *****************************/
		String Currenttime = new String("000");
		JLabel time = new JLabel(Currenttime);
		time.setForeground(Color.WHITE);
		time.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 30));
		Dimension timetext = time.getPreferredSize();
		time.setBounds(scoreboardsize.width + 138, 7, timetext.width, timetext.height);

		Timer timerfortimer = new Timer(1000, new ActionListener() {
			int i = 0;

			public void actionPerformed(ActionEvent a) {
				i++;
				time.setText(Integer.toString(i));
				time.repaint();
			}
		});
		timerfortimer.start();
		add(time);
		// ************************** Time Board *****************************//
		JLabel timeBoard = new JLabel("");
		timeBoard.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("timer.png")).getImage()
				.getScaledInstance(395 / 2, 56 / 2, Image.SCALE_DEFAULT)));
		Dimension timersize = timeBoard.getPreferredSize();
		timeBoard.setBounds(scoreboardsize.width + 20, 10, timersize.width, timersize.height);
		add(timeBoard);
	}

	private void putlevel() {
		// ************************** Level Text *****************************//
		String CurrentMode = new String(LevelsFrame.mode);
		JLabel mode = new JLabel(CurrentMode);
		mode.setForeground(Color.WHITE);
		mode.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 25));
		Dimension modetext = mode.getPreferredSize();
		mode.setBounds(103, scoreboardsize.height + 30, modetext.width, modetext.height);
		add(mode);
		// ************************** Score Board ****************************//
		JLabel modeBoard = new JLabel("");
		modeBoard.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("level.png")).getImage()
				.getScaledInstance(395 / 2, 56 / 2, Image.SCALE_DEFAULT)));
		Dimension modeBoardsize = modeBoard.getPreferredSize();
		modeBoard.setBounds(10, scoreboardsize.height + 30, modeBoardsize.width, modeBoardsize.height);
		add(modeBoard);
	}

	private void puthomebutton() {

		JLabel homebutton = new JLabel("");
		homebutton.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("home.png")).getImage()
				.getScaledInstance(182 / 3, 191 / 3, Image.SCALE_DEFAULT)));
		Dimension homebuttonsize = homebutton.getPreferredSize();
		homebutton.setBounds(SettingsFrame.GameWindowSize.width - homebuttonsize.width - 10, 10, homebuttonsize.width,
				homebuttonsize.height);
		homebutton.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(containerWindow, "Confirm if you Want to Back For Home", "Exit!",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					COPFrame.logr.warning("Backing home from the game");
					closeGame();
					StartFrame.getInstance().showFrame();
				}
			}
		});

		add(homebutton);
	}

	private void putpausebutton() {

		JLabel pausebutton = new JLabel("");
		pausebutton.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("pause.png")).getImage()
				.getScaledInstance(181 / 3, 263 / 3 - 10, Image.SCALE_DEFAULT)));
		Dimension pausebuttonsize = pausebutton.getPreferredSize();
		pausebutton.setBounds(SettingsFrame.GameWindowSize.width - pausebuttonsize.width * 2 - 20, 3, pausebuttonsize.width,
				pausebuttonsize.height);
		pausebutton.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				COPFrame.logr.info("Game Paused");
				gameController.pause();
				PausedFrame.getInstance().showFrame();
			}
		});
		add(pausebutton);
	}

	private void putsoundbutton() {

		JLabel soundbutton = new JLabel("");
		if (soundclicked % 2 == 0) {

			soundbutton.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("unmute.png")).getImage()
					.getScaledInstance(283 / 3, 232 / 3, Image.SCALE_DEFAULT)));

		} else {

			soundbutton.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("mute.png")).getImage()
					.getScaledInstance(283 / 3, 232 / 3, Image.SCALE_DEFAULT)));
		}
		soundbutton.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("mute.png")).getImage()
				.getScaledInstance(283 / 3, 232 / 3, Image.SCALE_DEFAULT)));
		Dimension soundbuttonsize = soundbutton.getPreferredSize();
		soundbutton.setBounds(SettingsFrame.GameWindowSize.width - soundbuttonsize.width * 2 - 40, 0, soundbuttonsize.width,
				soundbuttonsize.height);
		soundbutton.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				soundclicked++;
				if (soundclicked % 2 == 0) {
					COPFrame.logr.info("sound muted inside the game");
					COPFrame.soundClip.stop();
					soundbutton.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("unmute.png"))
							.getImage().getScaledInstance(283 / 3, 232 / 3, Image.SCALE_DEFAULT)));

				} else {
					COPFrame.logr.info("sound unmuted inside the game");
					COPFrame.soundClip.loop(Clip.LOOP_CONTINUOUSLY);
					soundbutton.setIcon(new ImageIcon(new ImageIcon(StartFrame.getRelativePakagePath("mute.png"))
							.getImage().getScaledInstance(283 / 3, 232 / 3, Image.SCALE_DEFAULT)));
				}
			}
		});
		add(soundbutton);
	}

	public static void closeGame() {
		containerWindow.setVisible(false);
		containerWindow.dispose();
		gameController.pause();
	}

}
