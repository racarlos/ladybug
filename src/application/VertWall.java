package application;

import javafx.scene.image.Image;

public class VertWall extends Sprite{

	public static final Image vertwall = new Image("images/hardwallfinal2.png",10,70,true,true);
	
	
	public VertWall(int x, int y) {
		super(x,y);
		this.loadImage(VertWall.vertwall);
	}
}
