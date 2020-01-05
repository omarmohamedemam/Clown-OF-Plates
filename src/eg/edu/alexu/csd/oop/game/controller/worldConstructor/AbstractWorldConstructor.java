package eg.edu.alexu.csd.oop.game.controller.worldConstructor;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.model.world.AbstractWorldModel;

public abstract class AbstractWorldConstructor {

	protected final int width ;
	protected final int height;
	
	protected final List<GameObject> constant;
	protected final List<GameObject> moving  ;
	protected final List<GameObject> control ;
	
	public AbstractWorldConstructor(AbstractWorldModel world) {
		this.width    = world.getWidth ();
		this.height   = world.getHeight();
		this.constant = world.constant;
		this.moving   = world.moving  ;
		this.control  = world.control ;
	}

	/**
	 * World construction
	 * @author Abdelrahman, Zidan
	 * @dPattern Factory
	 */
	public abstract void buildWorldObjects();
	
}
