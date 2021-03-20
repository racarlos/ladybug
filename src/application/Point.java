package application;

import javafx.scene.image.Image;

public class Point extends Sprite{
	
	private boolean	alive;
	// Image for the Point
	public static final Image POINT = new Image("images/nugget.png",15,15,true,true);
	
	public Point(double x, double y) {
		super(x, y);
		this.alive = true;
		this.loadImage(Point.POINT);
	}
	
	public boolean isALive() {
		return this.alive;
	}

}
