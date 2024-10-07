import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Agent {
	protected double p;
	protected boolean purchased;
	protected static Random rand;

	public Agent(double p) {
		this.p = p;
		this.purchased = true;
		rand = new Random();
	}
	public double getP() { return this.p; }
	public void setP(double purchasePrice) { this.p = purchasePrice; }
	public boolean getPurchased() { return this.purchased; }
	//public abstract String toString();
	public void exchange(double purchasePrice, boolean agreeable) {
        //System.out.println(this + " " + this.p + " " + purchasePrice);
		double surplus = rand.nextDouble(0.7, 1.0) * (this.p - purchasePrice);
        this.purchased = agreeable;
		if (!agreeable) {
			this.purchased = false;
			//this.p -= surplus;
            this.p += (this instanceof Buyer ? 1 : -1) * 0.005;
			return;
		}
		this.purchased = true;
        this.p += (this instanceof Buyer ? -1 : 1) * 0.005;
        //this.p += surplus;
	}
	public abstract boolean attemptExchange(double p);
    public static void main(String[] args) {}
}
