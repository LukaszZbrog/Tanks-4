package gui;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EndStage {
	private Stage primaryStage;
	private StackPane stackPaneEnd;
	private Stage stageEnd;

	public EndStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void createEndStage() {
		stageEnd = new Stage();
		stackPaneEnd = new StackPane();

		addImageBackground();
		addButtons();

		Scene sceneEnd = new Scene(stackPaneEnd, 400, 400);
		stageEnd.setScene(sceneEnd);
		stageEnd.setResizable(false);
		stageEnd.show();
	}

	private void addImageBackground() {
		Image image = new Image(getClass().getResourceAsStream("/faj.jpg"));
		Label label1 = new Label();
		label1.setGraphic(new ImageView(image));
		stackPaneEnd.getChildren().add(label1);
	}

	private void addButtons() {
		Button buttonQuit = new MyButton("Zakoñcz grê", "Zamknij okno", "#ffff00").createButton();
		buttonQuit.setTranslateY(50);
		stackPaneEnd.getChildren().add(buttonQuit);

		buttonQuit.setOnAction((ActionEvent event) -> {
			System.exit(1);
		});

		Button buttonPlayAgain = new MyButton("Menu", "Wróæ do menu", "#F6e7c9").createButton();
		stackPaneEnd.getChildren().add(buttonPlayAgain);

		buttonPlayAgain.setOnAction((ActionEvent) -> {
			MenuContainer menuContainer = new MenuContainer(primaryStage);
			primaryStage.setScene(new Scene(menuContainer.createContainer()));
			primaryStage.centerOnScreen();
			stageEnd.close();
		});
	}
}
