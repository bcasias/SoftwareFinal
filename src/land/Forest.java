package land;

import resource.Resource;

public class Forest extends Land {
	
	public Forest()
	{
		super('H');
	}

	public Forest(boolean river) {
		super('H',river);
	}
	
	public Forest(boolean river, Resource resource)
	{
		super('H',river, resource);
	}
	
	@Override
	public boolean canContainRiver()
	{
		return true;
	}
}
