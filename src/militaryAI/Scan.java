package militaryAI;

import java.util.Random;

import militaryAI.ground.Building;
import militaryAI.ground.Dirt;
import militaryAI.ground.Empty;
import militaryAI.ground.Grass;
import militaryAI.ground.Land;
import militaryAI.ground.Sand;
import militaryAI.ground.Water;

public class Scan {

	static Land[][] mapAI;

	private int ySize;
	private int xSize;
	
	public Scan(int a, int b){
		xSize = a;
		ySize = b;
		mapAI = new Land[xSize][ySize];
		for (int x = 0; x < xSize; x++){
			for (int y = 0; y < ySize; y++){
				boolean eB = false;
				mapAI[x][y]= new Empty(x, y, eB);
			}
		}

	}


	public String toString(){
		int margin = 15;
		int currmargin = 0;
		//int bignumber = 0;
		//if (ySize >= 10) bignumber += 1;
		//if (ySize >= 100) bignumber += 1;
		//if (xSize >= 10) bignumber += 1;
		//if (xSize >= 100) bignumber += 1;
		String ret = "";
		for (int y = 0; y < ySize; y++){
			for (int x = 0; x < xSize; x++){
				currmargin = mapAI[x][y].name.length();
				String spaces = String.format("%"+(margin-currmargin)+"s", "");
				ret += mapAI[x][y].name + spaces +" (" + Integer.toString(mapAI[x][y].xPos) + ", " + Integer.toString(mapAI[x][y].yPos) + ")";
				if (mapAI[x][y].enemy) ret += "!    ";
				else if (mapAI[x][y].player) ret += "#    ";
				else ret += "     ";
				if (x == xSize - 1) ret += "\n";
				
				
				/*if (bignumber == 0){
					if (mapAI[x][y].enemy) ret += "!    ";
					else if (mapAI[x][y].player) ret += "#    ";
					else ret += "     ";
					if (x == xSize - 1) ret += "\n";
				}
				else if ((bignumber == 1) && ((y>10) || (x>10))){
					System.out.println("I have One extra");
					if (mapAI[x][y].enemy) ret += "!   ";
					else if (mapAI[x][y].player) ret += "#   ";
					else ret += "    ";
					if (x == xSize - 1) ret += "\n";
				}
				else if ((bignumber == 2)&& ((y>10) || (x>10))){
					System.out.println("I have Two extra");
					if (mapAI[x][y].enemy) ret += "!  ";
					else if (mapAI[x][y].player) ret += "#  ";
					else ret += "   ";
					if (x == xSize - 1) ret += "\n";
				}
				else if ((bignumber == 3)&& ((y>100) || (x>100))){
					if (mapAI[x][y].enemy) ret += "! ";
					else if (mapAI[x][y].player) ret += "# ";
					else ret += "  ";
					if (x == xSize - 1) ret += "\n";
				}
				else if ((bignumber == 4)&& ((y>100) && (x>100))){
					if (mapAI[x][y].enemy) ret += "!";
					else if (mapAI[x][y].player) ret += "#";
					else ret += " ";
					if (x == xSize - 1) ret += "\n";
				} */
			}
		}
		return ret;
	}
}