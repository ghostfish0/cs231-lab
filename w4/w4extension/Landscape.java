import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Landscape {
	protected int width;
	protected int height;
	protected int buyersCnt;
	protected int sellersCnt;
	protected AgentList<Buyer> buyers;
	protected AgentList<Seller> sellers;
	protected AccumulativeGraph accumulativeGraph;
	protected DistributionGraph distributionGraph;
	final protected static Random rand = new Random();

	public Landscape(int w, int h) { this(w, h, 5); }
	public Landscape(int w, int h, int N) { this(w, h, N, N); }
	public Landscape(int w, int h, int nB, int nS) {
		this.accumulativeGraph = new AccumulativeGraph(0, 0, w, h);
		this.distributionGraph = new DistributionGraph(w, 0, w, h);
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
	public static int toScreen(double p, int n) { return (int)Math.floor(p * n); }
	public static int[] toScreen(double[] ps, int n) {
		int[] arr = new int[ps.length];
		for (int i = 0; i < ps.length; i++)
			arr[i] = toScreen(ps[i], n);
		return arr;
	}
	public int screenX(double p) { return toScreen(p, this.width); };
	public int screenY(double p) { return toScreen(p, this.height); };
	public int getWidth() { return this.width; };
	public int getHeight() { return this.height; };
	public String toString() {
		String str = "";
		str += this.buyersCnt + " buyers, " + this.sellersCnt + " sellers\n";
		return str;
	}

	public double getPe() { return (double)this.buyers.stream().mapToDouble(i -> (double)i.getP()).average().orElse(1.0); }
	public int getQe() {
		// number of agents (buyer or seller) transacted
		return 0;
	}

	public void matchAgents(ArrayList<Agent> as, ArrayList<Agent> bs) {
		if (as.size() > bs.size()) {
			ArrayList<Agent> temp_ = as;
			as = bs;
			bs = temp_;
		}
		Collections.shuffle(bs);
		for (int i = 0; i < as.size(); i++) {
			attemptExchange(as.get(i), bs.get(i));
		}
		for (int i = as.size(); i < bs.size(); i++) {
			bs.get(i).exchange(false);
		}
	}
	public void updateAgents() { matchAgents(new ArrayList<>(this.buyers), new ArrayList<>(this.sellers)); }
	public void attemptExchange(Agent a, Agent b) {
		boolean aWilling = b.attemptExchange(a.getP());
		boolean bWilling = a.attemptExchange(b.getP());
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
		this.accumulativeGraph.draw(g);
		this.distributionGraph.draw(g);
	}
	private abstract class Graph {
		final Landscape scape = Landscape.this;
		protected int x;
		protected int y;
		protected int width;
		protected int height;
		final protected int resolution = 50;
		public int getWidth() { return this.width; }
		public int getHeight() { return this.height; }
		public static void offset(int[] arr, int k) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] += k;
			}
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
		public int[] getYs() {
			double[] y = new double[this.resolution];
			for (int i = 0; i < resolution; i++)
				y[i] = (double)(resolution - i) / resolution;
			return toScreen(y, this.height);
		}
		public abstract void draw(Graphics g);
	}
	private class DistributionGraph extends Graph {
		public DistributionGraph(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
		}
		public void draw(Graphics g) {
			int[] xB = scape.buyers.toScreenDistribution(this.resolution, this.width / 2,
					             scape.buyers.size());
			int[] xS = scape.sellers.toScreenDistribution(this.resolution, this.width / 2,
					              scape.buyers.size());
			int[] yB = getYs();
			int[] yS = getYs();
			offset(xB, this.x);
			offset(xS, 3 * this.x / 2);
			offset(yB, this.y);
			offset(yS, this.y);

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

			AgentList<Agent> lostBuyers = scape.buyers.filterUnpurchased();
			AgentList<Agent> lostSellers = scape.sellers.filterUnpurchased();
			xB = lostBuyers.toScreenDistribution(this.resolution, this.width / 2, scape.buyers.size());
			xS = lostSellers.toScreenDistribution(this.resolution, this.width / 2, scape.buyers.size());
			offset(xB, this.x);
			offset(xS, 3 * this.x / 2);

			g.setColor(new Color(0, 0, 0, 128));
			for (int i = 1; i < this.resolution; i++) {
				g.fillRect(this.x, yB[i], xB[i] - this.x, yB[i - 1] - yB[i]);
			}
			for (int i = 1; i < this.resolution; i++) {
				g.fillRect(3 * this.x / 2, yS[i], xS[i] - 3 * this.x / 2,
				           yS[i - 1] - yS[i]);
			}
		}
	}
	private class AccumulativeGraph extends Graph {
		public AccumulativeGraph(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
		}
		public void draw(Graphics g) {
			int[] xB = scape.buyers.toScreenAccumulativeR(this.resolution, this.width);
			int[] xS = scape.sellers.toScreenAccumulative(this.resolution, this.width);
			int[] yB = getYs();
			int[] yS = getYs();

			offset(xB, this.x);
			offset(xS, this.x);
			offset(yB, this.y);
			offset(yS, this.y);

			drawNode(g, new Color(255, 0, 0), xB, yB);
			drawNode(g, new Color(0, 0, 255), xS, yS);

			g.setColor(new Color(255, 0, 0, 128));
			g.drawPolyline(xB, yB, this.resolution);

			g.setColor(new Color(0, 0, 255, 128));
			g.drawPolyline(xS, yS, this.resolution);

			g.setColor(new Color(255, 0, 0, 128));
			for (int i = 0; i < this.resolution - 1; i++) {
				g.fillRect(xB[i + 1], yB[i], Math.abs(xB[i] - xB[i + 1]),
				           this.height - yB[i]);
				g.drawRect(xB[i + 1], yB[i], Math.abs(xB[i] - xB[i + 1]),
				           this.height - yB[i]);
			}
			g.setColor(new Color(0, 0, 255, 128));
			for (int i = 0; i < this.resolution - 1; i++) {
				g.fillRect(xS[i], yS[i], Math.abs(xS[i + 1] - xS[i]), this.height - yS[i]);
				g.drawRect(xS[i], yS[i], Math.abs(xS[i + 1] - xS[i]), this.height - yS[i]);
			}
		}
	}
	public static void main(String[] args) {}
}
