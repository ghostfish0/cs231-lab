import java.util.Arrays;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

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

		for (int size = 5; size < 50; size++) {
			for (double chance = 0; chance < 1; chance += 0.05) {
				Landscape scape = new Landscape(size, size, chance);
                System.out.print(scape.getSum() + " ");
				for (int iteration = 0; iteration < 1000; iteration++) {
					scape.advance();
				}
                System.out.print(scape.getSum() + " ");
			}
            System.out.println("");
		}
	}
}
