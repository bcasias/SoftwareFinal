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
	GameManager game;
	ArrayList<ArrayList< Land>> map;
	
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
		for(int i = 0; i < map.size(); i++)
		{
			for(int j = 0; j < map.get(i).size(); i++)
			{
				switch(map.get(i).get(j).getInitial())
				{
				case 'm': mountainCount++; 	break;
				case 'd': desertCount++;	break;
				case 'p': plainCount++;		break;
				case 'h': hillCount++; 		break;
				case 'f': forestCount++; 	break;
				case 'o': waterCount++; 	break;
				}
			}
		}
		assertTrue(mountainCount> 5);
		assertTrue(desertCount 	> 5);
		assertTrue(plainCount 	> 5);
		assertTrue(hillCount 	> 5);
		assertTrue(forestCount 	> 5);
		assertTrue(waterCount 	> 5);
	}
	
	@Test
	public void randomResourceGeneration()
	{
		int foodCount = 0;
		int goldCount = 0;
		int stoneCount = 0;
		int woodCount = 0;
		for(int i = 0; i < map.size(); i++)
		{
			for(int j = 0; j < map.get(i).size(); i++)
			{
				switch(map.get(i).get(j).getResourceInitial())
				{
				case 'e': foodCount++; 		break;
				case 'g': goldCount++;		break;
				case 's': stoneCount++;		break;
				case 'w': woodCount++; 		break;
				}
			}
		}
		assertTrue(foodCount  > 1);
		assertTrue(goldCount  > 1);
		assertTrue(stoneCount > 1);
		assertTrue(woodCount  > 1);
	}
}