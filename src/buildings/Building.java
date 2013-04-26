package buildings;

import java.awt.Point;

public abstract class Building {
	
	private int woodCost, goldCost, stoneCost;
	protected Point location;
	public Building(int locx, int locy, int wood, int stone, int gold)
	{
		location = new Point(locx, locy);
		woodCost = wood;
		stoneCost = stone;
		goldCost = gold;
	}
	
	public Point getLocation()
	{
		return location;
	}

	public int getWoodCost() {
		return woodCost;
	}

	public int getGoldCost() {
		return goldCost;
	}

	public int getStoneCost() {
		return stoneCost;
	}
}
