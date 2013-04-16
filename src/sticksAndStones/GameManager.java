package sticksAndStones;

import java.util.ArrayList;


public class GameManager {
	private ArrayList<ArrayList<Land> > map;
	private Civilization playerCiv;
	
	public GameManager()
	{
		map = new ArrayList<ArrayList<Land> >();
		playerCiv = new Civilization();
	}
	
	/* All of these functions are for testing */
	
	public ArrayList<ArrayList<Land> > getMap()
	{
		return map;
	}

	public Civilization getPlayerCiv() {
		// TODO Auto-generated method stub
		return null;
	}

	public void nextTurn() {
		// TODO Auto-generated method stub
		
	}

	public void forceTurn(int i) {
		// TODO Auto-generated method stub
		
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
