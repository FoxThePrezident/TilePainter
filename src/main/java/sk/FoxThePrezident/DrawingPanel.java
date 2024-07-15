package sk.FoxThePrezident;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static sk.FoxThePrezident.TilePainter.*;

public class DrawingPanel extends PreviewPanel {
	private boolean drawCursor = true;
	private int cursorY, cursorX;
	private Listener listener;

	public DrawingPanel() {
		super();
		listener = new Listener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
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
		if (drawCursor) {
			g.setColor(Settings.getColor());
			g.fillRect(cursorX * scale, cursorY * scale, scale, scale);
		}
	}

	public static boolean saveImage() {
		BufferedImage image = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				Color color = matrix[y][x];
				image.setRGB(x, y, color.getRGB());
			}
		}

		String home = System.getProperty("user.home");
		File file = new File(home+"/Downloads/image.png");
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			return false;
		}
		return true;
	}


	private void updateMatrix(MouseEvent e) {
		int y = e.getY() / scale;
		int x = e.getX() / scale;
		try {
			matrix[y][x] = Settings.getColor();
		} catch (Exception _) {
		}
		repaint();
		TilePainter.repaint();
	}

	private class Listener implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			updateMatrix(e);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			drawCursor = false;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			drawCursor = true;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			updateMatrix(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			cursorY = e.getY() / scale;
			cursorX = e.getX() / scale;
			// Smaller
			if ((cursorX < 0) || (cursorY < 0)) return;
			// Larger
			if ((cursorX > tileSize - 1) || (cursorY > tileSize - 1)) return;
			repaint();
		}
	}
}
