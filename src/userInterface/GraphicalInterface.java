package userInterface;

import java.util.List;

import inputLayer.InputReciever;
import inputLayer.Instructions;
import inputLayer.TextInputReciever;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logicLayer.GameLogic;
import logicLayer.GameMap;
import logicLayer.Position;
import main.Coordinator;
import main.StartUpClass;
import output.Output;

public class GraphicalInterface extends Application{
	
	private GameMap gameMap;
	private GameLogic gameLogic;
	private VBox root;
	private HBox gameBoardDisplay;
	private Grid grid;
	private CommandSelection commandSelection;
	private Scene scene;
	private HandleKeyPresses handleKeyPresses;
	
	
	public void begin(GameMap gameMap) {
		this.gameMap = gameMap;
		this.gameLogic = new GameLogic(gameMap);
		this.launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start");
		GameMap map = StartUpClass.initializeGameMap();
		this.gameMap = map;
		this.gameLogic = new GameLogic(map);
		
		root = new VBox();
		root.setPrefHeight(400);
		root.setPrefWidth(400);
		
		Label label = new Label("Game: SRJRI");
		
		gameBoardDisplay = new HBox();
		gameBoardDisplay.setPrefHeight(220);
		gameBoardDisplay.setPrefWidth(380);
		
		grid = new Grid(map.getTiles(), 200, 200);
		commandSelection = new CommandSelection(50,80);
		gameBoardDisplay.getChildren().addAll(grid, commandSelection);
		
		
		root.getChildren().add(label);
		root.getChildren().add(gameBoardDisplay);
		scene = new Scene(root);
		handleKeyPresses = new HandleKeyPresses(grid,commandSelection, this);
		scene.setOnKeyTyped(handleKeyPresses);
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	
	public boolean performSelectToCommand(Position position) { // TODO: returns a boolean until we make error codes!
		gameLogic.selectUnitAtPosition(position);
		return gameLogic.hasSelectedUnit();
	}
	
	public boolean retrieveValidTilesToMoveTo() {
		List<Position> positions = gameLogic.findValidTileToMoveToPositions();
		grid.highlightMoveTiles(positions);
		return positions.isEmpty() == false;
	}
	
	public boolean performMoveToTileCommand(Position position) {
		return true;
	}
	
}
