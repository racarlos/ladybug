package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GameStage {
	public final static int WINDOW_HEIGHT = 700;		// 800 x 600 grid
	public final static int WINDOW_WIDTH = 900;
	public final static int ROWS = 23;				// Comprised of 23x23 Blocks
	public final static int COLS = 23;
	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gameTimer;
	
	//Class constructor;
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT,Color.BLACK);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		
		//Instantiate a new Game Timer
		this.gameTimer = new GameTimer(this.gc,this.scene);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		this.root.getChildren().add(canvas);
		
		this.stage.setTitle("Lady Bug Arcade Game Pokemon Version");
		this.stage.setScene(this.scene);
		
		//invoke the start method of the animation timer that is extended by the gametimer class
		this.gameTimer.start();
		this.stage.show();
	}
}
