/*
  Tin Nguyen
  Tick subclass of the abstract Agent class
  Loves dogs, hates ticks, move if there are no dog around or if  too many ticks around
  Ticks are black
*/
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Tick extends Agent {
	/**
	 * Initialize a Tick with given x and y coordinates and radius
	 * @param x The x coordinate of the Tick
	 * @param y The y coordinate of the Tick
	 * @param radius The search radius of the Tick
	 */
	public Tick(double x, double y, int radius) { super(x, y, radius); }
	/**
	 * Draw the Tick in Black to the display
	 * @param g The graphics to draw to
	 */
	public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0)); // BLACK
		g.fillOval((int)getX(), (int)getY(), 3, 3);
	}
	/**
	 * Find dogs and ticks and perhaps move
	 * @param scape The landscape the Agent belongs to
	 */
	public void updateState(Landscape scape) {
		LinkedList<Agent> dogs = this.getDogs(scape);
        LinkedList<Agent> ticks = this.getTicks(scape);
		if (dogs.size() == 0 || ticks.size() > 5)
			this.attemptMove(scape);
		else
			this.moved = false;
	}
}
