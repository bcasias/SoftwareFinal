package land;

import resource.Resource;
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
	LandType landType;
	private Resource resource;
	private Building building;
	private Boolean haveRiver;
	
	public Land(LandType type)
	{
		initialize(type, false, null);
	}
	
	public Land(LandType type, boolean haveRiver)
	{
		initialize(type, haveRiver, null);
	}
	
	public Land(LandType type, boolean river, Resource re)
	{
		initialize(type, river, re);
	}
	
	private void initialize(LandType type, boolean river, Resource re)
	{
		this.landType = type;
		this.haveRiver = river;
		this.resource = re;
	}
	public LandType getLandType() {
		return landType;
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
		rtn += landType.getType();
		if(haveRiver)
			rtn += "r";
		if(resource != null)
			rtn += resource.toString();
		return rtn;	
	}
}
