package application;

import javafx.scene.image.Image;

public class Lives extends Sprite {
	public final static Image LIVE = new Image("images/ledyba3.gif",40,40,true,true);
	
	public Lives(double x, double y){
		super(x,y);
		this.loadImage(Lives.LIVE);
	}
}
