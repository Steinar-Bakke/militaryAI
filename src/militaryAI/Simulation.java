package militaryAI;

import java.util.Scanner;

public class Simulation {
	public static void main(String[] args){
		int x = 0;
		int y = 0;
		int t = 0;
		Scanner reader = new Scanner(System.in);
		while (x < 4)
		{
			System.out.println("Enter the x grid size (4 or greater): ");
			x = reader.nextInt();
		}
		while(y < 4)
		{
			System.out.println("Enter the y grid size (4 or greater): ");
			y = reader.nextInt();
		}
		{
			System.out.println("Enter the amount of turns/time (5 or greater): ");
			t = reader.nextInt();
		}
		
		Map m = new Map(x,y);
		System.out.println("\tRandomly Created Map:\n" + m.toString());
		Scan n = new Scan(x,y);
		System.out.println("\tStart AI Map:\n" + n.toString());
		
		Turn.Turn(t,x,y,m,n);
		
	}
}
