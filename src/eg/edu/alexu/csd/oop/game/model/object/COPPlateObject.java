package eg.edu.alexu.csd.oop.game.model.object;

import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.COP;
import eg.edu.alexu.csd.oop.game.controller.displayPlates.PlateColor;
import eg.edu.alexu.csd.oop.game.controller.displayPlates.PlateShape;

public class COPPlateObject extends AbstractImageObject {

	public final PlateShape shape;
	public final PlateColor color;

	public COPPlateObject(int x, int y, PlateColor color, PlateShape shape) {
		super(x, y, 1);
		this.shape = shape;
		this.color = color;
		importObjectImages(shape.getName() + "-" + color.getName());
		resizeObjectImagesToFit();
		this.visible = true;
	}

	/** all the plate images native resolution is 96 x 21 */
	public static int aspectRatio(int width) {
		return width * 21 / 96;
	}

	protected void importObjectImages(String path) {
		try {
			String plate = "plates/" + path + ".png";
			objectImages[0] = ImageIO.read(getClass().getResourceAsStream(plate));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void resizeObjectImagesToFit() {
		objectImages[0] = resizeImage(objectImages[0], (int) (96/(2*COP.RATIO)), aspectRatio((int) (96/(2*COP.RATIO))));
		objectImages[0] = resizeImage(objectImages[0], (int) (96/(COP.RATIO)), aspectRatio((int) (96/(COP.RATIO))));
	}

	@Override
	public AbstractImageObject clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
