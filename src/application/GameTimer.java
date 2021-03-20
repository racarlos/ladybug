package application;

//Importing JavaFX Libraries
import java.util.ArrayList;
import java.util.Random;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
public class GameTimer extends AnimationTimer{
	
	//Class Attributes Declarations
	private GraphicsContext gc;
	private Scene theScene;
	private LadyBug ledyba;
	private Enemy ninjask1;
	private Enemy ninjask2;
	private Enemy ninjask3;
	private boolean movedown;       //enemy movement logic
	private boolean moveup;
	private boolean moveleft;
	private boolean moveright;
	private boolean movedown2;       //enemy movement logic
	private boolean moveup2;
	private boolean moveleft2;
	private boolean moveright2;
	private boolean movedown3;       //enemy movement logic
	private boolean moveup3;
	private boolean moveleft3;
	private boolean moveright3;
	private Font daFont;
	private LongValue lastNanoTime;
	private IntValue score;
	private int numLives;
	private ArrayList <Node> balls;						// Array List of Nodes 
	private ArrayList <Sprite> walls;					// Array List of Hard Walls
	private ArrayList <Point> points;					// Array List of Nuggets
	private ArrayList <Lives> lives;					// Array Lit of Lives
	private ArrayList <Skull> skulls;					// Array List of skulls
	private ArrayList <Flower> flowers;					// Array List of flowers
	private ArrayList <Heart> hearts;					// Array List of hearts
	private Sprite[] foreground;						// Array of Foreground Walls
	private Sprite[] background;						// Array of Background Walls 
	public static final int MAX_NUM_HARDWALLS = 30;
	public static final int MAX_NUM_ENEMY = 4;
	private Timer timer;
	private int spawnCounter;
	private long startSpawn;
	private boolean ninjask2Spawn;
	private boolean ninjask3Spawn;
	private int scoreMultiplierCounter;
	private boolean eatenFlower;
	
	public final static Image ladybugDown = new Image("images/LEDYBADOWN.gif",LadyBug.LADYBUG_WIDTH,LadyBug.LADYBUG_WIDTH,false,false);
	public final static Image ladybugUp = new Image("images/LEDYBAUP.gif",LadyBug.LADYBUG_WIDTH,LadyBug.LADYBUG_WIDTH,false,false);
	public final static Image ladybugLeft = new Image("images/LEDYBALEFT.gif",LadyBug.LADYBUG_WIDTH,LadyBug.LADYBUG_WIDTH,false,false);
	public final static Image ladybugRight = new Image("images/LEDYBARIGHT.gif",LadyBug.LADYBUG_WIDTH,LadyBug.LADYBUG_WIDTH,false,false);
	
	boolean iscollided = false;
	boolean iscollidedE= false;
	boolean iscollidedX= false;
	boolean iscollidedT= false;
	boolean iscollidedR= false;
	boolean iscollidedA= false;
	boolean finish = true;
	
	Image E_Image = new Image("images/E.png",LadyBug.LADYBUG_WIDTH,LadyBug.LADYBUG_WIDTH,false,false);
	Image X_Image = new Image("images/X.png",LadyBug.LADYBUG_WIDTH,LadyBug.LADYBUG_WIDTH,false,false);
	Image T_Image = new Image("images/T.png",LadyBug.LADYBUG_WIDTH,LadyBug.LADYBUG_WIDTH,false,false);
	Image R_Image = new Image("images/R.png",LadyBug.LADYBUG_WIDTH,LadyBug.LADYBUG_WIDTH,false,false);
	Image A_Image = new Image("images/A.png",LadyBug.LADYBUG_WIDTH,LadyBug.LADYBUG_WIDTH,false,false);
	long startime = System.nanoTime();
	private ArrayList <String> colors = new ArrayList<String>();
	private ArrayList <String> letters = new ArrayList <String>();
	
	private int count_Extra;
	
	
	
	Sprite E = new Sprite(15,20);
	Sprite X = new Sprite(615,615);
	Sprite T = new Sprite(615,20);
	Sprite R = new Sprite(325,260);
	Sprite A = new Sprite(20,615);
	private Image backgroundimage;
	
	GameTimer(GraphicsContext gc, Scene theScene){
		this.gc = gc;
		this.theScene = theScene;
		this.ledyba = new LadyBug("LEDYBA",11*30-10,17*30-10);
		this.ninjask1 = new Enemy(320,300);
		this.ninjask2 = new Enemy(320,300);
		this.ninjask3 = new Enemy(320,300);
		this.timer = new Timer(-13,-13);
		//Movement boolean
		this.movedown = false;
		this.moveup = true;
		this.moveleft = false;
		this.moveright = false;
		this.movedown2 = false;
		this.moveup2 = true;
		this.moveleft2 = false;
		this.moveright2 = false;
		this.movedown3 = false;
		this.moveup3 = true;
		this.moveleft3 = false;
		this.moveright3 = false;
		
		this.daFont = Font.font("Helvetica",FontWeight.BOLD,24);
		this.lastNanoTime = new LongValue(System.nanoTime());
		this.score = new IntValue(0);
		this.handleKeyPressEvent();
		this.generateHardWalls();
		this.generateSoftWalls();
		this.generateNodes();
		this.generatePoints();
		this.generateSkulls();
		this.generateHearts();
		this.generateFlowers();
		this.setFont();
		this.numLives = 3;
		this.generateLives();
		this.spawnCounter = 0;
		this.startSpawn = System.nanoTime();
		this.ninjask2Spawn = false;
		this.ninjask3Spawn = false;
		this.scoreMultiplierCounter = 0;
		this.eatenFlower = false;
		this.iscollided = false;
		this.colors.add("Teal");
		this.colors.add("Red");
		this.colors.add("Green");
		this.colors.add("Blue");
		this.colors.add("Yellow");
		this.letters.add("E");
		this.letters.add("X");
		this.letters.add("T");
		this.letters.add("R");
		this.letters.add("A");
		this.E.loadImage(E_Image);
		this.X.loadImage(X_Image);
		this.T.loadImage(T_Image);
		this.R.loadImage(R_Image);
		this.A.loadImage(A_Image);
		
		this.backgroundimage = new Image ("images/gamebg.jpg",680,680,false,true);
	}
	
	@Override
	public void handle(long currentNanotime) {
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.gc.drawImage(backgroundimage, 0, 0);
		this.ledyba.render(this.gc);
		this.ledyba.move();
		this.checkLives();
		this.renderWalls();
		this.checkCollision();
		//Extra algo
		if(this.E.isVisible()) {
			this.E.render(this.gc);	
		}
		if(this.X.isVisible()) {
			this.X.render(this.gc);	
		}
		if(this.T.isVisible()) {
			this.T.render(this.gc);	
		}
		if(this.R.isVisible()) {
			this.R.render(this.gc);	
		}
		if(this.A.isVisible()) {
			this.A.render(this.gc);	
		}
		this.willdraw(((currentNanotime-this.startime)/1000000000)%5);
		this.collision();
		this.isCompleted();
		
		
		this.ninjask1.render(this.gc);
		this.timer.render(this.gc);
		if(!this.ninjask1.getFrozen()) {
			this.moveEnemy(this.ninjask1);
		}
		this.updateScore();
		this.renderLives(this.numLives);
		//Spawn Second enemy
		if(this.spawnCounter == 0) {
			long currentSec = TimeUnit.NANOSECONDS.toSeconds(currentNanotime);
			long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startSpawn);
			
			if((currentSec-startSec) >= 15) {
				System.out.println("15 seconds done");
				this.ninjask2Spawn = true;
				this.spawnCounter++;
			}
		}
		//Spawn Third enemy
		if(this.spawnCounter == 1) {
			long currentSec = TimeUnit.NANOSECONDS.toSeconds(currentNanotime);
			long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startSpawn);
			
			if((currentSec-startSec) >= 30) {
				System.out.println("30 seconds done");
				this.ninjask3Spawn = true;
			}
		}
		if(this.ninjask2Spawn) {
			this.ninjask2.render(this.gc);
			if(!this.ninjask2.getFrozen()) {
				this.moveEnemy2(this.ninjask2);
			}
		}
		
		if(this.ninjask3Spawn) {
			this.ninjask3.render(this.gc);
			if(!this.ninjask3.getFrozen()){
				this.moveEnemy3(this.ninjask3);
			}
			long currentSec = TimeUnit.NANOSECONDS.toSeconds(currentNanotime);
			long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startSpawn);
			
			if((currentSec-startSec) >= 35) {
				System.out.println("45 seconds done");
				this.renderFlower();
			}
		}
		
		
		this.checkEnemyFrozen1(this.ninjask1);
		if(this.ninjask2Spawn || this.ninjask3Spawn) {
			this.checkEnemyFrozen2(this.ninjask2);
			this.checkEnemyFrozen3(this.ninjask3);
		}
		if(this.eatenFlower) {
			long currentSec = TimeUnit.NANOSECONDS.toSeconds(currentNanotime);
			long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startSpawn);
			
			if((currentSec-startSec) >= 45) {
				System.out.println("10 seconds done");
				this.ninjask1.setFrozen(false);
				this.ninjask2.setFrozen(false);
				this.ninjask3.setFrozen(false);
				this.eatenFlower = false;
			}
		}
		
		this.checkGame(new Stage());
	}
	
	// Method for generating the SoftWalls
	// SoftWalls - horizontal , FlipWalls - vertical 
	private void generateSoftWalls(){	//

		this.foreground = new Sprite[20];
		this.background = new Sprite[20];
		int index = 0;

		int count = 0;
		int set = 0;
		int pixel = 30;

		// Horizontally Starting Walls 
		
		int vert1[] = {2,16};
		int flip1[] = {4,18};
		int length1 = vert1.length;		
		
		for (int i=0;i<length1;i++) {		// 1st Set
			count = vert1[i]*30 + 10;
			set = flip1[i]*30;
			
			this.foreground[index] = new SoftWall(count,2*pixel);
			this.background[index] = new FlipWall(set,1*pixel-15);

			index +=1;
		}

		count = 0;
		set = 0;
		
		int vert2[] = {4,14};
		int flip2[] = {6,16};
		int length2 = vert2.length;

		for (int i = 0;i<length2;i++){		// 2nd Set
			count = vert2[i]*30+10;
			set = flip2[i]*30;

			this.foreground[index] = new SoftWall(count,6*pixel);
			this.background[index] = new FlipWall(set,5*pixel-15);
			index +=1;
		}
		
		count = 0;
		set = 0;
		
		int vert3[] = {6,12};
		int flip3[] = {8,14};
		int length3 = vert3.length;

		for (int i = 0;i<length3;i++){		// 3rd Set
			count = vert3[i]*30+10;
			set = flip3[i]*30;

			this.foreground[index] = new SoftWall(count,8*pixel);
			this.background[index] = new FlipWall(set,7*pixel-15);
			index +=1;
		}
		
		count = 0;
		set = 0;
		
		int vert4[] = {6,12};
		int flip4[] = {8,14};
		int length4 = vert4.length;

		for (int i = 0;i<length4;i++){		// 4th Set
			count = vert4[i]*30+10;
			set = flip4[i]*30;

			this.foreground[index] = new SoftWall(count,18*pixel);
			this.background[index] = new FlipWall(set,17*pixel-15);
			index +=1;
		}

		count = 0;
		set = 0;
		
		int vert5[] = {2,16};
		int flip5[] = {4,18};
		int length5 = vert5.length;

		for (int i = 0;i<length5;i++){		// 5th Set
			count = vert5[i]*30+10;
			set = flip5[i]*30;

			this.foreground[index] = new SoftWall(count,20*pixel);
			this.background[index] = new FlipWall(set,19*pixel-15);
			index +=1;
		}

		// For Vertically Starting Walls 

		count = 0;
		set = 0;
		
		int hori1[] = {8,14};
		int flip6[] = {6,12};
		length1 = hori1.length;

		for (int i = 0;i<length1;i++){		// 6th Set
			count = hori1[i]*30;
			set = flip6[i]*30+10;

			this.foreground[index] = new FlipWall(count,3*pixel-15);
			this.background[index] = new SoftWall(set,4*pixel);
			index +=1;
		}

		count = 0;
		set = 0;
		
		int hori2[] = {2,20};
		int flip7[] = {0,18};
		length2 = hori2.length;

		for (int i = 0;i<length2;i++){		// 7th Set
			count = hori2[i]*30;
			set = flip7[i]*30+10;

			this.foreground[index] = new FlipWall(count,5*pixel-15);
			this.background[index] = new SoftWall(set,6*pixel);
			index +=1;
		}

		count = 0;
		set = 0;
		
		int hori3[] = {4,18};
		int flip8[] = {2,16};
		length3 = hori3.length;

		for (int i = 0;i<length3;i++){		// 8th Set
			count = hori3[i]*30;
			set = flip8[i]*30+10;

			this.foreground[index] = new FlipWall(count,13*pixel-15);
			this.background[index] = new SoftWall(set,14*pixel);
			index +=1;
		}

		count = 0;
		set = 0;
	
		int hori4[] = {6,16};
		int flip9[] = {4,14};
		length4 = hori4.length;

		for (int i = 0;i<length4;i++){		// 9th Set
			count = hori4[i]*30;
			set = flip9[i]*30+10;

			this.foreground[index] = new FlipWall(count,11*pixel-15);
			this.background[index] = new SoftWall(set,12*pixel);
			index +=1;
		}

		count = 0;
		set = 0;
		
		int hori5[] = {8,14};
		int flip10[] = {6,12};
		length5 = hori5.length;

		for (int i = 0;i<length5;i++){		// 10th Set
			count = hori5[i]*30;
			set = flip10[i]*30+10;

			this.foreground[index] = new FlipWall(count,13*pixel-15);
			this.background[index] = new SoftWall(set,14*pixel);
			index +=1;
		}


		System.out.println("Index - " + index);
		// sets all foreground to visible 
		for(int i=0; i<index;i++){
			this.foreground[i].setVisible(true);
		}

		// sets all background to invisible 
		for(int i=0; i<index;i++) {
			this.background[i].setVisible(false);
		}


	}

	// Method for Generating the true walls 
	private void generateHardWalls() {
		
		this.walls = new ArrayList<Sprite>();
		
		int count = 0;
		
		// Left and Right  Part
		for(int i = 0; i<GameStage.ROWS;i++) {				
			this.walls.add(new HardWall(0,count));			
			this.walls.add(new HardWall(660,count));
			count += 30;
		}

		count = 0;
		
		// Upper and Lower Part 
		for(int i = 0; i<GameStage.COLS;i++) {
		 	this.walls.add(new HardWall(count,0)); 
		 	this.walls.add(new HardWall(count,660)); 
		 	count +=30; 
		}

		int hori1[] = {8,12};

		for (int i=0;i<2;i++) {
			this.walls.add(new HorWall(30*hori1[i],30*2));
		}

		int hori2[] = {2,18};

		for (int i=0;i<2;i++) {
			this.walls.add(new HorWall(30*hori2[i],30*4));
		}

		int hori3[] = {8,12};

		for (int i=0;i<2;i++) {
			this.walls.add(new HorWall(30*hori3[i],30*6));
		}

		int hori4[] = {2,18};

		for (int i=0;i<2;i++) {
			this.walls.add(new HorWall(30*hori4[i],30*8));
		}

		int hori5[] = {2,18};

		for (int i=0;i<2;i++) {
			this.walls.add(new HorWall(30*hori5[i],30*10));
		}

		int hori6[] = {10};

		for (int i=0;i<1;i++) {
			this.walls.add(new HorWall(30*hori6[i],30*12));
		}

		int hori7[] = {2,8,12,18};

		for (int i=0;i<4;i++) {
			this.walls.add(new HorWall(30*hori7[i],30*16));
		}

		int hori8[] = {2,4,16,18};

		for (int i=0;i<4;i++) {
			this.walls.add(new HorWall(30*hori8[i],30*18));
		}
		
		int hori9[] = {6,8,12,14};

		for (int i=0;i<4;i++){
			this.walls.add(new HorWall(30*hori9[i],30*20));
		}

		int vert1[] = {2,6,10,12,16,20};

		for (int i=0;i<6;i++) {
			this.walls.add(new VertWall(vert1[i]*30,30*2));
		}

		int vert2[] = {4,10,12,18};

		for (int i=0;i<4;i++) {
			this.walls.add(new VertWall(vert2[i]*30,30*6));
		}

		int vert3[] = {6,16};

		for (int i=0;i<2;i++) {
			this.walls.add(new VertWall(vert3[i]*30,30*8));
		}

		int vert4[] = {4,8,10,12,14,18};

		for (int i=0;i<6;i++) {
			this.walls.add(new VertWall(vert4[i]*30,30*10));
		}

		int vert5[] = {2,20};

		for (int i=0;i<2;i++) {
			this.walls.add(new VertWall(vert5[i]*30,30*12));
		}
		
		int vert6[] = {2,6,10,12,16,20};

		for (int i=0;i<6;i++) {
			this.walls.add(new VertWall(vert6[i]*30,30*14));
		}

		int vert7[] = {2,10,12,20};

		for (int i=0;i<4;i++) {
			this.walls.add(new VertWall(vert7[i]*30,30*18));
		}
	}	
	
	private void generateNodes() {
		this.balls = new ArrayList<Node>();

		int set1[] = {4,18};

		for (int i = 0;i<2;i++) {
			this.balls.add(new Node(set1[i]*30,2*30));
		}

		int set2[] = {8,14};

		for (int i = 0;i<2;i++) {
			this.balls.add(new Node(set2[i]*30,4*30));
		}
		
		int set3[] = {2,6,20,16};

		for (int i = 0;i<4;i++) {
			this.balls.add(new Node(set3[i]*30,6*30));
		}
		
		int set4[] = {8,14};

		for (int i = 0;i<2;i++) {
			this.balls.add(new Node(set4[i]*30,8*30));
		}
		
		int set5[] = {6,16};

		for (int i = 0;i<2;i++) {
			this.balls.add(new Node(set5[i]*30,12*30));
		}
		
		int set6[] = {4,8,14,18};

		for (int i = 0;i<4;i++) {
			this.balls.add(new Node(set6[i]*30,14*30));
		}
		
		int set7[] = {8,14};

		for (int i = 0;i<2;i++) {
			this.balls.add(new Node(set7[i]*30,18*30));
		}
		
		int set8[] = {4,18};

		for (int i = 0;i<2;i++) {
			this.balls.add(new Node(set8[i]*30,20*30));
		}
	}
	
	// Generate the points 
	private void generatePoints() {
		this.points = new ArrayList <Point>();
		
		int set1[] = {1,3,5,7,9,15,17,19,21};			// Row 1 

		for (int i = 0;i < set1.length;i++) {
			this.points.add(new Point(set1[i]*30,1*30));
		}
		
		int set3[] = {1,3,5,7,9,11,13,15,17,19,21};			// Row 3,7,13,15 

		for (int i = 0;i < set3.length;i++) {
			this.points.add(new Point(set3[i]*30,3*30));
			this.points.add(new Point(set3[i]*30,7*30));
			this.points.add(new Point(set3[i]*30,13*30));
			this.points.add(new Point(set3[i]*30,15*30));
		}
		
		int set5[] = {1,3,5,7,9,13,15,17,19,21};			// Row 5,11,19,21

		for (int i = 0;i < set5.length;i++) {
			this.points.add(new Point(set5[i]*30,5*30));
			this.points.add(new Point(set5[i]*30,11*30));
			this.points.add(new Point(set5[i]*30,19*30));
			this.points.add(new Point(set5[i]*30,21*30));
		}

		int set9[] = {3,5,7,9,11,13,15,17,21};			// Row 9  

		for (int i = 0;i < set9.length;i++) {
			this.points.add(new Point(set9[i]*30,9*30));
		}
		
		int set11[] = {1,3,5,7,13,15,17,19};			// Row 11  

		for (int i = 0;i < set11.length;i++) {
			this.points.add(new Point(set11[i]*30,11*30));
		}
		
		int set17[] = {1,7,9,13,15,19,21};			// Row 11  

		for (int i = 0;i < set17.length;i++) {
			this.points.add(new Point(set17[i]*30,17*30));
		}
	}
	
	// Methods for rendering the wall on the game timer
	private void renderWalls() {
		

		// Renders the Hardwalls
		for (Sprite hw: this.walls) {
			hw.render(this.gc);
		}
		
		// Renders the Visible foreground walls
		for(int i=0;i<this.foreground.length;i++) {
			if(this.foreground[i].getVisible()) {
				this.foreground[i].render(this.gc);
			}
		}
		
		// Renders the Visible background walls
		for(int i=0;i<this.background.length;i++) {
			if(this.background[i].getVisible()) {
				this.background[i].render(this.gc);
			}
		}

	
		// Renders the nodes in the game 
		for (Node ball:this.balls) {
			ball.render(this.gc); 
		}
		//Renders the points(nuggets in the game)
		for(Point p:this.points) {
			p.render(this.gc);
		}
		
		for(Skull s:this.skulls) {
			s.render(this.gc);
		}
		
//		// Renders the flowers
//		for(Flower f:this.flowers) {
//			f.render(this.gc);
//		}
		
		// Renders the hearts
		for(Heart h:this.hearts) {
			h.render(this.gc);
		}
		 
	}
	
	private void renderFlower() {
		for(Flower f:this.flowers) {
			f.render(this.gc);
		}
	}
	
	private void handleKeyPressEvent() {
		
		this.theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveLedyba(code);
			}
		});
		
		this.theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                stopLedyba(code);
            }
		});
    }
	
	private void moveLedyba(KeyCode ke) {
		
		double speed = 0.70;
		
		if(ke==KeyCode.UP || ke == KeyCode.W) {
			this.ledyba.loadImage(GameTimer.ladybugUp);
			this.ledyba.setDY(-speed);                 
		}
 
		if(ke==KeyCode.LEFT || ke == KeyCode.A) {
			this.ledyba.loadImage(GameTimer.ladybugLeft);
			this.ledyba.setDX(-speed);
		}

		if(ke==KeyCode.DOWN || ke == KeyCode.S) {
			this.ledyba.loadImage(GameTimer.ladybugDown);
			this.ledyba.setDY(speed);
		}
		
		if(ke==KeyCode.RIGHT || ke == KeyCode.D) {
			this.ledyba.loadImage(GameTimer.ladybugRight);
			this.ledyba.setDX(speed);			
		}
		
	}
	
	// Method for Checking Collision with other objects 
	private void checkCollision() {

		
		// If it collides with a hard wall
		for(int i = 0; i < this.walls.size();i++) {
			if(this.ledyba.collidesWith(this.walls.get(i))){	
				this.ledyba.freeze();
			}
		}
		
		// If it collides with a node 
		for(int i = 0; i < this.balls.size();i++) {
			if(this.ledyba.collidesWith(this.balls.get(i))){
				this.ledyba.freeze();
			}
		}
		
		// If it collides with a softwall 
		for(int i = 0; i < this.foreground.length;i++) {
			
			// collision with foreground wall
			if(this.ledyba.collidesWith(this.foreground[i]) && this.foreground[i].getVisible()){ 
				//System.out.println("Collision with Foreground softwall");
				this.foreground[i].setVisible(false);
				this.background[i].setVisible(true);
			} 
			// collision with background wall 
			else if(this.ledyba.collidesWith(this.background[i]) && this.background[i].getVisible()){
				//System.out.println("Collision with Background softwall");
				this.foreground[i].setVisible(true);
				this.background[i].setVisible(false);
			}
		}
		//If it collides with a point
		for(int i = 0; i < this.points.size();i++) {
			if(this.ledyba.collidesWith(this.points.get(i))) {
				this.points.get(i).setVisible(false);
				this.points.remove(this.points.get(i));
				this.score.value++;
			}
		}
		//If collides with a heart
		for(int i = 0; i < this.hearts.size();i++) {
			if(this.ledyba.collidesWith(this.hearts.get(i))) {
				this.hearts.get(i).setVisible(false);
				this.hearts.remove(this.hearts.get(i));
				
				if(this.scoreMultiplierCounter==0) {
					this.score.value = this.score.value+2;
					this.scoreMultiplierCounter++;
				}else if(this.scoreMultiplierCounter == 1){
					this.score.value = this.score.value+3;
					this.scoreMultiplierCounter++;
				}else {
					this.score.value = this.score.value+5;
					this.scoreMultiplierCounter++;
				}
			}
		}
		
		//If collides with a skull
		for(int i = 0; i < this.skulls.size();i++) {
			if(this.ledyba.collidesWith(this.skulls.get(i))) {
				this.skulls.get(i).setVisible(false);
				this.skulls.remove(this.skulls.get(i));
				this.ledyba.setX(11*30-10);
				this.ledyba.setY(17*30-10);
				this.numLives--;
			}
		}
		//If collides with a flower
		for(int i = 0; i < this.flowers.size();i++) {
			if(this.ledyba.collidesWith(this.flowers.get(i))) {
				this.flowers.get(i).setVisible(false);
				this.flowers.remove(this.flowers.get(i));
				this.eatenFlower = true;
				this.ninjask1.setFrozen(true);
				this.ninjask2.setFrozen(true);
				this.ninjask3.setFrozen(true);
			}
		}
		//if collides with enemies
		if(this.ledyba.collidesWith(this.ninjask1)) {
			this.ledyba.setX(11*30-10);
			this.ledyba.setY(17*30-10);
			this.ninjask1.setX(320);
			this.ninjask1.setY(300);
			this.numLives--;
		}
		if(this.ledyba.collidesWith(this.ninjask2)) {
			this.ledyba.setX(11*30-10);
			this.ledyba.setY(17*30-10);
			this.ninjask2.setX(320);
			this.ninjask2.setY(300);
			this.numLives--;
		}
		if(this.ledyba.collidesWith(this.ninjask3)) {
			this.ledyba.setX(11*30-10);
			this.ledyba.setY(17*30-10);
			this.ninjask3.setX(320);
			this.ninjask3.setY(300);
			this.numLives--;
		}
		
	}
	private boolean checkEnemyCollision(Enemy enemy) {
		for(int i = 0; i < this.walls.size(); i++) {
			if(enemy.collidesWith(this.walls.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean checkEnemyCollisionSoftWall(Enemy enemy) {

		for(int i = 0; i < this.foreground.length;i++) {		
			// collision with foreground wall
			if(enemy.collidesWith(this.foreground[i]) && this.foreground[i].getVisible()){ 
				System.out.println("Collision with Foreground softwall");
				return true;
			} 
			// collision with background wall 
			else if(enemy.collidesWith(this.background[i]) && this.background[i].getVisible()){
				System.out.println("Collision with Background softwall");
				return true;
			}
		}
		return false;
	}
	
	private void checkEnemyCollisionSkull(Enemy enemy) {
		for(int i = 0; i < this.skulls.size();i++) {
			if(enemy.collidesWith(this.skulls.get(i))) {
				this.skulls.get(i).setVisible(false);
				this.skulls.remove(this.skulls.get(i));
				enemy.setX(320);
				enemy.setY(300);
			}
		}
	}
	private void moveEnemy(Enemy en) {
		Random r = new Random();
		int x = r.nextInt(2);
		int y = r.nextInt(2);
		int w = r.nextInt(2);
		int z = r.nextInt(2);
		if(this.moveup) {
			if(this.checkEnemyCollision(en) || this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(0, -5);
				this.moveup = false;
				if(x == 0) {
					this.moveleft = true;
				}else if(x == 1) {
					this.moveright = true;
				}else {
					this.movedown = true;
				}
				
			}
			en.moveUp();
		}else if(this.moveleft) {
			if(this.checkEnemyCollision(en) || this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(-5, 0);
				this.moveleft = false;
				if(y == 0) {
					this.movedown = true;
				}else if(y == 1) {
					this.moveup = true;
				}else {
					this.moveright = true;
				}
			}
			en.moveLeft();
		}else if(this.moveright) {
			if(this.checkEnemyCollision(en) || this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(5,0);
				this.moveright = false;
				if(w == 0) {
					this.moveup = true;
				}else if(w == 1) {
					this.movedown = true;
				}else {
					this.moveleft = true;
				}
			}
			en.moveRight();
		}else if(this.movedown) {
			if(this.checkEnemyCollision(en)|| this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(0, 5);
				this.movedown = false;
				if(z == 0) {
					this.moveleft = true;
				}else if(z == 1) {
					this.moveright = true;
				}else {
					this.moveup = true;
				}
			}
			en.moveDown();
		}
		
		this.checkEnemyCollisionSkull(en);
	}
	
	private void moveEnemy2(Enemy en) {
		Random r = new Random();
		int x = r.nextInt(2);
		int y = r.nextInt(2);
		int w = r.nextInt(2);
		int z = r.nextInt(2);
		if(this.moveup2) {
			if(this.checkEnemyCollision(en) || this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(0, -5);
				this.moveup2 = false;
				if(x == 0) {
					this.moveleft2 = true;
				}else if(x == 1) {
					this.moveright2 = true;
				}else {
					this.movedown2 = true;
				}
				
			}
			en.moveUp();
		}else if(this.moveleft2) {
			if(this.checkEnemyCollision(en) || this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(-5, 0);
				this.moveleft2 = false;
				if(y == 0) {
					this.movedown2 = true;
				}else if(y == 1) {
					this.moveup2 = true;
				}else {
					this.moveright2 = true;
				}
			}
			en.moveLeft();
		}else if(this.moveright2) {
			if(this.checkEnemyCollision(en) || this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(5,0);
				this.moveright2 = false;
				if(w == 0) {
					this.moveup2 = true;
				}else if(w == 1) {
					this.movedown2 = true;
				}else {
					this.moveleft2 = true;
				}
			}
			en.moveRight();
		}else if(this.movedown2) {
			if(this.checkEnemyCollision(en)|| this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(0, 5);
				this.movedown2 = false;
				if(z == 0) {
					this.moveleft2= true;
				}else if(z == 1) {
					this.moveright2 = true;
				}else {
					this.moveup2 = true;
				}
			}
			en.moveDown();
		}
		this.checkEnemyCollisionSkull(en);
	}
	
	private void moveEnemy3(Enemy en) {
		Random r = new Random();
		int x = r.nextInt(2);
		int y = r.nextInt(2);
		int w = r.nextInt(2);
		int z = r.nextInt(2);
		if(this.moveup3) {
			if(this.checkEnemyCollision(en) || this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(0, -5);
				this.moveup3 = false;
				if(x == 0) {
					this.moveleft3 = true;
				}else if(x == 1) {
					this.moveright3 = true;
				}else {
					this.movedown3 = true;
				}
				
			}
			en.moveUp();
		}else if(this.moveleft3) {
			if(this.checkEnemyCollision(en) || this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(-5, 0);
				this.moveleft3 = false;
				if(y == 0) {
					this.movedown3 = true;
				}else if(y == 1) {
					this.moveup3 = true;
				}else {
					this.moveright3 = true;
				}
			}
			en.moveLeft();
		}else if(this.moveright3) {
			if(this.checkEnemyCollision(en) || this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(5,0);
				this.moveright3 = false;
				if(w == 0) {
					this.moveup3 = true;
				}else if(w == 1) {
					this.movedown3 = true;
				}else {
					this.moveleft3 = true;
				}
			}
			en.moveRight();
		}else if(this.movedown3) {
			if(this.checkEnemyCollision(en)|| this.checkEnemyCollisionSoftWall(en)) {
				en.bounce(0, 5);
				this.movedown3 = false;
				if(z == 0) {
					this.moveleft3= true;
				}else if(z == 1) {
					this.moveright3 = true;
				}else {
					this.moveup3 = true;
				}
			}
			en.moveDown();
		}
		this.checkEnemyCollisionSkull(en);
	}
	
	// Method that will stop the ship's movement; set the ship's DX and DY to 0
	private void stopLedyba(KeyCode ke){
		this.ledyba.setDX(0);
		this.ledyba.setDY(0);
	}
	 
	//Method for setting fonts
	private void setFont() {
		this.gc.setFont(this.daFont);
		this.gc.setFill(Color.ALICEBLUE);
		this.gc.setStroke(Color.BLACK);
		this.gc.setLineWidth(1);
	}
	
	private void updateScore() {
		String pointsText = "Score: "+ (10*score.value);
		this.gc.fillText(pointsText, 700,30);
		this.gc.strokeText(pointsText, 700, 30);
	}
	
	private void generateLives() {
		this.lives = new ArrayList<Lives>();
		for(int i = 0; i < 3; i++) {
			this.lives.add(new Lives((700+(i*40)),80));
		}
	}
	
	private void generateSkulls() {
		this.skulls = new ArrayList <Skull>();
		int off = 10;
		this.skulls.add(new Skull(17*30-off,17*30-off));
		this.skulls.add(new Skull(1*30-off,9*30-off));
		this.skulls.add(new Skull(19*30-off,9*30-off));
	}
	
	// Method for generating the Flowers
	private void generateFlowers(){
		this.flowers = new ArrayList <Flower>();
		this.flowers.add(new Flower(320,300));
	}
	
	// Method for generating the Hearts
	private void generateHearts(){
		this.hearts = new ArrayList <Heart>();
		int off = 10;
		this.hearts.add(new Heart(13*30-off,1*30-off));
		this.hearts.add(new Heart(3*30-off,17*30-off));
		this.hearts.add(new Heart(11*30-off,21*30-off));
	}
	private void renderLives(int num) {
		for(int i = 0; i < num;i++) {
			this.lives.get(i).render(this.gc);
		}
	}
	
	private void checkLives() {

		if(this.numLives <= 0) {
			this.ledyba.die();
		}
	}
	
	private void checkEnemyFrozen1(Enemy en){
		if(en.getFrozen() && this.moveup) {
			en.freezeUp();
		}else if(en.getFrozen() && this.movedown) {
			en.freezeDown();
		}else if(en.getFrozen() && this.moveleft) {
			en.freezeLeft();
		}else if(en.getFrozen() && this.moveright) {
			en.freezeRight();
		}
	}
	private void checkEnemyFrozen2(Enemy en){
		if(en.getFrozen() && this.moveup2) {
			en.freezeUp();
		}else if(en.getFrozen() && this.movedown2) {
			en.freezeDown();
		}else if(en.getFrozen() && this.moveleft2) {
			en.freezeLeft();
		}else if(en.getFrozen() && this.moveright2) {
			en.freezeRight();
		}
	}
	private void checkEnemyFrozen3(Enemy en){

		if(en.getFrozen() && this.moveup3) {
			en.freezeUp();
		}else if(en.getFrozen() && this.movedown3) {
			en.freezeDown();
		}else if(en.getFrozen() && this.moveleft3) {
			en.freezeLeft();
		}else if(en.getFrozen() && this.moveright3) {
			en.freezeRight();
		}
	}
	
	void collision() {
		if(this.ledyba.collidesWith(this.E) && !this.iscollidedE) {
			this.iscollidedE = true;
			this.E.setVisible(false);
			this.count_Extra++;
		}
		if(this.ledyba.collidesWith(this.X)&& !this.iscollidedX) {
			this.iscollidedX = true;
			this.X.setVisible(false);
			this.count_Extra++;
		}
		if(this.ledyba.collidesWith(this.T)&& !this.iscollidedT) {
			this.iscollidedT = true;
			this.T.setVisible(false);
			this.count_Extra++;
		}
		if(this.ledyba.collidesWith(this.R)&& !this.iscollidedR) {
			this.iscollidedR = true;
			this.R.setVisible(false);
			this.count_Extra++;
		}
		if(this.ledyba.collidesWith(this.A)&& !this.iscollidedA) {
			this.iscollidedA = true;
			this.A.setVisible(false);
			this.count_Extra++;
		}
		
		
	}
	
	void willdraw(long i) {
		this.gc.setFont(Font.font("Showcard Gothic",35));
		if(this.iscollidedE && this.finish) {
			this.gc.setFill(Paint.valueOf(this.colors.get((int) i)));
		}
		this.gc.fillText(this.letters.get(0),700,300);
		this.gc.setFill(Paint.valueOf("White"));
		if(this.iscollidedX&& this.finish	) {
			this.gc.setFill(Paint.valueOf(this.colors.get((int) i)));
		}
		this.gc.fillText(this.letters.get(1),735,300);
		this.gc.setFill(Paint.valueOf("White"));
		if(this.iscollidedT && this.finish) {
			this.gc.setFill(Paint.valueOf(this.colors.get((int) i)));
		}
		this.gc.fillText(this.letters.get(2),770,300);
		this.gc.setFill(Paint.valueOf("White"));
		if(this.iscollidedR&& this.finish) {
			this.gc.setFill(Paint.valueOf(this.colors.get((int) i)));
		}
		this.gc.fillText(this.letters.get(3),805,300);
		this.gc.setFill(Paint.valueOf("White"));
		if(this.iscollidedA&& this.finish) {
			this.gc.setFill(Paint.valueOf(this.colors.get((int) i)));
		}
		this.gc.fillText(this.letters.get(4),840,300);
		this.gc.setFill(Paint.valueOf("White"));
	}
	
	void isCompleted() {
		if(this.count_Extra == 5) {
			this.gc.setFill(Paint.valueOf("White"));
			this.finish = false;
			this.count_Extra = 0;
		}
	}
	
	private void checkGame(Stage stage ) {
		if(this.points.size() == 0 || this.numLives == 0) {
			GameOver over = new GameOver(this.score.getValue());
			stage.setScene(over.getScene());
			stage.show();
			this.stop();	
		}
	}
	
}
