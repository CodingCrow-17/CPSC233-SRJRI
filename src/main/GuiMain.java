package main;

import userInterface.graphicalUserInterface.GraphicalInterface;
import userInterface.graphicalUserInterface.GuiLogicCoordinator;
import logicLayer.GameMap;

public class GuiMain {
	public static void main(String[] args) {
		
		GraphicalInterface gui = new GraphicalInterface();
		
		try {
			gui.begin();
		} catch (Exception e) {
			System.out.println("A strange error occured while attempting to boot up the GUI!");
			e.printStackTrace();
		}
	}
}
