package inputLayer;



public interface InputReciever {
	Instruction getInstruction();
	void printStartingMessage();
	void close();
}
