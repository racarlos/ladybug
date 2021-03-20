package application;

import javafx.scene.image.Image;

public class Skull extends Sprite {

	
	// Image for the Skull
	public static final Image SKULL = new Image("images/skull.png",30,30,true,true);
	
	// Constructor Method 
	public Skull(double x, double y) {
		super(x, y);
		this.loadImage(Skull.SKULL);
	}

}
