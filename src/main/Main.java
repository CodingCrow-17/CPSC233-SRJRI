package main;

import logicLayer.GameMap;
import userInterface.GraphicalInterface;

public class Main {
	public static void main(String[] args) {
		System.out.println("begin main");
		
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
