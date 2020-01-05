package eg.edu.alexu.csd.oop.game.controller.factory;

import eg.edu.alexu.csd.oop.game.controller.worldConstructor.Level;
import eg.edu.alexu.csd.oop.game.model.object.AbstractImageObject;

import java.util.Random;

public class PlatesFactory implements AbstractObjectFactory {
	
	@SuppressWarnings("unused")
	private Level level;

    public PlatesFactory(Level level){
        this.level = level;
    }

    public AbstractImageObject get() {
        return null;
    }

    public void collect(AbstractImageObject drawnObject) {

    }

    public void clean() {

    }

    /**
     * KILLLLLLLLLLLLLLLLLLLLLLLLLLL MEEEEEEEEEEE
     * AJODEYFIAEDiA
     * AIDgaeidhahdei
     *
    * */
    public static <T> T random(Class<T> enumClass){
        Random random = new Random();
        int index = random.nextInt(enumClass.getEnumConstants().length);
        return enumClass.getEnumConstants()[index];
    }
}
