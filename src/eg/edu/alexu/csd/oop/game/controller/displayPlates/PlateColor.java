package eg.edu.alexu.csd.oop.game.controller.displayPlates;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum PlateColor {

	BLUE("blue"), GREEN("green"), ORANGE("orange"), RED("red"), WHITE("white"), YELLOW("yellow");

	private String name;

	PlateColor(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	private static final List<PlateColor> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static PlateColor random() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

}
