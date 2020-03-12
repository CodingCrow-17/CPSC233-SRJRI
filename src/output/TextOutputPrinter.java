package output;
import logicLayer.GameMap; // import GameMap
import logicLayer.Tile; //import Tile
import logicLayer.BattleInstance; //import BattleInstance
import logicLayer.BattleForecast; //import BattleForecast

public class TextOutputPrinter implements Output {
	
	final int CELL_WIDTH = 15;	
	final int CELL_HEIGHT = 8;
	public void printMap(GameMap gameMap)
	{
		Tile[][] tiles = gameMap.getTiles();
		printColumnIndicators(tiles[0]);
		// Loop through each row
		for (int rowInBoard = 0; rowInBoard<tiles.length; rowInBoard++) 
		{ 
			// Loop through the height of a tile; each tile is CELL_HEIGHT spaces tall
			for (int rowOfText = 0; rowOfText<CELL_HEIGHT; rowOfText++) 
			{
				// Halfway through each row, print the row number
				if (rowOfText == CELL_HEIGHT / 2) {
					System.out.print(rowInBoard);
				}
				else {
					System.out.print(" ");
				}
				
				// Loop through each column
				for (int columnInBoard = 0; columnInBoard<tiles[rowInBoard].length; columnInBoard++) 
				{
					String content = "";
					if (rowOfText==0) 
					{
						content = "+";
						content = padTextWithChar(content, '-');
					}
					
					// On the second line of the tile, print unit name if tile is occupied
					else if(rowOfText==1 && tiles[rowInBoard][columnInBoard].hasUnit()) 
					{
						String unitName = tiles[rowInBoard][columnInBoard].getUnit().getName();
						content = "|" + "name: " + unitName;
						content = padTextWithChar(content, ' ');
					}
					
					// On the third line of the tile, print unit hp stat if tile is occupied
					else if (rowOfText==2 && tiles[rowInBoard][columnInBoard].hasUnit()) 
					{
						String unitHp = tiles[rowInBoard][columnInBoard].getUnit().currentHpToString();
						content = "|" + unitHp; 
						content = padTextWithChar(content, ' ');
					}
					
					else if (rowOfText==3 && tiles[rowInBoard][columnInBoard].hasUnit()) 
					{
						String unitAtt = tiles[rowInBoard][columnInBoard].getUnit().getStats().getAtt().toString();
						content = "|" + unitAtt; 
						content = padTextWithChar(content, ' ');
					}
					else if (rowOfText==4 && tiles[rowInBoard][columnInBoard].hasUnit()) 
					{
						String unitDef = tiles[rowInBoard][columnInBoard].getUnit().getStats().getDef().toString();
						content = "|" + unitDef; 
						content = padTextWithChar(content, ' ');
					}
					else if (rowOfText==5 && tiles[rowInBoard][columnInBoard].hasUnit()) 
					{
						String unitMov = tiles[rowInBoard][columnInBoard].getUnit().getStats().getMov().toString();
						content = "|" + unitMov; 
						content = padTextWithChar(content, ' ');
					}
					else if (rowOfText==6 && tiles[rowInBoard][columnInBoard].hasUnit()) {
						String unitOwner = tiles[rowInBoard][columnInBoard].getUnit().getOwner().toString();
						content = "|" + unitOwner;
						content = padTextWithChar(content, ' ');
					}
					else if (rowOfText==7 && tiles[rowInBoard][columnInBoard].hasUnit()) {
						if (tiles[rowInBoard][columnInBoard].getUnit().getHasMoved() == false)
						{
							content = "|" + "READY";
						}
						else
						{
							content = "|" + "MOVED";
						}
						content = padTextWithChar(content, ' ');
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
	
	public void printBattleForecast(BattleForecast bf) {
		System.out.println(bf.getUserUnit().getName() + ":");
		if (bf.willHitTwice(bf.getUserUnit(), bf.getEnemyUnit())) {
			System.out.println("Atk: " + bf.getAttForecastDamage() + " x2");
		}
		else {
			System.out.println("Atk: " + bf.getAttForecastDamage());
		}
		System.out.println("Hit: " + bf.getAttForecastPercent());
		System.out.println("Crit: " + bf.getAttCritPercent());
		System.out.println("");
		
		System.out.println(bf.getEnemyUnit().getName() + ":");
		if (bf.willHitTwice(bf.getEnemyUnit(), bf.getUserUnit())) {
			System.out.println("Atk: " + bf.getDefForecastDamage() + " x2");
		}
		else {
			System.out.println("Atk: " + bf.getDefForecastDamage());
		}
		System.out.println("Hit: " + bf.getDefForecastPercent());
		System.out.println("Crit: " + bf.getDefCritPercent());
	}
	public void printBattleInstance(BattleInstance bi) {
		if (bi.getAttHasHit()) {
			if (bi.getAttCritActivated()) {
				System.out.println("CRITICAL!");
			}
			if (bi.getAttHasDoneDamage()) {
				System.out.println(bi.getBattleForecast().getUserUnit().getName() + " has done " + 
					       bi.getAttDamageDone() + " to " + bi.getBattleForecast().getEnemyUnit().getName() + ".");
			}
			else {
				System.out.println(bi.getBattleForecast().getUserUnit().getName() + " did no damage to " +
						           bi.getBattleForecast().getEnemyUnit().getName());
			}
		}
		else {
			System.out.println(bi.getBattleForecast().getUserUnit().getName() + " missed!");
		}
		
		System.out.println("");
		
		if (!bi.getBattleForecast().getEnemyUnit().isDead()) {
			if (bi.getDefHasHit()) {
				if (bi.getDefCritActivated()) {
					System.out.println("CRITICAL!");
				}
				if (bi.getAttHasDoneDamage()) {
					System.out.println(bi.getBattleForecast().getEnemyUnit().getName() + " has done " + 
						       bi.getDefDamageDone() + " to " + bi.getBattleForecast().getUserUnit().getName() + ".");
				}
				else {
					System.out.println(bi.getBattleForecast().getEnemyUnit().getName() + " did no damage to " +
							           bi.getBattleForecast().getUserUnit().getName());
				}
			}
			else {
				System.out.println(bi.getBattleForecast().getEnemyUnit().getName() + " missed!");
			}
			if (bi.getBattleForecast().getUserUnit().isDead()) {
				System.out.println(bi.getBattleForecast().getUserUnit().getName() + " is dead!");
			}
		}
		else {
			System.out.println(bi.getBattleForecast().getEnemyUnit().getName() + " is dead!");
		}
	}
	
	private String padTextWithChar(String text, char character) {
		for (int i = text.length(); i< CELL_WIDTH; i++) {
			text = text + character;
		}
		return text;
	}
	
	private void printColumnIndicators(Tile[] columnList) {
		String content = " ";
		for (int column = 0; column < columnList.length; column++) {
			String indicator = "";
			for (int spaces = 0; spaces < CELL_WIDTH/2; spaces++) {
				indicator = indicator + " ";
			}
			indicator = indicator + column;
			indicator = padTextWithChar(indicator, ' ');
			content = content + indicator;
		}
		System.out.println(content);
	}

	
}
