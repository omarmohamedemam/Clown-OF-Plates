package eg.edu.alexu.csd.oop.game.view.snapshot;

import eg.edu.alexu.csd.oop.game.model.world.AbstractWorldModel;

public class TimeOriginator {

	private AbstractWorldModel world;

	// Sets the value for the world
	public void set(AbstractWorldModel newWorld) {
		this.world = newWorld;
	}

	// Creates a new Memento with a new world
	public TimeMemento storeInMemento() {
		return new TimeMemento(world);
	}

	// Gets the world currently stored in memento
	public AbstractWorldModel restoreFromMemento(TimeMemento memento) {
		world = memento.getSavedWorld();
		return world;
	}

}

//http://www.newthinktank.com/2012/10/memento-design-pattern-tutorial/