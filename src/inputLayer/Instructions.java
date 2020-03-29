
package inputLayer;

import logicLayer.Position;

public class Instructions {

	private InstructionType type;
	private Position position;
	
	public Instructions(InstructionType type, Position position) {
		this.position = position;
		this.type = type;
	}
	
	public Instructions(Instructions copyOf) {
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
