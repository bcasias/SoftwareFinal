package buildings;

import java.util.ArrayList;

public class City extends Building {
	public enum CityType {
		VILLAGE, TOWN, CITY
	}
	private ArrayList<Integer> landOwned;
	private int locationX;
	private int locationY;

	private int pop;

	public City() {
		super(5, 5, 5);
		landOwned = new ArrayList<Integer>();
		pop = 1;
	}

	public void forcePopulation(int i) {
		pop = i;
	}

	public CityType getCityType() {
		if (pop >= 10)
			return CityType.CITY;
		else if (pop >= 5)
			return CityType.TOWN;
		else
			return CityType.VILLAGE;
	}
	
	public void placeCity(int x, int y) {
		locationX = x;
		locationY = y;
	}
	
	public boolean addLand(int x, int y) {
		int location = sticksAndStones.GameManager.calculateIndex(x, y);
		for (int land : landOwned) {
			if (land == location) {
				return false;
			} else if (land == location - 1) {
				landOwned.add(location - 1);
				return true;
			} else if (land == location + 1) {
				landOwned.add(location + 1);
				return true;
			} else if (land == location - sticksAndStones.GameManager.getBoardSizeY()) {
				landOwned.add(location - sticksAndStones.GameManager.getBoardSizeY());
				return true;
			} else if (land == location + sticksAndStones.GameManager.getBoardSizeY()) {
				landOwned.add(location + sticksAndStones.GameManager.getBoardSizeY());
				return true;
			}
		}
		return false;
	}

	public int getPop() {
		return pop;
	}

}
