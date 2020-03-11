package main;

import java.util.ArrayList;
import java.util.List;
import inputLayer.*;
import javafx.stage.Stage;
import logicLayer.*;
import output.Displayable;
import output.TextOutputPrinter;
import output.UnitDisplayable;
import userInterface.GraphicalInterface;

public class Main {
	public static void main(String[] args) {
		System.out.println("begin main");
		
		GameMap gameMap = StartUpClass.initializeGameMap();
		
		InputReciever input = new TextInputReciever();
		TextOutputPrinter output =  new TextOutputPrinter();
		
		try {
			GraphicalInterface gui = new GraphicalInterface();
			gui.begin();
		} catch (Exception e) {
			System.out.println("A strange error occured while attempting to boot up the GUI!");
			e.printStackTrace();
		}
	}

}
