package inputLayer;



public interface InputReciever {
	Instructions getInstruction();
	void printStartingMessage();
	void close();
}
