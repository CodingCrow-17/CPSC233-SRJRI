package inputLayer;



public interface InputReciever {
	Instructions getNextInstruction();
	void printStartingMessage();
	void close();
}
