package sticksAndStones;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
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
		this.add(gameManager, BorderLayout.CENTER);
		this.add(controlGUI, BorderLayout.SOUTH);
		this.add(statusBar, BorderLayout.EAST);
		this.setSize(800, 800);
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}
}
