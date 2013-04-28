package buildings;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class City extends Building {
	public enum CityType {
		VILLAGE('V'), TOWN('T'), CITY('C');
		private char type;
		private CityType(char type)
		{
			this.type = type;
		}
		public char getType()
		{
			return type;
		}
	}
	CityType cityType;
	private ArrayList<Integer> landOwned;
	private int growPopulation;
	private int pop;

	public City(int x, int y) {
		super(x, y, 5, 5, 5);
		growPopulation = 5;
		cityType = CityType.VILLAGE;
		landOwned = new ArrayList<Integer>();
		pop = 1;
		location = new Point(x,y);
		landOwned.add(0);
		try {
			image = ImageIO.read(new File("Textures/Building/Village.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		if(growPopulation <= 0)
		{
			growPopulation = 5;
			pop++;
		}
		else
		{
			growPopulation--;
		}
		
		if(pop >= 5 && pop < 10)
		{
			cityType = CityType.TOWN;
			try {
				image = ImageIO.read(new File("Textures/Building/Town.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (pop >= 10)
		{
			cityType = CityType.CITY;
			try {
				image = ImageIO.read(new File("Textures/Building/Town.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public CityType getCityType() {
		return cityType;
	}

	public int getPop() {
		return pop;
	}
	
	public boolean isLocatedAt(Point loc)
	{
		return location.equals(loc);
	}
}
