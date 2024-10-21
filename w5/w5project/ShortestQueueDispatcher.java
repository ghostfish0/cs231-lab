/*
file name:      ShortestQueueDispatcher.java
Authors:        Tin Nguyen
last modified:  10/11/2024
*/
import java.util.ArrayList;

public class ShortestQueueDispatcher extends JobDispatcher {

	public ShortestQueueDispatcher(int k, boolean showViz) { super(k, showViz); }
	public Server pickServer(Job j) {
		int index = 0;
		int[] queueSizes = new int[this.servers.size()];
		for (int i = 0; i < this.servers.size(); i++) {
			queueSizes[i] = this.servers.get(i).size();
		}
		//for (int i : queueSizes)
		//	System.out.print(i + " ");
		//      System.out.println("");
		for (int i = 0; i < this.servers.size(); i++) {
			if (queueSizes[i] < queueSizes[index])
				index = i;
		}
		return this.servers.get(index);
	}
}
