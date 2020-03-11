package userInterface;

import inputLayer.InputReciever;
import inputLayer.TextInputReciever;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
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

public class GraphicalInterface extends Application{
	
	
	private VBox root = new VBox();
	private Grid grid;
	private Scene scene;

	public void begin() {
		this.launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start");
		GameMap map = StartUpClass.initializeGameMap();
		Coordinator coordinator = new Coordinator(map, null, null);
		
		root = new VBox();
		root.setPrefHeight(400);
		root.setPrefWidth(400);
		
		Label label = new Label("Game: SRJRI");
		
		grid = new Grid(map.getTiles(), 200, 200);
		
		root.getChildren().add(label);
		root.getChildren().add(grid);
		scene = new Scene(root);
		scene.setOnKeyTyped(new HandleKeyPresses(grid, coordinator));
		primaryStage.setScene(scene);
		
		primaryStage.show();
		TextInputReciever input = new TextInputReciever();
		
	}
}
