package land;

import resource.Resource;

public class Plain extends Land{

	public Plain(boolean haveRiver)
	{
		super('P',haveRiver);
	}
	
	public Plain(boolean river, Resource resource)
	{
		super('P', river, resource);
	}

	public Plain() {
		super('P');
	}
	
	@Override
	public boolean canContainRiver()
	{
		return true;
	}
}
