package civilization;

import java.util.ArrayList;
import java.util.Set;

import resource.Resource.ResourceType;

import land.Land;

import buildings.Building;
import buildings.City;
import buildings.Farm;
import buildings.Mine;
import buildings.SawMill;

public class Civilization {
	private ArrayList<City> cities;
	private ArrayList<Land> civLand;
	private int goldCount, foodCount, stoneCount, woodCount;
	private int happiness;

	public Civilization()
	{
		cities = new ArrayList<City>();
		cities.add(new City(0,0));
		civLand = new ArrayList<Land>();
		goldCount = 100;
		foodCount = 100;
		stoneCount = 100;
		woodCount = 100;
		happiness = 10;
	}

	public boolean makeBuilding(Building building) {
		if (goldCount >= building.getGoldCost() && stoneCount >= building.getStoneCost() && woodCount >= building.getWoodCost()) {
			goldCount -= building.getGoldCost();
			stoneCount -= building.getStoneCost();
			woodCount -= building.getWoodCost();
			return true; //if sufficient resources are available, building is possible
		}
		return false;
	}

	public void gatherResources()
	{
		if(civLand.size() > 0);
		for(Land l : civLand)
		{
			switch(l.getLandType())
			{
			case PLAIN: foodCount++; break;
			case FOREST:woodCount++; break;
			case HILL:  stoneCount++; break;
			}
			if (l.getBuilding() instanceof Farm)
				foodCount++;
			else if (l.getBuilding() instanceof SawMill)
				woodCount++;
			else if (l.getBuilding() instanceof Mine) {
				if (l.getResourceType() == ResourceType.GOLD)
					goldCount++;
				else stoneCount++;
			}
			else if (l.getBuilding() instanceof City) {
				switch (l.getResourceType())
				{
				case GOLD:
					goldCount++;
					break;
				case STONE:
					stoneCount++;
					break;
				case FOOD:
					foodCount++;
					break;
				case WOOD:
					woodCount++;
					break;
				}
			}
		}
	}

public int getGoldCount() {
	return goldCount;
}

public int getWoodCount() {
	return woodCount;
}

public int getStoneCount() {
	return stoneCount;
}

public int getFoodCount() {
	return foodCount;
}
// for test

public ArrayList<City> getCities()
{
	return cities;
}

public void forceFood(int i) {
	foodCount = i;

}

public int getHappiness() {
	return happiness;
}

public void forceHappiness(int i) {
	happiness = i;

}

public void forceGold(int i) {
	goldCount = i;

}

public void forceWood(int i) {
	woodCount = i;

}

public void forceStone(int i) {
	stoneCount = i;

}

public int getNumberofTechs() {
	// TODO Auto-generated method stub
	return -1;
}

public void researchNewTech(String string) {
	// TODO Auto-generated method stub

}

public ArrayList<Land> getLand() {
	return civLand;
}

public int getLandcount()
{
		int total = 0;
		for(City c: cities)
		{
			total += c.getNumberOfLand();
		}
		return total;
}
}
