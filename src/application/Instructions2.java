package application;

//Importing JavaFX Libraries
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

public class Instructions2{
	private Group root;
	Scene scene;
	private Stage stage;
	private GraphicsContext gc;
	private Canvas canvas;
	private ImageView iv;
	private ImageView btn;
	private ImageView btn2;
	
	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 800;
	
	public final static Image UNO = new Image("images/inststruction1.png",Instructions2.WINDOW_WIDTH,Instructions2.WINDOW_HEIGHT,true,true);
	public final static Image TWO = new Image("images/inststruction2.png",Instructions2.WINDOW_WIDTH,Instructions2.WINDOW_HEIGHT,false,false);
	public final static Image THREE = new Image("images/NEXT.png",75,75,false,false);
	public final static Image FOUR = new Image("images/NEXT1.png",75,75,false,false);
	
	
	Instructions2(){
		this.root = new Group();	
		this.canvas = new Canvas(Instructions2.WINDOW_WIDTH,Instructions2.WINDOW_HEIGHT);	
		this.scene = new Scene(root, Instructions2.WINDOW_WIDTH,Instructions2.WINDOW_HEIGHT,Color.WHITE);
		this.iv = new ImageView();
		this.iv.setImage(Instructions2.TWO);
		
		this.btn = new ImageView();
		this.btn.setImage(Instructions2.FOUR);
		this.btn.relocate(Instructions2.WINDOW_WIDTH-75,0);
		
		this.btn2 = new ImageView();
		this.btn2.setImage(Instructions2.THREE);
		this.btn2.relocate(0,0);
		
		this.gc = canvas.getGraphicsContext2D();
		this.gc.drawImage(Instructions2.UNO, 120, Instructions2.WINDOW_HEIGHT);
		
		this.btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				MainMenu actual = new MainMenu();
				actual.setStage(stage);
			}
		});
		
		this.btn2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				Instruction prev = new Instruction();
				prev.setStage(stage);				
			}
		});
		
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent key) {
				if(key.getCode() == KeyCode.ENTER) {
					MainMenu actual = new MainMenu();
					actual.setStage(stage);
				}else if(key.getCode()==KeyCode.BACK_SPACE) {
					Instruction prev = new Instruction();
					prev.setStage(stage);
				}
			}
		});
		
	}
	
	void setStage(Stage stage){
		this.stage = stage;
		this.root.getChildren().add(this.iv);
		this.root.getChildren().add(this.btn);
		this.root.getChildren().add(this.btn2);
		this.stage.setTitle("Instruction");
		this.stage.setResizable(true);
		this.stage.setScene(this.scene);
		this.stage.show();
	}
}