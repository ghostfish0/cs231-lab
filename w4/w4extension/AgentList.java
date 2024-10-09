import java.util.ArrayList;
public class AgentList<E> extends ArrayList<Agent> {
	public static int toScreen(double p, int n) { return (int)Math.floor(p * n); }
	public static int[] toScreen(double[] ps, int n) {
		int[] arr = new int[ps.length];
		for (int i = 0; i < ps.length; i++)
			arr[i] = toScreen(ps[i], n);
		return arr;
	}
	public static int[] toScreen(double p, int[] ns) {
		int[] arr = new int[ns.length];
		for (int i = 0; i < ns.length; i++)
			arr[i] = toScreen(p, ns[i]);
		return arr;
	}
	public double[] getPs() {
		double[] arr = new double[this.size()];
		int iterator = 0;
		for (Agent a : this) {
			arr[iterator] = a.getP();
			iterator++;
		}
		return arr;
	}
	public double[] getPurchaseds() {
		double[] arr = new double[this.size()];
		int iterator = 0;
		for (Agent a : this) {
			arr[iterator] = a.getPurchased() ? 0.167 : 0.333;
			iterator++;
		}
		return arr;
	}
	public int[] distribution(int resolution) {
		int[] cnt = new int[resolution];
		for (int i : toScreen(this.getPs(), resolution)) {
			cnt[i]++;
		}
		return cnt;
	}
	public static int[] accumulate(int[] arr) {
		int[] arr_ = new int[arr.length];
		arr_[0] = arr[0];
		for (int i = 1; i < arr.length; i++)
			arr_[i] = arr_[i - 1] + arr[i];
		return arr_;
	}
	public static int[] accumulateR(int[] arr) {
		int[] arr_ = new int[arr.length];
		arr_[arr.length - 1] = arr[arr.length - 1];
		for (int i = arr.length - 2; i >= 0; i--)
			arr_[i] = arr_[i + 1] + arr[i];
		return arr_;
	}
	public int[] toScreenAccumulative(int resolution, int size) {
		int[] accumulation = accumulate(this.distribution(resolution));
		int[] x = toScreen(size / this.size(), accumulation);
		return x;
	}
	public int[] toScreenAccumulativeR(int resolution, int size) {
		int[] accumulation = accumulateR(this.distribution(resolution));
		int[] x = toScreen(size / this.size(), accumulation);
		return x;
	}
	public int[] toScreenDistribution(int resolution, int canvasSize, int dataSize) {
		int[] distribution = this.distribution(resolution);
		int max_ = -1;
		for (int i : distribution)
			if (i > max_)
				max_ = i;
		int[] x = toScreen((double) canvasSize / dataSize, distribution);
		return x;
	}
	public AgentList<Agent> filterUnpurchased() {
		AgentList<Agent> list = new AgentList<>();
		for (Agent a : this) {
			if (!a.getPurchased())
				list.add(a);
		}
        return list;
	}
}
