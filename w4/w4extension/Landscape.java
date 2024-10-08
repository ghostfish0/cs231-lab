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
	protected ArrayList<Buyer> buyers;
	protected ArrayList<Seller> sellers;
	protected AccumulativeGraph accumulativeGraph;
	protected DistributionGraph buyersDistributionGraph;
	protected DistributionGraph sellersDistributionGraph;
	protected static Random rand;

	public Landscape(int w, int h) { this(w, h, 5); }
	public Landscape(int w, int h, int N) { this(w, h, N, N); }
	public Landscape(int w, int h, int nB, int nS) {
		rand = new Random();
		this.width = 3 * w;
		this.height = h;
		this.accumulativeGraph = new AccumulativeGraph(0, 0, w, h);
		this.buyersDistributionGraph = new DistributionGraph(w, 0, w, h);
		this.sellersDistributionGraph = new DistributionGraph(2 * w, 0, w, h);
		this.buyersCnt = nB;
		this.sellersCnt = nS;
		this.buyers = new ArrayList<>();
		this.sellers = new ArrayList<>();
		for (int i = 0; i < this.buyersCnt; i++) {
			this.buyers.add(new Buyer(rand.nextDouble()));
		}
		for (int i = 0; i < this.sellersCnt; i++) {
			this.sellers.add(new Seller(rand.nextDouble()));
		}
	}
	public int getWidth() { return this.width; };
	public int getHeight() { return this.height; };
	public AccumulativeGraph getAccumulativeGraph() { return this.accumulativeGraph; };
	public DistributionGraph getBuyersDistributionGraph() { return this.buyersDistributionGraph; };
	public DistributionGraph getSellersDistributionGraph() { return this.sellersDistributionGraph; };

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
	}
	public void updateAgents() { matchAgents(new ArrayList<>(this.buyers), new ArrayList<>(this.sellers)); }
	public void attemptExchange(Agent a, Agent b) {
		boolean aWilling = b.attemptExchange(a.getP());
		boolean bWilling = a.attemptExchange(b.getP());
		b.exchange(a.getP(), aWilling && bWilling);
		a.exchange(b.getP(), aWilling && bWilling);
	}
	public void clearAgents() {
		this.buyers.clear();
		this.sellers.clear();
	}

    public void draw(Graphics g) {
        this.accumulativeGraph.draw(g);
    }
	private abstract class Graph {
		final Landscape scape = Landscape.this;
		protected int x;
		protected int y;
		protected int width;
		protected int height;
        protected static void offset(int[] arr, int k) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += k;
            }
        }
		public int getWidth() { return this.width; }
		public int getHeight() { return this.height; }
		public int screenX(double p) { return (int)Math.floor(p * this.width); }
		public int screenY(double p) { return (int)Math.floor(p * this.height); }
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
		public abstract void draw(Graphics g);
		public abstract int[] getXBs();
		public abstract int[] getXSs();
		public abstract int[] getYBs();
		public abstract int[] getYSs();
	}
	private class DistributionGraph extends Graph {
		public DistributionGraph(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
		}
		public int[] getXBs() {
			int[] x = new int[buyersCnt];
			for (int i = 0; i < buyersCnt; i++) {
				final double currP = 1.0 - 1.0 / buyersCnt * i;
				int buyersCnt = (int)scape.buyers.stream()
					        .filter(obj -> obj.getP() >= currP)
					        .count();
				x[i] = screenY((double)buyersCnt / scape.buyers.size());
			}
			return x;
		}
		public int[] getXSs() {
			int[] x = new int[sellersCnt];
			for (int i = 0; i < sellersCnt; i++) {
				final double currP = 1.0 - 1.0 / sellersCnt * i;
				int sellersCnt = (int)scape.sellers.stream()
					         .filter(obj -> obj.getP() <= currP)
					         .count();
				x[i] = screenY((double)sellersCnt / scape.sellers.size());
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
		public void draw(Graphics g) {
			for (Agent a : new LinkedList<>(scape.sellers)) {
				int x = screenY(a.getP());
				int y = screenY(a.getP());
				g.fillOval(x, y, 5, 5);
			}
			for (Agent a : new LinkedList<>(scape.buyers)) {
				int x = screenY(a.getP());
				int y = screenY(a.getP());
				g.fillOval(x, y, 5, 5);
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
		public int[] getXBs() {
			int[] x = new int[buyersCnt];
			for (int i = 0; i < buyersCnt; i++) {
				final double currP = 1.0 - 1.0 / buyersCnt * i;
				int buyersCnt = (int)scape.buyers.stream()
					        .filter(obj -> obj.getP() >= currP)
					        .count();
				x[i] = screenY((double)buyersCnt / scape.buyers.size());
			}
            offset(x, this.x);
			return x;
		}
		public int[] getXSs() {
			int[] x = new int[sellersCnt];
			for (int i = 0; i < sellersCnt; i++) {
				final double currP = 1.0 - 1.0 / sellersCnt * i;
				int sellersCnt = (int)scape.sellers.stream()
					         .filter(obj -> obj.getP() <= currP)
					         .count();
				x[i] = screenY((double)sellersCnt / scape.sellers.size());
			}
            offset(x, this.x);
			return x;
		}
		public int[] getYBs() {
			int[] y = new int[buyersCnt];
			for (int i = 0; i < buyersCnt; i++) {
				final double currP = 1.0 / buyersCnt * i;
				y[i] = screenY(currP);
			}
            offset(y, this.y);
			return y;
		}
		public int[] getYSs() {
			int[] y = new int[sellersCnt];
			for (int i = 0; i < sellersCnt; i++) {
				final double currP = 1.0 / sellersCnt * i;
				y[i] = screenY(currP);
			}
            offset(y, this.y);
			return y;
		}
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
				g.fillRect(xB[i - 1], yB[i], Math.abs(xB[i] - xB[i - 1]),
				           this.height - yB[i]);
				g.drawRect(xB[i - 1], yB[i], Math.abs(xB[i] - xB[i - 1]),
				           this.height - yB[i]);
			}
			g.setColor(new Color(0, 0, 255, 128));
			for (int i = 1; i < sellersCnt; i++) {
				g.fillRect(xS[i], yS[i], Math.abs(xS[i] - xS[i - 1]), this.height - yS[i]);
				g.drawRect(xS[i], yS[i], Math.abs(xS[i] - xS[i - 1]), this.height - yS[i]);
			}
			//      int pE = screenY(getPe());
			// g.drawRect(0, pE, this.width, 0);
		}
	}
	public static void main(String[] args) {}
}
