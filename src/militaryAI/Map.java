package militaryAI;

import java.util.Random;

import militaryAI.ground.Building;
import militaryAI.ground.Dirt;
import militaryAI.ground.Grass;
import militaryAI.ground.Land;
import militaryAI.ground.Sand;
import militaryAI.ground.Water;

public class Map {
	static Land[][] map;
	private Random random = new Random();
	//public static int xSize;
	//public static int ySize;
	private int ySize;
	private int xSize;
	
	public Map(int a, int b){
		xSize = a;
		ySize = b;
		map = new Land[xSize][ySize];
		
		
		for (int x = 0; x < xSize; x++){
			for (int y = 0; y < ySize; y++){
				int t = random.nextInt(100);
				int eI = random.nextInt(13);
				boolean eB = false;
				if(eI == 1) eB = true;
				if(x < 3 && y < 3)
				{
					map[x][y]= new Grass(x, y, false);
				}
				else if(t <= 45){
					map[x][y]= new Grass(x, y, eB);
				}
				else if((t > 45)&& (t <= 60)){
					map[x][y]= new Sand(x, y, eB);
				}
				else if((t > 60) && (t <= 85)){
					map[x][y]= new Dirt(x, y, eB);
				}
				else if((t > 85) && (t <= 93)){
					map[x][y]= new Building(x, y, eB);
				}
				else{
					map[x][y]= new Water(x, y);
				}
				//System.out.println(t);
			}
		}
	}
	
	public Map(int x, int y, String filePath){
		this.xSize = x;
		this.ySize = y;
		
		/*
		Insert loading of map from file
		Syntax will be:
		Ground, 0	Ground, 0	Building, 1	.....
		(type of tile, enemy here [0 - no, 1 - yes])
		*/
		
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
				currmargin = map[x][y].name.length();
				String spaces = String.format("%"+(margin-currmargin)+"s", "");
				ret += map[x][y].name + spaces +" (" + Integer.toString(map[x][y].xPos) + ", " + Integer.toString(map[x][y].yPos) + ")";
				if (map[x][y].enemy) ret += "!    ";
				else if (map[x][y].player) ret += "#    ";
				else ret += "     ";
				if (x == xSize - 1) ret += "\n";
				/*
				if (bignumber == 0){
					if (map[x][y].enemy) ret += "!    ";
					else if (map[x][y].player) ret += "#    ";
					else ret += "     ";
					if (x == xSize - 1) ret += "\n";
				}
				else if ((bignumber == 1) && ((y>10) || (x>10))){
					if (map[x][y].enemy) ret += "!   ";
					else if (map[x][y].player) ret += "#   ";
					else ret += "    ";
					if (x == xSize - 1) ret += "\n";
				}
				else if ((bignumber == 2)&& ((y>10) || (x>10))){
					if (map[x][y].enemy) ret += "!  ";
					else if (map[x][y].player) ret += "#  ";
					else ret += "   ";
					if (x == xSize - 1) ret += "\n";
				}
				else if ((bignumber == 3)&& ((y>100) || (x>100))){
					if (map[x][y].enemy) ret += "! ";
					else if (map[x][y].player) ret += "# ";
					else ret += "  ";
					if (x == xSize - 1) ret += "\n";
				}
				else if ((bignumber == 4)&& ((y>100) && (x>100))){
					if (map[x][y].enemy) ret += "!";
					else if (map[x][y].player) ret += "#";
					else ret += " ";
					if (x == xSize - 1) ret += "\n";
				}
				*/
			}
		}
		return ret;
	}
}
