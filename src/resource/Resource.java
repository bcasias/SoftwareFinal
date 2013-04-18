package resource;

public abstract class Resource {
	private char initial;
	
	public Resource(char initial)
	{
		this.initial = initial;
	}
	
	@Override
	public String toString()
	{
		String rtn = "";
		rtn += initial;
		return rtn;
	}
}
