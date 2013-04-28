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
		image = villageImage;
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
			image = townImage;
		}
		else if (pop >= 10)
		{
			cityType = CityType.CITY;
			image = cityImage;
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
