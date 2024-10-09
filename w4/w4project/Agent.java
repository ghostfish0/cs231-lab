/*
  Tin Nguyen 
  The abstract Agent class common of the
  SocialAgent and AntiSocialAgent class
  Holds the Agent's x, y position and 
  helper fields and functions to determine the Agent's behavior
*/
import java.util.Random;
import java.awt.Graphics;
import java.util.LinkedList;

public abstract class Agent {
	private double x;
	private double y;
	protected boolean moved;
	protected int radius;
	protected static Random rand;

	/**
     * Initialize an Agent with given x and y coordinates
	 * @param x The x coordinate of the Agent
	 * @param y The y coordinate of the Agent
	 */
	public Agent(double x, double y) {
		this.x = x;
		this.y = y;
		rand = new Random();
	}
	/**
     * Initialize an Agent with given x and y coordinates and radius 
	 * @param x The x coordinate of the Agent
	 * @param y The y coordinate of the Agent
     * @param radius The search radius of the agent
	 */
	public Agent(double x, double y, int radius) {
		this(x, y);
		this.radius = radius;
	}
	/**
     * Return the x coordinate of the Agent
	 * @return the x coordinate of the Agent
	 */
	public double getX() { return this.x; }
	/**
     * Return the y coordinate of the Agent
	 * @return the y coordinate of the Agent
	 */
	public double getY() { return this.y; }
	/**
     * Return the radius of the Agent
	 * @return the radius of the Agent
	 */
	public int getRadius() { return this.radius; }
    /**
     * Return whether the agent moved after the last update
     * @return whether the agent moved after the last update
     */
	public boolean getMoved() { return this.moved; }
	/**
     * Set the x coordinate of the agent
	 * @param newX the new x coordinate of the agent
	 */
	public void setX(double newX) { this.x = newX; }
	/**
     * Set the y coordinate of the agent
	 * @param newY the new y coordinate of the agent
	 */
	public void setY(double newY) { this.y = newY; }
	/**
     * Set the radius of the agent
	 * @param newRadius the new radius of the agent
	 */
	public void setRadius(int newRadius) { this.radius = newRadius; }
	/**
	 * @return A string representation of the agent (its position)
	 */
	public String toString() { return "(" + this.x + ", " + this.y + ")"; }
	/**
     * Attempt to move the Agent, checking the boundaries
	 * @param scape The Landscape the Agent belong to
	 */
	protected void attemptMove(Landscape scape) {
		double x_ = this.x + rand.nextInt(41) - 20;
		double y_ = this.y + rand.nextInt(41) - 20;
		if (x_ < 0 || y_ < 0 || x_ > scape.getWidth() || y_ > scape.getHeight()) {
			this.moved = false;
			return;
		}
		this.x = x_;
		this.y = y_;
		this.moved = true;
	}
	/**
     * Attempt to move the Agent, checking the boundaries
     * like attemptMove but returns whether the attempt is successful (whether the agent moved)
	 * @param scape The Landscape the Agent belong to
     * @return whether the agent moved;
	 */
	public boolean booleanUpdateState(Landscape scape) {
		updateState(scape);
		return this.moved;
	}
	/**
     * Return other agents within this agent's radius
     * @param scape The Landscape the Agent belong to
     * @return other agents within this agent's radius
	 */
    protected LinkedList<Agent> getNeighbors(Landscape scape) {
        return scape.getNeighbors(this.x, this.y, this.radius);
    }
    /**
     * Return dogs within this agent's radius
     * @param scape The Landscape the Agent belong to
     * @return dogs within this agent's radius
     */
    protected LinkedList<Agent> getDogs(Landscape scape) {
        return scape.getDogs(this.x, this.y, this.radius);
    }
    /**
     * Return ticks within this agent's radius
     * @param scape The Landscape the Agent belong to
     * @return dogs within this agent's radius
     */
    protected LinkedList<Agent> getTicks(Landscape scape) {
        return scape.getTicks(this.x, this.y, this.radius);
    }
	/**
     * Update the state of the Agent, to be defined in subclass
	 * @param scape The Landscape the agent belongs to
	 */
	public abstract void updateState(Landscape scape);
    /**
     * Draw the Agent to the display, to be defined in subclass
     * @param scape The Landscape the agent belongs to
     */
	public abstract void draw(Graphics g);
}
