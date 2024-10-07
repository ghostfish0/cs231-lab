import java.util.Random;
public class SocialAgentSimulation {
	public static void main(String[] args) throws InterruptedException {
		Random rand = new Random();
		int N;
		if (args.length == 0) {
			N = 200;
		} else {
			N = Integer.parseInt(args[0]);
		}
		Landscape scape = new Landscape(500, 500);
		// LandscapeDisplay display = new LandscapeDisplay(scape);

		for (int trial = 0; trial < 10; trial++) {
			for (N = 1; N <= 5; N++) {
				scape.clearAgents();
				int cntMoved = 1;
				int frame = 0;
				while (true) {
					// Thread.sleep(20);
					cntMoved = scape.updateAgents();
					frame++;
					// display.repaint();
					if (cntMoved == 0 || frame >= 5000) {
						System.out.println(N * 50 + " " + frame);
						break;
					}
				}
			}
		}
	}
}
