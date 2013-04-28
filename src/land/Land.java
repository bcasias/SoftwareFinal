package land;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import resource.Resource.ResourceType;
import buildings.Building;

public class Land {
	public enum LandType {FOREST('F'), HILL('H'), WATER('W'), DESERT('D'), MOUNTAIN('M'), PLAIN('P'), NONE('N');
	private char type;
	private LandType(char type) 
	{
		this.type = type;
	}
	public char getType() {return type;}
	}
	private BufferedImage image;
	private static BufferedImage waterImage, mountainImage, hillImage, plainImage, desertImage, forestImage; //stores textures, only loads once
	LandType landType;
	private ResourceType resource;
	private Boolean haveRiver;
	private int locX, locY;

	public Land(int locx, int locy, LandType type)
	{
		initialize(locx, locy, type, false, ResourceType.NONE);
	}

	public Land(int locx, int locy, LandType type, boolean haveRiver)
	{
		initialize(locx, locy, type, haveRiver, ResourceType.NONE);
	}

	public Land(int locx, int locy, LandType landType, boolean river, ResourceType resourceType)
	{
		initialize(locx, locy, landType, river, resourceType);
	}

	public static void initializeImages() {
		try {
			waterImage = ImageIO.read(new File("Textures/Land/Water.jpg"));
			mountainImage = ImageIO.read(new File("Textures/Land/Mountain.jpg"));
			hillImage = ImageIO.read(new File("Textures/Land/Hill.jpg"));
			plainImage = ImageIO.read(new File("Textures/Land/Plain.jpg"));
			desertImage = ImageIO.read(new File("Textures/Land/Desert.jpg"));
			forestImage = ImageIO.read(new File("Textures/Land/Forest.jpg"));
		} catch (Exception e) {
			System.out.println("A texture image failed to load. Please check configuration files.");
		}
	}

	private void initialize(int locx, int locy, LandType type, boolean river, ResourceType re)
	{
		this.locX = locx;
		this.locY = locy;
		this.landType = type;
		this.haveRiver = river;
		this.resource = re;

		switch(landType)
		{
		case WATER: 	image = waterImage; break;
		case DESERT:	image = desertImage; break;
		case HILL:		image = hillImage; break;
		case FOREST:	image = forestImage; break;
		case PLAIN:		image = plainImage; break;
		case MOUNTAIN:	image = mountainImage; break;
		}// end switch
	}

	public LandType getLandType() {
		return landType;
	}

	public boolean hasRiver() {
		return haveRiver;
	}

	public boolean hasResource()
	{
		if (resource != ResourceType.NONE) return true;
		return false;
	}

	public ResourceType getResourceType()
	{
		return resource;
	}


	public BufferedImage getImage() {
		return image;
	}

	public boolean canContainRiver()
	{
		switch(landType)
		{
		case MOUNTAIN:
		case WATER:
			return false;
		default:
			return true;
		}
	}

	@Override
	public String toString()
	{
		String rtn = "";
		rtn += landType.getType();
		if(haveRiver)
			rtn += "r";
		if(resource != null)
			rtn += resource.getType();
		return rtn;	
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Land other = (Land) obj;
		if (locX != other.locX)
			return false;
		if (locY != other.locY)
			return false;
		return true;
	}

	public void draw(Graphics g, int sizeX, int sizeY, int locX, int locY) {
		g.drawImage(image, locY, locX, sizeX, sizeY, null);
	}

	public void drawResource(Graphics g, int sizeX, int sizeY, int locX, int locY) {
		g.setColor(Color.BLACK);
		g.fillRect(locY + 5, locX + 5, 9*resource.toString().length(), 12);
		switch (resource) {
		case FOOD:
			g.setColor(Color.ORANGE);
			break;
		default:
			g.setColor(Color.GREEN);
			break;
		}
		g.drawString(resource.toString(), locY + 5, locX + 15);
	}
}
