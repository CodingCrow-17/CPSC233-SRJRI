package logicLayer;
public enum TileType{
	
	PLAIN,
	FOREST,
	DESSERT,
	MOUNTAIN,
	RIVER;
	
	public static String toString(TileType type) {
		String tileTypeModifier = "";
		switch (type) {
			case PLAIN :
				tileTypeModifier = "Plain";
				break;
			case FOREST :
				tileTypeModifier = "Forest";
				break;
			case DESSERT :
				tileTypeModifier = "Dessert";
				break;
			case MOUNTAIN :
				tileTypeModifier = "Mountain";
				break;
			case RIVER :
				tileTypeModifier = "River";
				break;
		}
		return tileTypeModifier;
	}

	public static Double getAttackBonus(TileType type) {
        Double attBonus= 1.0;
        switch (type) {
            case PLAIN :
            	attBonus = 1.0; 
                break;
            case FOREST :
            	attBonus = 0.9;
                break;
            case DESSERT:
            	attBonus = 1.1;
            case MOUNTAIN :
            	attBonus = 0.8;
            	break;
            case RIVER :
            	attBonus = 0.75;
            	break;
        }
        return attBonus;
    }
	
	public static Double getCritAttBonus(TileType type) {
        Double critAttBonus= 1.0;
        switch (type) {
            case PLAIN :
            	critAttBonus = 1.0; 
                break;
            case FOREST :
            	critAttBonus = 1.0;
                break;
            case DESSERT:
            	critAttBonus = 1.0;
            	break;
            case MOUNTAIN :
            	critAttBonus = 1.1;
            	break;
            case RIVER :
            	critAttBonus = 0.9;
            	break;
        }
        return  critAttBonus;
    }
	
	public static Double getHitChanceBonus(TileType type) {
        Double hitChanceBonus= 1.0;
        switch (type) {
            case PLAIN :
            	hitChanceBonus = 1.0; 
            	break;
            case FOREST :
            	hitChanceBonus = 1.0;
                break;
            case DESSERT:
            	hitChanceBonus = 0.85;
            	break;
            case MOUNTAIN :
            	hitChanceBonus = 1.0;
            	break;
            case RIVER :
            	hitChanceBonus = 1.0;
            	break;
        }
        return  hitChanceBonus;
    }
	
	public static Double getEvadeRateBonus(TileType type) {
        Double evadeBonus= 1.0;
        switch (type) {
            case PLAIN :
            	evadeBonus = 1.0; 
                break;
            case FOREST :
            	evadeBonus = 1.3;
                break;
            case DESSERT:
            	evadeBonus = 0.9;
            	break;
            case MOUNTAIN :
            	evadeBonus = 0.9;
            	break;
            case RIVER :
            	evadeBonus = 0.75;
            	break;
        }
        return  evadeBonus;
    }
	
	public static Double getDefenseBonus(TileType type) {
        Double getDefenseBonus= 1.0;
        switch (type) {
            case PLAIN :
            	getDefenseBonus = 1.0; 
                break;
            case FOREST :
            	getDefenseBonus = 1.2;
                break;
            case DESSERT:
            	getDefenseBonus = 0.75;
            	break;
            case MOUNTAIN :
            	getDefenseBonus = 1.2;
            	break;
            case RIVER :
            	getDefenseBonus = 1.0;
            	break;
        }
        return  getDefenseBonus;
	}
}