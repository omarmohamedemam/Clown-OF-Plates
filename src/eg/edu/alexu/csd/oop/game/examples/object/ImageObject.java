package eg.edu.alexu.csd.oop.game.examples.object;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class ImageObject implements GameObject{
	
	public void turnLeft() {
		spriteImages = spriteImagesL;
	}
	public void turnRight() {
		spriteImages = spriteImagesR;
	}
	
	private static final int NUM_OF_FRAMES = 1;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[NUM_OF_FRAMES];
	public BufferedImage[] spriteImagesL = new BufferedImage[NUM_OF_FRAMES];
	public BufferedImage[] spriteImagesR = new BufferedImage[NUM_OF_FRAMES];
	
	private int x;
	private int y;
	private boolean visible;
	private int type;
	
	public ImageObject(int posX, int posY, String path){
		this(posX, posY, path, 0);
	}
	
	public ImageObject(int posX, int posY, String path, int type){
		this.x = posX;
		this.y = posY;
		this.type = type;
		this.visible = true;
		// create a bunch of buffered images and place into an array, to be displayed sequentially
		try {
			for(int i=0; i<spriteImages.length; i++) {
				String s;
				if(path.contains("PP")) {
					String sL = "toLeft/" + path + "00" + ((i+5 < 100)? "0":"") + ((i+5 < 10)? "0":"") + (i+5) + ".png";
					String sR = "toRight/" + path + "00" + ((i+5 < 100)? "0":"") + ((i+5 < 10)? "0":"") + (i+5) + ".png";
					 spriteImages[i]  = ImageIO.read(getClass().getResourceAsStream(sR));
					 spriteImagesR[i] = ImageIO.read(getClass().getResourceAsStream(sR));
					 spriteImagesL[i] = ImageIO.read(getClass().getResourceAsStream(sL));
					 spriteImages[i] = resize(spriteImages[i], 400, 400);
					 spriteImagesR[i] = resize(spriteImagesR[i], 400, 400);
					 spriteImagesL[i] = resize(spriteImagesL[i], 400, 400);
				}
				else {
					s = path;
					spriteImages[i] = ImageIO.read(getClass().getResourceAsStream(s));
					int w = spriteImages[i].getWidth();
					int h = spriteImages[i].getHeight();
					spriteImages[i] = resize(spriteImages[i], w/2, h/2);
					spriteImages[i] = resize(spriteImages[i], w, h);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int mX) {
		this.x = mX;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int mY) {
		this.y = mY;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	@Override
	public int getWidth(){
		return spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
}
