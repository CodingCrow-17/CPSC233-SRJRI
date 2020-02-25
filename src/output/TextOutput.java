package output;
import logicLayer.GameMap; // import GameMap


import logicLayer.Tile; //import Tile
public class TextOutput {
	
	public static void main(String[] args) {
		Tile[][] tiles = new Tile[7][7];
		GameMap map = new GameMap(tiles);
		printMap(map);
	}
	
	public static void printMap(GameMap gameMap){
		Tile[][] tiles = gameMap.getTiles();
		for (int a = 0; a<tiles.length; a++) { 
			for (int c = 0; c<3; c++) {
				for (int b = 0; b<tiles[a].length; b++) {
					if (c==0) {
						System.out.print("+---");
					}
					else if(c==1 && a == 2 && b == 5) {
						System.out.print("|nam");
					}
					else if (c==2 && a == 2 && b == 5) {
						System.out.print("|atk");
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