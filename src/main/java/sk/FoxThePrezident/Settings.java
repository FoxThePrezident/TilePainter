package sk.FoxThePrezident;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static sk.FoxThePrezident.TilePainter.matrix;
import static sk.FoxThePrezident.TilePainter.tileSize;

public class Settings {
	/**
	 * Settings window
	 */
	private static JFrame frame;
	/**
	 * Panel holding settings buttons
	 */
	private final JPanel panel;
	/**
	 * Save button
	 */
	private final JButton save;
	/**
	 * Color chooser
	 */
	private static JColorChooser colorChooser;

	/**
	 * Drawing settings area
	 */
	public Settings() {
		// Initializing variables
		frame = new JFrame("Options");
		panel = new JPanel();
		colorChooser = new JColorChooser();
		save = new JButton("Save image");

		// Fancy code to have only Swatches for choosing colors
		// Thus restricting availability to choose colors
		// Not as many options, because in Swatches you have predefined colors
		// But in others (RGB CMYK), user have sliders
		AbstractColorChooserPanel[] panels=colorChooser.getChooserPanels();
		for(AbstractColorChooserPanel p:panels) {
			String displayName = p.getDisplayName();
			if(!Objects.equals(displayName, "Swatches")) {
				colorChooser.removeChooserPanel(p);
			}
		}
		// Default selection
		colorChooser.setColor(Color.BLACK);

		save.addActionListener(new Listener());

		// Final touches and adding stuff to screen
		panel.add(colorChooser);
		panel.add(save);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Public method for getting currently selected color
	 * @return currently selected color
	 */
	public static Color getColor() {
		return colorChooser.getColor();
	}

	/**
	 * Saving image to a Downloads folder
	 * @return boolean, if saving was successful
	 */
	public static boolean saveImage() {
		// Buffered image, that will hold pixel information
		BufferedImage image = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_RGB);

		// Looping through matrix and painting it onto image
		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				Color color = matrix[y][x];
				image.setRGB(x, y, color.getRGB());
			}
		}

		// Saving it to a drive
		String home = System.getProperty("user.home");
		File file = new File(home+"/Downloads/image.png");
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * Click listener for saving image
	 */
	private static class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean result = saveImage();
			if (result) {
				// Case of successfully saving
				JOptionPane.showMessageDialog(frame, "Image was successfully saved!");
			} else {
				// Error occurred
				JOptionPane.showMessageDialog(frame, "There was an error while saving an image!");
			}
		}
	}
}