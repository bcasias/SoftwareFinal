package sticksAndStones;

import land.Land;

public class SticksandStone {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MapGeneration m = new  MapGeneration();
		Land[][] l = m.generateMap(15,15);
		
		for(int i = 0; i < l.length; i++)
		{
			for(int j = 0; j < l[i].length; j++)
			{
				System.out.print(l[i][j].toString() + "\t");
			}
			System.out.println();
		}
	}

}
