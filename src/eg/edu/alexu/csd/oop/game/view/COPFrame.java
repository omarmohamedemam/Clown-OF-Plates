package eg.edu.alexu.csd.oop.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import eg.edu.alexu.csd.oop.game.LoggingManger;

@SuppressWarnings("serial")
public abstract class COPFrame extends JFrame {

	protected COPFrame pointerToMySelf;
	public final static Logger logr = LoggingManger.setupLogger();

	protected static final Color NO_BACK_GROUND = new Color(0, 0, 0, 0);

	protected static Clip soundClip = initiateSound();

	public abstract void showFrame();

	protected abstract void initiateFrame();

	protected abstract void layoutButtons();

	protected static Dimension getScreenSize() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		return tk.getScreenSize();
	}

	protected void removeBackgroundColor() {
		if (!isVisible()) {
			setUndecorated(true);
			setBackground(NO_BACK_GROUND);
		}
	}

	protected static String getRelativePakagePath(String fileName) {
		return "src/eg/edu/alexu/csd/oop/game/view/pics/" + fileName;
	}

	private static Clip initiateSound() {
		if (soundClip == null) {
			Clip soundClip = null;
			File soundtrack = new File(getRelativePakagePath("space.wav"));
			try {
				URL url = new URL(soundtrack.toURI().toURL(), "space.wav");
				soundClip = AudioSystem.getClip();
				AudioInputStream ais = AudioSystem.getAudioInputStream(url);
				soundClip.open(ais);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			soundClip.loop(Clip.LOOP_CONTINUOUSLY);
			return soundClip;
		}
		return soundClip;
	}

	abstract class DisposeOthersWhenActive implements WindowListener {

		public void windowOpened(WindowEvent e) {
		}

		public void windowClosing(WindowEvent e) {
		}

		public void windowClosed(WindowEvent e) {
		}

		public void windowIconified(WindowEvent e) {
		}

		public void windowDeiconified(WindowEvent e) {
		}

		public void windowDeactivated(WindowEvent e) {
		}

		public abstract void windowActivated(WindowEvent e);

	}

}
