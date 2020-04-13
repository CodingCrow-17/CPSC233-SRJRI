package userInterface;

import logicLayer.Position;

public class Instruction {

	private InstructionType type;
	private Position position;
	
	public Instruction(InstructionType type, Position position) {
		this.position = position;
		this.type = type;
	}
	
	public Instruction(Instruction copyOf) {
		this.position = copyOf.getPosition();
		this.type = copyOf.getType();
	}
	
	public InstructionType getType() {
		return this.type;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
}
