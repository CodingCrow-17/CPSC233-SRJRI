package main;

import java.util.ArrayList;
import java.util.List;

import logicLayer.GameMap;
import logicLayer.Owner;
import logicLayer.OwnerType;
import logicLayer.Stat;
import logicLayer.StatType;
import logicLayer.Stats;
import logicLayer.Tile;
import logicLayer.TileType;
import logicLayer.Unit;
import output.UnitDisplayable;

public class StartUpClass {
	
	public static GameMap initializeGameMap() {	
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
				tiles[i][j] = new Tile(i,j, TileType.PLAIN);
			}
		}
		GameMap gameMap = new GameMap(tiles, owners);
		
		//creates units and populates gameMap
		Stat unitAHp = new Stat(StatType.HP, 20, .35, 100);
		Stat unitAAtt = new Stat(StatType.ATTACK, 10, .35, 100);
		Stat unitASpd = new Stat(StatType.SPEED, 10, .35, 100);
		Stat unitADex = new Stat(StatType.DEXTERITY, 10, .35, 100);
		Stat unitADef = new Stat(StatType.DEFENSE, 5, .35, 100);
		Stat unitAMov= new Stat(StatType.MOVEMENT, 4, 0, 100);
		
		Stats unitAStats = new Stats(unitAHp, unitAAtt,unitASpd,unitADex,unitADef,unitAMov); //hp, att, spd, dex,def, mov
		
		UnitDisplayable unitTextDisplayable = null;
		
		Tile unitATile = gameMap.getTileAtCoordinates(0, 0);
		Unit unitA = new Unit("Geoff", "I'm afraid your story ends here!",unitAStats, player, unitATile,unitTextDisplayable);
		unitATile.setUnit(unitA);
		
		Stat unitBHp = new Stat(StatType.HP, 25, .45, 100);
		Stat unitBAtt = new Stat(StatType.ATTACK, 7, .30, 100);
		Stat unitBSpd = new Stat(StatType.SPEED, 10, .35, 100);
		Stat unitBDex = new Stat(StatType.DEXTERITY, 10, .35, 100);
		Stat unitBDef = new Stat(StatType.DEFENSE, 8, .35, 100);
		Stat unitBMov= new Stat(StatType.MOVEMENT, 4, 0, 100);
		
		Stats unitBStats = new Stats(unitBHp, unitBAtt,unitBSpd,unitBDex,unitBDef,unitBMov); //hp, att, spd, dex,def, mov
		Tile unitBTile = gameMap.getTileAtCoordinates(0, 1);
		Unit unitB = new Unit("Henry", "You're wide open!",unitBStats, player, unitBTile,unitTextDisplayable);
		unitBTile.setUnit(unitB);
		
		Stat unitCHp = new Stat(StatType.HP, 16, 0, 100);
		Stat unitCAtt = new Stat(StatType.ATTACK, 9, 0, 100);
		Stat unitCSpd = new Stat(StatType.SPEED, 5, 0, 100);
		Stat unitCDex = new Stat(StatType.DEXTERITY,5, 0, 100);
		Stat unitCDef = new Stat(StatType.DEFENSE, 6, 0, 100);
		Stat unitCMov= new Stat(StatType.MOVEMENT, 2, 0, 100);
		
		Stats unitCStats = new Stats(unitCHp, unitCAtt,unitCSpd,unitCDex,unitCDef,unitCMov); //hp, att, spd, dex,def, mov
		Tile unitCTile = gameMap.getTileAtCoordinates(4, 2);
		Unit unitC = new Unit("Rogue","DIE!",unitCStats, enemy, unitCTile,unitTextDisplayable);
		unitCTile.setUnit(unitC);
		
		player.addUnit(unitA);
		player.addUnit(unitB);
		enemy.addUnit(unitC);
		
		return gameMap;
	}
}