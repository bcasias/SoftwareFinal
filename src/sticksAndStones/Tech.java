package sticksAndStones;

public class Tech {
	private int timeToDevelop;
	private String techType;
	private boolean locked;
	
	public Tech(String type, int developTime)
	{
		timeToDevelop = developTime;
		techType = type;
		locked = true;
	}
	
	public boolean isLocked()
	{
		return locked;
	}
	
	public String getTechName()
	{
		return techType;
	}
	
	public int getDevelopmentTime()
	{
		return timeToDevelop;
	}
	
	public void updateTech()
	{
		--timeToDevelop;
		if(timeToDevelop <= 0)
			locked = false;
	}
}