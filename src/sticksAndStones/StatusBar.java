package sticksAndStones;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
		this.setupGUI();
	}
	
	public void update( )
	{
		status.update(humanCiv.getStoneCount(), humanCiv.getWoodCount(), humanCiv.getGoldCount(),
				humanCiv.getFoodCount(), humanCiv.getHappiness());
		cityPanel.update(humanCiv.getCities());
		
	}

	private void SetName(String name)
	{
		this.civName = name;
	}
	
	private void setupGUI()
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
		this.setVisible(true);
	}
	
	class Status extends JPanel
	{
		private JLabel stonel, woodl, goldl, foodl, happinessl, turnl;
		private JTextField stonet, woodt, goldt, foodt, happinesst, turnt;
		public Status (int stone, int wood, int gold, int food, int happiness)
		{
			
			this.stonel = new JLabel("Stone");
			this.woodl = new JLabel("Wood");
			this.goldl = new JLabel("Gold");
			this.foodl = new JLabel("Food");
			this.happinessl = new JLabel("Happiness");
			this.turnl = new JLabel("Turn");
			
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
			
			this.turnt = new JTextField(3);
			turnt.setText(Integer.toString(GameManager.getTurn()));
			turnt.setEditable(false);
			
			createGUI();
		}
		
		public void createGUI()
		{
			this.setLayout(new GridLayout(0,2));
			this.add(turnl);
			this.add(turnt);
			this.add(goldl);
			this.add(goldt);
			this.add(woodl);
			this.add(woodt);
			this.add(stonel);
			this.add(stonet);
			this.add(foodl);
			this.add(foodt);
			this.add(happinessl);
			this.add(happinesst);
			this.setBorder(new TitledBorder("Status: "));
			this.setVisible(true);
		}
		
		public void update(int stone, int wood, int gold, int food, int happiness)
		{
			stonet.setText(Integer.toString(stone));
			woodt.setText(Integer.toString(wood));
			goldt.setText(Integer.toString(gold));
			foodt.setText(Integer.toString(food));
			happinesst.setText(Integer.toString(happiness));
			turnt.setText(Integer.toString(GameManager.getTurn()));
		}
	}// end class

	class CityPanel extends JPanel
	{
		public ArrayList<City> cities;
		private JTextArea cityList;
		public CityPanel(ArrayList<City> cities)
		{
			this.cities = cities;
			cityList = new JTextArea(20, 20);
			cityList.setEditable(false);
			this.setLayout(new GridLayout(0,1));
			this.add(cityList);
			update(this.cities);
		}
		
		public void update(ArrayList<City> cities)
		{
			cityList.setText("");
			for(City c : cities)
			{
				cityList.setText(cityList.getText() + "X: " + Integer.toString(c.getLocation().x + 1) +
						", Y: " + Integer.toString(c.getLocation().y + 1) + ", Pop = " + Integer.toString(c.getPop()) + 
						", Turns to Grow: " + (c.getTimeToGrow() + 1) + "\n");
			}// end for
			this.setBorder(new TitledBorder("Cities:"));
			this.setVisible(true);
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
				JTextField field = new JTextField("X: " + Integer.toString(u.getLocation().x + 1) +
						", Y: " + Integer.toString(u.getLocation().y + 1) + ", Strength: " + 
						Integer.toString(u.getStrength()) + ", Health: " + Integer.toString(u.getHealth()));
				field.setEditable(false);
				this.add(field);
			}// end for
			this.setBorder(new TitledBorder("Units:"));
			this.setVisible(true);
		}
	}
}
