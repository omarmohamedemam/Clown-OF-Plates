package eg.edu.alexu.csd.oop.game.model.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class AbstractImageObject implements GameObject, Cloneable {

	protected final int NUM_OF_FRAMES;
	protected BufferedImage[] objectImages;

	protected int x;
	protected int y;
	protected boolean visible;

	public AbstractImageObject(int x, int y, int NUM_OF_FRAMES) {
		this.x = x;
		this.y = y;
		this.NUM_OF_FRAMES = NUM_OF_FRAMES;
		this.objectImages = new BufferedImage[NUM_OF_FRAMES];
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getWidth(){
		return objectImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return objectImages[0].getHeight();
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public BufferedImage[] getSpriteImages() {
		return objectImages;
	}

	protected String getRelativePakagePath(String fileName) {
		return "src/eg/edu/alexu/csd/oop/game/model/" + fileName;
	}

	public abstract AbstractImageObject clone();

	// https://stackoverflow.com/questions/9417356/bufferedimage-resize
	protected BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

}
