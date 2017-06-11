package gui;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InfoContainer {
	private StackPane stackPaneInfo;
	
	public Parent createContainer() {
		stackPaneInfo = new StackPane();

		addImageBackground();
		addText();
		
		return stackPaneInfo;
	}
	
	private void addImageBackground(){
		Image imageBackgroud = new Image(getClass().getResourceAsStream("/tlo2.png"));
		Label labelBackground = new Label();
		labelBackground.setGraphic(new ImageView(imageBackgroud));
		stackPaneInfo.getChildren().add(labelBackground);
	}
	
	private void addText(){
		Label labelText = new Label();
		labelText.setText("\tWykonali : \nPawluk Patrycja \nParapura Grzegorz \nZbrog Lukasz \nGrupa I5Y4S1");
		labelText.setTextFill(Color.web("#FF6000"));
		labelText.setFont(new Font("Impact", 40));

		stackPaneInfo.getChildren().add(labelText);
	}
}
