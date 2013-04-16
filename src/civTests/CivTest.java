package civTests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import sticksAndStones.City;
import sticksAndStones.Civilization;
import sticksAndStones.GameManager;
import sticksAndStones.Land;
import sticksAndStones.TradingPost;

public class CivTest {
	private GameManager game;
	private ArrayList<ArrayList< Land>> map;
	private Civilization civ;
	private City city;
	
	@Before
	public void setup()
	{
		game = new GameManager();
		map = game.getMap();
		civ = game.getPlayerCiv();
		city = civ.getCities().get(0);
	}
	
	@Test
	public void makeBuilding()
	{
		// makes sure resources are subtracted correctly
		// city will start with 100 of every resource
		// city needs 5 gold, wood, and stone
		assertTrue(civ.makeBuilding(new City()));
		// Checks resourceCount
		assertEquals(95, civ.getGoldCount());
		assertEquals(95, civ.getWoodCount());
		assertEquals(95, civ.getStoneCount());
		
		// a TradingPost needs 3 gold, 3 wood, 1 stone
		assertTrue(civ.makeBuilding(new TradingPost()));
		// Checks resourceCount
		assertEquals(92, civ.getGoldCount());
		assertEquals(92, civ.getWoodCount());
		assertEquals(94, civ.getStoneCount());		
	}
	
	@Test
	public void growingCity()
	{
		city.forcePopulation(2);
		assertEquals(City.CityType.VILLAGE, city.getCityType());
		
		city.forcePopulation(5);
		assertEquals(City.CityType.TOWN, city.getCityType());
		
		city.forcePopulation(10);
		assertEquals(City.CityType.CITY, city.getCityType());
	}
	
	@Test
	public void happiness()
	{
		// reduce happiness
		civ.forceFood(-100);
		game.nextTurn();
		assertEquals(9, civ.getHappiness());
		
		// make happiness
		civ.forceFood(100);
		game.nextTurn();
		assertEquals(10, civ.getHappiness());
	}
	
	@Test
	public void resourceTest()
	{
		// For test 
		// city will be at location 3, 7
		// land will be from 2,6 to 4,8
		
		// resources
		// 2 foods
		// 4 woods
		// 4 stone (1+ for city on top of resource)
		// 1 gold (from city)
		
		// 1 food for each population
		// start with a population of 1
		assertEquals(100, civ.getGoldCount());
		assertEquals(100, civ.getWoodCount());
		assertEquals(100, civ.getStoneCount());
		assertEquals(100, civ.getFoodCount());
		
		game.nextTurn();

		assertEquals(101, civ.getGoldCount());
		assertEquals(104, civ.getWoodCount());
		assertEquals(104, civ.getStoneCount());
		assertEquals(101, civ.getFoodCount());
	}
	
	@Test
	public void researchTest()
	{
		// when game initializes the first tech (should be researched)
		assertEquals(1, civ.getNumberofTechs());
		civ.researchNewTech("Pottery");
		for(int i = 0; i < 7; i++)
		{
			game.nextTurn();
		}
		assertEquals(2, civ.getNumberofTechs());
	}
	
}
