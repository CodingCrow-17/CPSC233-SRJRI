package inputLayer;
import java.util.Scanner;

public class MovementOptions{
		String options;
		private static Scanner in;
		public MovementOptions(String decisions) {
			setPlayerDecisions(decisions);
		}
		public void setPlayerDecisions(String line) {
		    in = new Scanner(System.in);

		    System.out.print("Select a move. A for Attack, D for defense, F for flee: ");
		    line = in.nextLine();	
		    if (line == "A") {
		    	this.options = "offense";
		    }
		    if (line == "D") {
		    	this.options = "defense";
		    }
		    if(line == "F") {
		    	this.options = "flee";
		    }
		}
		public Object getPlayerDecisions() {
			return this.options;
		}
}
