package buildings;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImprovementBuilding extends Building {
	public enum BuildingType {FARM, BARRACK, MINE, SAWMILL, TRADINGPOST, NONE}
	private BuildingType buildingType;
	public ImprovementBuilding(int locx, int locy, BuildingType buildingType, int wood, int stone, int gold) {
		super(locx, locy, wood, stone, gold);
		this.buildingType = buildingType;

		switch(buildingType)
		{
		case FARM: 			image = farmImage; break;
		case BARRACK:		image = barrackImage; break;
		case MINE:			image = mineImage; break;
		case SAWMILL:		image = sawMillImage; break;
		case TRADINGPOST:	image = tradingPostImage; break;
		}
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
