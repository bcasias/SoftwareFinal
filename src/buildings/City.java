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

	public City(int x, int y) {
		super(5, 5, 5);
		landOwned = new ArrayList<Integer>();
		pop = 1;
		locationX = x;
		locationY = y;
		landOwned.add(0);
	}

	public void forcePopulation(int i) {
		pop = i;
	}

	public int getNumberOfLand()
	{
		return landOwned.size();
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
		System.out.println(landOwned);
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

	public boolean addLand2(int x, int y) {
		int loc = sticksAndStones.GameManager.calculateIndex(x, y);
		boolean adjacent = false;
		for (int i : landOwned) {
			if (loc == i) return false; //do not add if already within city borders
			if (loc == i + 1 || loc == i - 1 || loc == i + sticksAndStones.GameManager.getBoardSizeY() ||
					loc == i - sticksAndStones.GameManager.getBoardSizeY()) { //recognize adjacency of a square
				adjacent = true;
				break;
			}
		}
		if (adjacent == false) return false; 
		landOwned.add(loc);
		return true;
	}

	public ArrayList<Integer> getLandOwned() {
		return landOwned;
	}

	public int getPop() {
		return pop;
	}

}
