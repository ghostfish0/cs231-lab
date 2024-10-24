/*
  Tin Nguyen
  The SocialAgentSimulation class
  runs only a static main function to
  extract the data needed for exploration 1:
  Finding the correlation between number of agents in a landscape
  and the time (frames) it take for the landscape to come to a stable (no movement) position
*/
import java.util.Random;
public class DogTicksSimulation {
	public static void main(String[] args) throws InterruptedException {
		Random rand = new Random();
		int N;
		if (args.length == 0) {
			N = 200;
		} else {
			N = Integer.parseInt(args[0]);
		}
		Landscape scape = new Landscape(500, 500);
        // disable draw to display to increase data collection speed
		LandscapeDisplay display = new LandscapeDisplay(scape);

		for (N = 1; N <= 12; N++) {
			for (int trial = 0; trial < 10; trial++) {
				scape.clearAgents();
				for (int i = 0; i < N * 50; i++) {
					scape.addAgent(new Tick(rand.nextDouble(scape.getWidth()),
						        rand.nextDouble(scape.getHeight()),
						        25));
				}
				for (int i = 0; i < N * 10; i++) {
					scape.addAgent(new Dog(rand.nextDouble(scape.getWidth()),
						       rand.nextDouble(scape.getHeight()),
						       25));
				}
				int cntMoved = 1;
				int frame = 0;
				while (true) {
					Thread.sleep(5);
					cntMoved = scape.updateDogTicks();
					frame++;
					display.repaint();
					if (cntMoved == 0 || frame >= 5000) {
						System.out.println(N * 50 + " " + frame);
						break;
					}
				}
			}
		}
	}
}
