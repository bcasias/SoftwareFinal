package land;

public class Mountain extends Land {

	public Mountain()
	{
		super('M');
	}
	public Mountain(boolean river) {
		super('H',false);
	}
}
