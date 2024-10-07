import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Agent {
	protected static Random rand;
	protected double p;
	protected boolean purchased;

	public Agent(double p) {
		this.p = p;
		this.purchased = true;
		rand = new Random();
	}
	public double getP() { return this.p; }
	public void setP(double purchasePrice) { this.p = purchasePrice; }
	public boolean getPurchased() { return this.purchased; }
	public abstract String toString();
	public abstract void exchange(double purchasePrice, boolean agreeable);
	public abstract boolean attemptExchange(double p);
    //public abstract void drawDistribution(Graphics p, LinkedList<Agent> agents);
}
