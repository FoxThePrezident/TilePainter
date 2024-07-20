package sk.FoxThePrezident.TilePainter.Listeners;

import sk.FoxThePrezident.TilePainter.Matrix;
import sk.FoxThePrezident.TilePainter.TilePainter;
import sk.FoxThePrezident.TilePainter.panels.ColorChooser;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static sk.FoxThePrezident.TilePainter.Matrix.scale;


public class DrawListener implements MouseListener, MouseMotionListener {
	/**
	 * Disabling drawing cursor while it is drawing something
	 */
	private static boolean drawCursor = true;
	/**
	 * Position of a cursor
	 */
	private int cursorY, cursorX;

	public boolean getDrawCursor() {
		return drawCursor;
	}

	public int getCursorY() {
		return cursorY;
	}

	public int getCursorX() {
		return cursorX;
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
			Matrix.Pixel pixel = new Matrix.Pixel(y, x, ColorChooser.getColor());
			Matrix.add(pixel);
			Matrix.construct();
		} catch (Exception ignored) {
		}
		// Calling original class, that it should repaint preview panels
		TilePainter.repaint();
	}

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
		// Updating cursor position
		cursorY = e.getY() / scale;
		cursorX = e.getX() / scale;
		TilePainter.repaint();
	}
}