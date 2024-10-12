/*
file name:      RandomDispatcher.java
Authors:        Tin Nguyen
last modified:  10/11/2024
*/
import java.util.ArrayList;
import java.util.Random;

public class ShortestQueueDispatcher extends JobDispatcher {
    private final Random rand = new Random();

    public ShortestQueueDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }
	public Server pickServer(Job j) {
        return this.servers.get(rand.nextInt(this.servers.size())); 
    }
}
