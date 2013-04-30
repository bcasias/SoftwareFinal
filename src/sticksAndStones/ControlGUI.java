package sticksAndStones;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import buildings.City;
import buildings.ImprovementBuilding;
import buildings.ImprovementBuilding.BuildingType;

import civilization.Civilization;

public class ControlGUI extends JPanel{
	private BuildingPanel buildingPanel;
	private MovePanel movePanel;
	private JButton nextTurn;
	private GameManager game;
	public ControlGUI()
	{
		nextTurn = new JButton("Next Turn");
		nextTurn.addActionListener(new ActionListener () 
		{
			 public void actionPerformed(ActionEvent e)
	            {
	                game.nextTurn();
	            }
		});
		buildingPanel = new BuildingPanel();
		movePanel = new MovePanel();
		this.setLayout(new BorderLayout());
		this.add(buildingPanel, BorderLayout.CENTER);
		this.add(movePanel, BorderLayout.EAST);
		this.add(nextTurn, BorderLayout.WEST);
		this.setVisible(true);
		setAllToFalse();
	}
	
	public void addManager(GameManager g)
	{
		game = g;
	}
	
	public void setAllToFalse()
	{
		buildingPanel.setAllToFalse();
		movePanel.setAllToFalse();
	}
	
	public void makeMoveControlTrue() {
		movePanel.setMovementToTrue();
		
	}

	public void showMakeUnitButton() {
		movePanel.showMakeUnit();
		
	}

	public void makeBuildingTrue() {
		buildingPanel.showBuildingButtons();
		
	}

	
	class BuildingPanel extends JPanel
	{
		private JButton newCity, newBarrack, newFarm, newMine, newSawmill, newTradingPost;
		
		public BuildingPanel()
		{
			newCity 		= new JButton("Make City");
			newBarrack 		= new JButton("Make Barrack");
			newFarm 		= new JButton("Make Farm");
			newMine 		= new JButton("Make Mine");
			newSawmill 		= new JButton("Make Sawmill");
			newTradingPost 	= new JButton("Make Trading Post");
			
			newCity.addActionListener(new ButtonListener());
			newBarrack.addActionListener(new ButtonListener());
			newFarm.addActionListener(new ButtonListener());
			newMine.addActionListener(new ButtonListener());
			newSawmill.addActionListener(new ButtonListener());
			newTradingPost.addActionListener(new ButtonListener());
			this.setLayout(new GridLayout(2,0));
			
			this.add(newCity);
			this.add(newBarrack);
			this.add(newFarm);
			this.add(newMine);
			this.add(newSawmill);
			this.add(newTradingPost);
			this.setVisible(true);
		}

		public void showBuildingButtons() {
			newCity.setEnabled(true);
			newBarrack.setEnabled(true);
			newFarm.setEnabled(true);
			newMine.setEnabled(true);
			newSawmill.setEnabled(true);
			newTradingPost.setEnabled(true);
		}

		public void setAllToFalse() {
			newCity.setEnabled(false);
			newBarrack.setEnabled(false);
			newFarm.setEnabled(false);
			newMine.setEnabled(false);
			newSawmill.setEnabled(false);
			newTradingPost.setEnabled(false);
		}
		
		public class ButtonListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				Point location = game.getSelectedLocation();
				System.out.println(location);
				if (e.getSource() == newCity) {
					game.buildBuilding((int) location.getX(), (int) location.getY(), new City((int) location.getX(), (int) location.getY()));
				} else if (e.getSource() == newBarrack) {
					game.buildBuilding((int) location.getX(), (int) location.getY(), 
							new ImprovementBuilding((int) location.getX(), (int) location.getY(), BuildingType.BARRACK, 2, 3, 1));
				} else if (e.getSource() == newFarm) {
					game.buildBuilding((int) location.getX(), (int) location.getY(), 
							new ImprovementBuilding((int) location.getX(), (int) location.getY(), BuildingType.FARM, 1, 1, 0));
				} else if (e.getSource() == newMine) {
					game.buildBuilding((int) location.getX(), (int) location.getY(), 
							new ImprovementBuilding((int) location.getX(), (int) location.getY(), BuildingType.MINE, 2, 0, 0));
				} else if (e.getSource() == newSawmill) {
					game.buildBuilding((int) location.getX(), (int) location.getY(), 
							new ImprovementBuilding((int) location.getX(), (int) location.getY(), BuildingType.SAWMILL, 1, 0, 1));
				} else {
					game.buildBuilding((int) location.getX(), (int) location.getY(), 
							new ImprovementBuilding((int) location.getX(), (int) location.getY(), BuildingType.TRADINGPOST, 1, 2, 3));
				}
			}
		}
	}
	
	class MovePanel extends JPanel
	{

		private JButton moveUp, moveRight, moveLeft, moveDown, newUnit;
		
		public MovePanel()
		{
			moveUp 			= new JButton("UP");
			moveRight 		= new JButton("RIGHT");
			moveLeft 		= new JButton("LEFT");
			moveDown 		= new JButton("DOWN");
			newUnit 		= new JButton("Make Unit");
			
			this.setLayout(new BorderLayout());
			this.add(moveUp, BorderLayout.NORTH);
			this.add(moveRight, BorderLayout.EAST);
			this.add(moveLeft, BorderLayout.WEST);
			this.add(moveDown, BorderLayout.SOUTH);
			this.add(newUnit, BorderLayout.CENTER);
			this.setVisible(true);
		}

		public class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		public void showMakeUnit() {
			newUnit.setEnabled(true);
		}

		public void setMovementToTrue() {
			moveUp.setEnabled(true);
			moveDown.setEnabled(true);
			moveRight.setEnabled(true);
			moveLeft.setEnabled(true);
		}

		public void setAllToFalse() {
			moveUp.setEnabled(false);
			moveDown.setEnabled(false);
			moveRight.setEnabled(false);
			moveLeft.setEnabled(false);
			newUnit.setEnabled(false);
		}
		
	}
}
