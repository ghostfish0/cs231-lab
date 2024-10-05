import java.awt.Color;
import java.awt.Graphics;
// import java.util.ArrayList;
// import java.util.Arrays;
 import java.util.LinkedList;
// import java.util.Queue;
// import java.util.Random;

public class AntiSocialAgent extends Agent {
	public AntiSocialAgent(double x, double y, int radius) { super(x, y, radius); }
	public void draw(Graphics g) {
		if (!moved)
			g.setColor(new Color(255, 0, 0));
		else
			g.setColor(new Color(255, 125, 125));

		g.fillOval((int)getX(), (int)getY(), 5, 5);
	}
	public void updateState(Landscape scape) {
		LinkedList<Agent> neighbors = scape.getNeighbors(this.x, this.y, this.radius);
		if (neighbors.size() > 1) 
            this.attemptMove(scape);
        else 
            this.moved = false;
    }
}
