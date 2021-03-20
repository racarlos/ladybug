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
import javafx.stage.Stage;

public class Credits {

	private Group root;
	private Scene scene;
	private Stage stage;
	private ImageView iv;
	private ImageView btn;
	private Canvas canvas;
	private GraphicsContext gc;
	
	public final static int WINDOW_WIDTH = 800;
	public final static int WINDOW_HEIGHT = 800;
	
	Image UNO = new Image("images/bg2.gif",Credits.WINDOW_WIDTH,Credits.WINDOW_HEIGHT,false,false);
	Image TWO = new Image("images/NEXT.png",75,75,false,false);
	Image text;
	Credits(){
		this.root = new Group();	
		this.scene = new Scene(root);
		this.canvas = new Canvas(800,800	);
		this.gc = canvas.getGraphicsContext2D();
		this.text = new Image("images/credits.png",400,200,true,true);
		this.iv = new ImageView();
		this.iv.setImage(this.UNO);
		this.btn = new ImageView();
		this.btn.setImage(this.TWO);
		this.btn.relocate(WINDOW_WIDTH-75,0);
		this.btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle (MouseEvent e) {
				MainMenu actual = new MainMenu();
				actual.setStage(stage);
			}
		});
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent key) {
				if(key.getCode() == KeyCode.ENTER) {
					MainMenu actual = new MainMenu();
					actual.setStage(stage);
				}
			}
		});
		
	}
	
	void setStage(Stage stage){
		this.gc.drawImage(text,200,150);
		this.stage = stage;
		this.root.getChildren().add(this.iv);
		this.root.getChildren().add(this.canvas);
		this.root.getChildren().add(this.btn);
		this.stage.setTitle("Credits");
		this.stage.setResizable(true);
		this.stage.setScene(this.scene);
		this.stage.show();
	}

	public Scene getScene() {
		return scene;
	}


}
