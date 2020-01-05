package eg.edu.alexu.csd.oop.game.controller.displayPlates;

public enum PlateShape {

	PLATE("plate"), FLAT("flat"), BOWL("bowl");

	private String name;

	PlateShape(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
