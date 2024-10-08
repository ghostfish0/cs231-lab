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
	public static double[] getPs(ArrayList<Agent> agents) {
		double[] arr = new double[agents.size()];
		int iterator = 0;
		for (Agent a : agents) {
			arr[iterator] = a.getP();
			iterator++;
		}
		return arr;
	}
	public int[] distribution(int resolution) {
		int[] cnt = new int[resolution];
		for (int i : toScreen(getPs(this), resolution)) {
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
	public int[] toScreenDistribution(int resolution, int size) {
		int[] distribution = this.distribution(resolution);
        int max_ = -1;
        for(int i : distribution)
            if (i > max_)
                max_ = i;
		int[] x = toScreen((double) size / max_, distribution);
		return x;
	}
}
