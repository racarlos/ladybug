package application;

// Importing Libraries
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Instruction{
	private Group root;
	private Scene scene;
	private Stage stage;
	private GraphicsContext gc;
	private Canvas canvas;
	private ImageView iv;
	private ImageView btn;
	
	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 800;
	
	public final static Image UNO = new Image("images/inststruction1.png",Instruction.WINDOW_WIDTH,Instruction.WINDOW_HEIGHT,true,true);
	public final static Image TWO = new Image("images/inststruction2.png",Instruction.WINDOW_WIDTH,Instruction.WINDOW_HEIGHT,false,false);
	public final static Image THREE = new Image("images/NEXT1.png",75,75,false,false);
	public final static Image FOUR = new Image("images/NEXT.png",Instruction.WINDOW_WIDTH,Instruction.WINDOW_HEIGHT,false,false);
	
	// Constructor Method
	Instruction(){
		this.root = new Group();	
		this.canvas = new Canvas(Instruction.WINDOW_WIDTH,Instruction.WINDOW_HEIGHT);	
		this.setScene(new Scene(root, Instruction.WINDOW_WIDTH,Instruction.WINDOW_HEIGHT,Color.WHITE));
		this.iv = new ImageView();
		this.iv.setImage(Instruction.UNO);
		this.btn = new ImageView();
		this.btn.setImage(Instruction.THREE);
		this.btn.relocate(Instruction.WINDOW_WIDTH-75,0);
		this.gc = canvas.getGraphicsContext2D();
		this.gc.drawImage(Instruction.UNO, 120, Instruction.WINDOW_HEIGHT);
		
		this.btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				Instructions2 next = new Instructions2();
				next.setStage(stage);
			}
		});
		
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent key) {
				if(key.getCode() == KeyCode.ENTER) {
					Instructions2 next = new Instructions2();
					next.setStage(stage);
				}
			}
		});
		
	}
	
	void setStage(Stage stage){
			this.stage = stage;
			this.root.getChildren().add(this.iv);
			this.root.getChildren().add(this.btn);
			this.stage.setTitle("Instruction");
			this.stage.setResizable(false);
			this.stage.setScene(this.getScene());
			this.stage.show();
	}

	public Scene getScene() {
		return scene;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
}