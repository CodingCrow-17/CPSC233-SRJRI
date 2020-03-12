import java.util.Scanner;

public class GetPos {
	String HelpMenu;
	int[] Positions;
	private static Scanner in;
	public GetPos(int[] Positions){
		SetPosition(Positions);
	}
	public void SetPosition(int[] Pos) {
        in = new Scanner(System.in);
        
        //Setting up local variables 
        //stringCheck1 and stringCheck2 are to determine the input numbers
        //userPosition1 and userPosition2 are player's starting x and y positions
        //movement1 and movement2 are player's movements in the x and y coordinates

        	String userPosition1;
        	String userPosition2;

        	
        	//User entering starting X and Y positions
        	System.out.println("Enter Starting X Position:");
        	userPosition1 = in.nextLine();
        	System.out.println("Enter Starting Y Position:");
        	System.out.println();
        	userPosition2 = in.nextLine();
        	
        	//Converting starting positions from string to integers
        	int newUserPositionX1 = Integer.parseInt(userPosition1);
        	int newUserPositionY1 = Integer.parseInt(userPosition2);

    //Making a ValidMenu array to append all four values of x-initial position, y-intial position, x-final position and y-final position    	
   //Returns ValidMenu to the instance variable
        	final int [] ValidMenu = {newUserPositionX1, newUserPositionY1};
        	this.Positions = ValidMenu;
        	//GetInfo.PLAYER =this.Positions;

        }
	public int[] getPosition() {
		return this.Positions;
	}
	
	
	public void printStartingMessage() {
		//Print start game message
        System.out.println("Welcome to our adventure game.");
        System.out.print("You can to start deploying your units.");
        System.out.print("You would get the chance to start your starting position.");
        System.out.print("The positions you enter are going to be the X and Y coordinates,");
        System.out.print(" where after you entre them you get the chance to move your position as well");
	}

	}