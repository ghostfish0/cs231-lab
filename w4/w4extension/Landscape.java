/*
  Tin Nguyen
  The Landscape class
  Simulates the landscape of the market
  Holds a list of every Buyer and every Seller
  helper fields and functions to update the Agent's behavior
  Includes helper class Graph and Graph subclasses to draw the results to the screen
*/
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Landscape {
	protected int width;
	protected int height;
	protected int buyersCnt;
	protected int sellersCnt;
	protected AgentList<Buyer> buyers;
	protected AgentList<Seller> sellers;
	protected CumulativeHistogramGraph cumulativeHistogramGraph;
	protected HistogramGraph histogramGraph;
	final protected static Random rand = new Random();

	public Landscape(int w, int h) { this(w, h, 5); }
	public Landscape(int w, int h, int N) { this(w, h, N, N); }
	public Landscape(int w, int h, int nB, int nS) {
		// place the cumulativeHistogramGraph first
		this.cumulativeHistogramGraph = new CumulativeHistogramGraph(0, 0, w, h);
		// place the histogramGraphs later using a width's offset
		this.histogramGraph = new HistogramGraph(w, 0, w, h);
		// reserve width for the graphs
		this.width = 2 * w;
		this.height = h;
		this.buyersCnt = nB;
		this.sellersCnt = nS;
		this.buyers = new AgentList<Buyer>();
		this.sellers = new AgentList<Seller>();
		for (int i = 0; i < this.buyersCnt; i++) {
			this.buyers.add(new Buyer(rand.nextDouble()));
		}
		for (int i = 0; i < this.sellersCnt; i++) {
			this.sellers.add(new Seller(rand.nextDouble()));
		}
	}
	// Multiply a double and an integer, takes the floor, most often used to translate real coordinates to position on the
	// screen
	public static int toScreen(double p, int n) { return (int)Math.floor(p * n); }
	// Same as above but take in an array of double instead
	public static int[] toScreen(double[] ps, int n) {
		int[] arr = new int[ps.length];
		for (int i = 0; i < ps.length; i++)
			arr[i] = toScreen(ps[i], n);
		return arr;
	}
	// Translate a real x-coordinate from [0; 1.0] to the screen
	public int screenX(double p) { return toScreen(p, this.width); };
	// Translate a real y-coordinate from [0; 1.0] to the screen
	public int screenY(double p) { return toScreen(p, this.height); };
	public int getWidth() { return this.width; };
	public int getHeight() { return this.height; };
	public String toString() {
		String str = "";
		str += this.buyersCnt + " buyers, " + this.sellersCnt + " sellers\n";
		return str;
	}
	// Randomly match sellers with buyers to prepare for exchange
	public void matchAgents(ArrayList<Agent> as, ArrayList<Agent> bs) {
		// shuffle the shorter list
		if (as.size() > bs.size()) {
			ArrayList<Agent> temp_ = as;
			as = bs;
			bs = temp_;
		}
		Collections.shuffle(bs);

		// match agents in common with each other
		for (int i = 0; i < as.size(); i++) {
			attemptExchange(as.get(i), bs.get(i));
		}
		// agents who can't find a partner, defaults to setting .purchased is false
		for (int i = as.size(); i < bs.size(); i++) {
			bs.get(i).exchange(false);
		}
	}
	// update agents, create a new copy of each list
	public void updateAgents() { matchAgents(new ArrayList<>(this.buyers), new ArrayList<>(this.sellers)); }
	// attempt an exchange
	public void attemptExchange(Agent a, Agent b) {
		// prompt each side using the other side's price
		boolean aWilling = b.attemptExchange(a.getP());
		boolean bWilling = a.attemptExchange(b.getP());
		// if only the price satisfies both sides, exchange
		b.exchange(aWilling && bWilling);
		a.exchange(aWilling && bWilling);
	}
	public void clearAgents() {
		this.buyers.clear();
		this.sellers.clear();
	}
	public void clearBuyers() { this.buyers.clear(); }
	public void clearSellers() { this.sellers.clear(); }
	public void draw(Graphics g) {
		// draw the CumulativeHistogram graph
		this.cumulativeHistogramGraph.draw(g);
		// draw the Histogram graphs
		this.histogramGraph.draw(g);
	}
	private abstract class Graph {
		final Landscape scape = Landscape.this;
		protected int x;
		protected int y;
		protected int width;
		protected int height;
		// Resolution, defaults to 50
		final protected int resolution = 50;
		public static void offset(int[] arr, int k) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] += k;
			}
		}
		// draw nodes given lists of x and y coordinates
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
		// the y-axis is always the price
		public int[] getYs() {
			double[] y = new double[this.resolution];
			for (int i = 0; i < resolution; i++)
				y[i] = (double)(resolution - i) / resolution;
			return toScreen(y, this.height);
		}
		public abstract void draw(Graphics g);
	}
	private class HistogramGraph extends Graph {
		public HistogramGraph(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
		}
		public void draw(Graphics g) {
			// draw the histogram in black for agents who didn't get to exchange last turn
			AgentList<Agent> lostBuyers = scape.buyers.filterUnpurchased();
			AgentList<Agent> lostSellers = scape.sellers.filterUnpurchased();

			// two graphs, each for the buyers and the sellers, each is worth half the width
			int[] xB =
			        lostBuyers.toScreenHistogram(this.resolution, this.width / 2, scape.buyers.size());
			int[] xS =
			        lostSellers.toScreenHistogram(this.resolution, this.width / 2, scape.buyers.size());
			int[] yB = getYs();
			int[] yS = getYs();

			offset(xB, this.x);
			offset(xS, 3 * this.x / 2);
			offset(yB, this.y);
			offset(yS, this.y);

			g.setColor(new Color(0, 0, 0, 128));
			for (int i = 1; i < this.resolution; i++) {
				g.fillRect(this.x, yB[i], xB[i] - this.x, yB[i - 1] - yB[i]);
			}
			for (int i = 1; i < this.resolution; i++) {
				g.fillRect(3 * this.x / 2, yS[i], xS[i] - 3 * this.x / 2,
				           yS[i - 1] - yS[i]);
			}

			xB = scape.buyers.toScreenHistogram(this.resolution, this.width / 2, scape.buyers.size());
			xS = scape.sellers.toScreenHistogram(this.resolution, this.width / 2, scape.buyers.size());
			offset(xB, this.x);
			// offset the second graph to 1.5 the width
			offset(xS, 3 * this.x / 2);

			// draw the histogram
			g.setColor(new Color(255, 0, 0, 128));
			for (int i = 1; i < this.resolution; i++) {
				g.fillRect(this.x, yB[i], xB[i] - this.x, yB[i - 1] - yB[i]);
				g.drawRect(this.x, yB[i], xB[i] - this.x, yB[i - 1] - yB[i]);
			}
			g.setColor(new Color(0, 0, 255, 64));
			for (int i = 1; i < this.resolution; i++) {
				g.fillRect(3 * this.x / 2, yS[i], xS[i] - 3 * this.x / 2,
				           yS[i - 1] - yS[i]);
				g.drawRect(3 * this.x / 2, yS[i], xS[i] - 3 * this.x / 2,
				           yS[i - 1] - yS[i]);
			}
		}
	}
	private class CumulativeHistogramGraph extends Graph {
		public CumulativeHistogramGraph(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
		}
		public void draw(Graphics g) {
			int[] xB = scape.buyers.toScreenCumulativeHistogramR(this.resolution, this.width);
			int[] xS = scape.sellers.toScreenCumulativeHistogram(this.resolution, this.width);
			int[] yB = getYs();
			int[] yS = getYs();

			offset(xB, this.x);
			offset(xS, this.x);
			offset(yB, this.y);
			offset(yS, this.y);

			// draw the nodes
			drawNode(g, new Color(255, 0, 0), xB, yB);
			drawNode(g, new Color(0, 0, 255), xS, yS);

			// draw the lines
			g.setColor(new Color(255, 0, 0, 128));
			g.drawPolyline(xB, yB, this.resolution);

			g.setColor(new Color(0, 0, 255, 128));
			g.drawPolyline(xS, yS, this.resolution);

			// draw the rectangles under the curves
			g.setColor(new Color(255, 0, 0, 128));
			g.fillRect(0, 0, xB[this.resolution - 1], this.height);
			g.drawRect(0, 0, xB[this.resolution - 1], this.height);
			for (int i = 0; i < this.resolution - 1; i++) {
				g.fillRect(xB[i + 1], yB[i], Math.abs(xB[i] - xB[i + 1]),
				           this.height - yB[i]);
				g.drawRect(xB[i + 1], yB[i], Math.abs(xB[i] - xB[i + 1]),
				           this.height - yB[i]);
			}
			g.setColor(new Color(0, 0, 255, 128));
			g.fillRect(xS[0], yS[0], this.width - xS[0], this.height);
			g.drawRect(xS[0], yS[0], this.width - xS[0], this.height);
			for (int i = 1; i < this.resolution; i++) {
				g.fillRect(xS[i - 1], yS[i - 1], Math.abs(xS[i] - xS[i - 1]),
				           this.height - yS[i - 1]);
				g.drawRect(xS[i - 1], yS[i - 1], Math.abs(xS[i] - xS[i - 1]),
				           this.height - yS[i - 1]);
			}
		}
	}
	public static void main(String[] args) {}
}
