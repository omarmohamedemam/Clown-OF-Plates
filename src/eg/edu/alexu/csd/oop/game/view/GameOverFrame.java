package eg.edu.alexu.csd.oop.game.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @dPattern Singleton
 */
@SuppressWarnings("serial")
public class GameOverFrame extends COPFrame {

	private static GameOverFrame single_instance = null;
	Clip endClip = null;
	private GameOverFrame() {
		pointerToMySelf = this;
	}

	public static GameOverFrame getInstance() {
		if (single_instance == null)
			single_instance = new GameOverFrame();

		return single_instance;
	}

	JFrame gameover = new JFrame();

	public void showFrame() {

		COPFrame.soundClip.stop();
		
		File soundtrack = new File(getRelativePakagePath("gameovertrack.wav"));
		try {
			URL url = new URL(soundtrack.toURI().toURL(), "gameovertrack.wav");
			endClip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
			endClip.open(ais);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		endClip.loop(Clip.LOOP_CONTINUOUSLY);

		initiateFrame();

		layoutButtons();

		
	}

	protected void initiateFrame() {
		COPFrame.logr.warning("Game is over");
		gameover.setTitle("GameOver");
		ImageIcon image = new ImageIcon(COPFrame.getRelativePakagePath("icon.png"));
		gameover.setIconImage(image.getImage());
		setUpBackground();
		removeBackgroundColor();
		gameover.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gameover.setResizable(false);
		gameover.setVisible(true);
	}

	private void setUpBackground() {
		Icon background = new ImageIcon(COPFrame.getRelativePakagePath("gameover.gif"));
		gameover.setSize((int) (StartFrame.getScreenSize().width / 1.5),
				(int) (StartFrame.getScreenSize().height / 1.5));
		gameover.setLocationRelativeTo(null);
		gameover.setContentPane(new JLabel(background));
	}

	protected void layoutButtons() {
		JLabel backbtn = new JLabel("");
		backbtn.setIcon(new ImageIcon(new ImageIcon(COPFrame.getRelativePakagePath("Settingback.png")).getImage()
				.getScaledInstance(182 / 3, 191 / 3, Image.SCALE_DEFAULT)));
		Dimension size = backbtn.getPreferredSize();
		backbtn.setBounds(520, 470, size.width, size.height);
		backbtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				COPFrame.logr.warning("Backing home from game over ");
				endClip.stop();
				gameover.dispose();
				StartFrame.getInstance().showFrame();
				COPFrame.soundClip.loop(Clip.LOOP_CONTINUOUSLY);
				COPFrame.soundClip.setFramePosition(0);
			}

		});
		gameover.add(backbtn);

		JLabel exitbtn = new JLabel("");
		exitbtn.setIcon(new ImageIcon(new ImageIcon(COPFrame.getRelativePakagePath("exitover.png")).getImage()
				.getScaledInstance(182 / 3, 191 / 3, Image.SCALE_DEFAULT)));
		Dimension size2 = exitbtn.getPreferredSize();
		exitbtn.setBounds(650, 470, size2.width, size2.height);
		exitbtn.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				endClip.stop();
				if (JOptionPane.showConfirmDialog(gameover, "Confirm if you Want to Exit", "Exit!",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					COPFrame.logr.warning("Exiting from game over ");
					System.exit(0);
				}
					
			}

		});
		gameover.add(exitbtn);
	}

	public void close() {
		gameover.dispose();
	}

}
