package sticksAndStones;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sticksAndStones.GameManager;
import sticksAndStones.ControlGUI;

public class SticksandStone extends JFrame { //frame that controls the overall game screen
	private GameManager gameManager;
	private ControlGUI controlGUI;
	private StatusBar statusBar;
	
	public SticksandStone()
	{
		controlGUI = new ControlGUI();
		gameManager = new GameManager(controlGUI);
		MovementManager.addManager(gameManager);
		controlGUI.addManager(gameManager);
		statusBar = new StatusBar(gameManager.getPlayerCiv());
		gameManager.addStatusBar(statusBar);
		this.setLayout(new BorderLayout());
		this.setTitle("Sticks and Stones");
		this.add(gameManager, BorderLayout.CENTER);
		this.add(controlGUI, BorderLayout.SOUTH);
		this.add(statusBar, BorderLayout.EAST);
		this.setJMenuBar(new MenuBar());
		this.setSize(800, 800); //default size when not full-screen
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		JOptionPane.showMessageDialog(null, "Welcome to Sticks and Stones! \nYour objective is to either gather 200 stone, wood, \nand gold or build 10" +
				" cities before your 100th turn. \nAlternately, you may attempt to kill the fearsome Yeti, but \nmake sure your people do not starve in your" +
				" quest to \nsave the world and ensure a lasting peace for your people!");
	}
}
