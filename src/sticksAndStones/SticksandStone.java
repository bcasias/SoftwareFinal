package sticksAndStones;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sticksAndStones.GameManager;
import sticksAndStones.ControlGUI;

public class SticksandStone extends JFrame {
	private GameManager gameManager;
	private ControlGUI controlGUI;
	private StatusBar statusBar;
	
	public SticksandStone()
	{
		gameManager = new GameManager();
		controlGUI = new ControlGUI();
		statusBar = new StatusBar(gameManager.getPlayerCiv());
		this.setLayout(new BorderLayout());
		this.setTitle("Sticks and Stones");
		this.add(gameManager, BorderLayout.CENTER);
		this.add(controlGUI, BorderLayout.SOUTH);
		this.add(statusBar, BorderLayout.EAST);
		this.setSize(800, 800); //default size when not full-screen
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		JOptionPane.showMessageDialog(null, "Welcome to Sticks and Stones! \nYour objective is to either gather 125 stone, wood, \nand gold or claim 25" +
				" land before your 100th turn. \nAlternately, you may attempt to kill the fearsome Yeti, but \nmake sure your people do not starve in your" +
				" quest to \nsave the world and ensure a lasting peace for your people!");
	}
}
