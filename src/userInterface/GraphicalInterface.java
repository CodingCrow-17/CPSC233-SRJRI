package userInterface;

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
import logicLayer.GameMap;
import logicLayer.OwnerType;
import logicLayer.Tile;
import main.Coordinator;
import main.StartUpClass;
import output.Output;

public class GraphicalInterface extends Application implements InputReciever, Output{
	
	
	private VBox root;
	private HBox gameBoardDisplay;
	private Grid grid;
	private CommandSelection commandSelection;
	private Scene scene;
	private HandleKeyPresses handleKeyPresses;
	
	private Instructions currentInstructions = null;

	public void begin() {
		this.launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start");
		GameMap map = StartUpClass.initializeGameMap();
		
		
		Coordinator coordinator = new Coordinator(map, this, this);
		
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
		handleKeyPresses = new HandleKeyPresses(grid,commandSelection,coordinator, currentInstructions);
		scene.setOnKeyTyped(handleKeyPresses);
		primaryStage.setScene(scene);
		
		primaryStage.show();
		TextInputReciever input = new TextInputReciever();
		
	}

	@Override
	public void printMap(GameMap gameMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printCurrentTurnOwner(String currentOwner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Instructions getNextInstruction() {
		while (currentInstructions == null) {
			
		}
		Instructions temp = new Instructions(currentInstructions);
		currentInstructions = null;
		return temp;
	}

	@Override
	public void printStartingMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}
