package buildings;

public abstract class Building {
	
	private int woodCost, goldCost, stoneCost;
	
	public Building(int wood, int stone, int gold)
	{
		woodCost = wood;
		stoneCost = stone;
		goldCost = gold;
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
