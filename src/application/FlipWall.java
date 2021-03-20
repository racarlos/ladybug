package application;

import javafx.scene.image.Image;

public class FlipWall extends Sprite{

	// Vertical Wall
	public static final Image flipWall = new Image("images/softwallUP.png",10,100,false,false);
	
	public FlipWall(int x, int y) {
		super(x,y);
		this.loadImage(FlipWall.flipWall);
	}
	
	
}
