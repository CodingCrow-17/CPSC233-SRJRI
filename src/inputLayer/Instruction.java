package inputLayer;

import logicLayer.Position;

public class Instruction {

	private InstructionType type;
	private Position position;
	
	public Instruction(InstructionType type, Position position) {
		this.position = position;
		this.type = type;
	}
	
}
