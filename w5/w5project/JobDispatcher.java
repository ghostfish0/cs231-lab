/*
file name:      JobDispatcher.java
Authors:        Tin Nguyen
last modified:  10/11/2024
*/
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;

public abstract class JobDispatcher {
	private double sysTime;
	private ServerFarmViz viz;
	protected ArrayList<Server> servers;
	private LinkedList<Job> jobsProcessed;
    private int timeProcessed;

	public JobDispatcher(int K, boolean showViz) {
		this.sysTime = 0;
		this.viz = new ServerFarmViz(this, showViz);
		this.servers = new ArrayList<>(K);
        for (int iterator = 0; iterator < K; iterator++) {
            this.servers.add(new Server());
        }
		this.jobsProcessed = new LinkedList<>();
	}
	public double getTime() { return this.sysTime; }
	public ArrayList getServerList() { return this.servers; }
	public void advanceTimeTo(double time) {
		this.sysTime = time;
		for (Server s : this.servers)
			s.processTo(this.sysTime);
	}
	public abstract Server pickServer(Job j);
	public void handleJob(Job job) {
		this.jobsProcessed.add(job);
        this.timeProcessed += job.getProcessingTimeNeeded();
		this.sysTime = job.getArrivalTime();
		this.viz.repaint();
		pickServer(job).addJob(job);
		this.viz.repaint();
	}
	public void finishUp() {}
	public int getNumJobsHandled() { return this.jobsProcessed.size(); }
	public double getAverageWaitingTime() {
        return this.timeProcessed / this.getNumJobsHandled();
	}
	public void draw(Graphics g) {
		double sep = (ServerFarmViz.HEIGHT - 20) / (getServerList().size() + 2.0);
		g.drawString("Time: " + getTime(), (int)sep, ServerFarmViz.HEIGHT - 20);
		g.drawString("Jobs handled: " + getNumJobsHandled(), (int)sep, ServerFarmViz.HEIGHT - 10);
		for (int i = 0; i < getServerList().size(); i++) {
			this.servers.get(i).draw(g,  Color.GRAY, (i + 1) * sep, getServerList().size());
		}
	}
}
