package application;

import javafx.scene.image.Image;

public class HardWall  extends Sprite{

	public static final Image brickwall = new Image("images/hardwall.png",10,10,true,true);
	
	
	public HardWall(int x, int y) {
		super(x,y);
		this.loadImage(HardWall.brickwall);
	}

}