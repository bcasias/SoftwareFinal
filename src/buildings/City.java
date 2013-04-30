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
	private int timeToGrow;
	private int pop;

	public City(int x, int y) { 
		super(x, y, 8, 8, 8);
		timeToGrow = 4;
		cityType = CityType.VILLAGE;
		landOwned = new ArrayList<Integer>();
		pop = 1;
		location = new Point(x,y);
		landOwned.add(0);
		image = villageImage;
	}

	public void update()
	{
		if(timeToGrow <= 0)
		{
			timeToGrow = 4;
			pop++;
		}
		else
		{
			timeToGrow--;
		}

		if(pop >= 5 && pop < 10 && cityType != CityType.TOWN)
		{
			cityType = CityType.TOWN;
			image = townImage;
		}
		else if (pop >= 10 && cityType != CityType.CITY)
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

	public int getTimeToGrow() {
		return timeToGrow;
	}

	public boolean isLocatedAt(Point loc)
	{
		return location.equals(loc);
	}
}
