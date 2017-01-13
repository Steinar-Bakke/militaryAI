package militaryAI;


import militaryAI.ground.*;

public class Turn {
	private static Map map;
	private static Scan mapAI;
	private static int TotalTurns;
	private static int CurrTurns = 1;


	public static void Turn(int a,int x, int y, Map m, Scan n){
		TotalTurns = a;
		int xSize = x;
		int ySize = y;
		map = m;
		mapAI = n;
		
		int moveX = 0;
		int moveY = 0;
		int enemy = 0; 	// Amount of enemies
		int unscanned = 0; // amount of unscanned areas
		int scanned = 0;
		int counter = 0;
		
		

		boolean xmax = false;
		boolean ymax = false;
		boolean xhalf = false;
		boolean yhalf = false;
		boolean xtempleft = false;
		boolean xtempright = true;
		boolean ytempleft = false;
		boolean ytempright = true;

		
		boolean indangerright = false;
		boolean indangerleft = false;
		boolean indangerup = false;
		boolean indangerdown = false;
		
		boolean nomoveright = false;
		boolean nomoveleft = false;
		boolean nomoveup = false;
		boolean nomovedown = false;
		
		boolean maneuverleft = false;
		boolean maneuverdown = false;
		boolean maneuverright = false;
		boolean maneuverup = false;
		
		boolean donemove = false;
		
		
		
		while (CurrTurns <= TotalTurns){
			
			
			
			
			// Putting "Player(robot) at current pos
			Map.map[moveX][moveY].player = true;
			
			
			
			
			
					/*
					 * Scanning a circle if possible
					 * +2 x and y cord
					 * +1 diagonal
					 */
					if (moveX == xSize-1) nomoveright = true;
					//else nomoveright = false;
					
					if (moveX == 0) nomoveleft = true;
					//else nomoveleft = false;
					
					if (moveY == ySize-1) nomoveup = true;
					//else nomoveup = false;
					
					if (moveY == 0) nomovedown = true;
					//else nomovedown = false;
					
			
					
					// Scanning current position (Only move 1)
					if (Scan.mapAI[moveX][moveY].name.equals("Empty")){ // If current is empty
						Scan.mapAI[moveX][moveY] = Map.map[moveX][moveY];		
						if (Scan.mapAI[moveX][moveY].enemy){
							enemy += 1;
							}
						}
					
					
					/*SCANNING FORWARD CIRCLE
					 */
					if (moveX+1 < xSize){ // Scanning +1 x cord
						if ((Scan.mapAI[moveX+1][moveY].name.equals("Building")) || (Scan.mapAI[moveX+1][moveY].name.equals("Water"))) nomoveright = true;
						else 	nomoveright = false;					//checking if next move is possible
						if (Scan.mapAI[moveX+1][moveY].name.equals("Empty")){ // If current is empty
							Scan.mapAI[moveX+1][moveY] = Map.map[moveX+1][moveY];		
							if ((Scan.mapAI[moveX+1][moveY].name.equals("Building")) || (Scan.mapAI[moveX+1][moveY].name.equals("Water"))) nomoveright = true;
							else 	nomoveright = false;					//checking if next move is possible
							if (Scan.mapAI[moveX+1][moveY].enemy){
								enemy += 1;
								}
							}
						}
					if (moveX+2 < xSize){ // +2
						if (Scan.mapAI[moveX+2][moveY].name.equals("Empty")){ // If current is empty
							if (!Scan.mapAI[moveX+1][moveY].name.equals("Building")) Scan.mapAI[moveX+2][moveY] = Map.map[moveX+2][moveY];		
							if (Scan.mapAI[moveX+2][moveY].enemy) enemy +=1;					// Can't scan through building										// Add enemy
						}
						if (Scan.mapAI[moveX+2][moveY].enemy){
							indangerright = true;
						}
						else {
							indangerright = false;
						}
					}
					
					if (moveY+1 < ySize){	// Scanning +2 y cord
						if ((Scan.mapAI[moveX][moveY+1].name.equals("Building")) || (Scan.mapAI[moveX][moveY+1].name.equals("Water"))) nomoveup = true;
						else nomoveup = false;					//checking if next move is possible
						if (Scan.mapAI[moveX][moveY+1].name.equals("Empty")){ // If current is empty
							Scan.mapAI[moveX][moveY+1] = Map.map[moveX][moveY+1];		
							if ((Scan.mapAI[moveX][moveY+1].name.equals("Building")) || (Scan.mapAI[moveX][moveY+1].name.equals("Water"))) nomoveup = true;
							else nomoveup = false;					//checking if next move is possible
							if (Scan.mapAI[moveX][moveY+1].enemy){
								enemy += 1;
								}
							}
						}
					if (moveY+2 < ySize){
						if (Scan.mapAI[moveX][moveY+2].name.equals("Empty")){ // If current is empty
							if (!Scan.mapAI[moveX][moveY+1].name.equals("Building")) Scan.mapAI[moveX][moveY+2] = Map.map[moveX][moveY+2];		
							if (Scan.mapAI[moveX][moveY+2].enemy) enemy +=1;					// Can't scan through building										// Add enemy
					}
					if (Scan.mapAI[moveX][moveY+2].enemy){
						indangerup = true;
					}
					else {
						indangerup = false;
					}
				}
					
					/*SCANNING DIAGONAL CIRCLE
					 */
					if ((moveX+1 < xSize) && (moveY+1 < ySize)){	// Scanning Diagonal
						if (Scan.mapAI[moveX+1][moveY+1].name.equals("Empty")){ // If current is empty
							Scan.mapAI[moveX+1][moveY+1] = Map.map[moveX+1][moveY+1];		// FIX THIS
							if (Scan.mapAI[moveX+1][moveY+1].enemy) enemy +=1 ;
							}
						if (Scan.mapAI[moveX+1][moveY+1].enemy){
							indangerup = true;
							indangerright = true;
							}
						else{
							if (indangerup != true) indangerup = false;
							if (indangerright != true) indangerright = false;
							}
						}
					if ((moveX-1 >= 0) && (moveY-1 >= 0)){	// Scanning Diagonal
						if (Scan.mapAI[moveX-1][moveY-1].name.equals("Empty")){ // If current is empty
							Scan.mapAI[moveX-1][moveY-1] = Map.map[moveX-1][moveY-1];		
							if (Scan.mapAI[moveX-1][moveY-1].enemy) enemy += 1;
							}
						if (Scan.mapAI[moveX-1][moveY-1].enemy){
							indangerdown = true;
							indangerleft = true;
							}
						else{
							indangerdown = false;
							if (indangerleft != true) indangerleft = false;
							}
					}

					if ((moveX+1 < xSize) && (moveY-1 >= 0)){	// Scanning Diagonal
						if (Scan.mapAI[moveX+1][moveY-1].name.equals("Empty")){ // If current is empty
							Scan.mapAI[moveX+1][moveY-1] = Map.map[moveX+1][moveY-1];		
							if (Scan.mapAI[moveX+1][moveY-1].enemy) enemy +=1;
							}
						if (Scan.mapAI[moveX+1][moveY-1].enemy){
							indangerdown = true;
							indangerright = true;
							}
						else{
							if (indangerdown != true) indangerdown = false;
							if (indangerright != true) indangerright = false;
							}
					}
					if ((moveX-1 >= 0) && (moveY+1 < ySize)){	// Scanning Diagonal
						if (Scan.mapAI[moveX-1][moveY+1].name.equals("Empty")){ // If current is empty
							Scan.mapAI[moveX-1][moveY+1] = Map.map[moveX-1][moveY+1];		
							if (Scan.mapAI[moveX-1][moveY+1].enemy) enemy +=1;
							}
						if (Scan.mapAI[moveX-1][moveY+1].enemy){
							indangerup = true;
							indangerleft = true;
							}
						else{
							if (indangerup != true) indangerup = false;
							indangerleft = false;
							}
					}
					
					
					
					/*SCANNING BACKWARD CIRCLE
					 */
					if (moveX-1 >= 0){ // Scanning -2 x cord
						if ((Scan.mapAI[moveX-1][moveY].name.equals("Building")) || (Scan.mapAI[moveX-1][moveY].name.equals("Water"))) nomoveleft = true;
						else nomoveleft = false;					//checking if next move is possible
						if (Scan.mapAI[moveX-1][moveY].name.equals("Empty")){ // If current is empty
							Scan.mapAI[moveX-1][moveY] = Map.map[moveX-1][moveY];		
							if ((Scan.mapAI[moveX-1][moveY].name.equals("Building")) || (Scan.mapAI[moveX-1][moveY].name.equals("Water"))) nomoveleft = true;
							else nomoveleft = false;					//checking if next move is possible
							if (Scan.mapAI[moveX-1][moveY].enemy){
								enemy += 1;
								}
							}
						}
					if (moveX-2 >= 0){
						if (Scan.mapAI[moveX-2][moveY].name.equals("Empty")){ // If current is empty
							if (!Scan.mapAI[moveX-1][moveY].name.equals("Building")) Scan.mapAI[moveX-2][moveY] = Map.map[moveX-2][moveY];		
							if (Scan.mapAI[moveX-2][moveY].enemy) enemy +=1;					// Can't scan through building										// Add enemy
						}
						if (Scan.mapAI[moveX-2][moveY].enemy){
							indangerleft = true;
						}
						else {
							if (indangerleft != true) indangerleft = false;
						}
					}
						
					if (moveY-1 >= 0){	// Scanning -2 y cord
						if ((Scan.mapAI[moveX][moveY-1].name.equals("Building")) || (Scan.mapAI[moveX][moveY-1].name.equals("Water"))) nomovedown = true;
						else nomovedown = false;					//checking if next move is possible
						if (Scan.mapAI[moveX][moveY-1].name.equals("Empty")){ // If current is empty
							Scan.mapAI[moveX][moveY-1] = Map.map[moveX][moveY-1];		
							if ((Scan.mapAI[moveX][moveY-1].name.equals("Building")) || (Scan.mapAI[moveX][moveY-1].name.equals("Water"))) nomovedown = true;
							else nomovedown = false;					//checking if next move is possible
							if (Map.map[moveX][moveY-1].enemy){
								enemy += 1;
								}
							}
						}
					if (moveY-2 >= 0){
						if (Scan.mapAI[moveX][moveY-2].name.equals("Empty")){ // If current is empty
							if (!Scan.mapAI[moveX][moveY-1].name.equals("Building")) Scan.mapAI[moveX][moveY-2] = Map.map[moveX][moveY-2];		
							if (Scan.mapAI[moveX][moveY-2].enemy) enemy +=1;					// Can't scan through building										// Add enemy
						}
						if (Scan.mapAI[moveX][moveY-2].enemy){
							indangerdown = true;
						}
						else {
							if (indangerdown != true) indangerdown = false;
						}
					}

					
					/*DONE SCANNING CIRCLE
					 * 
					 * 
					 */
					
				//}
					
			System.out.println("\n\n("+moveX+", "+moveY+") - Scanning");
			System.out.println("Turn : " + CurrTurns + "/"+TotalTurns+". \nPossible Enemies : "+ enemy +"\nIn Danger :\n\tRight: "+ indangerright + "\n\tLeft: "+indangerleft + "\n\tUp: "+indangerup + "\n\tDown: "+indangerdown + "\nNo Move :\n\tRight: "+ nomoveright + "\n\tLeft: "+nomoveleft + "\n\tUp: "+nomoveup + "\n\tDown: "+nomovedown +"\n\n" + mapAI.toString());
			
			
			
			/*
			 * 
			 *  Movement Test - Circle around the map
			 *  
			 *  Circle Scan // 20 (time) 
			 */
			
			// NEED TO WORK ON WHAT TO DO, ROBOT GETS STUCK TOO OFTEN

			if (TotalTurns >= 20){
				if ((moveX < xSize-1) && (xtempleft == false) && (xtempright == true) && (ytempleft == false) && (indangerright == false) && (nomoveright == false) && (maneuverdown == false) && (maneuverleft == false)){ 
					moveX +=1;	
					donemove = true;
					if (moveX == xSize-1) xtempleft = true;
				}
				else if ((moveY < ySize-1) && (ytempleft == false) && (ymax == false) && (ytempright == true) && (indangerup == false) && (nomoveup == false) && (maneuverdown == false) && (maneuverleft == false)){
					moveY +=1;
					donemove = true;
					counter +=1;
					if ((counter == 5) || (moveY == ySize-1)){
						ytempleft = true;
						ytempright = false;
						xtempright = false;
						xtempleft = true;
						counter = 0;
					}
					if (moveY == ySize-1) ymax = true;
				}
				else if ((moveX > 0 ) && (ytempleft == true) && (ytempright == false) && (xtempright == false) && (indangerleft == false) && (nomoveleft == false) && (maneuverdown == false) && (maneuverleft == false)){
					moveX -=1;
					donemove = true;
					if ((moveX == 0)) xtempright = true;
				}
				else if ((moveY < ySize-1) && (ytempright == false) && (ymax == false) && (xtempright == true) && (ytempleft == true) && (indangerup == false) && (nomoveup == false) && (maneuverdown == false) && (maneuverleft == false)){
					moveY +=1;
					donemove = true;
					counter +=1;
					if ((counter == 5) || (moveY == ySize-1)){
						ytempleft = false;
						ytempright = true;
						xtempright = true;
						xtempleft = false;
						counter = 0;
					}
					else if (moveY == ySize-1) ymax = true; 
				}
				// Maneuvers 
				else if (maneuverdown == true){
					if ((moveX+1 <= xSize) && (nomoveright == false) && (indangerright == false)){
						moveX +=1;
						donemove = true;
						System.out.println("Maneuvering right after down");
						// Start try to go more right
						maneuverdown = false;
					}
					else if ((moveX-1 >= 0) && (nomoveleft == false) && (indangerleft == false)){
						moveX -=1;
						donemove = true;
						System.out.println("Maneuvering left after down");
						// start try to go more up/right
						maneuverdown = false;
					}
					else {
						System.out.println("did nothing, but setting maneuver to false");
						maneuverdown = false;
						donemove = true;
					}
				}
				
				
				else if (maneuverup == true){
					if ((moveX+1 <= xSize) && (nomoveright == false) && (indangerright == false)){
						moveX +=1;
						donemove = true;
						System.out.println("Maneuvering right after up");
						// Start try to go more right
						maneuverup = false;
					}
					else if ((moveX-1 >= 0) && (nomoveleft == false) && (indangerleft == false)){
						moveX -=1;
						donemove = true;
						System.out.println("Maneuvering left after up");
						// start try to go more up/right
						maneuverup = false;
					}
					else {
						System.out.println("did nothing, but setting maneuver to false");
						maneuverup = false;
						donemove = true;
					}
				}
				
				
				else if (maneuverright == true){
					if ((moveY+1 <= ySize) && (nomoveup == false) && (indangerup == false)){
						moveY +=1;
						donemove = true;
						System.out.println("Maneuvering up after right");
						// Start try to go more right
						maneuverright = false;
					}
					else if ((moveY-1 >= 0) && (nomovedown == false) && (indangerdown == false)){
						moveY -=1;
						donemove = true;
						System.out.println("Maneuvering down after right");
						// start try to go more up/right
						maneuverright = false;
					}
					else {
						System.out.println("did nothing, but setting maneuver to false");
						maneuverright = false;
						donemove = true;
					}
				}
				
				else if (maneuverleft == true){
					if ((moveY+1 <= ySize) && (nomoveup == false) && (indangerup == false)){
						moveY +=1;
						donemove = true;
						System.out.println("Maneuvering up after left");
						// Start try to go more right
						maneuverleft = false;
					}
					else if ((moveY-1 >= 0) && (nomovedown == false) && (indangerdown == false)){
						moveY -=1;
						donemove = true;
						System.out.println("Maneuvering down after left");
						// start try to go more up/right
						maneuverleft = false;
					}
					else {
						System.out.println("did nothing, but setting maneuver to false");
						maneuverleft = false;
						donemove = true;
					}
				}
								
			}
			
			
			/*
			 * 
			 *  Movement Test - Get to Middle - Short Scan
			 *  
			 *  Middle
			 */

			else if (TotalTurns < 20){
				if ((moveY < ySize-1) && (yhalf == false) && (indangerup == false) && (nomoveup == false) && (maneuverdown == false) && (maneuverleft == false)){ 
					moveY +=1;	
					donemove = true;
					System.out.println("up ");
					if (moveY == ySize/2) yhalf = true;
				}
				else if ((moveX < xSize-1) && (xhalf == false) && (indangerright == false) && (nomoveright == false) && (maneuverdown == false) && (maneuverleft == false)){
					moveX +=1;
					donemove = true;
					System.out.println("right");
					if (moveX == xSize/2) xhalf = true;
				}
				// NOT QUITE SURE WHAT TO DO AFTER MIDDLE IS REACHED 
				// Currently going to bottom right corner						// Checking if move is valid
				else if ((xhalf == true) && (xmax == false) && (indangerright == false) && (nomoveright == false) && (maneuverdown == false) && (maneuverleft == false)){
					moveX +=1;
					donemove = true;
					System.out.println("right");
					if (moveX == xSize-1) xmax = true;
				}
				else if ((yhalf == true) && (ymax == false) && (xhalf == true) && (indangerup == false) && (nomoveup == false) && (maneuverdown == false) && (maneuverleft == false)){
					moveY +=1;
					donemove = true;
					System.out.println("up");
					if (moveY == ySize-1) ymax = true;
				}
				else if ((donemove == false) && ((indangerdown == true) || (nomovedown == true)) && (moveY+1 <= ySize)){
					moveY +=1;
					donemove = true;
					System.out.println("up");
					if (moveY == ySize-1) ymax = true;
				}
				

				else if (maneuverdown == true){
					if ((moveX+1 <= xSize) && (nomoveright == false) && (indangerright == false)){
						moveX +=1;
						donemove = true;
						System.out.println("Maneuvering right after down");
						// Start try to go more right
						maneuverdown = false;
					}
					else if ((moveX-1 >= 0) && (nomoveleft == false) && (indangerleft == false)){
						moveX -=1;
						donemove = true;
						System.out.println("Maneuvering left after down");
						// start try to go more up/right
						maneuverdown = false;
					}
					else {
						System.out.println("did nothing, but setting maneuver to false");
						maneuverdown = false;
						donemove = true;
					}
				}
				
				
				else if (maneuverup == true){
					if ((moveX+1 <= xSize) && (nomoveright == false) && (indangerright == false)){
						moveX +=1;
						donemove = true;
						System.out.println("Maneuvering right after up");
						// Start try to go more right
						maneuverup = false;
					}
					else if ((moveX-1 >= 0) && (nomoveleft == false) && (indangerleft == false)){
						moveX -=1;
						donemove = true;
						System.out.println("Maneuvering left after up");
						// start try to go more up/right
						maneuverup = false;
					}
					else {
						System.out.println("did nothing, but setting maneuver to false");
						maneuverup = false;
						donemove = true;
					}
				}
				
				
				else if (maneuverright == true){
					if ((moveY+1 <= ySize) && (nomoveup == false) && (indangerup == false)){
						moveY +=1;
						donemove = true;
						System.out.println("Maneuvering up after right");
						// Start try to go more right
						maneuverright = false;
					}
					else if ((moveY-1 >= 0) && (nomovedown == false) && (indangerdown == false)){
						moveY -=1;
						donemove = true;
						System.out.println("Maneuvering down after right");
						// start try to go more up/right
						maneuverright = false;
					}
					else {
						System.out.println("did nothing, but setting maneuver to false");
						maneuverright = false;
						donemove = true;
					}
				}
				
				else if (maneuverleft == true){
					if ((moveY+1 <= ySize) && (nomoveup == false) && (indangerup == false)){
						moveY +=1;
						donemove = true;
						System.out.println("Maneuvering up after left");
						// Start try to go more right
						maneuverleft = false;
					}
					else if ((moveY-1 >= 0) && (nomovedown == false) && (indangerdown == false)){
						moveY -=1;
						donemove = true;
						System.out.println("Maneuvering down after left");
						// start try to go more up/right
						maneuverleft = false;
					}
					else {
						System.out.println("did nothing, but setting maneuver to false");
						maneuverleft = false;
						donemove = true;
					}
				}

			}
			
			// Maneuvers
			
			// Can go only Down
			if (((nomoveright == true) || (indangerright == true)) && ((nomoveup== true) || (indangerup == true)) && ((nomoveleft == true) || (indangerleft == true)) && ((nomovedown == false) && (indangerdown == false)) && (donemove == false) && (moveY-1 >= 0)){
				moveY -=1;
				maneuverdown = true;
				System.out.println("Manouver down");
			}
			
			// Can go Down or Left // if not at end of board
			if (((nomoveright == true) || (indangerright == true)) && ((nomoveup== true) || (indangerup == true)) && ((nomoveleft == false) && (indangerleft == false)) && ((nomovedown == false) && (indangerdown == false)) && (donemove == false) &&  (moveY-1 >= 0)){
				moveY -=1;
				maneuverdown = true;
				System.out.println("Manouver down");
			}
			
			// can go only Left
			if (((nomoveright == true) || (indangerright == true)) && ((nomoveup == true) || (indangerup == true)) && ((nomovedown == true) || (indangerdown == true)) && ((nomoveleft == false) && (indangerleft == false)) && (donemove == false) &&  (moveX-1 >= 0)){
				moveX -=1;
				maneuverleft = true;
				System.out.println("Manouver left");
			}
			
			// can go Down or Left // if not at end of board
			if (((nomoveright == true) || (indangerright == true)) && ((nomoveup == true) || (indangerup == true)) && ((nomovedown == false) && (indangerdown == false)) && ((nomoveleft == false) && (indangerleft == false)) && (donemove == false) &&  (maneuverdown == false) && (moveX-1 >= 0)){
				moveX -=1;
				maneuverleft = true;
				System.out.println("Manouver left");
			}
			
			// can go Right or down
			if (((nomoveright == false) && (indangerright == false)) && ((nomoveup == true) || (indangerup == true)) && ((nomovedown == false) && (indangerdown == false)) && ((nomoveleft == true) || (indangerleft == true)) && (maneuverdown == false) && (donemove == false) &&  (moveX+1 <= xSize)){
				moveX +=1;
				maneuverright = true;
				System.out.println("Manouver Right");
			}
			
			// can only go up
			if (((nomoveright == true) || (indangerright == true)) && ((nomoveup == false) && (indangerup == false)) && ((nomovedown == true) || (indangerdown == true)) && ((nomoveleft == true) || (indangerleft == true)) && (donemove == false) &&  (moveY+1 <= ySize)){
				moveY +=1;
				maneuverup = true;
				System.out.println("Manouver up");
			}
			// Can only go Up or DOwn
			if (((nomoveright == true) || (indangerright ==true)) && ((nomoveup == false) && (indangerup == false)) && ((nomovedown == false) && (indangerdown == false)) && ((nomoveleft == true) || (indangerright == true)) && (maneuverdown == false) && (donemove == false) &&  (moveY-1 >=0) && (moveY+1 <= ySize)){
				//go down
				if (xhalf == true){
					moveY -=1;
					maneuverdown = true;
					System.out.println("Can go Up or Down, maneuverdown");
				}
				else {
					moveY +=1;
					maneuverup = true;
					System.out.println("Can go Up or Down, maneuverup");
				}
			}
			
			// can only go right
			if (((nomoveright == false) && (indangerright == false)) && ((nomoveup == true) || (indangerup == true)) && ((nomovedown == true) || (indangerdown == true)) && ((nomoveleft == true) || (indangerleft == true)) && (donemove == false) &&  (moveX+1 <= xSize)){
				moveX +=1;
				maneuverright = true;
				System.out.println("Manouver Right");
			}
			
			// up and left
			if (((nomoveright == true) || (indangerright ==true)) && ((nomoveup == false) && (indangerup == false)) && ((nomovedown == true) || (indangerdown == true)) && ((nomoveleft == false) && (indangerright == false)) && (maneuverup == false) && (donemove == false) &&  (moveX-1 >=0) && (moveY+1 <= ySize)){
				//go up
				if (xhalf == true){
					moveY +=1;
					maneuverup = true;
					System.out.println("Can go Up or left, maneuverup");
				}
				else {
					moveX -=1;
					maneuverleft = true;
					System.out.println("Can go Up or Down, maneuverleft");
				}
			}
			// Completely stuck
			if (((nomoveright == true) || (indangerright == true)) && ((nomoveup == true) || (indangerup == true)) && ((nomovedown == true) || (indangerdown == true)) && ((nomoveleft == true) || (indangerleft == true))) System.out.println("Completely Stuck");
			

			
			System.out.println("Do I get here : Checking Manouvers: Right, Left, Up, Down : "+ maneuverright + maneuverleft + maneuverup + maneuverdown);
			donemove = false;
			CurrTurns += 1;
		} // End of While Turn
		
		
		// Checking unscanned areas
		for (int q = 0; q < xSize; q++){
			for (int w = 0; w < ySize; w++){
				if (Scan.mapAI[q][w].name.equals("Empty")) unscanned +=1;
				else scanned +=1;
			}
		}
				
		
		System.out.println("\n\n\n\n **************************************************\n"
							 + " ******************* Final Results ****************\n"
							 + " **************************************************\n\n");
		System.out.println("Total Enemies = "+ enemy);
		System.out.println("Total Scanned Areas = "+ scanned);
		System.out.println("Total Unscanned Areas = "+ unscanned);
		System.out.println("\nFull Scan of map " +"\n\n**************************************************\n" + mapAI.toString() + "\n**************************************************");
		
		
				
		
		
	
	} // End of Turn
} // End of Class


