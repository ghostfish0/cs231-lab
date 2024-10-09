/*
  Tin Nguyen 
  The abstract Agent class common of the
  Buyer and Seller class
  Hold the Agent's current expected price and 
  helper fields and functions to determine the Agent's behavior
*/
import java.util.Random;
public abstract class Agent {
    // random generator
	protected static Random rand;
    // the agent's expected price
	protected double p;
    // keep track of whether this agent made an exchange last iteration
	protected boolean purchased;

	public Agent(double p) {
		this.p = p;
		this.purchased = true;
		rand = new Random();
	}
	public double getP() { return this.p; }
	public boolean getPurchased() { return this.purchased; }
	public abstract String toString();
	public abstract void exchange(boolean agreeable);
	public abstract boolean attemptExchange(double p);
}
