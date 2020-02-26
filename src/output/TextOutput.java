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
						System.out.print("+-----");
					}
					else if(c==1 && tiles[a][b].hasUnit()) {
						String unitName = tiles[a][b].getUnit().getName();
						System.out.print("|" + unitName);
						
						int nameLength = unitName.length();
						
						/* If the unit's name is less than 5 letters long, print out
						   the proper amount of spaces to fill out the tile         */ 
						for (int i = 0; i < (5 - nameLength); i++)
						{
							System.out.print(" ");
						}
					}
					else if (c==2 && tiles[a][b].hasUnit()) {
						int unitAtt = tiles[a][b].getUnit().getStats().getAtt();
						System.out.print("|" + unitAtt); 
						
						/*The following statements print the appropriate amount of spaces
						  after the attack number to fill out the tile                   */
						if (unitAtt < 10)
						{
							System.out.print("    ");
						}
						else if (unitAtt < 100)
						{
							System.out.print("   ");
						}
						else if (unitAtt < 1000)
						{
							System.out.print("  ");
						}
						else if (unitAtt < 10000)
						{
							System.out.print(" ");
						}
					}
					else {
						System.out.print("|     ");
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