import java.util.Scanner;
public class GetPos {
	String HelpMenu;
	String[] Positions;
	private static Scanner in;
	public void GetPose(String[] args) {
		String name;
		String line;
        in = new Scanner(System.in);

        System.out.println("Welcome to our adventure game.  If you want help, press H");
        System.out.print("Type something: ");
        line = in.nextLine();
        //if(boolean notEqual =!line.equals("A") )
        /*if(line != "A") {
        	System.out.println("#Error Message: Invalid input, please enter either A or H");
        }*/
        
        if (line.equals("A")) {
        	String stringCheck1;
        	String stringCheck2;
        	String userPosition1;
        	String userPosition2;
        	String movement1;
        	String movement2;
        	//Deployment starts
        	System.out.println("Enter Starting X Position:");
        	userPosition1 = in.nextLine();
        	System.out.println("Enter Starting Y Position:");
        	System.out.println();
        	userPosition2 = in.nextLine();
        	int newUserPositionX1 = Integer.parseInt(userPosition1);
        	int newUserPositionY1 = Integer.parseInt(userPosition2);
        	int newUserPositionX2 = 0 ;
        	int newUserPositionY2 = 0;
        	String newUserPositionX1String;
        	String  newUserPositionX2String;
        	String  newUserPositionY1String;
        	String newUserPositionY2String;
        	
        	System.out.println("Enter X coordinate units you want to move:");
        	stringCheck1 = in.nextLine();
        	
        	if(stringCheck1.equals("0")) {		
        		System.out.println("#Error message 1, movement points can not be 0");
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
        	System.out.println("Enter Y coordinate units you want to move:");
        	stringCheck2 = in.nextLine();
        	if(stringCheck2.equals("0")) {
        		System.out.println("#Error message 1, movement points can not be 0");
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
        	newUserPositionX1String = Integer.toString(newUserPositionX1);
        	newUserPositionX2String = Integer.toString(newUserPositionX2);
        	newUserPositionY1String = Integer.toString(newUserPositionY1);
        	newUserPositionY2String = Integer.toString(newUserPositionY2);
        	
        	final String [] ValidMenu = {newUserPositionX1String, newUserPositionX2String, newUserPositionY1String, newUserPositionY2String};
        	//System.out.println(ValidMenu[1]);
        	this.Positions = ValidMenu;
        }


       
      /*  if(line.equals("H")){
        	String yes;
        	yes = "If you want to start deploying your units, press A. After pressing A, you would get the chance to start your starting position. The positions you enter are going to be the X and Y coordinates, where after you entre them you get the chance to move your position as well";
        	this.HelpMenu = yes;
        	
        }
        
        if(line.equals("N")) {
        	
        	System.out.println("Enter Player name here:");
            line = in.nextLine();
            name = line;
            
        	
        }*/

	}
}