package sk.FoxThePrezident.TilePainter.panels;

import sk.FoxThePrezident.TilePainter.Listeners.DrawListener;

import java.awt.*;

import static sk.FoxThePrezident.TilePainter.Matrix.scale;

public class DrawingPanel extends PreviewPanel {
	private final DrawListener listener;

	/**
	 * Drawing panel, inherits from preview
	 */
	public DrawingPanel() {
		// Calling original setup
		super();

		// Drawing panel specific
		listener = new DrawListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}

	@Override
	public void paintComponent(Graphics g) {
		// Calling original painting
		super.paintComponent(g);

		// Drawing specific code
		// Drawing cursor
		if (listener.getDrawCursor()) {
			g.setColor(ColorChooser.getColor());
			g.fillRect(listener.getCursorX() * scale, listener.getCursorY() * scale, scale, scale);
		}
	}
}
