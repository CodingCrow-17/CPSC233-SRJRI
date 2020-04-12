package userInterface;

import customExceptions.InvalidInputException;

public enum InstructionType {

	SELECT,
	DISPLAY_UNIT_INFO,
	DESELECT,
	MOVE_TO,
	ATTACK,
	WAIT,
	INVALID,
	END_TURN,
	FIND_MOVE_TILES,
	FIND_ATTACK_TILES,
	CANCEL,
	DISPLAY_ATTACK_FORECAST;
	
	public static InstructionType getTypeFromString(String rawString) throws InvalidInputException {
		InstructionType.values();
		int counter = 0;
		InstructionType returnedType = null;
		for (InstructionType type: InstructionType.values()) {
			if(rawString.toUpperCase().replaceAll(" ", "_").replaceAll("\\s", "").equals(type.toString().toUpperCase())) {
				returnedType = type;
				break;
			}
		}
		if (returnedType == null) {
			throw new InvalidInputException("Invalid command selected");
		}
		return returnedType;
	}
	
	public static boolean getNeedsPosition(InstructionType type) {
		if (type.equals(SELECT)|| type.equals(ATTACK) || type.equals(MOVE_TO) || type.equals(DISPLAY_ATTACK_FORECAST)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
