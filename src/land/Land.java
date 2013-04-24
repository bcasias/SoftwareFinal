package land;

import resource.Resource;
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
	LandType landType;
	private ResourceType resource;
	private Building building;
	private Boolean haveRiver;
	
	public Land(LandType type)
	{
		initialize(type, false, ResourceType.NONE);
	}
	
	public Land(LandType type, boolean haveRiver)
	{
		initialize(type, haveRiver, ResourceType.NONE);
	}
	
	public Land(LandType landType, boolean river, ResourceType resourceType)
	{
		initialize(landType, river, resourceType);
	}
	
	private void initialize(LandType type, boolean river, ResourceType re)
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
	public ResourceType getResourceType()
	{
		return resource;
	}

	public Building getBuilding() {
		if (building != null) return building;
		return null;
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
}
