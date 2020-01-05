package eg.edu.alexu.csd.oop.game.controller.worldConstructor;

import eg.edu.alexu.csd.oop.game.controller.factory.AbstractObjectFactory;
import eg.edu.alexu.csd.oop.game.model.world.AbstractWorldModel;
/**
 * @dPatten Factory
 */
public class COPWorldConstructor extends AbstractWorldConstructor {
	
	AbstractObjectFactory platesFactory;
	@SuppressWarnings("unused")
	private Level level;
	
	// do nothing in the constructor, unless you want more info to be passed in
	public COPWorldConstructor(AbstractWorldModel world) {
		super(world);
	}

	@Override
	public void buildWorldObjects() {
		// You will be mixing up all the Factory methods,
		// any way you like, to achieve the desired result.
	}
}
