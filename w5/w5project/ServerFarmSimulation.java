/*
file name:      ServerFarmSimulation.java
Authors:        Ike Lage
last modified:  03/07/2024
*/

public class ServerFarmSimulation {

	public static void main(String[] args) {

		// You can explore how these change your results if you want!
		// How often a new job arrives at the server farm, on average
		int meanArrivalTime = 3;
		// How long a job takes to process, on average
		int meanProcessingTime = 100;

		// Debugging settings
		// int numServers = 4; // Numbers of servers in the farm
		// int numJobs = 100; // Number of jobs to process
		// boolean showViz = true; // Set to true to see the visualization, and false to run your experiments
		// to speed up the display, you can decrease the sleep time in the ServerFarmViz class.

		// Main experiment settings

		int numServers = 34;     // Numbers of servers in the farm
		int numJobs = 10000000;  // Number of jobs to process
		boolean showViz = false; // Set to true to see the visualization, and false to run your experiments

		//String dispatcherType = "round"; // Which jobDispatcher to use
		//if (args.length > 0) {
		//	dispatcherType = args[0];
		//}

		String[] dispatcherTypes = new String[] {"random", "round", "shortest", "least"};

        // Initialize the job maker with the mean arrival and processing time
        JobMaker jobMaker = new JobMaker(meanArrivalTime, meanProcessingTime);

        // exploration 1
		for (String dispatcherType : dispatcherTypes) {

			// Create a dispatcher of the appropriate type
			JobDispatcher dispatcher = null;
			if (dispatcherType.equals("random")) {
				dispatcher = new RandomDispatcher(numServers, showViz);
			} else if (dispatcherType.equals("round")) {
				dispatcher = new RoundRobinDispatcher(numServers, showViz);
			} else if (dispatcherType.equals("shortest")) {
				dispatcher = new ShortestQueueDispatcher(numServers, showViz);
			} else if (dispatcherType.equals("least")) {
				dispatcher = new LeastWorkDispatcher(numServers, showViz);
			}

			// Have the dispatched handle the specified number of jobs
			for (int i = 0; i < numJobs; i++) {
				dispatcher.handleJob(jobMaker.getNextJob());
			}
			dispatcher.finishUp(); // Finish all of the remaining jobs in Server queues

			// Print out the mean processing time
			System.out.println("Dispatcher: " + dispatcherType + ", Avg. Wait time: " + dispatcher.getAverageWaitingTime());
		}

        // exploration 2
		for (numServers = 30; numServers <= 40; numServers++) {

            // create a dispatcher of shortest type
            JobDispatcher dispatcher = new ShortestQueueDispatcher(numServers, showViz);

			// Have the dispatched handle the specified number of jobs
			for (int i = 0; i < numJobs; i++) {
				dispatcher.handleJob(jobMaker.getNextJob());
			}
			dispatcher.finishUp(); // Finish all of the remaining jobs in Server queues

			// Print out the mean processing time
			System.out.println("Number of server: " + numServers + ", Avg. Wait time: " + dispatcher.getAverageWaitingTime());
		}
	}
}
