package application;
import javafx.scene.image.Image;


public class Heart extends Sprite {
	
	// Image for the Skull
	public static final Image HEART = new Image("images/pokeheart.png",30,30 ,true,true);


	// Constructor Method 
	public Heart(double x, double y) {
		super(x, y);
		this.loadImage(Heart.HEART);
	}

}
