/*
  Tin Nguyen 
  AntiSocialAgent subclass of the abstract Agent class
  Opposite to the SocialAgent class in behavior 
  Determine how the AntiSocialAgent is drawn on the landscape 
  and the update behavior of the agent
*/
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class AntiSocialAgent extends Agent {
	/**
	 * Initialize an AntiSocialAgent with given x and y coordinates and radius
	 * @param x The x coordinate of the AntiSocialAgent
	 * @param y The y coordinate of the AntiSocialAgent
	 * @param radius The search radius of the AntiSocialAgent
	 */
	public AntiSocialAgent(double x, double y, int radius) { super(x, y, radius); }
	/**
	 * Draw the AntiSocialAgent in Red to the display
	 * @param g The graphics to draw to
	 */
	public void draw(Graphics g) {
		if (!moved)
			g.setColor(new Color(255, 0, 0)); // RED
		else
			g.setColor(new Color(255, 125, 125)); // LIGHT RED

		g.fillOval((int)getX(), (int)getY(), 5, 5);
	}
	/**
	 * Check the agent's neighbors and decide whether the agents attempts to move or not 
	 * @param scape The landscape the Agent belongs to
	 */
	public void updateState(Landscape scape) {
		LinkedList<Agent> neighbors = this.getNeighbors(scape);
		if (neighbors.size() > 1)
			this.attemptMove(scape);
		else
			this.moved = false;
	}
}
