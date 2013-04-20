package buildings;


public class City extends Building {
	public enum CityType {VILLAGE, TOWN, CITY}
	private int pop;

	public City()
	{
		super(5, 5, 5);
		pop = 1;
	}
	public void forcePopulation(int i) {
		pop = i;
	}

	public CityType getCityType() {
		if (pop >= 10) return CityType.CITY;
		else if (pop >= 5) return CityType.TOWN;
		else return CityType.VILLAGE;
	}
	public int getPop() {
		return pop;
	}

}
