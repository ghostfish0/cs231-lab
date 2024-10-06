/*
  Tin Nguyen
  SocialAgent subclass of the abstract Agent class
  Determine how the SocialAgent is drawn on the landscape
  and the update behavior of the agent
*/
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class SocialAgent extends Agent {
	/**
	 * Initialize a SocialAgent with given x and y coordinates and radius
	 * @param x The x coordinate of the SocialAgent
	 * @param y The y coordinate of the SocialAgent
	 * @param radius The search radius of the SocialAgent
	 */
	public SocialAgent(double x, double y, int radius) { super(x, y, radius); }
	/**
	 * Draw the SocialAgent in Blue to the display
	 * @param g The graphics to draw to
	 */
	public void draw(Graphics g) {
		if (!moved)
			g.setColor(new Color(0, 0, 255)); // BLUE
		else
			g.setColor(new Color(125, 125, 255)); // LIGHT BLUE

		g.fillOval((int)getX(), (int)getY(), 5, 5);
	}
	/**
	 * Check the agent's neighbors and decide whether the agents attempts to move or not 
	 * @param scape The landscape the Agent belongs to
	 */
	public void updateState(Landscape scape) {
		LinkedList<Agent> neighbors = this.getNeighbors(scape);
		if (neighbors.size() < 4)
			this.attemptMove(scape);
		else
			this.moved = false;
	}
}
