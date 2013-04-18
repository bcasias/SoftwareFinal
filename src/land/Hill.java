package land;

import resource.Resource;

public class Hill extends Land {

	public Hill()
	{
		super('H');
	}

	public Hill(boolean river) {
		super('H',river);
	}
	
	public Hill(boolean river, Resource resource)
	{
		super('H',river, resource);
	}
	
	@Override
	public boolean canContainRiver()
	{
		return true;
	}
}
