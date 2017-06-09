package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		MenuContainer menuContainer=new MenuContainer(primaryStage);
		scene = new Scene(menuContainer.createContainer(), 800, 500);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Czo³gi");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
