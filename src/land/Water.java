package land;

public class Water extends Land {

	public Water()
	{
		super('W');
	}
	
	public Water(boolean river) {
		super('H',false);
	}
}
