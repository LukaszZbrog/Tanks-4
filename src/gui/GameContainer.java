package gui;

import java.util.List;


import database.GetMap;
import database.Map;
import database.Save;
import database.SaveGame;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Player;
import logic.Shoot;
import logic.Tank;

public class GameContainer {
	private Rectangle hpPlayer1, hpPlayer2;
	private VBox vbox;
	private HBox hbox;
	private Pane pane, root;
	private Stage primaryStage;
	private Tile mapTile;
	Player player1 ,player2;
	Thread threadPlayer1,threadPlayer2;
	Tank tankPlayer1,tankPlayer2;

	private Group mapGroup = new Group();
	private Group bulletGroup = new Group();

	private Tile[][] board = new Tile[8][6];

	private int player1StartPositionX;
	private int player1StartPositionY;
	private int player1StartHealth;
	private String player1StartCourse;
	private int player2StartPositionX;
	private int player2StartPositionY;
	private int player2StartHealth;
	private String player2StartCourse;

	public GameContainer(Stage primaryStage) {
		this.primaryStage = primaryStage;

		player1StartPositionX = 0;
		player1StartPositionY = 320;
		player1StartHealth = 4;
		player1StartCourse = "RIGHT";
		player2StartPositionX = 750;
		player2StartPositionY = 320;
		player2StartHealth = 4;
		player2StartCourse = "LEFT";
	}

	public Parent createContent() {
		vbox = new VBox(10);
		vbox.getChildren().clear();
		vbox.setPadding(new Insets(10));

		root = new Pane();
		root.setPrefSize(800, 800);
		root.getChildren().addAll(mapGroup);
		root.getChildren().addAll(bulletGroup);

		createMap();
		addObjectToMap();
		handleKeyboard();

		vbox.getChildren().add(root);
		createButtons();
		vbox.getChildren().add(addPlayerTitle());
		pane = hpPlayer();
		pane.setTranslateY(-50);
		vbox.getChildren().add(pane);

		return vbox;
	}
	
	private void addObjectToMap(){
		tankPlayer1 = new Tank(player1StartPositionX, player1StartPositionY, "RED",player1StartCourse,this);
		root.getChildren().add(tankPlayer1);
		tankPlayer2 = new Tank(player2StartPositionX, player2StartPositionY, "GREEN",player2StartCourse,this);
		root.getChildren().add(tankPlayer2);

		player1 = new Player(tankPlayer1, 1,board);
		player2 = new Player(tankPlayer2, 2,board);
		
		player1.setOpponent(player2);
		player2.setOpponent(player1);
		
		player1.getTank().setTankHealth(player1StartHealth);
		player2.getTank().setTankHealth(player2StartHealth);

		threadPlayer1 = new Thread(player1);
		threadPlayer1.start();
		threadPlayer2 = new Thread(player2);
		threadPlayer2.start();
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

		buttonSaveGame.setOnAction((ActionEvent event) -> {
			saveGame();
		});
		
		vbox.getChildren().add(buttonExit);
		vbox.getChildren().add(buttonSaveGame);
	}
	
	private void createMap(){	
		GetMap getMap = new GetMap();
		List<Map> mapTileList = getMap.getDataList();
		int i = 0, j = 0;

		for (Map iteratorMap : mapTileList) {
			mapTile = new Tile(iteratorMap.isObstacle(), iteratorMap.getPositionX(), iteratorMap.getPositionY());
			board[i][j] = mapTile;
			j++;
			if (j == 6) {
				j = 0;
				i++;
			}
			mapGroup.getChildren().add(mapTile);
		}
	}
	
	private  void handleKeyboard(){
		vbox.setOnKeyPressed(e -> {
			if (KeyCode.DOWN == e.getCode() || KeyCode.UP == e.getCode() || KeyCode.RIGHT == e.getCode()
					|| KeyCode.LEFT == e.getCode()) {
				player2.keyboard(e.getCode());
			}
			if (KeyCode.S == e.getCode() || KeyCode.W == e.getCode() || KeyCode.D == e.getCode()
					|| KeyCode.A == e.getCode()) {
				player1.keyboard(e.getCode());
			}
			if (KeyCode.G == e.getCode()) {
				Shoot shootTank = new Shoot(player1, player2, board, bulletGroup);
				Thread shootThread = new Thread(shootTank);
				shootThread.start();
				bulletGroup.getChildren().add(shootTank.getBullet());
			}
			if (KeyCode.ENTER == e.getCode()) {
				Shoot shootTank = new Shoot(player2, player1, board, bulletGroup);
				Thread shootThread = new Thread(shootTank);
				shootThread.start();
				bulletGroup.getChildren().add(shootTank.getBullet());
			}
		});
	}
	
	private void saveGame(){
		SaveGame saveGame = new SaveGame();
		saveGame.saveActuallyGame(player1.getTank().getX(), player1.getTank().getY(), player1.getTank().getTankHealth(),
				player1.getTank().getCourse(), player2.getTank().getX(), player2.getTank().getY(),
				player2.getTank().getTankHealth(), player2.getTank().getCourse());
	}

	public void removeTank(Tank tankToRemove) {
		root.getChildren().remove(tankToRemove);
		tankToRemove = null;
		showEndStage();
	}
	
	private void showEndStage(){
		EndStage endStage = new EndStage(primaryStage);
		endStage.createEndStage();
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
		hpPlayer1 = new Rectangle(player1StartHealth*40, 30);
		hpPlayer2 = new Rectangle(player2StartHealth*40, 30);

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
	
	public void loadStartParametrs(Save lastSave){
		player1StartPositionX=lastSave.getPlayer1PositionX();
		player1StartPositionY=lastSave.getPlayer1PositionY();
		player1StartHealth=lastSave.getPlayer1Health();
		player1StartCourse=lastSave.getPlayer1Course();
		player2StartPositionX=lastSave.getPlayer2PositionX();
		player2StartPositionY=lastSave.getPlayer2PositionY();
		player2StartHealth=lastSave.getPlayer2Health();
		player2StartCourse=lastSave.getPlayer2Course();
	}
}
