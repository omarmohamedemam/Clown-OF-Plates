package eg.edu.alexu.csd.oop.game.model.object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @dPattern Flyweight (tomorrow will be implemented)
 */
public class COPCrashBurst extends AbstractImageObject {

	public COPCrashBurst(int x, int y) {
		super(x, y, 10);
		importObjectImages("crash");
		resizeObjectImagesToFit();
		this.visible = true;
	}

	protected void importObjectImages(String path) {
		try {
			for (int i = 0; i < NUM_OF_FRAMES; i++) {
				String s = "crash/" + path + i + ".png";
				objectImages[i] = ImageIO.read(getClass().getResourceAsStream(s));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void resizeObjectImagesToFit() {
		//for (int i = 0; i < NUM_OF_FRAMES; i++)
			//objectImages[i] = resizeImage(objectImages[i], WIDTH, WIDTH);
	}

	@Override
	public AbstractImageObject clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
