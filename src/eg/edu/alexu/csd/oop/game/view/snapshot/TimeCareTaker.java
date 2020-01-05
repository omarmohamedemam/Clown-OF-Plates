package eg.edu.alexu.csd.oop.game.view.snapshot;

import java.util.ArrayList;

public class TimeCareTaker {

	// Where all mementos are saved
	ArrayList<TimeMemento> savedWorlds = new ArrayList<TimeMemento>();

	// Adds memento to the ArrayList
	public void addMemento(TimeMemento m) {
		savedWorlds.add(m);
	}

	// Gets the memento requested from the ArrayList
	public TimeMemento getMemento(int index) {
		return savedWorlds.get(index);
	}

}

//http://www.newthinktank.com/2012/10/memento-design-pattern-tutorial/