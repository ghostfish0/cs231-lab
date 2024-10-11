/*
 * Tin Nguyen
 * Helper class specifically to work with an ArrayList of Agents
 * The simulation doesn't add or remove the Agents individually often so LinkedList is not used
 * ArrayList is better for constant access
 */
import java.util.ArrayList;
public class AgentList<E> extends ArrayList<Agent> {
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
	//  Same as above but take in an array of integer instead
	public static int[] toScreen(double p, int[] ns) {
		int[] arr = new int[ns.length];
		for (int i = 0; i < ns.length; i++)
			arr[i] = toScreen(p, ns[i]);
		return arr;
	}
	// Return the price of each agent of this list as a double array
	public double[] getPs() {
		double[] arr = new double[this.size()];
		int iterator = 0;
		for (Agent a : this) {
			arr[iterator] = a.getP();
			iterator++;
		}
		return arr;
	}
	// Count the frequency of the prices of the agents of this list across intervals
	// resolution is the number of intervals
	public int[] histogram(int resolution) {
		int[] cnt = new int[resolution];
		for (int i : toScreen(this.getPs(), resolution)) {
			cnt[i]++;
		}
		return cnt;
	}
	// Count the frequency of the prices of the agents of this list across intervals
	// and add the results cumulativeHistogramly from left to right
	// resolution is the number of intervals
	// use to calculate the seller's cumulativeHistogram function in the market
	// because the seller will sell at their expected price or higher
	// so the direction of accumulation is with the direction of the price
	public static int[] accumulate(int[] arr) {
		int[] arr_ = new int[arr.length];
		arr_[0] = arr[0];
		for (int i = 1; i < arr.length; i++)
			arr_[i] = arr_[i - 1] + arr[i];
		return arr_;
	}
	// Same as above but right to left
	// use to calculate the buyer's cumulativeHistogram function in the market
	// because the buyer will buy at their expected price or lower
	// so the direction of accumulation is against the direction of the price

	public static int[] accumulateR(int[] arr) {
		int[] arr_ = new int[arr.length];
		arr_[arr.length - 1] = arr[arr.length - 1];
		for (int i = arr.length - 2; i >= 0; i--)
			arr_[i] = arr_[i + 1] + arr[i];
		return arr_;
	}
	// Get the accumulation of prices and translate to screen coordinates
	public int[] toScreenCumulativeHistogram(int resolution, int size) {
		int[] accumulation = accumulate(this.histogram(resolution));
		int[] x = toScreen(size / this.size(), accumulation);
		return x;
	}
	// Same as above but right to left
	public int[] toScreenCumulativeHistogramR(int resolution, int size) {
		int[] accumulation = accumulateR(this.histogram(resolution));
		int[] x = toScreen(size / this.size(), accumulation);
		return x;
	}
	// Get the histogram of prices and translate to screen coordinates
	public int[] toScreenHistogram(int resolution, int canvasSize, int dataSize) {
		int[] histogram = this.histogram(resolution);
		int max_ = -1;
		for (int i : histogram)
			if (i > max_)
				max_ = i;
		int[] x = toScreen((double)canvasSize / max_, histogram);
		return x;
	}
	// Return the list of Agents that did not exchanged last iteration
	public AgentList<Agent> filter(boolean purchased) {
		AgentList<Agent> list = new AgentList<>();
		if (purchased)
			for (Agent a : this) {
				if (a.getPurchased())
					list.add(a);
			}
		else
			for (Agent a : this) {
				if (!a.getPurchased())
					list.add(a);
			}
		return list;
	}
}
