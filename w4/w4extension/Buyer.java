import java.awt.Graphics;

public class Buyer extends Agent {
	public Buyer(double p) { super(p); }
	public String toString() { return "Buyer: " + this.p; }
	public void exchange(double purchasePrice, boolean agreeable) {
		double surplus = this.p - purchasePrice;
		this.purchased = agreeable;
		this.p -= (agreeable ? 1 : -1) * rand.nextDouble(0.4, 0.6) * 0.005;
	}
	public boolean attemptExchange(double p) { return (p < this.p); }
}
