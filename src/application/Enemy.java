package application;

import javafx.scene.image.Image;
public class Enemy extends Sprite{
	public final static double MAX_SPEED = 0.50;
	public final static int ENEMYWIDTH = 35;
	public final static Image nijaskup = new Image("images/NINJASKUP.gif",Enemy.ENEMYWIDTH,Enemy.ENEMYWIDTH,false,false);
	public final static Image nijaskdown= new Image("images/NINJASKDOWN.gif",Enemy.ENEMYWIDTH,Enemy.ENEMYWIDTH,false,false);
	public final static Image nijaskleft = new Image("images/NINJASKLEFT.gif",Enemy.ENEMYWIDTH,Enemy.ENEMYWIDTH,false,false);
	public final static Image nijaskright = new Image("images/NINJASKRIGHT.gif",Enemy.ENEMYWIDTH,Enemy.ENEMYWIDTH,false,false);
	public final static Image freezeup = new Image("images/FROZENUP.png",Enemy.ENEMYWIDTH,Enemy.ENEMYWIDTH,false,false);
	public final static Image freezedown= new Image("images/FROZENDOWN.png",Enemy.ENEMYWIDTH,Enemy.ENEMYWIDTH,false,false);
	public final static Image freezeleft = new Image("images/FROZENLEFT.png",Enemy.ENEMYWIDTH,Enemy.ENEMYWIDTH,false,false);
	public final static Image freezeright = new Image("images/FROZENRIGHT.png",Enemy.ENEMYWIDTH,Enemy.ENEMYWIDTH,false,false);
	private boolean alive;
	private boolean isFrozen;
	private double speed;
	public Enemy(int x, int y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		this.alive = true;
		this.loadImage(Enemy.nijaskdown);
		this.speed = Enemy.MAX_SPEED;
		this.isFrozen = false;
	}
	public boolean isAlive() {
		return this.alive;
	}
	public boolean getFrozen() {
		return this.isFrozen;
	}
	public void setFrozen(boolean val) {
		this.isFrozen = val;
	}
	
	public void moveUp() {
		this.loadImage(Enemy.nijaskup);
		this.setDY(this.speed);
		this.y -= this.dy;
	}
	
	public void moveDown() {
		this.loadImage(Enemy.nijaskdown);
		this.setDY(this.speed);
		this.y += this.dy;
	}
	public void moveLeft() {
		this.loadImage(Enemy.nijaskleft);
		this.setDX(this.speed);
		this.x -= this.dx;
	}
	public void moveRight() {
		this.loadImage(nijaskright);
		this.setDX(this.speed);
		this.x += this.dx;
	}
	
	public void freezeUp() {
		this.loadImage(Enemy.freezeup);
	}
	public void freezeDown() {
		this.loadImage(Enemy.freezedown);
	}
	public void freezeLeft() {
		this.loadImage(Enemy.freezeleft);
	}
	public void freezeRight() {
		this.loadImage(Enemy.freezeright);
	}
	
//	public void changeImage(int x) {
//		if(x == 1) {
//			this.loadImage(Enemy.nijaskdown);
//		}else if(x == 2) {
//			this.loadImage(Enemy.nijaskup);
//		}else if(x == 3) {
//			this.loadImage(nijaskleft);
//		}else if(x == 4) {
//			this.loadImage(nijaskright);
//		}
//	}

}
