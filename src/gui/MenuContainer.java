package gui;




import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenuContainer {
	private Stage primaryStage;
	private StackPane stackPane;
	
	public MenuContainer(Stage primaryStage){
		this.primaryStage=primaryStage;
	}
	
	public StackPane createContainer(){
		stackPane = new StackPane();

		addImageBackground();
		addButtons();
		
		return stackPane;
	}
	
	private void addImageBackground(){
		Image imageStart = new Image(getClass().getResourceAsStream("/tlo.png"));
		Label labelStart = new Label();
		labelStart.setGraphic(new ImageView(imageStart));
		stackPane.getChildren().add(labelStart);
	}
	
	private void addButtons(){
		Button buttonStart= new MyButton("Start", "Zacznij grê", "#b6e7c9").createButton();
		buttonStart.setTranslateY(-50);
		stackPane.getChildren().add(buttonStart);

		Button buttonQuit=new MyButton("Zakoncz", "Zakoñcz grê", "#ff3f3f").createButton();
		buttonQuit.setTranslateY(100);
		stackPane.getChildren().add(buttonQuit);

		buttonQuit.setOnAction((ActionEvent event) -> {
			System.exit(1);
		});
		
		Button buttonLoadGame=new MyButton("Wczytaj grê", "Wczytaj ostatni¹ grê", "#FFFF00").createButton();
		stackPane.getChildren().add(buttonLoadGame);
		

		
		Button buttonInfo= new MyButton("Informacje", "Zobacz wiêcej informacji", "#FFFF00").createButton();
		buttonInfo.setTranslateY(50);
		stackPane.getChildren().add(buttonInfo);

		
		

	}
	
	
}
