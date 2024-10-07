import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Landscape {
	private int width;
	private int height;
	private ArrayList<Buyer> buyers;
	private ArrayList<Seller> sellers;
	private static Random rand;

	public Landscape(int w, int h) {
		this.width = w;
		this.height = h;
		this.buyers = new ArrayList<>();
		this.sellers = new ArrayList<>();
		rand = new Random();
	}
	public Landscape(int w, int h, int N) {
		this(w, h);
		for (int i = 0; i < N; i++) {
			this.buyers.add(new Buyer(rand.nextDouble()));
			this.sellers.add(new Seller(rand.nextDouble()));
		}
	}

	public int getHeight() { return this.height; }
	public int getWidth() { return this.width; }

	public String toString() {
		String str = "";
		str += "Width: " + this.width + ", Height: " + this.height + "\n";
		str += "Equilibirum price" + this.getPe();
		str += "Equilibirum quantity" + this.getQe();
		return str;
	}
	public int screenY(double p) { return (int)Math.floor(p * this.height); }
    public int screenX(double p) { return (int)Math.floor(p * this.width); }

	public void drawDistribution(Graphics g) {
		for (Agent a : new LinkedList<>(this.sellers)) {
            int x = screenY(a.getP());
            int y = screenY(a.getP());
            g.fillOval(x, y, 5, 5);
		}
		for (Agent a : new LinkedList<>(this.buyers)) {
            int x = screenY(a.getP());
			int y = screenY(a.getP());
			g.fillOval(x, y, 5, 5);
		}
	}
	public void draw(Graphics g) {
		int resolution = 50;
		int[] xB = new int[resolution];
		int[] xS = new int[resolution];
		int[] y = new int[resolution];
		for (int i = 0; i < resolution; i++) {
			final double currP = 1.0 - 1.0 / resolution * i;
			y[i] = screenY(currP);
		}
        g.setColor(Color.BLACK);
		for (int i = 0; i < resolution; i++) {
			final double currP = 1.0 / resolution * i;
			int numBuyers = (int)this.buyers.stream().filter(obj -> obj.getP() >= currP).count();
			int numSellers = (int)this.sellers.stream().filter(obj -> obj.getP() <= currP).count();
			xB[i] = screenY((double)numBuyers / this.buyers.size());
			xS[i] = screenY((double)numSellers / this.sellers.size());
            g.drawString(currP + ", " + numBuyers, xB[i] - 60, y[i]);
            g.drawString(currP + ", " + numSellers, xS[i] - 60, y[i]);
		}
		g.setColor(new Color(255, 0, 0, 128));
		for (int i = 0; i < resolution; i++) {
			g.fillRect(xB[i] - 2, y[i] - 2, 5, 5);
		}
		g.drawPolyline(xB, y, resolution);
        int pE = screenY(getPe());
        g.drawRect(0, pE, this.width, 0);

		g.setColor(new Color(0, 0, 255, 128));
		for (int i = 0; i < resolution; i++) {
			g.fillRect(xS[i] - 2, y[i] - 2, 5, 5);
		}
		g.drawPolyline(xS, y, resolution);

	}
	public double getPe() {
        return (double)this.buyers.stream().mapToDouble(i -> (double) i.getP()).average().orElse(1.0);
	}
	public int getQe() {
		// number of agents (buyer or seller) transacted
		return 0;
	}
	public void updateAgents() {
		// randomize an array of indices -> match a random buyer with a random seller
		ArrayList<Integer> indxs = new ArrayList<>();
		for (int i = 0; i < this.buyers.size(); i++) {
			indxs.add(i);
		}
		Collections.shuffle(indxs);

		// compare the two's prices, if valid then the two agent exchanges
		for (int iB = 0; iB < this.buyers.size(); iB++) {
			int iS = indxs.get(iB);
			attemptExchange(this.buyers.get(iB), this.sellers.get(iS));
		}
	}
	public void attemptExchange(Buyer b, Seller s) {
		boolean buyerBuys = b.attemptExchange(s.getP());
		boolean sellerSells = s.attemptExchange(b.getP());
		b.exchange(s.getP(), buyerBuys && sellerSells);
		s.exchange(b.getP(), buyerBuys && sellerSells);
	}
	public void clearAgents() {
		this.buyers.clear();
		this.sellers.clear();
	}

	public static void main(String[] args) {}
}
