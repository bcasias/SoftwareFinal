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
