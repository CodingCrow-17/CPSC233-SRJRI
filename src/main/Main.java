package main;

import java.util.ArrayList;
import java.util.List;
import inputLayer.*;

import logicLayer.*;

public class Main {
	public static void main(String[] args) {
		GameMap gameMap = initializeGameMap();
		InputReciever input = new TextInputReciever();
		Coordinator coordinator = new Coordinator(gameMap,input);
		coordinator.startGameLoop();
	}
	
	private static GameMap initializeGameMap() {
		
		//sets up the players for this map
		Owner player = new Owner(OwnerType.PLAYER);
		Owner enemy = new Owner(OwnerType.ENEMY);
		List<Owner> owners = new ArrayList<Owner>();
		owners.add(player);
		owners.add(enemy);
		
		//creates tiles and puts them in gameMap
		Tile[][] tiles = new Tile[3][5];
		for(int i = 0; i < tiles.length; i++) {
			for (int j = 0; j<tiles[i].length; j++) {
				tiles[i][j] = new Tile(i,j);
			}
		}
		GameMap gameMap = new GameMap(tiles, owners);
		
		//creates units and populates gameMap
		Stats unitAStats = new Stats(20, 10,5,2); //hp, att, def, mov
		Tile unitATile = gameMap.getTileAtCoordinates(0, 0);
		Unit unitA = new Unit("Geoff", unitAStats, player, unitATile);
		unitATile.setUnit(unitA);
		
		Stats unitBStats = new Stats(25, 7,8,2); //hp, att, def, mov
		Tile unitBTile = gameMap.getTileAtCoordinates(0, 1);
		Unit unitB = new Unit("Henry", unitBStats, player, unitBTile);
		unitBTile.setUnit(unitB);
		
		Stats unitCStats = new Stats(16, 9,6,2); //hp, att, def, mov
		Tile unitCTile = gameMap.getTileAtCoordinates(4, 2);
		Unit unitC = new Unit("Lewis", unitCStats, enemy, unitCTile);
		unitCTile.setUnit(unitC);
		
		player.addUnit(unitA);
		player.addUnit(unitB);
		enemy.addUnit(unitC);
		
		return gameMap;
	}
}
