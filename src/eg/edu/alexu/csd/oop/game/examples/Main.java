package eg.edu.alexu.csd.oop.game.examples;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import eg.edu.alexu.csd.oop.game.COPGameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

@SuppressWarnings("unused")
public class Main {
	
	private static int trackIt = 0;
	
	public static void main(String[] args) {
		System.out.println("Uncomment any of the lines in the Main to run a new game, Have Fun :)");

		/* -------------------------------------------------------------------- */
		/* using default background color */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.examples.world.Gold(400, 400));
		
		/* -------------------------------------------------------------------- */
		/* using background color at the game */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.examples.world.Ball(700, 400), Color.YELLOW);
		
		/* -------------------------------------------------------------------- */
		/* controlling the behavior of the close button
		 * alternatively, you can use JFrame.DISPOSE_ON_CLOSE, JFrame.HIDE_ON_CLOSE */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.examples.world.Bubles(600, 600), JFrame.EXIT_ON_CLOSE);

//		/* using menus in the game */
//		JMenuBar  menuBar = new JMenuBar();;
//		JMenu menu = new JMenu("File");
//		JMenuItem  exitMenuItem = new JMenuItem("Exit");
//		exitMenuItem.addActionListener(new ActionListener() {
//			@Override public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
//		menu.add(exitMenuItem);
//		menuBar.add(menu);
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.examples.world.StarWar(800, 600), menuBar, Color.BLACK);

		/* -------------------------------------------------------------------- */
		/* allow pause, resume, and restart multiple worlds in the same frame */
		JMenuBar  menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem nextMenuItem   = new JMenuItem("Next");
		JMenuItem pauseMenuItem  = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		nextMenuItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		
		menu.add(nextMenuItem);
		menu.addSeparator();
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		menuBar.add(menu);
		
		GameController gameController = COPGameEngine.start("5 in 1 Simple Games", new eg.edu.alexu.csd.oop.game.examples.world.DummyWorld(400, 600), menuBar, Color.GRAY);
		
		nextMenuItem.addActionListener(new ActionListener()  {
		@Override public void actionPerformed(ActionEvent e) {
			boolean rewind = false;
			switch (trackIt) {
			case 0:
				gameController.changeWorld(new eg.edu.alexu.csd.oop.game.examples.world.Gold(400, 600));
				break;
			case 1:
				gameController.changeWorld(new eg.edu.alexu.csd.oop.game.examples.world.Ball(400, 600));
				break;
			case 2:
				gameController.changeWorld(new eg.edu.alexu.csd.oop.game.examples.world.Bubles(400, 600));
				break;
			case 3:
				gameController.changeWorld(new eg.edu.alexu.csd.oop.game.examples.world.StarWar(400, 600));
				break;
			default:
				gameController.changeWorld(new eg.edu.alexu.csd.oop.game.examples.world.DummyWorld(400, 600));
				rewind = true;
				break;
			}
			trackIt++;
			if(rewind) trackIt = 0;	
			}
		});
		pauseMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
				gameController.pause();
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e)  {
				gameController.resume();
			}
		});
	}
	
}
