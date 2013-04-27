package sticksAndStones;

import java.awt.Point;
import java.util.ArrayList;

import civilization.Civilization;
import civilization.Unit;

public class MovementManager {
	private static ArrayList<Civilization> civs = new ArrayList<Civilization>();;
	
	public MovementManager()
	{
	}
	public static void addCiv(Civilization c)
	{
		civs.add(c);
	}
	
	public static Point canMoveTo(Unit unit, Direction direction)
	{
		Point currentLocation = unit.getLocation();
		Point endPoint;
		switch(direction)
		{
			case NORTH: 
				endPoint = new Point(currentLocation.x, currentLocation.y - 1); 
				if(!validPoint(endPoint)) return null;
				break;
			case EAST:  
				endPoint = new Point(currentLocation.x + 1, currentLocation.y); 
				if(!validPoint(endPoint)) return null;
				break;
			case SOUTH: 
				endPoint = new Point(currentLocation.x, currentLocation.y + 1); 
				if(!validPoint(endPoint)) return null;
				break;
			case WEST:  
				endPoint = new Point(currentLocation.x -1, currentLocation.y); 
				if(!validPoint(endPoint)) return null;
				break;
			default: 
				endPoint = new Point(currentLocation); // should never be reached
				break;
		}
		
		for(Civilization c : civs)
		{
			if(c.hasCityAt(endPoint))
				return null;
			if(c.hasUnitAt(endPoint))
				return attackUnit(unit, c.getUnitAt(endPoint));
		} // end for
		
		return endPoint;
	} // end function

	private static boolean validPoint(Point point) {
		int boardSizeX = GameManager.getBoardSizeX();
		int boardSizeY = GameManager.getBoardSizeY();
		
		if(point.x < 0 || point.x >= boardSizeX) return false;
		if(point.y < 0 || point.y >= boardSizeY) return false;
		
		return true;
	}
	
	public static Point attackUnit(Unit attacker, Unit defender)
	{
		defender.takeDamage(attacker.getStrength());
		if(defender.getHealth() <= 0)
		{
			for(Civilization c : civs)
			{
				if(!(c.getUnitAt(defender.getLocation()) != null))
				{
					c.removeUnit(defender);
					defender.getLocation();
				}
			}
		} // end if
		return attacker.getLocation();
	}
}
