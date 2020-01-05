package eg.edu.alexu.csd.oop.game.model;

public enum Quality {

	LOW(3), MEDIUM(2), HIGH(1);

	private int num;

	Quality(int num) {
		this.num = num;
	}

	public int getNum() {
		return this.num;
	}

}