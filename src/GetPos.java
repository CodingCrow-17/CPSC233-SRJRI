import java.util.Scanner;
public class GetPos {
	String HelpMenu;
	int[] Positions;
	private static Scanner in;
	public int[] SetPos(int[] Pos) {
        in = new Scanner(System.in);
        
        //Setting up local variables 
        //stringCheck1 and stringCheck2 are to determine the input numbers
        //userPosition1 and userPosition2 are player's starting x and y positions
        //movement1 and movement2 are player's movements in the x and y coordinates
        	String stringCheck1;
        	String stringCheck2;
        	String userPosition1;
        	String userPosition2;
        	String movement1;
        	String movement2;
        	
        	//User entering starting X and Y positions
        	System.out.println("Enter Starting X Position:");
        	userPosition1 = in.nextLine();
        	System.out.println("Enter Starting Y Position:");
        	System.out.println();
        	userPosition2 = in.nextLine();
        	
        	//Converting starting positions from string to integers
        	int newUserPositionX1 = Integer.parseInt(userPosition1);
        	int newUserPositionY1 = Integer.parseInt(userPosition2);
        	int newUserPositionX2 = 0 ;
        	int newUserPositionY2 = 0;

        	//User enters x coordinates unit he wants to move
        	System.out.println("Enter X coordinate units you want to move:");
        	stringCheck1 = in.nextLine();
     
        	//User adds, subtracts or no change to the new user position after moving.
        	//Adds or subtracts units of movements to the original equation
        	if(stringCheck1.equals("0")) {		
        		newUserPositionX2 = newUserPositionX1;
        	}
        		else {
        	        movement1 = stringCheck1;

	        int newMovement1 = Integer.parseInt(movement1);
	        if (newMovement1 < 0) {
	        	
	        	newUserPositionX1 -= Math.abs(newMovement1);
	        	newUserPositionX2 = newUserPositionX1;
	        	
	        }
	        else {
	        	newUserPositionX1 += newMovement1;
	        	newUserPositionX2 = newUserPositionX1;
	        }}
        	
        	//User enters y coordinates unit he wants to move 
        	System.out.println("Enter Y coordinate units you want to move:");
        	stringCheck2 = in.nextLine();
        	
        	//User adds, subtracts or no change to the new user position after moving.
        	//Adds or subtracts units of movements to the original equation
        	if(stringCheck2.equals("0")) {
        		newUserPositionY2 = newUserPositionY1;
        	}
    		else {
    	        movement2 = stringCheck2;
    	        int newMovement2 = Integer.parseInt(movement2);
    	        if (newMovement2 < 0) {
    	        	
    	        	newUserPositionY1 -= Math.abs(newMovement2);
    	        	newUserPositionY2 = newUserPositionY1;
    	        }
    	        else {
    	        	newUserPositionY1 += newMovement2;
    	        	newUserPositionY2 = newUserPositionY1;
    	        }
    	        
        		}
        	
        	System.out.println(newUserPositionX2+ "," + newUserPositionY2);

    //Making a ValidMenu array to append all four values of x-initial position, y-intial position, x-final position and y-final position    	
   //Returns ValidMenu to the instance variable
        	final int [] ValidMenu = {newUserPositionX1, newUserPositionY1,newUserPositionX2 ,newUserPositionY2};
        	this.Positions = ValidMenu;
        	
        	return this.Positions;
        }
	
	
	public void printStartingMessage() {
        System.out.println("Welcome to our adventure game.");
        System.out.print("You can to start deploying your units.");
        System.out.print("You would get the chance to start your starting position.");
        System.out.print("The positions you enter are going to be the X and Y coordinates,");
        System.out.print(" where after you entre them you get the chance to move your position as well");
	}

	}