package civilization;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JOptionPane;

import resource.Resource.ResourceType;

import buildings.Building;
import buildings.City;
import buildings.ImprovementBuilding;

import civilization.Unit.UnitType;

import land.Land;
import land.Land.LandType;

public class Civilization {
	private ArrayList<City> cities;
	private Land[][] map;
	private ArrayList<ImprovementBuilding> buildings;
	private ArrayList<Land> civLand;
	private ArrayList<Unit> units;
	private int goldCount, foodCount, stoneCount, woodCount;
	private int happiness, happyCount; // number of turns until more happiness

	public Civilization(Land[][] board, int CityLocX, int CityLocY)
	{
		map = board;
		buildings = new ArrayList<ImprovementBuilding>();
		cities = new ArrayList<City>();
		//cities.add(new City(0,0));
		civLand = new ArrayList<Land>();
		goldCount = 10;
		foodCount = 10;
		stoneCount = 10;
		woodCount = 10;
		happiness = 5;
		units = new ArrayList<Unit>();
		happyCount = 5;
		
		// initialize first City
		civLand.add(map[CityLocX][CityLocY]);
		cities.add(new City(CityLocX, CityLocY));
	}
	
	public void update()
	{
		gatherResources();
		consumeResources();
		for (City c : cities) c.update();
		for(Unit u : units)
		{
			u.update();
		}
	}

	public boolean makeBuilding(int locx, int locy, Building building) {
		Point landLoc = new Point(locx, locy);
		//if(civLand.contains(landLoc))
			if (goldCount >= building.getGoldCost() && stoneCount >= building.getStoneCost() && woodCount >= building.getWoodCost()) {
				goldCount -= building.getGoldCost();
				stoneCount -= building.getStoneCost();
				woodCount -= building.getWoodCost();
				if(building instanceof City) {
					Point loc = building.getLocation();
					cities.add((City) building);
					civLand.add(map[loc.x][loc.y]);
				}
				else
					buildings.add((ImprovementBuilding) building);
				return true; //if sufficient resources are available, building is possible
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "You do not have resources to build this"  + "!");
				return false;
			}
		//return false;
	}

	public void gatherResources()
	{
		if(civLand.size() > 0)
		for(Land l : civLand)
		{
			switch(l.getLandType())
			{
			case PLAIN: foodCount++; break;
			case FOREST:woodCount++; break;
			case HILL:  stoneCount++; break;
			}
			if (l.hasRiver()) foodCount++;
		}// end for
		
		for(ImprovementBuilding i : buildings)
		{
				switch(i.getBuildingType()) // FARM, BARRACK, MINE, SAWMILL, TRADINGPOST
				{
				case FARM : foodCount++; break;
				case BARRACK: break;
				case MINE: if (map[(int) i.getLocation().getX()][(int) i.getLocation().getY()].getResourceType() == ResourceType.GOLD) 
					goldCount++;
				else stoneCount++;
				break;
				case SAWMILL: woodCount++; break;
				case TRADINGPOST: goldCount++; break;
				}// end switch
		}// end For
		for (City c : cities) {
			Point loc = c.getLocation();
			if (map[(int) loc.getX()][(int) loc.getY()].hasResource()) {
				switch(map[(int) loc.getX()][(int) loc.getY()].getResourceType()) {
				case FOOD: foodCount++; break;
				case WOOD: woodCount++; break;
				case STONE: stoneCount++; break;
				case GOLD: goldCount++; break;
				}
			}
		}
	} // end function
	
	private void consumeResources() {
		for(City c : cities)
		{
			foodCount -= c.getPop()/2;
		}
		foodCount -= units.size();
		// change happiness
		if(foodCount < 0 ) 
		{
			happiness--;
			happyCount = 5;
		}// end if
		else
		{
			if(happyCount <= 0)
			{
				happyCount = 5;
				setHappiness(happiness++);
			}
		}// end else
		
	}// end function
	
	private void setHappiness(int i) {
		if ( i > 10)
			happiness = 10;
		else
			happiness = i;
	}

	public boolean addLand(int locX, int locY)
	{
		Point north = new Point(locX, locY -1);
		Point east = new Point(locX + 1, locY);
		Point south = new Point(locX, locY + 1);
		Point west = new Point(locX -1, locY);
		if(civLand.contains(north) || civLand.contains(east) 
				|| civLand.contains(south) ||  civLand.contains(west)){
			civLand.add(map[locX][locY]);
			return true;
		}// end if
		
		return false;
	}
	
	public boolean makeUnit(int cityLocX, int cityLocY, UnitType type)
	{
		// Units cost Gold
		if(goldCount < 5)
			return false;
		goldCount -= 5;
		Point cityLocaiton = new Point(cityLocX, cityLocY);
		for(City c : cities)
		{
			if(c.isLocatedAt(cityLocaiton))
			{
				for(Unit u : units)
				{
					if(u.isAtLocation(cityLocaiton)) // unit on the city tile
						return false;
				} // end for
				units.add(new Unit(cityLocaiton, type));
				return true;
			}// end if
		}// end for
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
	
	public void setCivLand(ArrayList<Land> civLand) {
		this.civLand = civLand;
	}
	
	public void forceFood(int i) {
		foodCount = i;
	
	}
	
	public int getHappiness() {
		return happiness;
	}
	
	public ArrayList<ImprovementBuilding> getBuildings() {
		return buildings;
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
	
	public ArrayList<Land> getLand() {
		return civLand;
	}

	public boolean hasCityAt(Point point) {
		for(City c : cities)
			if(c.isLocatedAt(point))
				return true;
		return false;
	}
	
	public boolean hasImprovementBuildingAt(Point point) {
		for(ImprovementBuilding b : buildings)
			if(b.isLocatedAt(point))
				return true;
		return false;
	}

	public City getCityAt(Point point) {
		for(City c : cities)
			if(c.isLocatedAt(point))
				return c;
		return null;
	}

	public boolean hasUnitAt(Point point) {
		for(Unit u : units)
			if(u.isAtLocation(point))
				return true;
		return false;
	}

	public Unit getUnitAt(Point point) {
		for(Unit u : units)
			if(u.isAtLocation(point))
				return u;
		return null;
	}

	public void removeUnit(Unit defender) {
		units.remove(defender);
		
	}

	public ArrayList<Unit> getUnits() {
		return units;
	}

}
