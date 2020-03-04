package logicLayer;

public enum TileType {
	
	PLAIN, 
	FOREST;
	
	public String toString(TileType type) {
		String stringType = "";
		switch (type) {
			case PLAIN :
				stringType = "plain";
				break;
			case FOREST :
				stringType = "forest";
				break;
		}
		return stringType;
	}
}
