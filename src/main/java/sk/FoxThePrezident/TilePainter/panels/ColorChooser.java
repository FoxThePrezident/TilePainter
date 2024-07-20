package sk.FoxThePrezident.TilePainter.panels;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.util.Objects;

public class ColorChooser extends JPanel {
	/**
	 * Color chooser
	 */
	private static JColorChooser colorChooser;

	/**
	 * Color choosing panel
	 */
	public ColorChooser() {
		// Initializing variables
		colorChooser = new JColorChooser();

		// Fancy code to have only Swatches for choosing colors
		// Thus restricting availability to choose colors
		// Not as many options, because in Swatches you have predefined colors
		// But in others (HSV, HSL, RGB, CMYK), user have sliders
		AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
		for (AbstractColorChooserPanel p : panels) {
			String displayName = p.getDisplayName();
			if (!Objects.equals(displayName, "Swatches")) {
				colorChooser.removeChooserPanel(p);
			}
		}
		// Default color selection
		colorChooser.setColor(Color.BLACK);

		// Removing preview panel
		colorChooser.setPreviewPanel(new JPanel());

		// Adding color chooser to panel
		this.add(colorChooser);
	}

	/**
	 * Public method for getting currently selected color
	 *
	 * @return currently selected color
	 */
	public static Color getColor() {
		return colorChooser.getColor();
	}

	/**
	 * Public method for setting currently selected color
	 *
	 * @param color which we want ColorChooser to be
	 */
	public static void setColor(Color color) {
		colorChooser.setColor(color);
	}
}