/*
  Tin Nguyen 
  Dog subclass of the abstract Agent class
  Hates ticks, moves if there are too many ticks around
  Dog is green
*/
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Dog extends Agent {
	/**
	 * Initialize an Dog with given x and y coordinates and radius
	 * @param x The x coordinate of the Dog
	 * @param y The y coordinate of the Dog
	 * @param radius The search radius of the Dog
	 */
	public Dog(double x, double y, int radius) { super(x, y, radius); }
	/**
	 * Draw the Dog in Green to the display
	 * @param g The graphics to draw to
	 */
	public void draw(Graphics g) {
        g.setColor(new Color(0, 255, 0)); // RED
		g.fillRect((int)getX() - 5, (int)getY() - 5, 10, 10);
	}
	/**
	 * Check the Dog for ticks and perhaps move
	 * @param scape The landscape the Agent belongs to
	 */
	public void updateState(Landscape scape) {
		LinkedList<Agent> ticks = this.getTicks(scape);
		if (ticks.size() > 10)
			this.attemptMove(scape);
		else
			this.moved = false;
	}
}
