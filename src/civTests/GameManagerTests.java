package civTests;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import land.Land;

import org.junit.Before;
import org.junit.Test;

import civilization.Civilization;

import buildings.City;

import sticksAndStones.GameManager;

public class GameManagerTests {
	private GameManager game;
	private Land[][] map;
	private Civilization civ;
	private City city;
	
	@Before
	public void setup()
	{
		game = new GameManager();
		map = game.getMap();
		civ = game.getPlayerCiv();
		city = civ.getCities().get(0);
	}
	
	@Test
	public void checkLossTurn()
	{
		game.forceTurn(99);
		assertFalse(game.gameEnd());
		game.nextTurn();
		assertTrue(game.gameEnd());
	}
	
	@Test
	public void checkLossHappy()
	{
		game.forceTurn(50);
		civ.forceHappiness(1);
		civ.forceFood(-100);
		assertFalse(game.gameEnd());
		game.nextTurn();
		assertTrue(game.gameEnd());
	}
	
	@Test
	public void landCountWin()
	{
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 2; j++)
				civ.getCities().get(0).addLand(i,j);
		assertFalse(game.gameEnd());
		game.claimLand();
		game.nextTurn();
		assertTrue(game.gameEnd());
	}
	
	@Test
	public void resourceWin()
	{
		assertEquals(100, civ.getGoldCount());
		assertEquals(100, civ.getWoodCount());
		assertEquals(100, civ.getStoneCount());
		assertFalse(game.gameEnd());
		civ.forceFood(150);
		civ.forceGold(150);
		civ.forceWood(150);
		civ.forceStone(150);
		game.nextTurn();
		assertTrue(game.gameEnd());
	}
}
