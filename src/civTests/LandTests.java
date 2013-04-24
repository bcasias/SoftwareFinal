package civTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;
import land.Land;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import buildings.Building;
import buildings.City;

import sticksAndStones.GameManager;

public class LandTests {
	private GameManager game;
	private Land[][] map;
	
	@Before
	public void setup()
	{
		game = new GameManager();
		map = game.getMap();
	}
	
	@Test
	public void MapGeneration()
	{
		// checking random map generation
		int mountainCount = 0;
		int desertCount = 0;
		int plainCount = 0;
		int hillCount = 0;
		int forestCount = 0;
		int waterCount = 0;
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				switch(map[i][j].getLandType())
				{
				case MOUNTAIN: 	mountainCount++; 	break;
				case DESERT: 	desertCount++;		break;
				case PLAIN: 	plainCount++;		break;
				case HILL: 		hillCount++; 		break;
				case FOREST: 	forestCount++; 		break;
				case WATER: 	waterCount++; 		break;
				}
			}
		}
		assertTrue(plainCount 	> 1);
		assertTrue(hillCount 	> 1);
		assertTrue(forestCount 	> 1);
		assertTrue(waterCount 	> 1);
	}
	
	@Test
	public void randomResourceGeneration()
	{
		int foodCount = 0;
		int goldCount = 0;
		int stoneCount = 0;
		int woodCount = 0;
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				switch(map[i][j].getResourceType())
				{
				case FOOD: foodCount++; 	break;
				case GOLD: goldCount++;		break;
				case STONE: stoneCount++;	break;
				case WOOD: woodCount++; 	break;
				}
			}
		}
		assertTrue(foodCount  > 0);
		assertTrue(goldCount  > 0);
		assertTrue(stoneCount > 0);
		assertTrue(woodCount  > 0);
	}
}