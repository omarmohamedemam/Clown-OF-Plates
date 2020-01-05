package eg.edu.alexu.csd.oop.game.model.object;

import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.model.Turnable;


public class COPClownObject extends AbstractImageObject implements Turnable {

	public BufferedImage[] dirImagesL = new BufferedImage[NUM_OF_FRAMES];
	public BufferedImage[] dirImagesR = new BufferedImage[NUM_OF_FRAMES];

	public COPClownObject(int x, int y) {
		super(x, y, 129);
		ImagesVisitor.ImagesManger.adjust(this);
		objectImages = dirImagesR;
		this.visible = true;
	}

	/** all clown images native resolution 480 x 543 */
	public static int aspectRatio(int width) {
		return width * 543 / 480;
	}

	public void setX(int x) {
		if(x > this.x) turnRight();
		else if (x < this.x) turnLeft();
		this.x = x;
	}
	
	public void setY(int y) {
	}

	public void turnLeft() {
		objectImages = dirImagesL;
	}

	public void turnRight() {
		objectImages = dirImagesR;
	}

	@Override
	public AbstractImageObject clone() {
		
		return null;
	}

}
