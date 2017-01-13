package militaryAI.ground;

import java.util.Random;

public class Land {
	public String name;
	public int xPos;
	public int yPos;
	public int friction;
	public boolean accessible;
	public boolean enemy;
	public boolean player;
	
	public Land(){
		Random r = new Random();
	}
	
	public Land(String name, int x, int y, int f, boolean a, boolean e){
		this.name = name;
		this.xPos = x;
		this.yPos = y;
		this.friction = f;
		this.accessible = a;
		this.enemy = e;
		this.player = false;
	}
	
	/*
	public int getX(){
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
	
	public int getFriction(){
		return friction;
	}
	
	public boolean getPassable(){
		return xPos;
	}
	
	public int getX(){
		return xPos;
	}
	*/

}
