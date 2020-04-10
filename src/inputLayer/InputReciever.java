package inputLayer;



public interface InputReciever {
	Instruction getNextInstruction();
	void printStartingMessage();
	void close();
}
