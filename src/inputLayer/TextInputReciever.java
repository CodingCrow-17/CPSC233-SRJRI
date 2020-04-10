package inputLayer;
import java.util.Scanner;

import logicLayer.Position;

public class TextInputReciever implements InputReciever{
	private Instruction lastInstruction;
	private InstructionType conmand;
	private static Scanner in;
	
	
	public  void TextInputReceiver(int[] Positions, InstructionType conmand){
		setInstruction(Positions);
		checkStatement(conmand);
	}
	public void checkStatement(InstructionType conmand) {
		String command;
		System.out.println("Help: Enter either select, deselect, attack, move, wait. Other inputs would all be considered invalid.");
		System.out.println("Enter command here:");
		command = in.nextLine();
		command = command.toUpperCase().replaceAll("\\s", "");
		
		if(command.equals("SELECT")) {
			this.conmand = InstructionType.SELECT;
		}
		else if(command.equals("DESELECT")) {
			this.conmand = InstructionType.DESELECT;
		}
		else if(command.equals("ATTACK")){
			this.conmand = InstructionType.ATTACK;
		}
		else if(command.equals("MOVE")) {
			this.conmand = InstructionType.MOVE;
		}
		else if(command.equals("WAIT")) {
			this.conmand = InstructionType.WAIT;
		}
		else {
			System.out.println("Invalid statement.");
			System.out.println("Enter new command here:");
			command = in.nextLine();
			command = command.toUpperCase().replaceAll("\\s", "");
		}
		
	}
	public InstructionType returnCommand() {
		return this.conmand;
	}
	public void setInstruction(int[] positions) {
		in = new Scanner(System.in);        
        String stringCheck1 = "";
       	String stringCheck2 = "";
       	String userPosition1 = "";
       	String userPosition2 = "";
       	String movement1;
       	String movement2;
       	//Deployment starts
       	System.out.println("Enter Starting X Position:");
       	while (isParsable(userPosition1) == false)
       	{   	
       		userPosition1 = in.nextLine().replaceAll("\\s", "");
       		if (isParsable(userPosition1) == false)
       		{
       			System.out.println("That's not an integer!");
       			System.out.println("");
       			System.out.println("Enter Starting X Position:");
       		}
       	}
       	System.out.println("Enter Starting Y Position:");
       	while (isParsable(userPosition2) == false)
       	{
       		userPosition2 = in.nextLine().replaceAll("\\s", "");
       		if (isParsable(userPosition2) == false)
       		{
       			System.out.println("That's not an integer!");
       			System.out.println("");
       			System.out.println("Enter Starting Y Position:");
       		}
       	}
       	int newUserPositionX1 = Integer.parseInt(userPosition1);
       	int newUserPositionY1 = Integer.parseInt(userPosition2);
       	int newUserPositionX2 = 0;
       	int newUserPositionY2 = 0;
    	if(this.conmand == InstructionType.WAIT) {
    		System.out.println("Waiting");}
    	else {
       	System.out.println("Enter X movement:");
       	while (isParsable(stringCheck1) == false)
       	{
       		stringCheck1 = in.nextLine().replaceAll("\\s", "");
       		if (isParsable(stringCheck1) == false)
       		{
       			System.out.println("That's not an integer!");
       			System.out.println("");
       			System.out.println("Enter X movement:");
       		}
       	}
       		
       	movement1 = stringCheck1;
        int newMovement1 = Integer.parseInt(movement1);
        newUserPositionX2 = newUserPositionX1 + newMovement1;}
    	if(this.conmand == InstructionType.WAIT) {
    		System.out.println("Waiting");}
    	else {
        System.out.println("Enter Y movement:");
        while (isParsable(stringCheck2) == false)
       	{
       		stringCheck2 = in.nextLine().replaceAll("\\s", "");
       		if (isParsable(stringCheck2) == false)
       		{
       			System.out.println("That's not an integer!");
       			System.out.println("");
       			System.out.println("Enter Y movement");
       		}
       	}
       	movement2 = stringCheck2;
        int newMovement2 = Integer.parseInt(movement2);
        newUserPositionY2 = newUserPositionY1 + newMovement2;}
       	
       	Position position = new Position(newUserPositionX2, newUserPositionY2);
       	
       	lastInstruction = new Instruction(returnCommand(), position);

	}
	
	
	
	public void close() {
		if (in != null) {
			in.close();
		}
	}
	
	private boolean isParsable(String input)
	{
		try
		{
	        Integer.parseInt(input);
	        return true;
	    }catch(Exception e)
		{
	        return false;
	    }
	}
	
	public void printStartingMessage() {
        System.out.println("Welcome to our adventure game.");
        System.out.println("You can now start deploying your units.");
        System.out.println("First, choose a tile occupied with one of your units.");
        System.out.println("Then, enter the number of spaces you would like to move in the X direction,");
        System.out.println("followed by the number of spaces you would like to move in the Y direction");
	}
	@Override
	public Instruction getInstruction() {
		// TODO Auto-generated method stub
		return this.lastInstruction;
	}

}


