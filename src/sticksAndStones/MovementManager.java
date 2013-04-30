package sticksAndStones;

import java.awt.Point;
import java.util.ArrayList;

import civilization.Civilization;
import civilization.Unit;

public class MovementManager {
	private static GameManager game;
	private static Unit yeti;
	private static Civilization civ;
	public MovementManager()
	{

	}
	
	public static void  addManager(GameManager g)
	{
		game = g;
		yeti = game.getYeti();
		civ = game.getPlayerCiv();
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
		

		if(civ.hasUnitAt(endPoint))
			endPoint =  currentLocation;
		
		if(yeti.isAtLocation(endPoint))
		{
			endPoint = attackUnit(unit, yeti);
		}
		game.repaint();
		game.updateStatus();
		return endPoint;
	} // end function

	public static boolean validPoint(Point point) {
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
			civ.removeUnit(defender);
		} // end if
		return attacker.getLocation();
	}
}
