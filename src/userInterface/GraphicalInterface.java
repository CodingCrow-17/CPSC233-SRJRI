package userInterface;


import inputLayer.InputReciever;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import logicLayer.GameMap;
import logicLayer.Tile;
import output.Output;

public class GraphicalInterface extends Application implements Output, InputReciever{
	
	
	private VBox root = new VBox();
	private Group grid = new Group();
	
	

	
	public void begin() {
		System.out.println("begin gui");
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new VBox();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		
		Label label = new Label("hello world");
		root.getChildren().add(label);
		root.getChildren().add(grid);
		

		
		primaryStage.show();
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getInstruction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printStartingMessage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printMap(GameMap gameMap) {
		
		Tile[][] tileMap = gameMap.getTiles();
		for (int i = 0; i < tileMap.length; i++) {
			for (int j = 0; j < tileMap[i].length; j++) {
				int xCoordinate = 20*i;
				int yCoordinate = 20*j;
				Rectangle rectangle = new Rectangle(xCoordinate,yCoordinate, 20,20);
				rectangle.setFill(Color.STEELBLUE);
				rectangle.setStroke(Color.BLANCHEDALMOND);
				grid.getChildren().add(rectangle);
			}
		}
	}

	@Override
	public void printCurrentTurnOwner(String currentOwner) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
