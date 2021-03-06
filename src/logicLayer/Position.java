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
	
	public void setXPosition(int xpos)
	{
		this.xPosition = xpos;
	}
	public void setYPosition(int ypos)
	{
		this.yPosition = ypos;
	}
	
	public Position getInversePosition() {
		return new Position(this.yPosition, this.xPosition);
	}
	
	public boolean equals(Position otherPosition) {
		return this.xPosition == otherPosition.getXPosition() && this.yPosition == otherPosition.getYPosition();
	}
	
	public String toString() {
		String stringVal = "(" + yPosition + "," + xPosition + ")";
		return stringVal;
	}
}
