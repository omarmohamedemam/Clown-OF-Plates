package eg.edu.alexu.csd.oop.game;

import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.border.BevelBorder;
import javax.swing.JPanel;
import java.awt.Component;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.Timer;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.World;

public class COPGameEngine extends GameEngine implements Runnable {

	public static GameController start(String title, World world, JMenuBar menuBar, Color background) {
		GameController controller = new COPGameController(title, world, menuBar, 3, background);
		EventQueue.invokeLater(new COPGameEngine(controller));
		return controller;
	}

	public static GameController start(JFrame window, JPanel topLayer, Image gifBackground, World world) {
		GameController controller = new COPGameController(window, topLayer, gifBackground, world);
		EventQueue.invokeLater(new COPGameEngine(controller));
		return controller;
	}

	// https://stackoverflow.com/questions/9560600/java-no-enclosing-instance-of-type-foo-is-accessible
	private static class COPGameController implements GameEngine.GameController {

		private final Timer timer;
		private final AnimationPanel panel;

		private COPGameController(final JFrame window, final JPanel topLayer, Image gifBackground, final World world) {

			final Dimension worldDims = new Dimension(world.getWidth(), world.getHeight());
			this.panel = new COPAnimationPanel(world, gifBackground);
			// https://stackoverflow.com/questions/852631/java-swing-how-to-show-a-panel-on-top-of-another-panel
			// https://docs.oracle.com/javase/tutorial/uiswing/components/rootpane.html

			JLayeredPane lpane = new JLayeredPane();

			window.add(lpane, BorderLayout.CENTER);
			lpane.setBounds(0, 0, worldDims.width, worldDims.height);
			settingLayers(topLayer, worldDims);
			lpane.add(panel, new Integer(0), 0);
			lpane.add(topLayer, new Integer(1), 0);

			defineWindowLayout(window);

			this.timer = new Timer(world.getSpeed(), (ActionListener) new SpinTimerListener(this.panel, new JLabel()));
		}

		private void settingLayers(final JPanel topLayer, Dimension worldDims) {
			panel.setBounds(0, 0, worldDims.width, worldDims.height);
			panel.setOpaque(false);

			topLayer.setBounds(0, 0, worldDims.width, worldDims.height);
			// https://stackoverflow.com/questions/2545214/how-to-set-a-transparent-background-of-jpanel/12643715
			topLayer.setOpaque(false);
		}

		private void defineWindowLayout(final JFrame window) {
			window.setUndecorated(true);
			window.setResizable(false);
			window.setSize(panel.getPreferredSize());
			ImageIcon image = new ImageIcon("src/eg/edu/alexu/csd/oop/game/view/pics/icon.png");
			window.setIconImage(image.getImage());
			locateCenter(window);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
		}

		private void locateCenter(final JFrame window) {
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension dim = tk.getScreenSize();

			int xPos = (dim.width / 2) - (window.getWidth() / 2);
			int yPos = (dim.height / 2) - (window.getHeight() / 2);

			window.setLocation(xPos, yPos);
		}

		private COPGameController(final String title, final World world, final JMenuBar menuBar,
				final int onFrameCloseAction, final Color background) {
			this.panel = new COPAnimationPanel(world, null);
			final JFrame frame = new JFrame(title);
			frame.getContentPane().add((Component) this.panel);
			if (menuBar != null) {
				frame.setJMenuBar(menuBar);
			}
			frame.setDefaultCloseOperation(onFrameCloseAction);
			frame.pack();
			this.panel.setBackground((background == null) ? Color.WHITE : background);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			final JPanel statusPanel = new JPanel();
			statusPanel.setBorder(new BevelBorder(1));
			frame.add(statusPanel, "South");
			statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
			statusPanel.setLayout(new BoxLayout(statusPanel, 0));
			final JLabel statusLabel = new JLabel("");
			statusLabel.setHorizontalAlignment(2);
			statusPanel.add(statusLabel);
			frame.setVisible(true);
			this.timer = new Timer(world.getSpeed(), (ActionListener) new SpinTimerListener(this.panel, statusLabel));
		}

		public void pause() {
			this.timer.stop();
		}

		public void resume() {
			this.timer.restart();
		}

		public void changeWorld(final World world) {
			this.panel.init(world);
		}

	}

	private final GameEngine.GameController controller;

	private COPGameEngine(GameController controller) {
		this.controller = controller;
	}

	public void run() {
		this.controller.resume();
	}

}