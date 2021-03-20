package application;

import javafx.scene.image.Image;

public class Flower extends Sprite{
	
	// Image for the Flower 
	public static final Image FLOWER = new Image("images/pechaberry.png",30,30,true,true);
	
	// Constructor Method 
	public Flower(double x, double y) {
		super(x,y);
		this.loadImage(Flower.FLOWER);
	}

	
}
