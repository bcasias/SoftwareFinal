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
	public void loadMap()
	{
		map = game.getMap();
		
		// Checking map size for a 10 rows by 1l columns
		assertTrue(map.size() == 10); // rows
		for(int i = 0; i < map.size(); i++)
		{
			assertTrue(map.get(i).size() == 11); // check columns
		}
		
		// Check if board pieces are the correct land type
		assertTrue(map.get(0).get(4).getInitial() == 'm' );
		assertTrue(map.get(1).get(2).getInitial() == 'd' );
		assertTrue(map.get(1).get(4).getInitial() == 'p' );
		assertTrue(map.get(2).get(7).getInitial() == 'h' );
		assertTrue(map.get(8).get(4).getInitial() == 'f' );
		assertTrue(map.get(4).get(4).getInitial() == 'o' );
		
		//check for rivers
		assertTrue(map.get(1).get(5).hasRiver());
		assertFalse(map.get(1).get(1).hasRiver());
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
	public void resourceGeneration()
	{
		// Check if a land has resources
		assertTrue(map.get(1).get(3).hasResource());
		assertTrue(map.get(1).get(8).hasResource());
		assertTrue(map.get(4).get(7).hasResource());
		assertTrue(map.get(8).get(6).hasResource());
		
		// e is a food tile (don't ask)
		assertTrue(map.get(1).get(3).getResourceInitial() == 'e');
		assertTrue(map.get(1).get(8).getResourceInitial() == 'g');
		assertTrue(map.get(8).get(6).getResourceInitial() == 's');
		assertTrue(map.get(4).get(7).getResourceInitial() == 'w');
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
	
	@Test
	public void buildingConstructed()
	{
		Building b = map.get(3).get(7).getBuilding();
		assertTrue(b instanceof City);
	}
}
