package buildings;

public class ImprovementBuilding extends Building {
	public enum BuildingType {FARM, BARRACK, MINE, SAWMIL, TRADINGPOST, NONE}
	private BuildingType buildingType;
	public ImprovementBuilding(int locx, int locy, BuildingType buildingType, int wood, int stone, int gold) {
		super(locx, locy, wood, stone, gold);
		this.buildingType = buildingType;
	}
	
	public boolean hasResource()
	{
		return (buildingType == BuildingType.NONE) ? true: false;
	}
	public BuildingType getBuildingType()
	{
		return buildingType;
	}
}
