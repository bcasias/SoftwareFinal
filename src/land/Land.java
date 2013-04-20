package land;

import resource.Resource;
import buildings.Building;

public abstract class Land {
	private char initial;
	private Resource resource;
	private Building building;
	private Boolean haveRiver;
	
	public Land(char initial)
	{
		initialize(initial, false, null);
	}
	
	public Land(char initial, boolean haveRiver)
	{
		initialize(initial, haveRiver, null);
	}
	
	public Land(char initial, boolean river, Resource re)
	{
		initialize(initial, river, re);
	}
	
	private void initialize(char initial, boolean river, Resource re)
	{
		this.initial = initial;
		this.haveRiver = river;
		this.resource = re;
	}
	public char getInitial() {
		return initial;
	}

	public boolean hasRiver() {
		return haveRiver;
	}

	public boolean hasResource()
	{
		if (resource != null) return true;
		return false;
	}
	public char getResourceInitial()
	{
		if (hasResource()) return resource.getInitial();
		return 'n';
	}

	public Building getBuilding() {
		if (building != null) return building;
		return null;
	}

	public boolean canContainRiver()
	{
		return false;
	}
	
	@Override
	public String toString()
	{
		String rtn = "";
		rtn += initial;
		if(haveRiver)
			rtn += "r";
		if(resource != null)
			rtn += resource.toString();
		return rtn;	
	}
}
