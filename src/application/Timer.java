package application;

import javafx.scene.image.Image;

public class Timer extends Sprite{
	public final static Image TIMER = new Image("images/walltimer.gif",730,700,true,true);
	
	public Timer(double x, double y) {
		super(x,y);
		this.loadImage(Timer.TIMER);
	}
}
