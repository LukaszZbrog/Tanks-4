package gui;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
	private final int TILE_SIZE=100;
	
	private boolean obstacle;
	
	public Tile(boolean obstacle,int x,int y){
		this.obstacle=obstacle;	
		
		setWidth(TILE_SIZE);
		setHeight(TILE_SIZE);
		relocate(x,y);
		
		Image image = new Image(getClass().getResourceAsStream("/brickbig.gif"));
		
		setFill(obstacle ? Color.valueOf("#000000"):new ImagePattern(image));		
	}
	
	public boolean getObstacle(){
		return obstacle;
	}
}
