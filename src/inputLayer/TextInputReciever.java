package inputLayer;
import java.util.Scanner;

import logicLayer.Position;

public class TextInputReciever implements InputReciever{
	private Instructions lastInstruction;
	private InstructionType conmand;
	private static Scanner in = new Scanner(System.in);
	
	public InstructionType setInstruction() {
		String command;
		System.out.println("Help: Enter either select, deselect, attack, move, wait. Other inputs would all be considered invalid.");
		System.out.println("Enter command here:");
		command = in.nextLine();
		command = command.toUpperCase().replaceAll("\\s", "");
		
		if(command.equals("SELECT")) {
			return InstructionType.SELECT;
		}
		else if(command.equals("DESELECT")) {
			return InstructionType.DESELECT;
		}
		else if(command.equals("ATTACK")){
			return InstructionType.ATTACK;
		}
		else if(command.equals("MOVE")) {
			return InstructionType.MOVE;
		}
		else if(command.equals("WAIT")) {
			return InstructionType.WAIT;
		}
		else {
			System.out.println("Invalid statement.");
		}
		return InstructionType.INVALID;
		
	}
	public InstructionType returnCommand() {
		return this.conmand;
	}
	public Position selectPosition() {
		in = new Scanner(System.in);        
       	String userPosition1 = "";
       	String userPosition2 = "";
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
       	Position pos = new Position(newUserPositionX1,newUserPositionY1);
       	this.lastInstruction = new Instructions(InstructionType.SELECT,pos);
       	return pos;


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
	public Instructions getInstruction() {
		// TODO Auto-generated method stub
		return this.lastInstruction;
	}

}


