package sticksAndStones;

import java.awt.Point;
import java.util.ArrayList;

import civilization.Civilization;
import civilization.Unit;

public class MovementManager {
	private static GameManager game;
	private static ArrayList<Civilization> civs = new ArrayList<Civilization>();
	public MovementManager()
	{

	}
	public static void addCiv(Civilization c)
	{
		civs.add(c);
	}
	
	public static void  addManager(GameManager g)
	{
		game = g;
	}
	
	// TODO adding can't move on mountains and decrement movement points
	public static Point moveTo(Unit unit, Direction direction)
	{
		Point currentLocation = unit.getLocation();
		if(unit.getMovementPoints() <= 0) return currentLocation;
		Point endPoint;
		switch(direction)
		{
			case NORTH: 
				endPoint = new Point(currentLocation.x - 1, currentLocation.y ); 
				if(!validPoint(endPoint)) return currentLocation;
				break;
			case EAST:  
				endPoint = new Point(currentLocation.x, currentLocation.y + 1); 
				if(!validPoint(endPoint)) return currentLocation;
				break;
			case SOUTH: 
				endPoint = new Point(currentLocation.x + 1, currentLocation.y); 
				if(!validPoint(endPoint)) return currentLocation;
				break;
			case WEST:  
				endPoint = new Point(currentLocation.x, currentLocation.y - 1); 
				if(!validPoint(endPoint)) return currentLocation;
				break;
			default: 
				return currentLocation;
		}
		
		for(Civilization c : civs)
		{
			if(c.hasCityAt(endPoint))
				return currentLocation;
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
		
		switch(GameManager.getLandAt(point).getLandType())
		{
		case MOUNTAIN:
		case WATER:
			return false;
		}
		
		return true;
	}
	
	public static Point attackUnit(Unit attacker, Unit defender)
	{
		defender.takeDamage(attacker.getStrength());
		attacker.attack();
		if(defender.getHealth() <= 0)
		{
			for(Civilization c : civs)
			{
				if(!(c.getUnitAt(defender.getLocation()) != null))
				{
					Point p = defender.getLocation();
					c.removeUnit(defender);
					return p;
				}
			}
		} // end if
		return attacker.getLocation();
	}
}
