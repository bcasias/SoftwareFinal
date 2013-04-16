package sticksAndStones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TechTree {
	
	private Map<Tech, ArrayList<Tech> > techTree;
	private Tech researchingTech;
	
	public TechTree()
	{
		techTree = new HashMap<Tech, ArrayList<Tech> >();
		researchingTech = null;
	}
	
	// This adds a Tech to the tech tree
	// dependentTechs come in as an array
	public void addTech(Tech techology, Tech ... dependentTechs)
	{
		ArrayList<Tech> techs = new ArrayList<Tech>();
		for(Tech t : dependentTechs)
		{
			techs.add(t);
		}
		techTree.put(techology, techs);
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
			}
		}
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
		Tech masonary = new Tech("Masonary",4);
		Tech construction = new Tech("Construction", 4);
		Tech weel = new Tech("Weel", 4);
		
		techTree.addTech(pottery, agriculture);
		techTree.addTech(animal, agriculture);
		techTree.addTech(archery, agriculture);
		techTree.addTech(mining, agriculture);
		techTree.addTech(weel, archery);
		techTree.addTech(masonary, mining);
		techTree.addTech(construction, weel, masonary);
		
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
			System.out.println("constrction was researched before permitted");
			allTestsPass = false;
		}
		
		if(techTree.isTechUnlocked("Construction"))
		{
			System.out.println("Just kidding");
			allTestsPass = false;
		}
		
		// research weel
		if(!techTree.researchNewTech("Weel"))
		{
			System.out.println("Failed to research Weel");
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
		
		// research masonary
		if(!techTree.researchNewTech("Masonary"))
		{
			System.out.println("Failed to research Masonary");
			allTestsPass = false;
		}
		for(int i = 0; i < 4; i++)
		{
			techTree.update();
		}
		
		// research construction
		if(!techTree.researchNewTech("Construction"))
		{
			System.out.println("Failed to research Masonary");
			allTestsPass = false;
		}
		for(int i = 0; i < 4; i++)
		{
			techTree.update();
		}
		
		if(techTree.isTechUnlocked("Pottery"))
		{
			System.out.println("Potter is unlocked!");
			allTestsPass = false;
		}
		
		if(techTree.isTechUnlocked("Construction") && allTestsPass)
			System.out.println("All tests passed.");
	}
}
