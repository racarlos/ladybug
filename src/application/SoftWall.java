package application;

import javafx.scene.image.Image;

public class SoftWall extends Sprite{

	// Horizontal Wall 
	public static final Image softwall = new Image("images/softwall.png",110,10,false,false);
	
	public SoftWall(int x, int y) {
		super(x,y);
		this.loadImage(SoftWall.softwall);
	}

}
