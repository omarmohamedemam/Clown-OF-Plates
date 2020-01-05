package eg.edu.alexu.csd.oop.game.controller.factory;

import eg.edu.alexu.csd.oop.game.model.object.AbstractImageObject;

/**
 * Dynamic Linkage:
 * After implementing all the plates classes it will be 
 * extracted to an external jar to be loaded dynamically
 * Fly weight:
 * It will be added at the very end, because it does not
 * fit at all with Snapshot Pattern.
 * @dPattern Dynamic Linkage, Fly weight
 */
public interface AbstractObjectFactory {

	AbstractImageObject get();

	void collect(AbstractImageObject drawnObject);
	void clean();
	
}
