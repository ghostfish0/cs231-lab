/*
  Tin Nguyen 
  The Seller class, represents firms participant in the market
*/
public class Seller extends Agent {
	public Seller(double p) { super(p); }
	public String toString() { return "Seller: " + this.p; }
	public void exchange(boolean agreeable) {
		this.purchased = agreeable;
        // The Seller raises their price if they made a successful deal 
        // and lower it otherwise
        // each time the amount is randomly varied
		this.p += (agreeable ? 1 : -1) * rand.nextDouble(0.4, 0.6) * 0.005;
		if (this.p >= 1.0)
			this.p = 0.9;
		if (this.p <= 0)
			this.p = 0.1;
	}
    // If the item's price is higher than the Sellers's expectation they buy it
	public boolean attemptExchange(double p) { return (p > this.p); }
}
