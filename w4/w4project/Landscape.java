import java.awt.Graphics;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class Landscape {
	private int width;
	private int height;
	private LinkedList<Agent> agents;
	private static Random rand;

	public Landscape(int w, int h) {
		this.width = w;
		this.height = h;
		this.agents = new LinkedList<>();
		rand = new Random();
	}
	public int getHeight() { return this.height; }
	public int getWidth() { return this.width; }
	public void addAgent(Agent a) { agents.add(a); }
	public String toString() { return "Width: " + this.width + ", Height: " + this.height + "\n" + agents.size() + " agents"; }
	public static double distanceSquare(double ax, double ay, double bx, double by) {
		return (ax - bx) * (ax - bx) + (ay - by) * (ay - by);
	}
	public LinkedList<Agent> getNeighbors(double x0, double y0, double radius) {
		LinkedList<Agent> neighbors = new LinkedList<>();
		for (Agent a : this.agents) {
			if (distanceSquare(x0, y0, a.getX(), a.getY()) < radius * radius)
				neighbors.add(a);
		}
		return neighbors;
	}
	public void draw(Graphics g) {
		LinkedList<Agent> agentsCopy = new LinkedList<>(this.agents);
		for (Agent a : agentsCopy) {
			a.draw(g);
		}
	}
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
	public static void main(String[] args) {}
}
