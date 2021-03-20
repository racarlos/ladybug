package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	protected Image img;
	protected double x,y,dx,dy;
	protected boolean visible;
	protected double width;
	protected double height;
	//Constructor
	public Sprite(double x, double y) {
		this.x = x;
		this.y = y;
		this.visible = true;
	}
	
	protected void freeze() {
		this.x -= this.dx;
		this.y -= this.dy;
	}
	
	protected void bounce(double x, double y) {
		this.x -= x;
		this.y -= y;
	}
	
	//method to set the object's image
	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}
	
	//method to set the image to the image view node
	void render(GraphicsContext gc){
		gc.drawImage(this.img, this.x, this.y);
        
    }
	
	//method to set the object's width and height properties
	private void setSize(){
		this.width = this.img.getWidth();
	    this.height = this.img.getHeight();
	}
	//method that will check for collision of two sprites
	public boolean collidesWith(Sprite rect2)	{
		Rectangle2D rectangle1 = this.getBounds();
		Rectangle2D rectangle2 = rect2.getBounds();	

		return rectangle1.intersects(rectangle2);
	}
	
	//method that will return the bounds of an image
	private Rectangle2D getBounds(){
		return new Rectangle2D(this.x, this.y, this.width, this.height);
	}
	
	//Method to return the image
	Image getImage(){
		return this.img;
	}
	
	//getters
	public double getX() {
    	return this.x;
	}

	public double getY() {
    	return this.y;
	}
	
	public boolean getVisible(){
		return visible;	
	}
	public boolean isVisible(){
		if(visible) return true;
		return false;
	}
	
	//setters
	public void setDX(double dx){
		this.dx = dx;
	}
	
	public void setDY(double d){
		this.dy = d;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setWidth(double val){
		this.width = val;
	}

	public void setHeight(double val){
		this.height = val;
	}
		
	public void setVisible(boolean value){
		this.visible = value;
	}
}
