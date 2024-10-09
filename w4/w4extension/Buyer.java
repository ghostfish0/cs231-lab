import java.awt.Graphics;

public class Buyer extends Agent {
	public Buyer(double p) { super(p); }
	public String toString() { return "Buyer: " + this.p; }
	public void exchange(boolean agreeable) {
		this.purchased = agreeable;
		this.p += (agreeable ? -1 : 1) * rand.nextDouble(0.4, 0.6) * 0.005;
        if (this.p >= 1.0)
            this.p = 0.9;
        if (this.p <= 0)
            this.p = 0.1;
	}
	public boolean attemptExchange(double p) { return (p < this.p); }
}
