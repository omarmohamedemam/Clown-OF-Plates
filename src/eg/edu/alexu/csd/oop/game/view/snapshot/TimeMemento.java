package eg.edu.alexu.csd.oop.game.view.snapshot;

import eg.edu.alexu.csd.oop.game.model.world.AbstractWorldModel;

public class TimeMemento {

	private AbstractWorldModel world;

	// Save a new world to the memento Object
	public TimeMemento(AbstractWorldModel worldSave) {
		world = worldSave;
	}

	// Return the value stored in world
	public AbstractWorldModel getSavedWorld() {
		return world;
	}

}

//http://www.newthinktank.com/2012/10/memento-design-pattern-tutorial/