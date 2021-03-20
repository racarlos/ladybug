package application;
import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import java.util.concurrent.TimeUnit;
public class MyThread extends Thread {
	private Enemy enemy;
	private boolean movedown;       //enemy movement logic
	private boolean moveup;
	private boolean moveleft;
	private boolean moveright;
	private GraphicsContext gc;
	private ArrayList <Node> balls;						// Array List of Nodes 
	private ArrayList <Sprite> walls;					// Array List of Hard Walls
	private Sprite[] foreground;						// Array of Foreground Walls
	private Sprite[] background;						// Array of Background Walls 
	
	public MyThread (Enemy en, ArrayList<Node> balls, ArrayList<Sprite> walls, Sprite[] foreground, Sprite[] background,GraphicsContext gc) {
		this.enemy = en;
		this.movedown = false;
		this.moveup = true;
		this.moveleft = false;
		this.moveright = false;
		this.balls = balls;
		this.walls = walls;
		this.foreground = foreground;
		this.background = background;
		this.gc = gc;
	}
	
	private boolean checkEnemyCollision() {
		for(int i = 0; i < this.walls.size(); i++) {
			if(this.enemy.collidesWith(this.walls.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkEnemyCollisionSoftWall() {
		for(int i = 0; i < this.foreground.length;i++) {		
			// collision with foreground wall
			if(this.enemy.collidesWith(this.foreground[i]) && this.foreground[i].getVisible()){ 
				System.out.println("Collision with Foreground softwall");
				return true;
			} 
			// collision with background wall 
			else if(this.enemy.collidesWith(this.background[i]) && this.background[i].getVisible()){
				System.out.println("Collision with Background softwall");
				return true;
			}
		}
		return false;
	}
	
	private void moveEnemy() {
		Random r = new Random();
		int x = r.nextInt(2);
		int y = r.nextInt(2);
		int w = r.nextInt(2);
		int z = r.nextInt(2);
		if(this.moveup) {
			if(this.checkEnemyCollision() || this.checkEnemyCollisionSoftWall()) {
				this.enemy.bounce(0, -5);
				this.moveup = false;
				if(x == 0) {
					this.moveleft = true;
				}else if(x == 1) {
					this.moveright = true;
				}else {
					this.movedown = true;
				}
				
			}
			this.enemy.moveUp();
		}else if(this.moveleft) {
			if(this.checkEnemyCollision() || this.checkEnemyCollisionSoftWall()) {
				this.enemy.bounce(-5, 0);
				this.moveleft = false;
				if(y == 0) {
					this.movedown = true;
				}else if(y == 1) {
					this.moveup = true;
				}else {
					this.moveright = true;
				}
			}
			this.enemy.moveLeft();
		}else if(this.moveright) {
			if(this.checkEnemyCollision() || this.checkEnemyCollisionSoftWall()) {
				this.enemy.bounce(5,0);
				this.moveright = false;
				if(w == 0) {
					this.moveup = true;
				}else if(w == 1) {
					this.movedown = true;
				}else {
					this.moveleft = true;
				}
			}
			this.enemy.moveRight();
		}else if(this.movedown) {
			if(this.checkEnemyCollision()|| this.checkEnemyCollisionSoftWall()) {
				this.enemy.bounce(0, 5);
				this.movedown = false;
				if(z == 0) {
					this.moveleft = true;
				}else if(z == 1) {
					this.moveright = true;
				}else {
					this.moveup = true;
				}
			}
			this.enemy.moveDown();
		}
	}
	
	public void run() {
		//this.enemy.render(this.gc);
		this.moveEnemy();
		
	}
}
