package application;

import javafx.scene.image.Image;

public class HorWall extends Sprite{

	public static final Image horwall = new Image("images/hardwallfinal.png",70,10,true,true);
	
	public HorWall(int x, int y) {
		super(x,y);
		this.loadImage(HorWall.horwall);
	}
}
