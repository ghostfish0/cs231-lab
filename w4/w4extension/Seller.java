public class Seller extends Agent {
	public Seller(double p) { super(p); }
	public String toString() { return "Seller: " + this.p; }
	public void exchange(double purchasePrice, boolean agreeable) {
		this.purchased = agreeable;
		this.p += (agreeable ? 1 : -1) * 0.005;
	}
	public boolean attemptExchange(double p) { return (p > this.p); }
	public static void main(String[] args) {}
}
