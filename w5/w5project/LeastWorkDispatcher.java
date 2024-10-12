/*
file name:      LeastWorkDispatcher.java
Authors:        Tin Nguyen
last modified:  10/11/2024
*/
import java.util.ArrayList;

public class LeastWorkDispatcher extends JobDispatcher {

    public LeastWorkDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }
	public Server pickServer(Job j) {
        int index = 0; 
        double[] queueWorks = new double[this.servers.size()];
        for (int i = 0; i < this.servers.size(); i++) {
            queueWorks[i] = this.servers.get(i).remainingWorkInQueue();
        }
        for (int i = 0; i < this.servers.size(); i++) {
            if (queueWorks[i] < queueWorks[index])
                index = i;
        }
        return this.servers.get(index); 
    }
}
