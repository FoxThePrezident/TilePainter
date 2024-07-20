package sk.FoxThePrezident.TilePainter.panels;

import sk.FoxThePrezident.TilePainter.Matrix;

import javax.swing.*;

import java.awt.*;

import static sk.FoxThePrezident.TilePainter.Matrix.scale;
import static sk.FoxThePrezident.TilePainter.Matrix.tileSize;


public class PreviewPanel extends JPanel {
	/**
	 * Preview panels used for only displaying currently drawn image
	 */
	public PreviewPanel() {
		// Setting panel
		int size = tileSize * scale;
		this.setPreferredSize(new Dimension(size, size));
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.repaint();
	}

	/**
	 * Painting squares onto a panel
	 *
	 * @param g the <code>Graphics</code> object to protect
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, tileSize * scale, tileSize * scale);

		// Looping through every color in matrix and displaying it as a rectangle onto panel
		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				g.setColor(Matrix.matrix[y][x]);
				g.fillRect(x * scale, y * scale, scale, scale);
				g.setColor(Color.WHITE);
			}
		}
	}
}
