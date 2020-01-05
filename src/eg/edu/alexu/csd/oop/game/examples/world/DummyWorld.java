package eg.edu.alexu.csd.oop.game.examples.world;

import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.controller.displayPlates.PlateColor;
import eg.edu.alexu.csd.oop.game.controller.displayPlates.PlateShape;
import eg.edu.alexu.csd.oop.game.model.object.COPClownObject;
import eg.edu.alexu.csd.oop.game.model.object.COPPlateObject;
import eg.edu.alexu.csd.oop.game.model.world.AbstractWorldModel;
import eg.edu.alexu.csd.oop.game.view.GameOverFrame;
import eg.edu.alexu.csd.oop.game.view.GamePanel;

import static eg.edu.alexu.csd.oop.game.COP.RATIO;
import static eg.edu.alexu.csd.oop.game.COP.customGameOver;

public class DummyWorld extends AbstractWorldModel {

    private long startTime = System.currentTimeMillis();
    private final int width;
    private final int height;
    private final List<GameObject> constant = new LinkedList<GameObject>();
    private final List<GameObject> moving = new LinkedList<GameObject>();
    private final List<GameObject> control = new LinkedList<GameObject>();
    private final List<GameObject> leftCollected = new LinkedList<GameObject>();
    private final List<GameObject> rightCollected = new LinkedList<GameObject>();
    private double tolerance = -5;
    private double leftX1=(15-tolerance)/RATIO;
    private double leftX2=(125+tolerance)/RATIO;
    private double rightX1=(355-tolerance)/RATIO;
    private double rightX2=(465+tolerance)/RATIO;
    private double leftY1=(202-20)/RATIO;
    private double leftY2=202/RATIO;
    private double rightY1=(202-20)/RATIO;
    private double rightY2=202/RATIO;

    public DummyWorld(int screenWidth, int screenHeight) {
    	super(screenWidth, screenHeight);
        width = screenWidth;
        height = screenHeight;
        control.add(new COPClownObject(screenWidth/2, (int) (screenWidth/3.75)));

        for (int i = 0; i < 5; i++)
            moving.add(new COPPlateObject((int) (Math.random() * screenWidth), -1 * (int) (Math.random() * screenHeight), PlateColor.ORANGE,PlateShape.PLATE));
        for (int i = 0; i < 5; i++)
            moving.add(new COPPlateObject((int) (Math.random() * screenWidth), -1 * (int) (Math.random() * screenHeight), PlateColor.RED,PlateShape.PLATE));
        for (int i = 0; i < 5; i++)
            moving.add(new COPPlateObject((int) (Math.random() * screenWidth), -1 * (int) (Math.random() * screenHeight), PlateColor.BLUE,PlateShape.PLATE));
        for (int i = 0; i < 5; i++)
            moving.add(new COPPlateObject((int) (Math.random() * screenWidth), -1 * (int) (Math.random() * screenHeight), PlateColor.WHITE,PlateShape.PLATE));
        for (int i = 0; i < 5; i++)
            moving.add(new COPPlateObject((int) (Math.random() * screenWidth), -1 * (int) (Math.random() * screenHeight), PlateColor.GREEN,PlateShape.PLATE));
        for (int i = 0; i < 5; i++)
            moving.add(new COPPlateObject((int) (Math.random() * screenWidth), -1 * (int) (Math.random() * screenHeight), PlateColor.YELLOW,PlateShape.PLATE));
         
    }

    // might be useful
    private boolean intersect(GameObject o1, GameObject o2, double x1, double x2, double y1, double y2) {
        double minX = o1.getX()+x1;
        double maxX = o1.getX()+x2;
        double minY = o1.getY()+y1;
        double maxY = o1.getY()+y2;

        double plateX1= o2.getX();
        double plateX2= o2.getX()+o2.getWidth();
        double plateY1= o2.getY();
        double plateY2= o2.getY()+o2.getHeight();
        
        if(plateX2<minX || plateX1>maxX){
            return false;
        }else if(plateY2<minY || plateY1>maxY){
            return false;
        }else{
            return true;
        }
    }
    
    private boolean leftMatches(List<GameObject> collected){
        for(int i=0; i<collected.size()-2; i++){
            if(((COPPlateObject)collected.get(i)).color==((COPPlateObject)collected.get(i+1)).color &&
                    ((COPPlateObject)collected.get(i+1)).color==((COPPlateObject)collected.get(i+2)).color){
                leftY1+=collected.get(i).getHeight();
                leftY2+=collected.get(i).getHeight();
                collected.remove(i);
                leftY1+=collected.get(i).getHeight();
                leftY2+=collected.get(i).getHeight();
                collected.remove(i);
                leftY1+=collected.get(i).getHeight();
                leftY2+=collected.get(i).getHeight();
                collected.remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean rightMatches(List<GameObject> collected){
        for(int i=0; i<collected.size()-2; i++){
            if(((COPPlateObject)collected.get(i)).color==((COPPlateObject)collected.get(i+1)).color &&
                    ((COPPlateObject)collected.get(i+1)).color==((COPPlateObject)collected.get(i+2)).color){
                rightY1+=collected.get(i).getHeight();
                rightY2+=collected.get(i).getHeight();
                collected.remove(i);
                rightY1+=collected.get(i).getHeight();
                rightY2+=collected.get(i).getHeight();
                collected.remove(i);
                rightY1+=collected.get(i).getHeight();
                rightY2+=collected.get(i).getHeight();
                collected.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        GameObject clown = control.get(0);
        // moving plates
        for (GameObject m : moving) {
            m.setY((m.getY() + 1));
            if (m.getY() == getHeight()) {
                // reuse the plate in another position
                m.setY(-1 * (int) (Math.random() * getHeight()));
                m.setX((int) (Math.random() * getWidth()));
                if(!m.isVisible()){
                    ((COPPlateObject)m).setVisible(true);
                }
            }
            m.setX(m.getX() + (Math.random() > 0.5 ? 1 : -1));

            /******************************You Should Start Here******************************/

            if(intersect(clown, m, leftX1,leftX2,leftY1,leftY2)){
                if(m.isVisible()){
                    System.out.println("HITTTTTTTT LEFT");
                    System.out.println(this.hashCode());
                    ((COPPlateObject)m).setVisible(false);
                    leftCollected.add(m);
                    //leftY1-=m.getHeight();
                    //leftY2-=m.getHeight();
                }
            }

            if(intersect(clown, m, rightX1,rightX2,rightY1,rightY2)){
                if(m.isVisible()){
                    System.out.println("HITTTTTTTT RIGHT");
                    System.out.println(this.hashCode());
                    ((COPPlateObject)m).setVisible(false);
                    rightCollected.add(m);
                    //rightY1-=m.getHeight();
                    //rightY2-=m.getHeight();
                }
            }
        }

        if(leftMatches(leftCollected)){
            System.out.println("left CORRECT");
            score++;
        }
        if(rightMatches(rightCollected)){
            System.out.println("right CORRECT");
            score++;
        }

        if (timeout)
			if (customGameOver) {
				GameOverFrame.getInstance().showFrame();
				GamePanel.closeGame();
				return true;
			}

		return !timeout;
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public int getControlSpeed() {
        return 20;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}