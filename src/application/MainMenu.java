 package application;

//Importing JavaFX Libraries
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// Class for the Stage
public class MainMenu {
	
	// Private Attributes
	private Stage stage;
	private Scene scene;
	private GridPane root;
	private GraphicsContext gc;
	private Canvas canvas;
	
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 800;
	
	public final static long startNanoTime = System.nanoTime();
	
	Image newgame;
	Image instruction;
	Image credits;
	Image exit;
	Image selector;
	Image title;
	Image mainTitle;
	Image bg;
	Image ledyba;
	int choice;		// for choosing in the main menu
	int selPos;		// the vertical selector position 
	//public final static MusicClass auio = new MusicClass();
	// Constructor Method
	public MainMenu() {
		this.root = new GridPane();							// Instantiates a layout to be used by the scene
		this.scene = new Scene(root,800,800);	// Passes Layout to the Scene
		this.canvas = new Canvas(800,800);
		this.gc = canvas.getGraphicsContext2D();			
		
		// Loads Images 
		this.selector = new Image("images/pokeball3.gif",50,50,true,true);
		this.newgame = new Image("images/newgame.png",200,50,true,true);
		this.instruction = new Image("images/instructionButton.png",200,50,true,true);
		this.credits = new Image("images/creditsButton.png",200,50,true,true);
		this.exit = new Image("images/exit.png",200,50,true,true);
		this.title = new Image("images/pokemon1.gif");
		this.mainTitle = new Image("images/title.gif", 500,300,true,true);
		this.bg = new Image("images/bg1.jpg",1400,1200,true,true);
		this.ledyba = new Image("images/ledyba2.gif",200,200,true,true);
		this.choice = 4;		// default choice aimed at new game
		this.selPos = 450;		// default selector position aligned at new game
	}
	

	// Method for adding Components
	private void addComponents(){
	
		gc.clearRect(0, 0, 800, 800);
		
		// Drawing the Images
		this.gc.drawImage(bg,-300,0);
		this.gc.drawImage(newgame,325,450);
		this.gc.drawImage(instruction,325,500);
		this.gc.drawImage(credits,325,550);
		this.gc.drawImage(exit,325,600);
		new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
	            // background image clears canvas
	            gc.drawImage( title, 150, 100);
	            gc.drawImage(mainTitle, 50, 280);
	            gc.drawImage(ledyba, 500, 250);
	            gc.drawImage(selector,250,selPos);
	        }
	    }.start();
	}
	
	// Key Press event Handler
	private void handleKeyPress() {
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){		
				
				if (e.getCode() == KeyCode.UP) {		// Up key
	
					if(choice != 4) {	// Upper Limit of Selection
						choice +=1;
						setPos(-50);
						
					}	
					
			    } else if (e.getCode() == KeyCode.DOWN) {		// Down Key
			    
			    	if(choice != 1 ) {	// Lower Limit of Selection
			    	  	choice -=1;
				    	setPos(+50);
			    	}
			    	
			    } else if(e.getCode() == KeyCode.ENTER) {		// Enter Key 
			    	System.out.println("Enter key pressed!");
			    	
			    	if(choice == 4) {
			    		System.out.println("Starting a New Game.");
			    		GameStage gs = new GameStage();
			    		gs.setStage(stage);
			    	} else if(choice == 3) {
			    		System.out.println("Reading Instructions");
			    		Instruction ins = new Instruction();
			    		ins.setStage(stage);
			    	} else if(choice == 2) {
			    		System.out.println("Viewing the Credits.");
			    		Credits cred = new Credits();
			    		cred.setStage(stage);
			    	} else if(choice == 1) {
			    		System.out.println("Exiting the Game.");
			    		System.exit(0); 
			    	}
			    	
			    }
				
				System.out.println("Selector Position : " + selPos);	// Print Current Selector Position
				System.out.println("Current Choice : " + choice);
			}
		});
	}
	
	// Main Method to be called
	public void setStage(Stage stage) {
		this.stage = stage;
		this.handleKeyPress();
		this.addComponents();							// adding components	
		this.root.getChildren().add(canvas);
		this.stage.setTitle("LadyBug Game");
		this.stage.setScene(this.scene);
		this.stage.show();
	}
	
	// method for adjusting the position of the selector 
	private void setPos(int pos) {
		this.selPos += pos;			// adjusts the position
		this.addComponents();		// redraws the stage
	}
	
}
