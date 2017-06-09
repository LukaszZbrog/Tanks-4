package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class MyButton extends Button {
	private String title,toolTipText,colorButton;
	
	public MyButton(String title, String toolTipText, String colorButton){
		this.title=title;
		this.toolTipText=toolTipText;
		this.colorButton=colorButton;
	}
	
	public  Button createButton() {
		Button button = new Button(title);
		Tooltip tooltip = new Tooltip(toolTipText);
		Tooltip.install(button, tooltip);
		button.setStyle("-fx-font: 22 arial; -fx-base: " + colorButton);
		return button;
	}	
	
	public void setTranlationX(int x){
		
	}
}
