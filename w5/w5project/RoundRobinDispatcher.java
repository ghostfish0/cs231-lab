/*
file name:      RoundRobinDispatcher.java
Authors:        Tin Nguyen
last modified:  10/11/2024
*/
import java.util.ArrayList;

public class RoundRobinDispatcher extends JobDispatcher {
    private int robinIndex = this.servers.size() - 1;

    public RoundRobinDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }
	public Server pickServer(Job j) {
        robinIndex++;
        robinIndex %= this.servers.size();
        return this.servers.get(robinIndex);
    }
}
