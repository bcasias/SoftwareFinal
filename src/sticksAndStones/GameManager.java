package sticksAndStones;

import java.util.ArrayList;

import buildings.City;

import civilization.Civilization;

import land.Land;


public class GameManager {
	private ArrayList<ArrayList<Land> > map;
	private Civilization playerCiv;
	int turn;
	
	public GameManager()
	{
		map = new ArrayList<ArrayList<Land> >();
		playerCiv = new Civilization();
		turn = 0;
	}
	
	/* All of these functions are for testing */
	
	public ArrayList<ArrayList<Land> > getMap()
	{
		return map;
	}

	public Civilization getPlayerCiv() {
		return playerCiv;
	}

	public void nextTurn() {
		int foodConsumed = 0;
		for (City c : playerCiv.getCities()) {
			foodConsumed += c.getPop(); //each population consumes one food
		}
		playerCiv.forceFood(playerCiv.getFoodCount() - foodConsumed);
		turn++;
		// TODO Auto-generated method stub
		
	}

	public void forceTurn(int i) {
		turn = i;
		
	}

	public boolean gameEnd() {
		// TODO Auto-generated method stub
		return false;
	}

	public void forcePlayerLand(int i) {
		// TODO Auto-generated method stub
		
	}

	public void claimLand() {
		// TODO Auto-generated method stub
		
	}

}
