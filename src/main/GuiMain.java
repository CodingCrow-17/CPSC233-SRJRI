package main;

import logicLayer.GameMap;
import userInterface.GraphicalInterface;

public class GuiMain {
	public static void main(String[] args) {
		
		GameMap gameMap = StartUpClass.initializeGameMap();
		
		GraphicalInterface gui = new GraphicalInterface();
		
		try {
			gui.begin(gameMap);
		} catch (Exception e) {
			System.out.println("A strange error occured while attempting to boot up the GUI!");
			e.printStackTrace();
		}
	}
}
