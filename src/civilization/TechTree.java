package civilization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class TechTree {
	
	private Map<Tech, ArrayList<Tech> > techTree;
	private Tech researchingTech;
	private int numTechsUnlocked;
	
	public TechTree()
	{
		techTree = new HashMap<Tech, ArrayList<Tech> >();
		researchingTech = null;
		numTechsUnlocked = 0;
	}
	
	// This adds a Tech to the tech tree
	// dependentTechs come in as an array
	public void addTech(Tech technology, Tech ... dependentTechs)
	{
		ArrayList<Tech> techs = new ArrayList<Tech>();
		for(Tech t : dependentTechs)
		{
			techs.add(t);
		}
		techTree.put(technology, techs);
	}
	
	public boolean researchNewTech(String techName)
	{
		Iterator<Entry<Tech, ArrayList<Tech>>> map_it = techTree.entrySet().iterator();
		boolean selectedNewTech = true;
		while(map_it.hasNext())
		{
			Map.Entry<Tech, ArrayList<Tech> > pairs 
				= (Map.Entry<Tech, ArrayList<Tech> >)map_it.next();
			if(pairs.getKey().getTechName().equals(techName))
			{
				if(!pairs.getKey().isLocked())
				{
					return false;
				}
				for(Tech t : pairs.getValue() )
				{
					if(t.isLocked())
					{
						selectedNewTech = false;
						return selectedNewTech;
					}
				}
				researchingTech = pairs.getKey();
				break;
			}
		}
		return selectedNewTech;
	}
	
	public boolean isTechUnlocked(String techName)
	{
		Iterator<Entry<Tech, ArrayList<Tech>>> map_it = techTree.entrySet().iterator();
		while(map_it.hasNext())
		{
			Map.Entry<Tech, ArrayList<Tech> > pairs 
			= (Map.Entry<Tech, ArrayList<Tech> >)map_it.next();
			
			if(pairs.getKey().getTechName().equals(techName))
			{
				return !pairs.getKey().isLocked();
			}
		}
		return false;
	}
	
	public void update()
	{
		if(researchingTech != null)
		{
			researchingTech.updateTech();
			if(!researchingTech.isLocked())
			{
				researchingTech = null;
				++numTechsUnlocked;
			}
		}
	}
	
	public int getNumberOfTechs()
	{
		return numTechsUnlocked;
	}
	
	public static void main(String[] args)
	{
		/*
		 * These test the tech tree
		 * each tech is created and then added to the tech tree with there dependent techs
		 * The tests below run a simulation of a player making new choosing new techs
		 * 		
		 * cases that are covered
		 * initial tech
		 * techs and not be researched out of order
		 * once a tech is unlocked the approbate techs can be researched. 
		 */
		TechTree techTree = new TechTree();
		boolean allTestsPass = true;
		
		Tech agriculture = new Tech("Agriculture", 0);
		techTree.addTech(agriculture);
		
		Tech pottery = new Tech("Pottery", 4);
		Tech animal = new Tech("Animal", 4);
		Tech archery = new Tech("Archery", 4);
		Tech mining = new Tech("Mining", 4);
		Tech masonry = new Tech("Masonry",4);
		Tech construction = new Tech("Construction", 4);
		Tech wheel = new Tech("Wheel", 4);
		
		techTree.addTech(pottery, agriculture);
		techTree.addTech(animal, agriculture);
		techTree.addTech(archery, agriculture);
		techTree.addTech(mining, agriculture);
		techTree.addTech(wheel, archery);
		techTree.addTech(masonry, mining);
		techTree.addTech(construction, wheel, masonry);
		
		// research basic 
		if(!techTree.researchNewTech("Agriculture"))
		{
			System.out.println("Failed to research Ag");
			allTestsPass = false;
		}
		techTree.update();
		// research archery
		if(!techTree.researchNewTech("Archery"))
		{
			System.out.println("Failed to research Archery");
			allTestsPass = false;
		}
		for(int i = 0; i < 4; i++)
		{
			techTree.update();
		} 
		// check for fail
		if(techTree.researchNewTech("Construction"))
		{
			System.out.println("Consturction was researched before permitted");
			allTestsPass = false;
		}
		
		if(techTree.isTechUnlocked("Construction"))
		{
			System.out.println("Just kidding");
			allTestsPass = false;
		}
		
		// research wheel
		if(!techTree.researchNewTech("Wheel"))
		{
			System.out.println("Failed to research Wheel");
			allTestsPass = false;
		}
		for(int i = 0; i < 4; i++)
		{
			techTree.update();
		}
		
		// research Mining
		if(!techTree.researchNewTech("Mining"))
		{
			System.out.println("Failed to research Mining");
			allTestsPass = false;
		}
		for(int i = 0; i < 4; i++)
		{
			techTree.update();
		}
		
		// research masonry
		if(!techTree.researchNewTech("Masonry"))
		{
			System.out.println("Failed to research Masonry");
			allTestsPass = false;
		}
		for(int i = 0; i < 4; i++)
		{
			techTree.update();
		}
		
		// research construction
		if(!techTree.researchNewTech("Construction"))
		{
			System.out.println("Failed to research Construction");
			allTestsPass = false;
		}
		for(int i = 0; i < 4; i++)
		{
			techTree.update();
		}
		
		if(techTree.isTechUnlocked("Pottery"))
		{
			System.out.println("Harry Potter is unlocked!");
			allTestsPass = false;
		}
		
		// tests to see if a tech is unlocked could be researched multiple times
		techTree.researchNewTech("Construction");
		for(int i = 0; i < 4; i++)
		{
			techTree.update();
		}
		techTree.researchNewTech("Construction");
		for(int i = 0; i < 4; i++)
		{
			techTree.update();
		}
		techTree.researchNewTech("Construction");
		for(int i = 0; i < 4; i++)
		{
			techTree.update();
		}
		
		if(techTree.isTechUnlocked("Construction") && allTestsPass && techTree.getNumberOfTechs() == 6)
			System.out.println("All tests passed.");
	}
}
