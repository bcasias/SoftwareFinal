package civilization;

import java.util.ArrayList;

import buildings.Building;
import buildings.City;

public class Civilization {
	private ArrayList<City> cities;
	private int goldCount, foodCount, stoneCount, woodCount;
	private int happiness;
	
	public Civilization()
	{
		goldCount = 100;
		foodCount = 100;
		stoneCount = 100;
		woodCount = 100;
		happiness = 10;
	}
	
	public boolean makeBuilding(Building building) {
		// TODO Auto-generated method stub
		return false;
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
}
