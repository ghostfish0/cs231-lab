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
	public AntiSocialAgent(double x, double y, int radius) { super(x, y, radius); }
	public void draw(Graphics g) {
		if (!moved)
			g.setColor(new Color(255, 0, 0)); // RED
		else
			g.setColor(new Color(255, 125, 125)); // LIGHT RED

		g.fillOval((int)getX(), (int)getY(), 5, 5);
	}
	public void updateState(Landscape scape) {
		LinkedList<Agent> neighbors = this.getNeighbors(scape);
		if (neighbors.size() > 1)
			this.attemptMove(scape);
		else
			this.moved = false;
	}
}
