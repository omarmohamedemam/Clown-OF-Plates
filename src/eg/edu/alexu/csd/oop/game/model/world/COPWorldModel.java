package eg.edu.alexu.csd.oop.game.model.world;

import eg.edu.alexu.csd.oop.game.controller.gameLoop.AbstractGameLoop;
import eg.edu.alexu.csd.oop.game.controller.gameLoop.COPGameLoop;
import eg.edu.alexu.csd.oop.game.controller.worldConstructor.AbstractWorldConstructor;
import eg.edu.alexu.csd.oop.game.controller.worldConstructor.COPWorldConstructor;

/**
 * @dPattern Strategy
 */
public class COPWorldModel extends AbstractWorldModel {

	AbstractGameLoop gameLoop;
	AbstractWorldConstructor worldConstructor;

	/**
	 * World construction
	 * 
	 * @author Abdelrahman, Zidan
	 * @dPattern Factory
	 */
	public COPWorldModel(int screenWidth, int screenHeight) {
		super(screenWidth, screenHeight);
		gameLoop = new COPGameLoop(this);
		worldConstructor = new COPWorldConstructor(this);
		worldConstructor.buildWorldObjects();
	}

	/**
	 * Game Loop logic
	 * 
	 * @author Abdelrahman, Zidan
	 * @dPattern Facade
	 */
	public boolean refresh() {
		return gameLoop.updateAndCheck();
	}

}
