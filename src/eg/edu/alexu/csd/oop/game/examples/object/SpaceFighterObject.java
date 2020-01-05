package eg.edu.alexu.csd.oop.game.examples.object;

public class SpaceFighterObject extends ImageObject{
	
	// static variable single_instance of type Singleton 
    private static SpaceFighterObject single_instance = null;
  
    // private constructor restricted to this class itself 
    private SpaceFighterObject() {
    	super(200, 200, "/PPModel");
    }
  
    // static method to create instance of Singleton class 
    public static SpaceFighterObject getInstance() { 
        if (single_instance == null) 
            single_instance = new SpaceFighterObject(); 
  
        return single_instance; 
    }

	public static interface FireListener{
		void fire();
	}
	
	@SuppressWarnings("unused")
	private FireListener fireListener;
	
	public SpaceFighterObject(int posX, int posY, String path, FireListener fireListener) {
		super(posX, posY, "/PPModel");
		this.fireListener = fireListener;
	}
	
	/*@Override
	public void setY(int mY) {
		if(fireListener!=null)
			fireListener.fire();
	}*/

}
