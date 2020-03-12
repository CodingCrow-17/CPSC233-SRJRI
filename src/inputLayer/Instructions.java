package inputLayer;

import logicLayer.Position;

public class Instructions {

	private InstructionType type;
	private Position position;
	
	public Instructions(InstructionType type, Position position) {
		this.position = position;
		this.type = type;
	}
	
}
