package eg.edu.alexu.csd.oop.game.controller.gameLoop;

import eg.edu.alexu.csd.oop.game.model.world.AbstractWorldModel;
import eg.edu.alexu.csd.oop.game.view.GameOverFrame;
import eg.edu.alexu.csd.oop.game.view.GamePanel;

import static eg.edu.alexu.csd.oop.game.COP.customGameOver;

/**
 * @dPatten Facade
 */
public class COPGameLoop extends AbstractGameLoop {

	// do nothing in the constructor, unless you want more info to be passed in
	public COPGameLoop(AbstractWorldModel world) {
		super(world);
	}

	@Override
	public boolean updateAndCheck() {

		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over

		if (timeout)
			if (customGameOver) {
				GameOverFrame.getInstance().showFrame();
				GamePanel.closeGame();
				return true;
			}

		return !timeout;
	}

}
