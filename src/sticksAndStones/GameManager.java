package sticksAndStones;

import java.util.ArrayList;

import buildings.Building;
import buildings.City;

import civilization.Civilization;

import land.Land;


public class GameManager {
	private Land[][] map;
	private Civilization playerCiv;
	private int turn;
	private static int boardSizeX;
	private static int boardSizeY;
	
	public GameManager()
	{
		MapGeneration g = new MapGeneration();
		map =  g.generateMap(10, 10);
		boardSizeX = 10;
		boardSizeY = 10;
		playerCiv = new Civilization();
		turn = 0;
	}
	
	public static int calculateIndex(int x, int y) {
		return x * boardSizeY + y; 
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

	public Civilization getPlayerCiv() {
		return playerCiv;
	}

	public void nextTurn() {
		playerCiv.gatherResources();
		int foodConsumed = 0;
		for (City c : playerCiv.getCities()) {
			foodConsumed += c.getPop(); //each population consumes one food
		}
		playerCiv.forceFood(playerCiv.getFoodCount() - foodConsumed); //remove all eaten food for the turn
		if (playerCiv.getFoodCount() < 0) playerCiv.forceHappiness(playerCiv.getHappiness() - 1); //if starving, lose one happiness
		if (playerCiv.getFoodCount() > 0 && playerCiv.getHappiness() < 10) {
			playerCiv.forceHappiness(playerCiv.getHappiness() + 1); //if well-fed and below 10 happiness, add one happiness
		}
		turn++;		
	}
	public void buildBuilding(int locX, int locY, Building building)
	{
		Land l = map[locX][locY];
		if(l.getBuilding() != null)
			if(playerCiv.getLand().contains(l)) {
				playerCiv.makeBuilding(building);
				l.setBuilding(building);
			}
	}

	public void forceTurn(int i) {
		turn = i;
	}

	public boolean gameEnd() {
		if (playerCiv.getHappiness() == 0) return true; //lose if happiness reaches 0
		if (turn == 100) return true; //lose on turn 100 if not winning
		if (playerCiv.getLandcount() > 24) return true;
		if (playerCiv.getGoldCount() > 125 && playerCiv.getStoneCount() > 125 && playerCiv.getWoodCount() > 125)
			return true;
		return false;
	}

	public void forcePlayerLand(int i) {
		// TODO Auto-generated method stub
		
	}

	public void claimLand() {
		// TODO Auto-generated method stub
		
	}

}
