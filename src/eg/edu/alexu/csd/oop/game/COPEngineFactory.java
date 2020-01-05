package eg.edu.alexu.csd.oop.game;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.game.model.world.AbstractWorldModel;

@SuppressWarnings("serial")
public class COPEngineFactory extends JMenuBar {

	AbstractWorldModel world;
	GameController gameController;

	public GameEngine.GameController getEngine(AbstractWorldModel world) {
		this.world = world;
		constructMenuBar();
		return gameController = GameEngine.start("Circus of Plates", world, this, Color.BLACK);
	}
	public GameEngine.GameController getEngine(JFrame window, JPanel topLayer, Image gifBackground, AbstractWorldModel world) {
		this.world = world;
		COP.customGameOver = true;
		return gameController = COPGameEngine.start(window, topLayer, gifBackground, world);
	}

	private void constructMenuBar() {

		JMenu menu = new JMenu("File");

		JMenuItem pauseMenuItem = createMenuItem("Pause"); // pause bugs the timer! (we should over come that)
		JMenuItem resumeMenuItem = createMenuItem("Resume");

		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		this.add(menu);

		pauseMenuItem.addActionListener(e -> pauseGame());
		resumeMenuItem.addActionListener(e -> resumeGame());

	}

	private void pauseGame() {
		gameController.pause();
	}

	private void resumeGame() {
		gameController.resume();
	}

	private JMenuItem createMenuItem(String itemName) {
		JMenuItem menuItem = new JMenuItem(itemName);
		menuItem.setAccelerator(
				KeyStroke.getKeyStroke(itemName.charAt(0), Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		return menuItem;
	}

}
