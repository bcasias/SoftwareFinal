package resource;

public abstract class Resource {
	public enum ResourceType {FOOD('f'), GOLD('g'), STONE('s'), WOOD('w'), NONE('n');
	private char type;
	private ResourceType(char type) 
	{
		this.type = type;
	}
		public char getType() {return type;}
	}
	private ResourceType resourceType;
	
	public Resource(ResourceType resource)
	{
		resourceType = resource;
	}
	
	@Override
	public String toString()
	{
		String rtn = "";
		rtn += resourceType.getType();
		return rtn;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}
	
}
