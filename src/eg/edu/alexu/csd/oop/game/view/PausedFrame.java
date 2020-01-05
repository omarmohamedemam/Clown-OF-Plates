package eg.edu.alexu.csd.oop.game.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PausedFrame extends COPFrame {

	private static PausedFrame single_instance = null;

	private PausedFrame() {
		pointerToMySelf = this;
		initiateFrame();
		layoutButtons();
	}

	protected static PausedFrame getInstance() {
		if (single_instance == null)
			single_instance = new PausedFrame();

		return single_instance;
	}

	public void showFrame() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	protected void initiateFrame() {
		setTitle("Paused");
		ImageIcon image = new ImageIcon(getRelativePakagePath("icon.png"));
		pointerToMySelf.setIconImage(image.getImage());
		removeBackgroundColor();
		setUpBackground();
	}

	private void setUpBackground() {
		ImageIcon background = new ImageIcon(getRelativePakagePath("paused.png"));
		setSize(927, 742);
		setLocationRelativeTo(null);
		Image image = background.getImage();
		Image newimg = image.getScaledInstance(getWidth() / 2, getHeight() / 2, java.awt.Image.SCALE_SMOOTH);
		background = new ImageIcon(newimg);
		setContentPane(new JLabel(background));
	}

	protected void layoutButtons() {
		addresumebutton();
	}

	private void addresumebutton() {
		JLabel resume = new JLabel("");
		resume.setIcon(new ImageIcon(new ImageIcon(getRelativePakagePath("resume.png")).getImage()
				.getScaledInstance(472 / 3, 190 / 3, Image.SCALE_DEFAULT)));
		Dimension size = resume.getPreferredSize();
		resume.setBounds(380, 370, size.width, size.height);
		resume.addMouseListener((MouseListener) new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GamePanel.gameController.resume();
				pointerToMySelf.dispose();
			}

		});
		add(resume);

	}

}
