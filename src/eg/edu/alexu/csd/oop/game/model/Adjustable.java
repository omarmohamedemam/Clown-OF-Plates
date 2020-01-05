package eg.edu.alexu.csd.oop.game.model;

import eg.edu.alexu.csd.oop.game.model.object.ImagesVisitor;

/**
 * @dPattern Visitor
 */
public interface Adjustable {

	// Allows the ImagesVisitor to pass the object so
	// the right operations occur on the right
	// type of object.

	// adjuctQuality() is passed the same ImagesVisitor object
	// but then the method adjust() is called using
	// the ImagesVisitor object. The right version of adjust()
	// is called because of method overloading

	public void adjustQuality(ImagesVisitor visitor);

}

// https://www.newthinktank.com/2012/11/visitor-design-pattern-tutorial/