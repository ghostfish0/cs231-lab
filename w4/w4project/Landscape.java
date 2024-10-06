/*
  Tin Nguyen 
  The Landscape class holds 
  the width and height of the map,
  an ArrayList of Agents 
  and functions to modify and update this list
*/

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Landscape {
	private int width;
	private int height;
	private LinkedList<Agent> agents;
	private static Random rand;

	/**
     * Initialize a landscape with given width and height
	 * @param w Width of the landscape
	 * @param h Height of the landscape
	 */
	public Landscape(int w, int h) {
		this.width = w;
		this.height = h;
		this.agents = new LinkedList<>();
		rand = new Random();
	}
	/**
	 * Return the height of the Landscape
	 * @return the height of the Landscape
	 */
	public int getHeight() { return this.height; }
	/**
	 * Return the width of the Landscape
	 * @return the width of the Landscape
	 */
	public int getWidth() { return this.width; }
    /**
     * Add an agent to the list 
     * @param a the Agent to be added 
     */
	public void addAgent(Agent a) { this.agents.add(a); }
    /**
     * @return A string representation of the Landscape
     */
	public String toString() { return "Width: " + this.width + ", Height: " + this.height + "\n" + agents.size() + " agents"; }
    /**
     * Static method to calculate the distance squared between two given points in the landscape
     * @param ax x coordinate of the first point
     * @param ay y coordinate of the first point
     * @param bx x coordinate of the second point
     * @param by y coordinate of the second point
     * @return
     */
	public static double distanceSquared(double ax, double ay, double bx, double by) {
		return (ax - bx) * (ax - bx) + (ay - by) * (ay - by);
	}
    /**
     * Returns an ArrayList of Agents within the given radius from the given position
     * @param x0 x coordinate of the given postition
     * @param y0 y coordinate of the given postition
     * @return An ArrayList of Agents within the given radius from the given position
     */
	protected LinkedList<Agent> getNeighbors(double x0, double y0, double radius) {
		LinkedList<Agent> neighbors = new LinkedList<>();
		for (Agent a : this.agents) {
			if (distanceSquared(x0, y0, a.getX(), a.getY()) < radius * radius)
				neighbors.add(a);
		}
		return neighbors;
	}
    /**
     * Draw each agent in the landscape's list
     * @param g the Graphics instance to draw to 
     */
	public void draw(Graphics g) {
		LinkedList<Agent> agentsCopy = new LinkedList<>(this.agents);
		for (Agent a : agentsCopy) {
			a.draw(g);
		}
	}
    /**
     * Update the postition of each agents in the landscape's list 
     * @return The number of agents that moved
     */
	public int updateAgents() {
		int victimIndex = rand.nextInt(agents.size());
		Agent victim = this.agents.get(victimIndex);
		double x_ = victim.getX();
		double y_ = victim.getY();
		int r_ = victim.getRadius();
		this.agents.remove(victimIndex);
		AntiSocialAgent rebornedVictim = new AntiSocialAgent(x_, y_, r_);
		this.agents.add(victimIndex, rebornedVictim);
		int cntMoved = 0;
		for (int i = 0; i < this.agents.size(); i++) {
			cntMoved += this.agents.get(i).booleanUpdateState(this) ? 1 : 0;
		}
		return cntMoved;
	}
    /**
     * Clear the landscape's list of agents
     */
    public void clearAgents() {
        this.agents.clear();
    }
	public static void main(String[] args) {}
}
