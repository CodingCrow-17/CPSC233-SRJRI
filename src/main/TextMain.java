package main;

import java.util.ArrayList;
import java.util.List;
import inputLayer.*;

import logicLayer.*;

public class TextMain {
	public static void main(String[] args) {
		GameMap gameMap = StartUpClass.initializeGameMap();
		InputReciever input = new TextInputReciever();
		Coordinator coordinator = new Coordinator(gameMap,input, null);
		coordinator.startGameLoop();
	}
}

