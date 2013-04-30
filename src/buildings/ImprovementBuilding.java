package buildings;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImprovementBuilding extends Building { //has all improvements
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

	public BuildingType getBuildingType()
	{
		return buildingType;
	}
	
	public boolean isLocatedAt(Point loc)
	{
		return location.equals(loc);
	}
}
