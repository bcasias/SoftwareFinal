package buildings;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Building {

	private int woodCost, goldCost, stoneCost;
	protected Point location;
	protected BufferedImage image;
	protected static BufferedImage villageImage, townImage, cityImage, farmImage, sawMillImage, mineImage, tradingPostImage, barrackImage;
	public Building(int locx, int locy, int wood, int stone, int gold)
	{
		location = new Point(locx, locy);
		woodCost = wood;
		stoneCost = stone;
		goldCost = gold;
	}

	public static void initializeImages() {
		try {
			villageImage = ImageIO.read(new File("Textures/Building/Village.jpg"));
			townImage = ImageIO.read(new File("Textures/Building/Town.jpg"));
			cityImage = ImageIO.read(new File("Textures/Building/City.jpg"));
			farmImage = ImageIO.read(new File("Textures/Building/Farm.jpg"));
			barrackImage = ImageIO.read(new File("Textures/Building/Barracks.jpg"));
			mineImage = ImageIO.read(new File("Textures/Building/Mine.jpg"));
			sawMillImage = ImageIO.read(new File("Textures/Building/SawMill.jpg"));
			tradingPostImage = ImageIO.read(new File("Textures/Building/TradingPost.jpg"));
		} catch (Exception e) {
			System.out.println("An error has occurred.");
		}
	}

	public Point getLocation()
	{
		return location;
	}

	public int getWoodCost() {
		return woodCost;
	}

	public int getGoldCost() {
		return goldCost;
	}

	public int getStoneCost() {
		return stoneCost;
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public void draw(Graphics g, int sizeX, int sizeY, int locX, int locY) {
		g.drawImage(image, locY, locX, sizeX, sizeY, null);
	}
}
