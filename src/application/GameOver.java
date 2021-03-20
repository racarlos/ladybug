package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

// CLass 
public class GameOver {

	// Private Attributes
	private Scene scene;
	private GridPane root;
	private GraphicsContext gc;
	private Canvas canvas;
	private int score;
	

	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	
	public Image gameover = new Image("images/gameover.png",GameOver.HEIGHT,GameOver.WIDTH,false,true);
	
	
	// Constructor Method for the game over
	public GameOver(int score) {
		this.root = new GridPane();
		this.scene = new Scene(root,GameOver.WIDTH,GameOver.HEIGHT);
		this.canvas = new Canvas(GameOver.WIDTH,GameOver.HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.score = score;
		
		
		// Starting the method
		this.setProperties(score);
	}

	
	// Method for setting the properties 
	private void setProperties(int score) {
		System.out.println("The Player has won");
		System.out.println("Total Score: " + this.score );
		this.gc.drawImage(gameover, 0, 0);
		
	
		Button exit = new Button("Exit Game");
		exit.setLayoutX(400);
		exit.setLayoutY(400);
		
		this.addEventHandler(exit);
		
		root.getChildren().add(this.canvas);
		root.getChildren().add(exit);
	}
	
	
	// Event Handler for the button 
	private void addEventHandler(Button btn) {
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				System.exit(0);
			}
		});
	}

		
	Scene getScene(){
		return this.scene;
	}	
}
