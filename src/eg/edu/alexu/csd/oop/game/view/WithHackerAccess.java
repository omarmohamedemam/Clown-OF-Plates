package eg.edu.alexu.csd.oop.game.view;

import java.lang.reflect.Field;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.model.world.AbstractWorldModel;

public interface WithHackerAccess {

	static final FieldsManipulator hackingManger = new FieldsManipulator();

	class FieldsManipulator {

		private FieldsManipulator() {
		}

		// http://java-performance.info/updating-final-and-static-final-fields/

		public void illegalAccess(AbstractWorldModel toCopy, String fieldName, int intValue)
				throws NoSuchFieldException, IllegalAccessException {
			final Field hackableField = AbstractWorldModel.class.getDeclaredField(fieldName);
			hackableField.setAccessible(true);
			hackableField.setInt(toCopy, intValue);
		}

		public void illegalAccess(AbstractWorldModel toCopy, String fieldName, long longValue)
				throws NoSuchFieldException, IllegalAccessException {
			final Field hackableField = AbstractWorldModel.class.getDeclaredField(fieldName);
			hackableField.setAccessible(true);
			hackableField.setLong(toCopy, longValue);
		}

		public void illegalAccess(AbstractWorldModel toCopy, String fieldName, List<GameObject> listValue)
				throws NoSuchFieldException, IllegalAccessException {
			final Field hackableField = AbstractWorldModel.class.getDeclaredField(fieldName);
			hackableField.setAccessible(true);
			hackableField.set(toCopy, listValue);
		}

	}

}
