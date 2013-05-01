package sticksAndStones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MenuBar extends JMenuBar {
	
	public MenuBar() {
		super();
		add(createTutorialButton());
		add(createExitButton());
	}

	private JMenuItem createTutorialButton() {
		JMenuItem tutorial = new JMenuItem("How to Play");
		tutorial.addActionListener(new TutorialListener());
		return tutorial;
	}

	private JMenuItem createExitButton() {
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());
		return exit;
	}
	
	public class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}
	public class TutorialListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(null, "How to Play Sticks and Stones\n\n" +
		"Building are constructed from the three basic resources- wood, stone, and gold.\n" +
		"The construction costs for each building are listed in this order on each construction button. Initially, \n" +
		"you may only build one building per turn, but this number will increase for every city you build. \n" +
		"Be careful not to build too many cities, though- each population will consume one food, and if your \n" +
		"food supply is exhausted, your people will become unhappy and eventually riot. \n\n" +
		"Units cost 5 gold each, and will consume a food every turn. The basic unit is the soldier, produced from \n" +
		"villages and barracks. As a city grows, it will produce stronger soldiers, so growing your population may \n" +
		"be more efficient than quickly going on the attack. \n\n" +
		"Each city will provide one of every resource, plus one for the terrain type (food for Plains, stone for Hills, \n" +
		"and wood for Forest). Also, if a city is on a resource square, it will gather that resource as well (i.e. gold).\n" +
		"Build mines to gather gold and stone, sawmills to gather wood, and farms to gather food.\n\n" +
		"There are three win conditions: \n" +
		"* Have 10 villages grow into towns \n" +
		"* Gather 500 wood, stone, and gold\n" +
		"* Kill the yeti\n\n" +
		"There are also 2 loss conditions:\n" +
		"* Reach 0 happiness through starvation\n" +
		"* Reach turn 100, when the Yeti destroys your civilization\n\n" +
		"Good luck, and build a civilization that will survive through the ages!");
		}
	}
}
