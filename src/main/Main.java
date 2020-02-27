package main;

import java.util.ArrayList;
import java.util.List;

import logicLayer.*;

public class Main {
	public static void main(String[] args) {
		GameMap gameMap = initializeGameMap();
		Coordinator coordinator = new Coordinator(gameMap);
		coordinator.startGameLoop();
	}
	
	private static GameMap initializeGameMap() {
		Owner player = new Owner(OwnerType.PLAYER);
		Owner enemey = new Owner(OwnerType.ENEMY);
		List<Owner> owners = new ArrayList<Owner>();
		owners.add(player);
		owners.add(enemey);
		Tile[][] tiles = new Tile[5][5];
		for(int i = 0; i < tiles.length; i++) {
			for (int j = 0; j<tiles[i].length; j++) {
				tiles[i][j] = new Tile(i,j);
			}
		}
		GameMap gameMap = new GameMap(tiles, owners);
		
		Stats unitAStats = new Stats(20, 10,5,2); //hp, att, def, mov
		Tile unitATile = gameMap.getTileAtCoordinates(0, 0);
		Unit unitA = new Unit("Geoff", unitAStats, player, unitATile);
		unitATile.setUnit(unitA);
		
		Stats unitBStats = new Stats(25, 7,8,2); //hp, att, def, mov
		Tile unitBTile = gameMap.getTileAtCoordinates(3, 0);
		Unit unitB = new Unit("Henry", unitBStats, player, unitBTile);
		unitBTile.setUnit(unitB);
		
		Stats unitCStats = new Stats(16, 9,6,2); //hp, att, def, mov
		Tile unitCTile = gameMap.getTileAtCoordinates(4, 0);
		Unit unitC = new Unit("Henry", unitCStats, player, unitCTile);
		unitCTile.setUnit(unitC);
		
		return gameMap;
	}
}
