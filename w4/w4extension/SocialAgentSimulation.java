public class SocialAgentSimulation {
	public static void main(String[] args) throws InterruptedException {
		Landscape scape = new Landscape(500, 500, 100);

		LandscapeDisplay display = new LandscapeDisplay(scape);

		// Uncomment below when updateAgents() has been implemented
		while (true) {
			Thread.sleep(50);
			scape.updateAgents();
			display.repaint();
		}
	}
}
