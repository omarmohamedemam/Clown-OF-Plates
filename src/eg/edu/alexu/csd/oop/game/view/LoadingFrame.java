package eg.edu.alexu.csd.oop.game.view;

import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LoadingFrame extends COPFrame{

	private static LoadingFrame single_instance = null;

	private LoadingFrame() {
		initiateFrame();
		layoutButtons();
	}

	protected static LoadingFrame getInstance() {
		if (single_instance == null)
			single_instance = new LoadingFrame();

		return single_instance;
	}

	public void showFrame() {
		logr.info("Loading");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
	}

	protected void initiateFrame() {
		setTitle("Loading ...");
		ImageIcon image = new ImageIcon(COPFrame.getRelativePakagePath("icon.png"));
		setIconImage(image.getImage());
		setUpBackground();
	}

	private void setUpBackground() {
		
		removeBackgroundColor();
	}
	
	protected void layoutButtons() {
		Icon background = new ImageIcon(
				Toolkit.getDefaultToolkit().createImage("src/eg/edu/alexu/csd/oop/game/view/pics/load.gif"));
		setSize((int) (StartFrame.getScreenSize().width / 1.5), (int) (StartFrame.getScreenSize().height / 1.5));
		setContentPane(new JLabel(background));
		setLocationRelativeTo(null);
	}

	public void close() {
		dispose();
	}

}
