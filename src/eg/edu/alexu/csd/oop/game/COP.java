package eg.edu.alexu.csd.oop.game;

import java.util.logging.Logger;

import eg.edu.alexu.csd.oop.game.view.StartFrame;

public class COP {

	public static double RATIO; // <- just to make life easier
	public static boolean customGameOver = false; // <- the world must know which engine is used!
	public final static Logger logr = LoggingManger.setupLogger(); // <- watch this first https://youtu.be/W0_Man88Z3Q

	public static void main(String[] args) {

		StartFrame.getInstance().showFrame();
		logr.info("starting");

	}

}
