package eg.edu.alexu.csd.oop.game.controller.gameLoop;

import java.util.Iterator;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.model.world.AbstractWorldModel;

public abstract class AbstractGameLoop {
	
	protected final int  MAX_TIME ;
	protected final long startTime;
	protected final int  width    ;
	protected final int  height   ;
	
	/**dPattern Iterator*/
	protected final Iterator<GameObject> constant;
	/**dPattern Iterator*/
	protected final Iterator<GameObject> moving  ;
	/**dPattern Iterator*/
	protected final Iterator<GameObject> control ;
	
	public AbstractGameLoop(AbstractWorldModel world) {
		this.startTime = world.startTime;
		this.MAX_TIME = world.MAX_TIME ;
		this.width = world.getWidth();
		this.height = world.getHeight();
		this.constant = world.constant.iterator();
		this.moving = world.moving.iterator();
		this.control = world.control.iterator();
	}
	/**
	 * Game Loop logic
	 * refresh the world state and update locations
	 * @author Abdelrahman, Zidan
	 * @dPattern Facade
	 * @return false means game over
	 */
	public abstract boolean updateAndCheck();

}
