package application;

import javafx.scene.image.Image;

public class LadyBug extends Sprite {
	private String name;
	private boolean alive;
	
	public final static int LADYBUG_WIDTH = 35;
	public final static Image ladybugDown = new Image("images/LEDYBADOWN.gif",LadyBug.LADYBUG_WIDTH,LadyBug.LADYBUG_WIDTH,true,true);
	
	
	// Constructor Method
	public LadyBug(String name, double x, double y) {	
		super(x,y);
		this.name = name;
		this.alive = true;
		this.loadImage(LadyBug.ladybugDown);//initial image
	}
	
	public boolean isAlive() {
		if(this.alive) return true;
		else return false;
	}
	
	public String getName() {
		return this.name;
	}
	public void die() {
		this.alive = false;
	}
	
	public void move() {
		this.x += this.dx;
		this.y += this.dy;
	}

}
