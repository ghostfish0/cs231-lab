import java.util.Random;
public class SocialAgentSimulation {
	public static void main(String[] args) throws InterruptedException {
		Random rand = new Random();
		int N = args.length == 0 ? 200 : Integer.parseInt(args[0]);

		Landscape scape = new Landscape(100, 100);
		LandscapeDisplay display = new LandscapeDisplay(scape);

		for (int i = 0; i < N; i++) {
			scape.addAgent(new SocialAgent(rand.nextDouble(scape.getWidth()), rand.nextDouble(scape.getHeight()), 25));
		}
		int cntMoved = 1;
		int frame = 0;
		while (true) {
			Thread.sleep(20);
			cntMoved = scape.updateAgents();
			display.repaint();
			if (cntMoved == 0 || frame++ >= 5000)
				break;
		}
	}
}
