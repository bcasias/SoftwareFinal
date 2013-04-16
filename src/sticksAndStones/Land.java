package sticksAndStones;

public abstract class Land {
	private char initial;
	private Resource resource;
	private Building building;
	
	public Land()
	{

	}
	
	public char getInitial() {
		// TODO Auto-generated method stub
		return 0; // STUB
	}

	public boolean hasRiver() {
		return false;
	}

	public boolean hasResource()
	{
		// TODO
		return false;
	}
	public char getResourceInitial()
	{
		// TODO
		return 0;
	}

	public Building getBuilding() {
		// TODO Auto-generated method stub
		return null;
	}
}
