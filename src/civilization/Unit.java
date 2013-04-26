package civilization;

import java.awt.Point;
import java.util.ArrayList;

public class Unit {
	public enum MoveDirection{NORTH, EAST, SOUTH, WEST}
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
	
	public void move(MoveDirection direction, ArrayList<Unit> units, Unit monster)
	{
		// TODO
	}
	public void takeDamage(int damage)
	{
		health -= damage;
	}
}
