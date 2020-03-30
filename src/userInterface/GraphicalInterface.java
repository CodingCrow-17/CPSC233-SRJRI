package userInterface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logicLayer.GameMap;
import main.StartUpClass;

public class GraphicalInterface extends Application{

	private GuiLogicCoordinator coordinator;
	private VBox root;
	private HBox gameBoardDisplay;
	private Grid grid;
	private Scene scene;
	private HandleKeyPresses handleKeyPresses;
	private InformationDisplay informationDisplay;
	
	public void begin() {
		this.launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GameMap gameMap = StartUpClass.initializeGameMap();
		this.coordinator = new GuiLogicCoordinator(gameMap);
		
		root = new VBox();
		root.setPrefHeight(400);
		root.setPrefWidth(400);
		
		gameBoardDisplay = new HBox();
		gameBoardDisplay.setPrefHeight(200);
		gameBoardDisplay.setPrefWidth(200);
		
		grid = new Grid(gameMap.getTiles(), 200, 200);
		gameBoardDisplay.getChildren().addAll(grid);
		
		informationDisplay = new InformationDisplay();
		root.getChildren().add(gameBoardDisplay);
		root.getChildren().add(informationDisplay);
		scene = new Scene(root);
		handleKeyPresses = new HandleKeyPresses(this.grid, this.informationDisplay,this.coordinator);
		scene.setOnKeyTyped(handleKeyPresses);
		primaryStage.setScene(scene);
		primaryStage.setTitle("SJRJI game project");
		primaryStage.show();
	}
}
