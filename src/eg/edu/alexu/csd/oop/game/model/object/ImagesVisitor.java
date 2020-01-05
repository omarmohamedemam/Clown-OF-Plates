package eg.edu.alexu.csd.oop.game.model.object;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import static eg.edu.alexu.csd.oop.game.COP.RATIO;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.model.Quality;

/**
 * @dPattern Flyweight, Visitor
 */
@SuppressWarnings("unused")
public interface ImagesVisitor {

	// Created to automatically use the right
	// code based on the Object sent
	// Method Overloading

	public void adjust(COPCrashBurst crash);

	public void adjust(COPClownObject clown);

	public void adjust(COPPlateObject plate);

	static final ImagesManipulator ImagesManger = new ImagesManipulator();

	class ImagesManipulator implements ImagesVisitor, Observer {

		// A map of all images to be loaded and shared between all objects
		private HashMap<Class<? extends AbstractImageObject>, BufferedImage[][]> imagesCollection;
		private Quality picQuality = Quality.MEDIUM;
		private boolean loadedAlready = false;
		
		public void readThemAll() {

			// missing the step of resizing them to fit before doing any thing
			
			if(loadedAlready)
				return;
			
			loadedAlready = true;

			BufferedImage[][] clownImages = new BufferedImage[6][129];
			int clownExpectedWidth = 480;
			int clownActualWidth = (int) (1920 / (4 * RATIO));

			try {
				for (int i = 0; i < 129; i++) {
					String sL = "toLeft/clownL" + i + ".png";
					String sR = "toRight/clownR" + i + ".png";
					clownImages[4][i] = ImageIO.read(getClass().getResourceAsStream(sL));
					clownImages[5][i] = ImageIO.read(getClass().getResourceAsStream(sR));

					if (clownActualWidth != clownExpectedWidth) {
						clownImages[4][i] = resizeImage(clownImages[4][i], clownActualWidth,
								COPClownObject.aspectRatio(clownActualWidth));
						clownImages[5][i] = resizeImage(clownImages[5][i], clownActualWidth,
								COPClownObject.aspectRatio(clownActualWidth));
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			clownImages[2] = pixelationFilter(COPClownObject.class, clownImages[4], Quality.MEDIUM);
			clownImages[0] = pixelationFilter(COPClownObject.class, clownImages[4], Quality.LOW);
			clownImages[3] = pixelationFilter(COPClownObject.class, clownImages[5], Quality.MEDIUM);
			clownImages[1] = pixelationFilter(COPClownObject.class, clownImages[5], Quality.LOW);

			imagesCollection.put(COPClownObject.class, clownImages);

		}

		private ImagesManipulator() {
			imagesCollection = new HashMap<Class<? extends AbstractImageObject>, BufferedImage[][]>();
		}

		public void adjust(COPCrashBurst crash) {

		}

		public void adjust(COPClownObject clown) {
			switch (picQuality) {
			case LOW:
				clown.dirImagesL = imagesCollection.get(clown.getClass())[0];
				clown.dirImagesR = imagesCollection.get(clown.getClass())[1];
				break;
			case MEDIUM:
				clown.dirImagesL = imagesCollection.get(clown.getClass())[2];
				clown.dirImagesR = imagesCollection.get(clown.getClass())[3];
				break;
			case HIGH:
				clown.dirImagesL = imagesCollection.get(clown.getClass())[4];
				clown.dirImagesR = imagesCollection.get(clown.getClass())[5];
				break;
			default:
				break;
			}

		}

		public void adjust(COPPlateObject plate) {

		}

		private BufferedImage[] pixelationFilter(Class<? extends AbstractImageObject> ownerClass,
				BufferedImage[] images, Quality quality) {
			final int WIDTH = images[0].getWidth();
			final int HEIGHT;

			if (ownerClass.equals(COPClownObject.class))
				HEIGHT = COPClownObject.aspectRatio(WIDTH);
			else
				HEIGHT = WIDTH;

			BufferedImage[] pixeledImages = new BufferedImage[images.length];

			for (int i = 0; i < images.length; i++) {
				pixeledImages[i] = resizeImage(images[i], WIDTH / quality.getNum(), HEIGHT / quality.getNum());
				pixeledImages[i] = resizeImage(pixeledImages[i], WIDTH, HEIGHT);
			}

			return pixeledImages;
		}

		// https://stackoverflow.com/questions/9417356/bufferedimage-resize
		private BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
			Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
			BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2d = dimg.createGraphics();
			g2d.drawImage(tmp, 0, 0, null);
			g2d.dispose();

			return dimg;
		}

		public void update(Observable o, Object arg) {
			picQuality = (Quality) arg;
		}

	}

}
