package logicLayer;

public class Position
{
	private int xPosition;
	private int yPosition;
	
	public Position (int xPosition, int yPosition)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public Position(Position pos) {
		this.xPosition = pos.getXPosition();
		this.yPosition = pos.getYPosition();// TODO Auto-generated constructor stub
	}

	public int getXPosition()
	{
		return xPosition;
	}
	
	public int getYPosition()
	{
		return yPosition;
	}
	
	public void movePositionUp() {
		yPosition--;
	}
	
	public void movePositionDown() {
		yPosition++;
	}
	
	public void movePositionRight() {
		xPosition++;
	}
	
	public void movePositionLeft() {
		xPosition--;
	}
	
	public String toString() {
		String stringVal = "(" + yPosition + "," + xPosition + ")";
		return stringVal;
	}
}
