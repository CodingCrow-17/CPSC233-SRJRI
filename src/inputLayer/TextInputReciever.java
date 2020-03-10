package inputLayer;
import java.util.Scanner;

public class TextInputReciever implements InputReciever{
	int[] Positions;
	private static Scanner in;
	
	public int[] getInstruction() {
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
       		userPosition1 = in.nextLine();
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
       		userPosition2 = in.nextLine();
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
       	
       	System.out.println("Enter X movement:");
       	while (isParsable(stringCheck1) == false)
       	{
       		stringCheck1 = in.nextLine();
       		if (isParsable(stringCheck1) == false)
       		{
       			System.out.println("That's not an integer!");
       			System.out.println("");
       			System.out.println("Enter X movement:");
       		}
       	}
       		
       	movement1 = stringCheck1;
        int newMovement1 = Integer.parseInt(movement1);
        newUserPositionX2 = newUserPositionX1 + newMovement1;
        
        System.out.println("Enter Y movement:");
        while (isParsable(stringCheck2) == false)
       	{
       		stringCheck2 = in.nextLine();
       		if (isParsable(stringCheck2) == false)
       		{
       			System.out.println("That's not an integer!");
       			System.out.println("");
       			System.out.println("Enter Y movement");
       		}
       	}

        	
       	final int [] ValidMenu = {newUserPositionX1, newUserPositionY1};
       	this.Positions = ValidMenu;

        return this.Positions;
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
}


