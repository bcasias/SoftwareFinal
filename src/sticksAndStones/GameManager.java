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
		//TODO gathering logic
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

	public void forceTurn(int i) {
		turn = i;
	}

	public boolean gameEnd() {
		if (playerCiv.getHappiness() == 0) return true; //lose if happiness reaches 0
		if (turn == 100) return true; //lose on turn 100 if not winning
		return false;
	}

	public void forcePlayerLand(int i) {
		// TODO Auto-generated method stub
		
	}

	public void claimLand() {
		// TODO Auto-generated method stub
		
	}

}
