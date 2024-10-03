public class LifeSimulation {
	public static void main(String[] args) throws InterruptedException {
		// Landscape scape = new Landscape(100, 100, .25);
		final String[] blinker = {
		        "_____", //
		        "_____", //
		        "_xxx_", //
		        "_____", //
		        "_____", //
		};
		final String[] pulsar = {
		        "_____________________", //
		        "_____________________", //
		        "_____________________", //
		        "_____________________", //
		        "_____xxx___xxx_______", //
		        "_____________________", //
		        "___x____x_x____x_____", //
		        "___x____x_x____x_____", //
		        "___x____x_x____x_____", //
		        "_____xxx___xxx_______", //
		        "_____________________", //
		        "_____xxx___xxx_______", //
		        "___x____x_x____x_____", //
		        "___x____x_x____x_____", //
		        "___x____x_x____x_____", //
		        "_____________________", //
		        "_____xxx___xxx_______", //
		        "_____________________", //
		        "_____________________", //
		        "_____________________", //
		};

			for (double chance = 0; chance <= 1; chance += 0.05) {
				Landscape scape = new Landscape(128, 128, chance);
                System.out.print(scape.getSum() + " ");
				for (int iteration = 0; iteration < 1000; iteration++) {
					scape.advance();
				}
                System.out.print(scape.getSum() + " \n");
			}
	}
}
