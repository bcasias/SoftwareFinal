package land;

public class Desert extends Land{

	public Desert()
	{
		super('D');
	}

	public Desert(boolean river) {
		super('D',river);
	}
	
	@Override
	public boolean canContainRiver()
	{
		return true;
	}
}
