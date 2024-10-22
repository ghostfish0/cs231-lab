/*
file name:      Server.java
Authors:        Tin Nguyen
last modified:  10/11/2024
*/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Queue;
import java.util.LinkedList;

public class Server {
	private double sysTime;       // track system time
	private double remainingTime; // remaining amoutn of processing time needed for all jobs still in the queue.
	private int jobsProcessed;
	private Queue<Job> jobs;
	private int size;

	public Server() {
		this.sysTime = 0;
		this.remainingTime = 0;
		this.jobsProcessed = 0;
		this.jobs = new LinkedList<>();
	}

    public String toString() {
        return "[systime: " + this.sysTime + ", remainingTime: " + this.remainingTime + ", jobs processed: " + this.jobsProcessed + ", size:" + this.size() + " & " + this.jobs.size();
    }
    
	public void addJob(Job job) {
		this.jobs.offer(job);
        this.sysTime = job.getArrivalTime();
		this.remainingTime += job.getProcessingTimeRemaining();
		this.size++;
	}
	public void processTo(double time) {
		double timeLeft = time - this.sysTime;
		while (timeLeft > 0) {
			if (this.jobs.peek() == null) {
                this.sysTime = time;
                return;
            }
			Job curr = this.jobs.peek();
			double currTimeRequired = curr.getProcessingTimeRemaining();
			if (timeLeft >= currTimeRequired) { // more time than needed
				curr.process(currTimeRequired, this.sysTime);
				this.sysTime += currTimeRequired;
				this.remainingTime -= currTimeRequired;
				timeLeft -= currTimeRequired;
				this.jobsProcessed++;
				this.size--;
				this.jobs.poll();
			} else { // less than needed
				curr.process(timeLeft, this.sysTime);
				this.sysTime += timeLeft;
				this.remainingTime -= timeLeft;
				timeLeft = 0;
			}
		}
	}

	public double remainingWorkInQueue() { return this.remainingTime; }
	public int size() { return this.size; }
	public void draw(Graphics g, Color c, double loc, int numberOfServers) {
		double sep = (ServerFarmViz.HEIGHT - 20) / (numberOfServers + 2.0);
		g.setColor(Color.BLACK);
		g.setFont(new Font(g.getFont().getName(), g.getFont().getStyle(), (int)(72.0 * (sep * .5) / Toolkit.getDefaultToolkit().getScreenResolution())));
		g.drawString("Work: " + (remainingWorkInQueue() < 1000 ? remainingWorkInQueue() : ">1000"), 2, (int)(loc + .2 * sep));
		g.drawString("Jobs: " + (size() < 1000 ? size() : ">1000"), 5, (int)(loc + .55 * sep));
		g.setColor(c);
		g.fillRect((int)(3 * sep), (int)loc, (int)(.8 * remainingWorkInQueue()), (int)sep);
		g.drawOval(2 * (int)sep, (int)loc, (int)sep, (int)sep);
		if (remainingWorkInQueue() == 0)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
		g.fillOval(2 * (int)sep, (int)loc, (int)sep, (int)sep);
	}
}
