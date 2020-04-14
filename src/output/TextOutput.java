package output;
import logicLayer.GameMap; // import GameMap
import logicLayer.Unit; // import Unit
import logicLayer.Stats; // import Stats

import logicLayer.Tile; //import Tile
public class TextOutput {
	
	public static void main(String[] args) {
		Tile[][] tiles = new Tile[7][7];
		GameMap map = new GameMap(tiles);
		printMap(map);
	
	//tes tes tes tes tse te
	public static void printMap(GameMap gameMap){
		Tile[][] tiles = gameMap.getTiles();
		for (int a = 0; a<tiles.length; a++) { 
			for (int c = 0; c<3; c++) {
				for (int b = 0; b<tiles[a].length; b++) {
					if (c==0) {
						System.out.print("+---");
					}
					else if(c==1 && tiles[a][b].hasUnit()) {
						System.out.print("|" + tiles[a][b].getUnit().getName());
					}
					else if (c==2 && tiles[a][b].hasUnit()) {
						System.out.print("|" + tiles[a][b].getUnit().getStats().getAtt()); // Stats and unit need some new constructors for this to work
					}
					else {
						System.out.print("|   ");
					}
				}
				System.out.println();
			}
		}

		

//		System.out.println(" --- --- --- --- --- --- ---");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println(" --- --- --- --- --- --- ---");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println(" --- --- --- --- --- --- ---");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println(" --- --- --- --- --- --- ---");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println(" --- --- --- --- --- --- ---");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println(" --- --- --- --- --- --- ---");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println(" --- --- --- --- --- --- ---");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println("|   |   |   |   |   |   |   |");
//		System.out.println(" --- --- --- --- --- --- ---");
	}
}