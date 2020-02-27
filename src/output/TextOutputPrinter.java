package output;
import logicLayer.GameMap; // import GameMap

import logicLayer.Tile; //import Tile
public class TextOutputPrinter {
	
	final int CELL_WIDTH = 15;	
	final int CELL_HEIGHT = 8;
	public void printMap(GameMap gameMap)
	{
		Tile[][] tiles = gameMap.getTiles();
		// Loop through each row
		for (int a = 0; a<tiles.length; a++) 
		{ 
			// Loop through the height of a tile; each tile is CELL_HEIGHT spaces tall
			for (int c = 0; c<CELL_HEIGHT; c++) 
			{
				// Loop through each column
				for (int b = 0; b<tiles[a].length; b++) 
				{
					String content = "";
					if (c==0) 
					{
						content = "+";
						content = padTextWithChar(content, '-');
					}
					
					// On the second line of the tile, print unit name if tile is occupied
					else if(c==1 && tiles[a][b].hasUnit()) 
					{
						String unitName = tiles[a][b].getUnit().getName();
						content = "|" + "name: " + unitName;
						content = padTextWithChar(content, ' ');
					}
					
					// On the third line of the tile, print unit hp stat if tile is occupied
					else if (c==2 && tiles[a][b].hasUnit()) 
					{
						String unitHp = String.valueOf(tiles[a][b].getUnit().getStats().getHp());
						content = "|" + "hp: " + unitHp; 
						content = padTextWithChar(content, ' ');
					}
					
					else if (c==3 && tiles[a][b].hasUnit()) 
					{
						String unitAtt = String.valueOf(tiles[a][b].getUnit().getStats().getAtt());
						content = "|" + "att: " + unitAtt; 
						content = padTextWithChar(content, ' ');
					}
					else if (c==4 && tiles[a][b].hasUnit()) 
					{
						String unitDef = String.valueOf(tiles[a][b].getUnit().getStats().getDef());
						content = "|" + "def: " + unitDef; 
						content = padTextWithChar(content, ' ');
					}
					else if (c==5 && tiles[a][b].hasUnit()) 
					{
						String unitMov = String.valueOf(tiles[a][b].getUnit().getStats().getMov());
						content = "|" + "mov: " + unitMov; 
						content = padTextWithChar(content, ' ');
					}
					else if (c==6 && tiles[a][b].hasUnit()) {
						String unitOwner = tiles[a][b].getUnit().getOwner().getType().toString();
						content = "|" + "own: " + unitOwner;
						content = padTextWithChar(content, ' ');
					}
					else if (c==7 && tiles[a][b].hasUnit()) {
						if (tiles[a][b].getUnit().getHasMoved())
						{
							content = "|" + "READY";
						}
						else
						{
							content = "|" + "MOVED";
						}
					}
					else 
					{
						content = "|"; 
						content = padTextWithChar(content, ' ');
					}
					System.out.print(content);
				}
				System.out.println();
			}
		}
	}
	
	public void printCurrentTurnOwner(String currentOwner) {
		System.out.println("It is the "+ currentOwner + " turn!");
	}
	
	private String padTextWithChar(String text, char character) {
		for (int i = text.length(); i< CELL_WIDTH; i++) {
			text = text + character;
		}
		return text;
	}
	
}
