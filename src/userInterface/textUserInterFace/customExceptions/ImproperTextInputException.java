package userInterface.textUserInterFace.customExceptions;

public class ImproperTextInputException extends Exception{

	private static final String DEFAULT_MESSAGE = "INVALID INPUT";
	
	public ImproperTextInputException() {
		super(DEFAULT_MESSAGE);
	}
	
	public ImproperTextInputException(String message) {
		super(message);
	}
	
}
