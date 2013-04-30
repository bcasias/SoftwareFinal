package sticksAndStones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import resource.Resource.ResourceType;

import buildings.Building;
import buildings.City;
import buildings.ImprovementBuilding;

import civilization.Civilization;
import civilization.Unit;
import civilization.Unit.UnitType;

import land.Land;
import land.Land.LandType;


public class GameManager extends JPanel { // this draws the board to the screen
	private static Land[][] map;
	private static Civilization playerCiv;
	private static int turn;
	private static int boardSizeX = 10;
	private static int boardSizeY = 10;
	private static Unit yeti;
	private ControlGUI controlGUI;
	private StatusBar statusBar;
	private Point selectedLocation;
	private int buildPerTurn = 1;
	private static int buildingsLeft = 1;
	
	public GameManager(ControlGUI controlGUI)
	{
		MapGeneration g = new MapGeneration();
		map =  g.generateMap(boardSizeX, boardSizeY);
		boardSizeX = 10;
		boardSizeY = 10;
		boolean foundPlaceForCity = false;
		int randX, randY;
		Random rand = new Random();
		this.controlGUI = controlGUI;
		// place city
		while(!foundPlaceForCity)
		{
			randX = rand.nextInt(boardSizeX);
			randY = rand.nextInt(boardSizeY);
			if(map[randX][randY].getLandType() != LandType.WATER && map[randX][randY].getLandType() != LandType.MOUNTAIN )
			{
				foundPlaceForCity = true;
				playerCiv = new Civilization(map, randX, randY);
			}
		}
		placeYeti(playerCiv.getCities().get(0).getLocation());
		turn = 0;
		this.addMouseListener(new clickListener());
		this.addComponentListener(new resizeListener());
		this.repaint();
		// TODO add in monster
	}
	
	private void placeYeti(Point loc) {
		switch(getQuadrant(loc))
		{
		case 0: placeYetiInQuad(3); break;
		case 1: placeYetiInQuad(2); break;
		case 2: placeYetiInQuad(1); break;
		case 3: placeYetiInQuad(0); break;
		}
	}

	private void placeYetiInQuad(int quad) {
		Random rand = new Random();
		boolean found = false;
		Point placePoint = new Point(0,0);
		while (!found)
		{
			int x = rand.nextInt(5);
			int y = rand.nextInt(5);
			switch(quad)
			{
			case 0: placePoint = new Point(x,y); break;
			case 1:placePoint = new Point(x,y + 5); break;
			case 2: placePoint = new Point(x + 5,y); break;
			default: placePoint = new Point(x + 5,y + 5); break;
			
			}
			if(MovementManager.validPoint(placePoint))
			{
				yeti = new Unit(placePoint, UnitType.DEMON);
				found = true;
			}
		}
	}

	private int getQuadrant(Point loc) {
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		if(x < 5 && y < 5) return 0;
		else if (x < 5) return 1;
		else if(y < 5) return 2;
		return 3;
	}

	public void addStatusBar(StatusBar statusBar) {
		this.statusBar = statusBar;
	}
	
	public class resizeListener extends ComponentAdapter {
		public void componentResized(ComponentEvent e) {} //empty function forces size to update properly
	}
	
	public static int calculateIndex(int x, int y) {
		return x * boardSizeY + y; 
	}
	
	public static Point calcReverseIndex(int index) {
		int x = index / boardSizeY;
		int y = index % boardSizeY;
		return new Point(x, y);
	}
	
	public static int getBoardSizeX() {
		return boardSizeX;		
	}
	
	public static int getBoardSizeY() {
		return boardSizeY;
	}
		
	/* All of these functions are for testing */
	
	public Land[][] getMap()
	{
		return map;
	}
	
	public Land getSquare(int index) {
		Point p = sticksAndStones.GameManager.calcReverseIndex(index);
		return map[p.x][p.y];
	}

	public Civilization getPlayerCiv() {
		return playerCiv;
	}

	public static int getBuildingsLeft() {
		return buildingsLeft;
	}

	public void nextTurn() {
		turn++;	
		yetiAttack();
		buildingsLeft = buildPerTurn;
		selectedLocation = null;
		playerCiv.update();
		statusBar.update();
		this.repaint();
		gameEnd();
	}
	
	private void yetiAttack() {
		int x =(int) yeti.getLocation().getX();
		int y = (int) yeti.getLocation().getY();
		Point up = new Point(x -1, y);
		Point down = new Point(x +1, y);
		Point left = new Point(x , y - 1);
		Point right = new Point(x , y + 1);
		
		if(playerCiv.hasUnitAt(up))
		{
			MovementManager.attackUnit(yeti, playerCiv.getUnitAt(up));
		} else if(playerCiv.hasUnitAt(down))
		{
			MovementManager.attackUnit(yeti, playerCiv.getUnitAt(down));
		}else if(playerCiv.hasUnitAt(left))
		{
			MovementManager.attackUnit(yeti, playerCiv.getUnitAt(left));
		}else if(playerCiv.hasUnitAt(right))
		{
			MovementManager.attackUnit(yeti, playerCiv.getUnitAt(right));
		}
	}

	public void buildBuilding(int locX, int locY, Building building)
	{
		if (buildingsLeft == 0) {
			JOptionPane.showMessageDialog(null, "You cannot build any more this turn!");
			return;
		}
		boolean built = playerCiv.makeBuilding(locX, locY, building);
		if (built) {
			buildingsLeft--;
			if (building instanceof City) buildPerTurn++;
			}
		statusBar.update(); //update resources
		controlGUI.setAllToFalse();
		this.repaint();
	}
	
	public void buildUnit()
	{
		playerCiv.makeUnit(selectedLocation);
		statusBar.update();
		controlGUI.setAllToFalse();
		this.repaint();
	}

	public static void forceTurn(int i) {
		turn = i;
	}

	public static int getTurn() {
		return turn;
	}

	public static boolean gameEnd() {
		boolean done = false;
		if (playerCiv.getHappiness() == 0) {
			JOptionPane.showMessageDialog(null, "Your people have starved and destroyed your civilization in a series of riots..." +
					"\nYou lose!");
			done = true; //lose if happiness reaches 0
		}
		else if (turn == 100) {
			JOptionPane.showMessageDialog(null, "You have taken too long to develop, and the fierce Yeti has destroyed your civilization!" +
					"\nYou lose!");
			done = true; //lose on turn 100 if not winning
		}
		else if (playerCiv.getLand().size() > 9) {
			JOptionPane.showMessageDialog(null, "You have developed your civilization into a sprawling empire, one that will stand the test of time!" +
					"\nYou win!");
			done = true;
		}
		else if (playerCiv.getGoldCount() > 100 && playerCiv.getStoneCount() > 100 && playerCiv.getWoodCount() > 100) {
			JOptionPane.showMessageDialog(null, "Your stockpiles are so plentiful that your civilization is the envy of all..." +
					"\nYou win!");
			done = true;
		} else if (yeti.getHealth() <= 0) {
			JOptionPane.showMessageDialog(null, "You have slain the fearsome yeti, and saved your civilization from destruction!" +
					"\nYou win!");
			done = true;
		} else done = false;
		if (done) 
			System.exit(0);
		return done;
	}
	
	public static Land getLandAt(Point point)
	{
		return map[point.x][point.y];
	}

	public String[][] drawOutline()
	// creates a list of what to draw in each cell
	// format "<landType>,<City>, <Unit> "
	{
		String[][] architect = new String[boardSizeX][boardSizeY];
		
		for(int i = 0; i < boardSizeX; i++)
		{
			for(int j = 0; j < boardSizeY; j++)
			{
				// Add land Type
				String value = "";
				value += map[i][j].getLandType().getType();
				if (map[i][j].hasRiver()) value += 'r';
				value += ",";
				// Add cities
				if(playerCiv.hasCityAt(new Point(i,j)))
				{
					value += playerCiv.getCityAt(new Point(i,j)).getCityType().getType();
				}// end if
				else
				{
					value += ",";
				}// end else
				// Add units
				if(playerCiv.hasUnitAt(new Point(i,j)))
				{
					value += playerCiv.getUnitAt(new Point(i,j)).getUnitType().getType();
					value += playerCiv.getUnitAt(new Point(i,j)).getHealth();
				}// end if
			} // end for j
		}// end for i
		
		return architect;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int incWidth = getWidth()/boardSizeY;
		int incHeight = getHeight()/boardSizeX;
		for(int i = 0; i < boardSizeX; i++) {
			for(int j = 0; j < boardSizeY; j++) {
				map[i][j].draw(g, incWidth, incHeight, i * incHeight, j * incWidth); //draw land squares first
				if (map[i][j].hasResource()) {
				}
			}
		}
		for (ImprovementBuilding b : playerCiv.getBuildings()) {
			b.draw(g, incWidth, incHeight, (int) b.getLocation().getX() * incHeight, (int) b.getLocation().getY() * incWidth); //draw improvements second
		}
		for (City c : playerCiv.getCities()) {
			c.draw(g, incWidth, incHeight, (int) c.getLocation().getX() * incHeight, (int) c.getLocation().getY() * incWidth); //draw cities third
		}
		for (Unit u : playerCiv.getUnits()) {
			u.draw(g, incWidth, incHeight, (int) u.getLocation().getX() * incHeight, (int) u.getLocation().getY() * incWidth); //draw units 4th
		}
		g.setColor(Color.BLUE);
		for (Land c : playerCiv.getLand()) 
			g.drawRect(c.getLocY() * incWidth, c.getLocX() * incHeight, incWidth, incHeight);
		g.setColor(Color.RED);
		if (selectedLocation != null) 
			g.drawRect((int) selectedLocation.getY() * incWidth, (int) selectedLocation.getX() * incHeight, incWidth, incHeight);
		for(int i = 0; i < boardSizeX; i++) {
			for(int j = 0; j < boardSizeY; j++) {
				if (map[i][j].hasResource()) {
					map[i][j].drawResource(g, incWidth, incHeight, i * incHeight, j * incWidth); //draw resources 5th
				}
			}
		}
		yeti.draw(g, incWidth, incHeight, (int) yeti.getLocation().getX() * incHeight, (int) yeti.getLocation().getY() * incWidth);
	}
	
	public void mapToGrid(int x, int y) {
		int incWidth = getWidth()/boardSizeY;
		int incHeight = getHeight()/boardSizeX;
		Point newLoc = new Point(y/incHeight, x/incWidth);
		if (newLoc != selectedLocation) {
			selectedLocation = newLoc;
			this.repaint();
		}
	}
	private void updateControlGUI( ) {
		controlGUI.setAllToFalse();
		// TODO select unit is needed
		// consider land
		// producing material
		if(playerCiv.hasCityAt(selectedLocation))
		{
			if(playerCiv.hasUnitAt(selectedLocation))
			{
				controlGUI.makeMoveControlTrue();
			}
			else
			{
				controlGUI.showMakeUnitButton();
			}
			
		}
		else
		{
			if(playerCiv.hasUnitAt(selectedLocation))
			{
				controlGUI.makeMoveControlTrue();
			}
			int x = (int) selectedLocation.getX();
			int y = (int) selectedLocation.getY();
			if (playerCiv.hasCityAt(selectedLocation)) return;
			if (playerCiv.hasImprovementBuildingAt(selectedLocation)) return;
			if (map[x][y].getLandType() == LandType.FOREST) controlGUI.getBuildingPanel().showSawmillButton();
			if (map[x][y].getResourceType() == ResourceType.GOLD) controlGUI.getBuildingPanel().showMineButton();
			if (map[x][y].getResourceType() == ResourceType.STONE) controlGUI.getBuildingPanel().showMineButton();
			if (map[x][y].getResourceType() == ResourceType.FOOD) controlGUI.getBuildingPanel().showFarmButton();
			if (map[x][y].getLandType() != LandType.MOUNTAIN && map[x][y].getLandType() != LandType.WATER) { //if buildable
				controlGUI.getBuildingPanel().showCityButton();
				controlGUI.getBuildingPanel().showBarrackButton();
				controlGUI.getBuildingPanel().showTradingPostButton();
			}
		}
	}

	public void setHighlightedCell(Point p) {
		selectedLocation = p;
	}
	
	public Point getSelectedLocation() {
		return selectedLocation;
	}

	class clickListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mapToGrid(e.getX(), e.getY());
			updateControlGUI();
			
		}
	}

	public void updateStatus() {
		statusBar.update();
		
	}

	public void setSelectedLocation(Point position) {
		selectedLocation = position;
		
	}

	public static Unit getYeti() {
		return yeti;
	}
}
