package sk.FoxThePrezident;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static sk.FoxThePrezident.TilePainter.*;

public class DrawingPanel extends PreviewPanel {
	/**
	 * Disabling drawing cursor while it is drawing something
	 */
	private boolean drawCursor = true;
	/**
	 * Position of a cursor
	 */
	private int cursorY, cursorX;

	/**
	 * Drawing panel, inherits from preview
	 */
	public DrawingPanel() {
		super();
		Listener listener = new Listener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	@Override
	public void paintComponent(Graphics g) {
		// Calling original painting
		super.paintComponent(g);

		// drawing specific code
		// Drawing cursor
		if (drawCursor) {
			g.setColor(Settings.getColor());
			g.fillRect(cursorX * scale, cursorY * scale, scale, scale);
		}
	}

	/**
	 * Updating matrix based on user mouse position
	 *
	 * @param e MouseEvent
	 */
	private void updateMatrix(MouseEvent e) {
		// Getting position of a mouse
		int y = e.getY() / scale;
		int x = e.getX() / scale;
		// Trying to set color into matrix
		try {
			matrix[y][x] = Settings.getColor();
		} catch (Exception ignored) {
		}
		// Repainting self
		repaint();
		// Calling original class, that it should repaint preview panels
		TilePainter.repaint();
	}

	/**
	 * Click listener
	 */
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
			// Smaller, top and left
			if ((cursorX < 0) || (cursorY < 0)) return;
			// Larger, bottom and right
			if ((cursorX > tileSize - 1) || (cursorY > tileSize - 1)) return;
			repaint();
		}
	}
}
