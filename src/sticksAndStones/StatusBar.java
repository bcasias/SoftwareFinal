package sticksAndStones;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import buildings.City;

import civilization.Civilization;
import civilization.Unit;

public class StatusBar extends JPanel {
	private Civilization humanCiv;
	private String civName;
	private Status status;
	private UnitPanel unitPanel;
	private CityPanel cityPanel;
	public StatusBar(Civilization civ)
	{
		humanCiv = civ;
		this.status = new Status(  humanCiv.getStoneCount(), 
									humanCiv.getWoodCount(), 
									humanCiv.getGoldCount(),
									humanCiv.getFoodCount(), 
									humanCiv.getHappiness());
		this.cityPanel = new CityPanel(humanCiv.getCities());
		this.unitPanel = new UnitPanel(humanCiv.getUnits());
	}
	
	private void SetName(String name)
	{
		this.civName = name;
	}
	
	private void SetupGUI()
	{
		this.add(status);
		this.add(cityPanel);
		this.add(unitPanel);
		/* 
		 *  units
		 *  	point health damage 
		 */
		this.setLayout(new GridLayout(0,1));
		this.setBorder(new TitledBorder("Civilization Manager"));
	}
	
	class Status extends JPanel
	{
		private JLabel stonel, woodl, goldl, foodl, happinessl;
		private JTextField stonet, woodt, goldt, foodt, happinesst;
		public Status (int stone, int wood, int gold, int food, int happiness)
		{
			
			this.stonel = new JLabel("Stone");
			this.woodl = new JLabel("Wood");
			this.goldl = new JLabel("Gold");
			this.foodl = new JLabel("Food");
			this.happinessl = new JLabel("Happiness");
			
			this.stonet = new JTextField(3);
			stonet.setText(Integer.toString(stone));
			stonet.setEditable(false);
			
			this.woodt = new JTextField(3);
			woodt.setText(Integer.toString(wood));
			woodt.setEditable(false);
			
			this.goldt = new JTextField(3);
			goldt.setText(Integer.toString(gold));
			goldt.setEditable(false);
			
			this.foodt = new JTextField(3);
			foodt.setText(Integer.toString(food));
			foodt.setEditable(false);
			
			this.happinesst = new JTextField(3);
			happinesst.setText(Integer.toString(happiness));
			happinesst.setEditable(false);
			
			createGUI();
		}
		
		public void createGUI()
		{
			this.setLayout(new GridLayout(0,2));
			this.add(goldl);
			this.add(goldt);
			this.add(happinessl);
			this.add(happinesst);
			this.add(foodl);
			this.add(foodt);
			this.add(woodl);
			this.add(woodt);
			this.add(stonel);
			this.add(stonet);
			this.setVisible(true);
			this.setBorder(new TitledBorder("Status: "));
		}
		
		public void update(int stone, int wood, int gold, int food, int happiness)
		{
			stonet.setText(Integer.toString(stone));
			woodt.setText(Integer.toString(wood));
			goldt.setText(Integer.toString(gold));
			foodt.setText(Integer.toString(food));
			happinesst.setText(Integer.toString(happiness));
		}
	}// end class

	class CityPanel extends JPanel
	{
		public ArrayList<City> cities;
		public CityPanel(ArrayList<City> cities)
		{
			this.cities = cities;
			this.setLayout(new GridLayout(0,1));
			update(this.cities);
		}
		
		public void update(ArrayList<City> cities)
		{
			this.removeAll();
			for(City c : cities)
			{
				JTextField field = new JTextField("X: " + Integer.toString(c.getLocation().x) +
						", Y: " + Integer.toString(c.getLocation().y) + ", Pop: " + 
						Integer.toString(c.getPop()));
				field.setEditable(false);
				this.add(field);
			}// end for
			this.setVisible(true);
			this.setBorder(new TitledBorder("Cities:"));
		}
	}

	class UnitPanel extends JPanel
	{
		public ArrayList<Unit> units;
		public UnitPanel(ArrayList<Unit> units)
		{
			this.units = units;
			this.setLayout(new GridLayout(0,1));
			update(this.units);
		}
		
		public void update(ArrayList<Unit> units)
		{
			this.removeAll();
			for(Unit u : units)
			{
				JTextField field = new JTextField("X: " + Integer.toString(u.getLocation().x) +
						", Y: " + Integer.toString(u.getLocation().y) + ", Strength: " + 
						Integer.toString(u.getStrength()) + ", Health: " + Integer.toString(u.getHealth()));
				field.setEditable(false);
				this.add(field);
			}// end for
			this.setVisible(true);
			this.setBorder(new TitledBorder("Units:"));
		}
	}
}
