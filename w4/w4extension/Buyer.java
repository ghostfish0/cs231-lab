/*
  Tin Nguyen 
  The Buyer class, represents consumer participant in the market
*/
public class Buyer extends Agent {
	public Buyer(double p) { super(p); }
	public String toString() { return "Buyer: " + this.p; }
	public void exchange(boolean agreeable) {
		this.purchased = agreeable;
        // The Buyer raises their price if they made a successful deal 
        // and lower it otherwise
        // each time the amount is randomly varied
		this.p += (agreeable ? -1 : 1) * rand.nextDouble(0.4, 0.6) * 0.005;
        // reset price if price is above or below the imaginary limit [0;1.0]
        if (this.p > this.absP)
            this.p = this.absP;
        if (this.p <= 0)
            this.p = 0.1;
	}
    // If the item's price is lower than the Buyer's expectation they buy it
    // The price must not exceed the absolute price (the paying capacity of the buyer)
	public boolean attemptExchange(double p) { return (p <= this.p); }
}
