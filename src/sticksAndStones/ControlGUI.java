package sticksAndStones;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlGUI extends JPanel{
	private BuildingPanel buildingPanel;
	private MovePanel movePanel;
	
	public ControlGUI()
	{
		buildingPanel = new BuildingPanel();
		movePanel = new MovePanel();
		this.setLayout(new BorderLayout());
		this.add(buildingPanel, BorderLayout.CENTER);
		this.add(movePanel, BorderLayout.WEST);
		this.setVisible(true);
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
		
	}
}
