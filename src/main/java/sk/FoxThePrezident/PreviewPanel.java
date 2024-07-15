package sk.FoxThePrezident;

import javax.swing.*;

import java.awt.*;

import static sk.FoxThePrezident.TilePainter.scale;
import static sk.FoxThePrezident.TilePainter.tileSize;
import static sk.FoxThePrezident.TilePainter.matrix;

public class PreviewPanel extends JPanel {

	public PreviewPanel() {
		int size = tileSize * scale;
		this.setPreferredSize(new Dimension(size, size));
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, tileSize * scale, tileSize * scale);

		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				g.setColor(matrix[y][x]);
				g.fillRect(x * scale, y * scale, scale, scale);
				g.setColor(Color.WHITE);
			}
		}
	}
}
