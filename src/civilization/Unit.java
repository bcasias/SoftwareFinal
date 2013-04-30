package civilization;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import sticksAndStones.Direction;
import sticksAndStones.GameManager;
import sticksAndStones.MovementManager;

public class Unit { //fighting units
	private BufferedImage image;
	private static BufferedImage warriorImage, yetiImage;
	public enum UnitType {SOLDIER('S'), KNIGHT('K'), WARRIOR('W'), DEMON('D');
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
		moveCount = 0;
		setAttributes();
		if (type == UnitType.DEMON) image = yetiImage;
		else image = warriorImage;
	}

	public int getMoveCount()
	{
		return moveCount;
	}
	public static void initializeImages() {
		try {
			warriorImage = ImageIO.read(new File("Textures/Sprite/Warrior.gif"));
			yetiImage = ImageIO.read(new File("Textures/Sprite/Yeti.png"));
		} catch (Exception e) {
			System.out.println("Unit loading has failed.");
		}
	}

	public void update()
	{
		moveCount = 2;
	}
	
	private void setAttributes() { //gets different soldier depending on type provided
		switch(unitType)
		{
		case SOLDIER: strength = 3; health = 10; break;
		case KNIGHT:  strength = 5; health = 15; break;
		case WARRIOR: strength = 7; health = 20; break;
		case DEMON:  strength = 9; health = 40; break;
		default: unitType = UnitType.SOLDIER; setAttributes(); break;
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

	public void move(Direction direction, GameManager game) //uses logic to move unit
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
		default: break;
		} 
		game.updateStatus();
		game.repaint();
		game.setSelectedLocation(position);
		if (moveCount <= 0) game.getControlGUI().makeMovementFalse();
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

	public BufferedImage getImage()
	{
		return image;
	}
	
	public void draw(Graphics g, int sizeX, int sizeY, int locX, int locY) {
		g.drawImage(image, locY + sizeX*3/16, locX + sizeY*3/16, sizeX*5/8, sizeY*5/8, null);
	}
}
