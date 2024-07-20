package sk.FoxThePrezident.TilePainter;

import sk.FoxThePrezident.TilePainter.panels.Controls;
import sk.FoxThePrezident.TilePainter.panels.DrawingPanel;
import sk.FoxThePrezident.TilePainter.panels.PreviewPanel;
import sk.FoxThePrezident.TilePainter.panels.ColorChooser;

import javax.swing.*;
import java.awt.*;

public class TilePainter {
	/**
	 * Window
	 */
	public static JFrame frame;
	/**
	 * Area grouping each display panel
	 */
	private static JPanel drawingArea;
	/**
	 * Panel containing preview and drawing panels
	 */
	private static DrawingPanel drawingPanel;
	/**
	 * List of all panels for previewing image
	 */
	private static PreviewPanel[] previewPanels;

	/**
	 * Main window for drawing
	 */
	public TilePainter() {
		// Initializing variables
		frame = new JFrame("Tile painter");
		ColorChooser colorChooserArea = new ColorChooser();
		Controls controlsPanel = new Controls();

		// Setting layout
		frame.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;

		// Adding settings area (0, 0)
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		frame.add(colorChooserArea, gbc);
		gbc.gridwidth = 1;

		// Adding controls panel (0, 1)
		gbc.gridx = 0;
		gbc.gridy = 1;
		frame.add(controlsPanel, gbc);

		// Drawing area
		initDrawing();
		gbc.gridx = 1;
		gbc.gridy = 1;
		frame.add(drawingArea, gbc);

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
		drawingPanel.repaint();
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
	}
}
