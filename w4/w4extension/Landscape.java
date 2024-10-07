import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Landscape {
	private int width;
	private int height;
	private int buyersCnt;
	private int sellersCnt;
	private ArrayList<Buyer> buyers;
	private ArrayList<Seller> sellers;
	private static Random rand;

	public Landscape(int w, int h) {
        this(w, h, 5);
	}
	public Landscape(int w, int h, int N) {
		this(w, h, N, N);
	}
	public Landscape(int w, int h, int nB, int nS) {
		this.width = w;
		this.height = h;
		this.buyersCnt = nB;
		this.sellersCnt = nS;
		this.buyers = new ArrayList<>();
		this.sellers = new ArrayList<>();
        rand = new Random();
		for (int i = 0; i < this.buyersCnt; i++) {
			this.buyers.add(new Buyer(rand.nextDouble()));
		}
		for (int i = 0; i < this.sellersCnt; i++) {
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
	public int screenY(double p) { return screenY(p, this.height); }
	public int screenX(double p) { return screenX(p, this.width); }
	public static int screenY(double p, int height) { return (int)Math.floor(p * height); }
	public static int screenX(double p, int width) { return (int)Math.floor(p * width); }

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
	public int[] getXBs() {
		int[] x = new int[buyersCnt];
		for (int i = 0; i < buyersCnt; i++) {
			final double currP = 1.0 - 1.0 / buyersCnt * i;
			int numBuyers = (int)this.buyers.stream().filter(obj -> obj.getP() >= currP).count();
			x[i] = screenY((double)numBuyers / this.buyers.size());
		}
		return x;
	}
	public int[] getXSs() {
		int[] x = new int[sellersCnt];
		for (int i = 0; i < sellersCnt; i++) {
			final double currP = 1.0 - 1.0 / sellersCnt * i;
			int numSellers = (int)this.sellers.stream().filter(obj -> obj.getP() <= currP).count();
			x[i] = screenY((double)numSellers / this.sellers.size());
		}
		return x;
	}
	public int[] getYBs() {
		int[] y = new int[buyersCnt];
		for (int i = 0; i < buyersCnt; i++) {
			final double currP = 1.0 / buyersCnt * i;
			y[i] = screenY(currP);
		}
		return y;
	}
	public int[] getYSs() {
		int[] y = new int[sellersCnt];
		for (int i = 0; i < sellersCnt; i++) {
			final double currP = 1.0 / sellersCnt * i;
			y[i] = screenY(currP);
		}
		return y;
	}
	public void drawNode(Graphics g, Color c, int[] x, int[] y) {
        if (x.length != y.length) {
            System.err.println("x-s and y-s don't have the same length!");
            return;
        }
		g.setColor(c);
		for (int i = 0; i < x.length; i++) {
			g.fillRect(x[i] - 2, y[i] - 2, 5, 5);
		}
	}
	public void drawLines(Graphics g, Color c, int[] x, int[] y) {}
	public void drawRectsUnder(Graphics g, Color c, int[] x, int[] y) {}
	public void draw(Graphics g) {
		int[] xB = getXBs();
		int[] xS = getXSs();
		int[] yB = getYBs();
        int[] yS = getYSs();

		drawNode(g, new Color(255, 0, 0), xB, yB);
		drawNode(g, new Color(0, 0, 255), xS, yS);

		g.setColor(new Color(255, 0, 0, 128));
		g.drawPolyline(xB, yB, buyersCnt);

		g.setColor(new Color(0, 0, 255, 128));
		g.drawPolyline(xS, yS, sellersCnt);

		g.setColor(new Color(255, 0, 0, 128));
		for (int i = 1; i < buyersCnt; i++) {
			g.fillRect(xB[i - 1], yB[i], Math.abs(xB[i] - xB[i - 1]), this.height - yB[i]);
			g.drawRect(xB[i - 1], yB[i], Math.abs(xB[i] - xB[i - 1]), this.height - yB[i]);
		}
		g.setColor(new Color(0, 0, 255, 128));
		for (int i = 1; i < sellersCnt; i++) {
			g.fillRect(xS[i], yS[i], Math.abs(xS[i] - xS[i - 1]), this.height - yS[i]);
			g.drawRect(xS[i], yS[i], Math.abs(xS[i] - xS[i - 1]), this.height - yS[i]);
		}
		//      int pE = screenY(getPe());
		// g.drawRect(0, pE, this.width, 0);
	}
	public double getPe() { return (double)this.buyers.stream().mapToDouble(i -> (double)i.getP()).average().orElse(1.0); }
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
