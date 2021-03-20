package application;

import javafx.scene.image.Image;

public class Node extends Sprite{
	
	// Horizontal Wall 
	public static final Image NODE = new Image("images/pokeball1.gif",10,10,true,true);
	
	public Node(int x, int y) {
		super(x,y);
		this.loadImage(Node.NODE);
	}

}
