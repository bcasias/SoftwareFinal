package sticksAndStones;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import resource.Food;
import resource.Wood;

import land.Desert;
import land.Forest;
import land.Hill;
import land.Land;
import land.Mountain;
import land.Plain;
import land.Water;

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
					land[i][j] = new Plain();
				else if(next >= 31 && next < 63 )
					land[i][j] = new Forest();
				else if (next >= 63 && next <=96 )
					land[i][j] = new Hill();
				else
					land[i][j] = new Mountain();
			}
		}
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
					land[locX + i][locY + j] = new Desert();
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
					land[locX + i][locY + j] = new Water();
				}
			}
		}
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
				if(land[i][j] instanceof Mountain)
				{
					mountains.add(new Point(i,j));
				}
			}
		}
		
		if(mountains.size() == 0) return land;
		
		Point loc = mountains.get(rand.nextInt(mountains.size()));
		
		int riverLength = 7;
		int maxCount = 20;
		while (riverLength > 0 && maxCount >0)
		{
			switch(rand.nextInt(4))
			{
			case 0:
				if(loc.x + 1 < land.length && !land[loc.x + 1][loc.y].hasRiver() && land[loc.x + 1][loc.y].canContainRiver())
				{
					land[loc.x + 1][loc.y] = new Plain(true);
					loc.setLocation(loc.x + 1, loc.y);
					riverLength--;
					land = makeLandWithRiver(loc.x, loc.y, land);
				}
				break;
			case 1:
				if(loc.x - 1> 0 && (!land[loc.x - 1][loc.y].hasRiver() && land[loc.x - 1][loc.y].canContainRiver()))
				{
					land[loc.x - 1][loc.y] = new Plain(true);
					if(loc.x != 0)
					loc.setLocation(loc.x - 1, loc.y);
					riverLength--;
					land = makeLandWithRiver(loc.x, loc.y, land);
				}
				break;
			case 2:
				if(loc.y  + 1< land[0].length && !land[loc.x][loc.y + 1].hasRiver() && land[loc.x][loc.y+1].canContainRiver())
				{
					land[loc.x][loc.y + 1] = new Plain(true);
					loc.setLocation(loc.x, loc.y + 1);
					riverLength--;
					land = makeLandWithRiver(loc.x, loc.y, land);
				}
				break;
			case 3:
				if(loc.y > 0 && !land[loc.x][loc.y-1].hasRiver() && land[loc.x][loc.y-1].canContainRiver())
				{
					if(loc.y != 0)
					loc.setLocation(loc.x, loc.y -1);
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
		if(land[locX][locY] instanceof Plain)
		{
			land[locX][locY] = new Plain(true);
		}
		else if(land[locX][locY] instanceof Desert)
		{
			land[locX][locY] = new Desert(true);
		}
		else if(land[locX][locY] instanceof Hill)
		{
			land[locX][locY] = new Hill(true);
		}
		else if(land[locX][locY] instanceof Forest)
		{
			land[locX][locY] = new Forest(true);
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
				if(rand.nextInt(2) == 0) haveResource = true;
				else haveResource = false;
				
				if(land[i][j] instanceof Plain && haveResource)
				{
					haveRiver = land[i][j].hasRiver();
					land[i][j] = new Plain(haveRiver, new Food());
				}
				else if(land[i][j] instanceof Hill && haveResource)
				{
					haveRiver = land[i][j].hasRiver();
					land[i][j] = new Hill(haveRiver, new Food());
				}
				else if(land[i][j] instanceof Forest && haveResource)
				{
					haveRiver = land[i][j].hasRiver();
					land[i][j] = new Forest(haveRiver, new Wood());
				}
			}
		}
		return land;
	}
}
