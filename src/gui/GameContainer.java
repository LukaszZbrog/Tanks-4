package gui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GameContainer {
	private Rectangle hpPlayer1, hpPlayer2;
	private VBox vbox;
	private HBox hbox;
	private Pane pane, root;
	private Stage primaryStage;

	Thread threadPlayer1,threadPlayer2;


	private Group mapGroup = new Group();
	private Group bulletGroup = new Group();



	public GameContainer(Stage primaryStage) {
		this.primaryStage = primaryStage;


	}

	public Parent createContent() {
		vbox = new VBox(10);
		vbox.getChildren().clear();
		vbox.setPadding(new Insets(10));

		root = new Pane();
		root.setPrefSize(800, 800);
		root.getChildren().addAll(mapGroup);
		root.getChildren().addAll(bulletGroup);



		vbox.getChildren().add(root);
		createButtons();
		vbox.getChildren().add(addPlayerTitle());
		pane = hpPlayer();
		pane.setTranslateY(-50);
		vbox.getChildren().add(pane);

		return vbox;
	}
	

	
	private void createButtons(){
		Button buttonExit = new MyButton("Zakoncz", "Zakoñcz grê", "#ff3f3f").createButton();
		buttonExit.setTranslateX(650);
		buttonExit.setTranslateY(20);

		buttonExit.setOnAction((ActionEvent event) -> {
			System.exit(1);
		});

		Button buttonSaveGame = new MyButton("Zapisz grê", "Zapisz stan obecnej gry", " #00FF00").createButton();
		buttonSaveGame.setTranslateX(650);
		buttonSaveGame.setTranslateY(50);


		
		vbox.getChildren().add(buttonExit);
		vbox.getChildren().add(buttonSaveGame);
	}
	

	

	


	public void updateHpRectangle(int i, int numberPlayer) {
		if (numberPlayer == 1) {
			pane.getChildren().remove(hpPlayer1);

			hpPlayer1 = new Rectangle(40 * i, 30);
			hpPlayer1.setFill(Color.valueOf("#FF1000"));
			hpPlayer1.setTranslateX(50);
			pane.getChildren().add(hpPlayer1);
		}
		if (numberPlayer == 2) {
			pane.getChildren().remove(hpPlayer2);

			hpPlayer2 = new Rectangle(40 * i, 30);
			hpPlayer2.setFill(Color.valueOf("#FF1000"));
			hpPlayer2.setTranslateX(370);
			pane.getChildren().add(hpPlayer2);
		}
	}

	private Pane hpPlayer() {
		pane = new Pane();

		hpPlayer1.setFill(Color.valueOf("#FF1000"));
		hpPlayer1.setTranslateX(50);
		pane.getChildren().add(hpPlayer1);

		hpPlayer2.setFill(Color.valueOf("#FF1000"));
		hpPlayer2.setTranslateX(370);
		pane.getChildren().add(hpPlayer2);

		return pane;
	}

	private HBox addPlayerTitle() {
		hbox = new HBox();

		Label labelPlayer1 = new Label();
		labelPlayer1.setText("Gracz 1");
		labelPlayer1.setTextFill(Color.web("#000000"));
		labelPlayer1.setFont(new Font("Impact", 22));
		labelPlayer1.setTranslateX(50);

		Label labelPlayer2 = new Label();
		labelPlayer2.setText("Gracz 2");
		labelPlayer2.setTextFill(Color.web("#000000"));
		labelPlayer2.setFont(new Font("Impact", 22));
		labelPlayer2.setTranslateX(300);

		hbox.getChildren().add(labelPlayer1);
		hbox.getChildren().add(labelPlayer2);
		hbox.setTranslateY(-50);
		return hbox;
	}
	

}
