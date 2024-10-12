/*
file name:      RandomDispatcher.java
Authors:        Tin Nguyen
last modified:  10/11/2024
*/
import java.util.ArrayList;
import java.util.Random;

public class LeastWorkDispatcher extends JobDispatcher {
    private final Random rand = new Random();

    public LeastWorkDispatcher(int k, boolean showViz) {
        super(k, showViz);
    }
	public Server pickServer(Job j) {
        System.out.println(this.servers.size());
        return this.servers.get(0); 
    }
}
