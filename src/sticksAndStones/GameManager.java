package sticksAndStones;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import buildings.Building;
import buildings.City;

import civilization.Civilization;
import civilization.Unit;

import land.Land;
import land.Land.LandType;


public class GameManager extends JPanel { // this draws the board to the screen
	private static Land[][] map;
	private Civilization playerCiv;
	private int turn;
	private static int boardSizeX;
	private static int boardSizeY;
	private Unit monster;
	
	public GameManager()
	{
		MapGeneration g = new MapGeneration();
		map =  g.generateMap(10, 10);
		boardSizeX = 10;
		boardSizeY = 10;
		boolean foundPlaceForCity = false;
		int randX, randY;
		Random rand = new Random();
		// place city
		while(!foundPlaceForCity)
		{
			randX = rand.nextInt(boardSizeX);
			randY = rand.nextInt(boardSizeY);
			if(map[randX][randY].getLandType() != LandType.WATER && map[randX][randY].getLandType() != LandType.MOUNTAIN )
			{
				foundPlaceForCity = true;
				playerCiv = new Civilization(map, randX, randY);
			}
		}
		turn = 0;
		// TODO add in monster
	}
	
	public static int calculateIndex(int x, int y) {
		return x * boardSizeY + y; 
	}
	
	public static Point calcReverseIndex(int index) {
		int x = index / boardSizeY;
		int y = index % boardSizeY;
		return new Point(x, y);
	}
	
	public static int getBoardSizeX() {
		return boardSizeX;		
	}
	
	public static int getBoardSizeY() {
		return boardSizeY;
	}
		
	/* All of these functions are for testing */
	
	public Land[][] getMap()
	{
		return map;
	}
	
	public Land getSquare(int index) {
		Point p = sticksAndStones.GameManager.calcReverseIndex(index);
		return map[p.x][p.y];
	}

	public Civilization getPlayerCiv() {
		return playerCiv;
	}
	
	public void nextTurn() {
		playerCiv.update();
		gameEnd(); // TODO Logic???
		turn++;		
	}
	
	public void buildBuilding(int locX, int locY, Building building)
	{
		playerCiv.makeBuilding(locX, locY, building);
	}

	public void forceTurn(int i) {
		turn = i;
	}

	public boolean gameEnd() {
		if (playerCiv.getHappiness() == 0) return true; //lose if happiness reaches 0
		if (turn == 100) return true; //lose on turn 100 if not winning
		if (playerCiv.getLand().size() > 24) return true;
		if (playerCiv.getGoldCount() > 125 && playerCiv.getStoneCount() > 125 && playerCiv.getWoodCount() > 125)
			return true;
		return false;
	}
	
	public static Land getLandAt(Point point)
	{
		return map[point.x][point.y];
	}

	public String[][] drawOUtline()
	// creates a list of what to draw in each cell
	// format "<landType>,<City>, <Unit> "
	{
		String[][] architect = new String[boardSizeX][boardSizeY];
		
		for(int i = 0; i < boardSizeX; i++)
		{
			for(int j = 0; j < boardSizeY; j++)
			{
				// Add land Type
				String value = "";
				value += map[i][j].getLandType().getType();
				if (map[i][j].hasRiver()) value += 'r';
				value += ",";
				// Add cities
				if(playerCiv.hasCityAt(new Point(i,j)))
				{
					value += playerCiv.getCityAt(new Point(i,j)).getCityType().getType();
				}// end if
				else
				{
					value += ",";
				}// end else
				// Add units
				if(playerCiv.hasUnitAt(new Point(i,j)))
				{
					value += playerCiv.getUnitAt(new Point(i,j)).getUnitType().getType();
					value += playerCiv.getUnitAt(new Point(i,j)).getHealth();
				}// end if
			} // end for j
		}// end for i
		
		return architect;
	}
	
}
