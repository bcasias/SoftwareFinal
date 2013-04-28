package sticksAndStones;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import resource.Resource.ResourceType;

import land.Land;

import land.Land.LandType;

public class MapGeneration {
	/*
	 * Land generation happens in several phases
	 * Phase1: a probability of farm, wood, hill, and Mountains are put into the the map
	 * Phase 2: A clump of desert is added
	 * Phase 3: A clump of water is added
	 * Phase 4: Rivers are added
	 * Phase 5: Resources are added
	 */
	Random rand;
	public MapGeneration()
	{
		rand = new Random();
	}
	public Land[][] generateMap(int width, int height)
	{
		Land landMass[][] = new Land[height][width];
		landMass = generateBasicLand(landMass);
		landMass = clumpOfDesert(landMass);
		landMass = clumpOfWater(landMass);
		landMass = addRivers(landMass);
		landMass = addResources(landMass);
		return landMass;
	}


	private Land[][] generateBasicLand(Land[][] land)
	{ // Phase 1
		Random rand = new Random();
		for(int i = 0; i < land.length; i++)
		{
			for(int j = 0; j < land[i].length; j++)
			{
				int next = rand.nextInt(100);
				if(next < 31)
					land[i][j] = new Land(i,j, LandType.PLAIN);
				else if(next >= 31 && next < 63 )
					land[i][j] = new Land(i,j, LandType.FOREST);
				else if (next >= 63 && next <=93 )
					land[i][j] = new Land(i,j, LandType.HILL);
				else
					land[i][j] = new Land(i,j, LandType.MOUNTAIN);
			}
		}
		boolean hasMountain = false;
		for(int i = 0; i < land.length; i++)
			for(int j = 0; j < land[i].length; j++)
				if(land[i][j].getLandType() == LandType.MOUNTAIN)
					hasMountain = true;
		if(!hasMountain)
			return generateBasicLand(land);
		return land;
	}
	
	private Land[][] clumpOfDesert(Land[][] land)
	{
		int tilesForDesert = (int)((land.length * land[0].length)* .05);
		int width = (int)tilesForDesert/2;
		int height = tilesForDesert - width;
		int tilesToRemove = (int)((width * height)*.5);
		Point[][] desert = new Point[width][height];
		
		for(int i = 0; i < desert.length; i++) {
			for(int j = 0; j < desert[i].length; j++) {
				desert[i][j] = new Point(i,j);
			}
		}
		
		ArrayList <Point> edge = new ArrayList<Point>();
		for(int i = 0; i < desert[0].length; i++)
		{
			if(!edge.contains(desert[0][i]))
				edge.add(desert[0][i]);
			if(!edge.contains(desert[desert.length -1][i]))
				edge.add(desert[desert.length -1][i]);
		}
		for(int i = 0; i < desert.length; i++)
		{
			if(!edge.contains(desert[i][0]))
				edge.add(desert[i][0]);
			if(!edge.contains(desert[i][desert[i].length -1]))
				edge.add(desert[i][desert[i].length -1]);
		}
		for(int i = 0; i < tilesToRemove; i++)
		{
			int next = rand.nextInt(edge.size());
			Point p = edge.get(next);
			edge.remove(next);
			desert[p.x][p.y] = null;
			if(p.x - 1 > 0)
			{
				if(desert[p.x -1][p.y] != null)
				{
					Point p1 = desert[p.x-1][p.y];
					if(!edge.contains(p1))
						edge.add(desert[p1.x][p1.y]);
				}
			}
			if(p.x + 1 < desert.length)
			{
				if(desert[p.x + 1][p.y] != null)
				{
					Point p1 = desert[p.x +1][p.y];
					if(!edge.contains(p1))
						edge.add(desert[p1.x][p1.y]);
				}
			}
			if(p.y - 1 > 0)
			{
				if(desert[p.x][p.y - 1] != null)
				{
					Point p1 = desert[p.x][p.y - 1];
					if(!edge.contains(p1))
						edge.add(desert[p1.x][p1.y]);
				}
			}
			if(p.y + 1 < desert[0].length)
			{
				if(desert[p.x][p.y + 1] != null)
				{
					Point p1 = desert[p.x][p.y + 1];
					if(!edge.contains(p1))
						edge.add(desert[p1.x][p1.y]);
				}
			}
		}
		// overlay on land
		int locX = rand.nextInt(land.length - width);
		int locY = rand.nextInt(land[0].length - height);
		
		for(int i = 0; i < desert.length; i++) {
			for(int j = 0; j < desert[i].length; j++) {
				if(desert[i][j] != null)
				{
					land[locX + i][locY + j] = new Land(i,j, LandType.DESERT);
				}
			}
		}
		
		return land;
	}
	
	private Land[][] clumpOfWater(Land[][] land) {
		int tilesForDesert = (int)((land.length * land[0].length)* .075);
		int width = (int)tilesForDesert/2;
		int height = tilesForDesert - width;
		int tilesToRemove = (int)((width * height)*.5);
		Point[][] water = new Point[width][height];
		
		for(int i = 0; i < water.length; i++) {
			for(int j = 0; j < water[i].length; j++) {
				water[i][j] = new Point(i,j);
			}
		}
		
		ArrayList <Point> edge = new ArrayList<Point>();
		edge.add(water[0][0]);
		edge.add(water[water.length -1 ][0]);
		edge.add(water[0][water[0].length -1 ]);
		edge.add(water[water.length -1][water[0].length -1]);

		for(int i = 0; i < tilesToRemove; i++)
		{
			int next = rand.nextInt(edge.size());
			Point p = edge.get(next);
			edge.remove(next);
			water[p.x][p.y] = null;
			if(p.x - 1 > 0)
			{
				if(water[p.x -1][p.y] != null)
				{
					Point p1 = water[p.x-1][p.y];
					if(!edge.contains(p1))
						edge.add(water[p1.x][p1.y]);
				}
			}
			if(p.x + 1 < water.length)
			{
				if(water[p.x + 1][p.y] != null)
				{
					Point p1 = water[p.x +1][p.y];
					if(!edge.contains(p1))
						edge.add(water[p1.x][p1.y]);
				}
			}
			if(p.y - 1 > 0)
			{
				if(water[p.x][p.y - 1] != null)
				{
					Point p1 = water[p.x][p.y - 1];
					if(!edge.contains(p1))
						edge.add(water[p1.x][p1.y]);
				}
			}
			if(p.y + 1 < water[0].length)
			{
				if(water[p.x][p.y + 1] != null)
				{
					Point p1 = water[p.x][p.y + 1];
					if(!edge.contains(p1))
						edge.add(water[p1.x][p1.y]);
				}
			}
		}
		
		for(int i = 0; i < edge.size(); i++)
		{
			Point p = edge.get(i);
			if(p.x + 1< water.length && water[p.x + 1][p.y] != null)
					continue;
			else if(p.x - 1 > 0 && water[p.x - 1][p.y] != null)
				continue;
			else if(p.y + 1 < water[0].length && water[p.x][p.y + 1] != null)
				continue;
			else if(p.y - 1 > 0 && water[p.x][p.y - 1] != null)
				continue;
			
			water[p.x][p.y] = null;
		}
		
		int locX = rand.nextInt(land.length - width);
		int locY = rand.nextInt(land[0].length - height);
		
		for(int i = 1; i < water.length; i++) {
			for(int j = 1; j < water[i].length; j++) {
				if(water[i][j] != null)
				{
					land[locX + i][locY + j] = new Land(locX + i,locY +j, LandType.WATER);
				}
			}
		}
		
		boolean hasWater = false;
		for(int i = 1; i < water.length; i++) 
			for(int j = 1; j < water[i].length; j++)
				if(land[i][j].getLandType() == LandType.WATER)
					hasWater = true;
		if(!hasWater)
			return clumpOfWater(land);
		return land;	
	}
	
	private Land[][] addRivers(Land[][] land) {
		int totalRivers = (land.length * land[0].length)/70;
		if (totalRivers == 0) totalRivers = 1;
		
		for(int i = 0; i < totalRivers; i++)
		{
			int next = rand.nextInt(10);
				land = makeRiverFromMountain(land);
		}
		
		return land;
	}

	private Land[][] makeRiverFromMountain(Land[][] land) 
	{
		ArrayList<Point> mountains = new ArrayList<Point>();
		
		for(int i = 0; i < land.length; i++)
		{
			for(int j = 0; j < land[i].length; j++)
			{
				if(land[i][j].getLandType() == LandType.MOUNTAIN)
				{
					mountains.add(new Point(i,j));
				}
			}
		}
		
		if(mountains.size() == 0) return land;
		
		Point loc = mountains.get(rand.nextInt(mountains.size()));
		
		switch(rand.nextInt(4))
		{
		case 0: if(loc.x + 1 < land.length) loc.setLocation(loc.x + 1, loc.y); break;
		case 1: if( loc.x - 1> 0) loc.setLocation(loc.x - 1, loc.y); break;
		case 2: if( loc.y + 1 < land[0].length) loc.setLocation(loc.x, loc.y + 1); break;
		case 3: if ( loc.y > 0) loc.setLocation(loc.x, loc.y - 1); break;
		}
		
		int riverLength = 7;
		int maxCount = 20;
		while (riverLength > 0 && maxCount >0)
		{
			switch(rand.nextInt(4))
			{
			case 0:
				if(loc.x + 1 < land.length && !land[loc.x + 1][loc.y].hasRiver() && land[loc.x + 1][loc.y].canContainRiver())
				{
					LandType type = land[loc.x + 1][loc.y].getLandType();
					loc.setLocation(loc.x + 1, loc.y);
					land[loc.x][loc.y] = new Land(loc.x, loc.y, type, true);
					riverLength--;
					land = makeLandWithRiver(loc.x, loc.y, land);
				}
				break;
			case 1:
				if(loc.x - 1> 0 && (!land[loc.x - 1][loc.y].hasRiver() && land[loc.x - 1][loc.y].canContainRiver()))
				{
					LandType type = land[loc.x - 1][loc.y].getLandType();
					loc.setLocation(loc.x - 1, loc.y);
					land[loc.x][loc.y] = new Land(loc.x, loc.y, type, true);
					riverLength--;
					land = makeLandWithRiver(loc.x, loc.y, land);
				}
				break;
			case 2:
				if(loc.y  + 1< land[0].length && !land[loc.x][loc.y + 1].hasRiver() && land[loc.x][loc.y+1].canContainRiver())
				{
					LandType type = land[loc.x ][loc.y + 1].getLandType();
					loc.setLocation(loc.x, loc.y + 1);
					land[loc.x][loc.y] = new Land(loc.x, loc.y, type, true);
					riverLength--;
					land = makeLandWithRiver(loc.x, loc.y, land);
				}
				break;
			case 3:
				if(loc.y > 0 && !land[loc.x][loc.y-1].hasRiver() && land[loc.x][loc.y-1].canContainRiver())
				{
					LandType type = land[loc.x ][loc.y - 1].getLandType();
					loc.setLocation(loc.x, loc.y -1);
					land[loc.x][loc.y] = new Land(loc.x, loc.y, type, true);
					riverLength--;
					land = makeLandWithRiver(loc.x, loc.y, land);
				}
				break;
			}
			--maxCount;
		}

		return land;
	}

	private Land[][] makeLandWithRiver(int locX, int locY, Land[][] land) {
		if(land[locX][locY].getLandType() == LandType.PLAIN)
		{
			land[locX][locY] = new Land(locX, locY, LandType.PLAIN, true);
			
		}
		else if(land[locX][locY].getLandType() == LandType.DESERT)
		{
			land[locX][locY] = new Land(locX, locY, LandType.DESERT, true);
		}
		else if(land[locX][locY].getLandType() == LandType.HILL)
		{
			land[locX][locY] = new Land(locX, locY, LandType.HILL, true);
		}
		else if(land[locX][locY].getLandType() == LandType.FOREST)
		{
			land[locX][locY] = new Land(locX, locY, LandType.FOREST, true);
		}
		return land;
	}
	
	private Land[][] addResources(Land[][] land) {
		Random rand = new Random();
		boolean haveResource;
		boolean haveRiver;
		for(int i = 0; i < land.length; i++)
		{
			for(int j = 0; j < land[i].length; j++)
			{
				boolean haveStone;
				if(rand.nextInt(2) == 0) haveResource = true;
				else haveResource = false;
				if(rand.nextInt(2) == 0) haveStone = true;
				else haveStone = false;
				
				if(land[i][j].getLandType() == LandType.PLAIN && haveResource)
				{
					haveRiver = land[i][j].hasRiver();
					land[i][j] = new Land(i,j,LandType.PLAIN,haveRiver, ResourceType.FOOD);
				}
				else if(land[i][j].getLandType() == LandType.HILL && haveResource)
				{
					haveRiver = land[i][j].hasRiver();
					if(haveStone)
						land[i][j] = new Land(i,j,LandType.HILL,haveRiver, ResourceType.STONE);
					else 
						land[i][j] = new Land(i,j,LandType.HILL,haveRiver, ResourceType.GOLD);
				}
				else if(land[i][j].getLandType() == LandType.FOREST)
				{
					haveRiver = land[i][j].hasRiver();
					land[i][j] = new Land(i,j,LandType.FOREST,haveRiver, ResourceType.WOOD);
				}
			}
		}
		return land;
	}
}
