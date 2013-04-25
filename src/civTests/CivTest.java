package civTests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;
import land.Land;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import civilization.Civilization;

import buildings.City;
import buildings.TradingPost;

import sticksAndStones.GameManager;

public class CivTest {
	private GameManager game;
	private Land[][] map;
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
		assertTrue(civ.makeBuilding(new City(1,1)));
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
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 2; j++)
				civ.getCities().get(0).addLand2(i,j);
		
		// 1 food for each population
		// start with a population of 1
		assertEquals(100, civ.getGoldCount());
		assertEquals(100, civ.getWoodCount());
		assertEquals(100, civ.getStoneCount());
		assertEquals(100, civ.getFoodCount());
		
		game.nextTurn();

		assertTrue(civ.getGoldCount() > 100 || civ.getWoodCount() > 100 || civ.getStoneCount() > 100 || civ.getFoodCount() > 100);
	}	
}
