package logicLayer;

public enum AttackOrderType {

	INITIAl, COUNTER, FOLLOW_UP;
	
	public static String getInSentence(AttackOrderType type) {
		String returnedString = "";
		
		switch (type) {
			case INITIAl:
				returnedString = "initiated combat with ";
				break;
			case COUNTER:
				returnedString = "countered against ";
				break;
			case FOLLOW_UP:
				returnedString = "followed up against ";
				break;
		}
		
		return returnedString;
	}
	
}
