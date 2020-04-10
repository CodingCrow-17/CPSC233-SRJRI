package logicLayer;


public enum TileType{
	
	PLAIN,
	FOREST,
	DESSERT,
	MOUNTAIN,
	RIVER;
	
	public String toString(TileType type) {
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

	public Double getAttBonus(TileType type) {
        Double attBonus= 1.0;
        switch (type) {
            case PLAIN :
            	attBonus = 1.2; 
                break;
            case FOREST :
            	attBonus = 0.9;
                break;
            case DESSERT:
            	attBonus = 1.1;
            case MOUNTAIN :
            	attBonus = 0.8;
            case RIVER :
            	attBonus = 0.7;
        }
        return attBonus;
    }
	public Double getCritAttBonus(TileType type) {
        Double critAttBonus= 1.0;
        switch (type) {
            case PLAIN :
            	 critAttBonus = 1.2; 
                break;
            case FOREST :
            	 critAttBonus = 1.1;
                break;
            case DESSERT:
            	 critAttBonus = 0.9;
            case MOUNTAIN :
            	 critAttBonus = 0.5;
            case RIVER :
            	 critAttBonus = 1.2;
        }
        return  critAttBonus;
    }
	public Double getDefenseMultiplier(TileType type) {
        Double getDefenseBonus= 1.0;
        switch (type) {
            case PLAIN :
            	getDefenseBonus = 1.0; 
                break;
            case FOREST :
            	getDefenseBonus = 1.2;
                break;
            case DESSERT:
            	getDefenseBonus = 0.6;
            case MOUNTAIN :
            	getDefenseBonus = 1.3;
            case RIVER :
            	getDefenseBonus = 1.1;
        }
        return  getDefenseBonus;
	}
	
	}


