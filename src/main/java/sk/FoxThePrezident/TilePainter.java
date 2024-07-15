package sk.FoxThePrezident;

import javax.swing.*;
import java.awt.*;

public class TilePainter {
	/**
	 * Color matrix, for storing pixels in an image
	 */
	public static Color[][] matrix;
	/**
	 * Size of a tile
	 */
	public static int tileSize;
	/**
	 * Scale factor
	 */
	public static int scale;
	/**
	 * Window
	 */
	private static JFrame frame;
	/**
	 * Area grouping each display panel
	 */
	private static JPanel drawingArea;
	/**
	 * Settings window
	 * Holding stuff like save button and color palette
	 */
	private static Settings settingsArea;
	/**
	 * Drawing panel, which user paints on
	 */
	private static DrawingPanel drawingPanel;
	/**
	 * List of all panels for previewing image
	 */
	private static PreviewPanel[] previewPanels;

	/**
	 * Main window for drawing
	 * @param TileSize size of output image
	 * @param Scale factor for resizing image, so it is easier to see
	 */
	public TilePainter(int TileSize, int Scale) {
		// Initializing variables
		frame = new JFrame("Tile painter");
		settingsArea = new Settings();
		tileSize = TileSize;
		scale = Scale;

		// Initializing matrix, to be every pixel White
		matrix = new Color[tileSize][tileSize];
		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				matrix[y][x] = Color.WHITE;
			}
		}

		// Setting up grid layout
		frame.setLayout(new GridLayout(1, 2));

		initDrawing();

		// Initializing window
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Method used by DrawingPanel to refresh Preview panels
	 */
	public static void repaint() {
		// Looping trough each preview panel and calling repaint method
		for (PreviewPanel panel : previewPanels) {
			if (panel == null) continue;
			panel.repaint();
		}
	}

	/**
	 * Helper method used to initialize all preview panels and drawing panel
	 */
	private static void initDrawing() {
		// Initializing variables
		previewPanels = new PreviewPanel[9];
		drawingArea = new JPanel();
		drawingPanel = new DrawingPanel();

		// Setting layout
		drawingArea.setLayout(new GridLayout(3, 3, 0, 0));

		// Creating and adding drawing panels to a list
		for (int i = 0; i < 9; i++) {
			// Place for drawing panel
			if (i == 4) {
				// Adding to window
				drawingArea.add(drawingPanel);
				// Setting null on that position and continuing loop
				previewPanels[i] = null;
				continue;
			}
			// Creating new preview panel
			PreviewPanel panel = new PreviewPanel();
			previewPanels[i] = panel;
			drawingArea.add(panel);
		}
		frame.add(drawingArea);
	}
}