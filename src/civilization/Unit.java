package civilization;

import java.awt.Point;
import java.util.ArrayList;

import sticksAndStones.Direction;
import sticksAndStones.GameManager;
import sticksAndStones.MovementManager;

public class Unit {
	public enum UnitType {SOLDIER('S'), KNIGHT('K'), WARRIOR('W'), DEMOND('D');
		private char type;
		private UnitType(char type)
		{
			this.type = type;
		}
		public char getType()
		{
			return type;
		}
	}
	private UnitType unitType;
	private Point position;
	private int strength, health;
	int moveCount = 2;
	public Unit(Point loc, UnitType type)
	{
		position = loc;
		unitType = type;
		setAttributes();
	}
	
	public void update()
	{
		moveCount = 2;
	}
	private void setAttributes() {
		switch(unitType)
		{
		case SOLDIER: strength = 3; health = 10; break;
		case KNIGHT:  strength = 5; health = 15; break;
		case WARRIOR: strength = 7; health = 20; break;
		case DEMOND:  strength = 9; health = 40; break;
		default: unitType = UnitType.SOLDIER; setAttributes();break;
		}
	}

	public boolean isAtLocation(Point loc)
	{
		return position.equals(loc);
	}
	
	public UnitType getUnitType()
	{
		return unitType;
	}

	public int getHealth() {
		return health;
	}
	
	public void move(Direction direction)
	{
		Point newPoint = MovementManager.moveTo(this, direction); 
		if(newPoint.equals(position))
			return;
		position = newPoint;
		
		switch(GameManager.getLandAt(position).getLandType())
		{
		case PLAIN:
		case DESERT:
			moveCount--;
			break;
		case FOREST:
		case HILL:
			moveCount -= 2;
			break;
		} 
		return;
	}
	public int getStrength()
	{
		return strength;
	}
	public void takeDamage(int damage)
	{
		health -= damage;
	}

	public Point getLocation() {
		return position;
	}

	public void attack()
	{
		moveCount = 0;
	}

	public int getMovementPoints() {
		return moveCount;
	}
}
