package eg.edu.alexu.csd.oop.game.model.world;

import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

/**
 * @note contains all common functionality from example objects.
 */
public abstract class AbstractWorldModel implements World {

	public final int MAX_TIME = 1 * 60 * 1000;
	public final long startTime = System.currentTimeMillis();

	protected int score = 0;

	private final int width;
	private final int height;

	public final List<GameObject> constant = new LinkedList<GameObject>();
	public final List<GameObject> moving = new LinkedList<GameObject>();
	public final List<GameObject> control = new LinkedList<GameObject>();

	public AbstractWorldModel(int screenWidth, int screenHeight) {
		width = screenWidth;
		height = screenHeight;
	}

	public List<GameObject> getConstantObjects() {
		return constant;
	}

	public List<GameObject> getMovableObjects() {
		return moving;
	}

	public List<GameObject> getControlableObjects() {
		return control;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSpeed() {
		return 10;
	}
	
	public int getControlSpeed() {
		return 15;
	}

	public int getScore() {
		return score;
	}
	
	public String getStatus() {
		return "Score=" + getScore() + "   |   Time="
				+ Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);
	}

}
