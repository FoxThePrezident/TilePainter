package sk.FoxThePrezident;

import javax.swing.*;
import java.awt.*;

public class TilePainter {
	public static Color[][] matrix;
	public static int tileSize;
	public static int scale;
	private static JFrame frame;
	private static JPanel drawingArea;
	private static Settings settingsArea;
	private static DrawingPanel drawingPanel;
	private static PreviewPanel[] previewPanels;

	public static void main(String[] args) {
		new TilePainter(16, 10);
	}

	public TilePainter(int TileSize, int Scale) {
		tileSize = TileSize;
		scale = Scale;
		matrix = new Color[tileSize][tileSize];
		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				matrix[y][x] = Color.WHITE;
			}
		}

		frame = new JFrame("Tile painter");
		settingsArea = new Settings();

		frame.setLayout(new GridLayout(1, 2));

		initDrawing();

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void repaint() {
		for (PreviewPanel panel : previewPanels) {
			if (panel == null) continue;
			panel.repaint();
		}
	}

	private static void initDrawing() {
		previewPanels = new PreviewPanel[9];
		drawingArea = new JPanel();
		drawingPanel = new DrawingPanel();

		drawingArea.setLayout(new GridLayout(3, 3, 0, 0));

		for (int i = 0; i < 9; i++) {
			if (i == 4) {
				drawingArea.add(drawingPanel);
				previewPanels[i] = null;
				continue;
			}
			PreviewPanel panel = new PreviewPanel();
			previewPanels[i] = panel;
			drawingArea.add(panel);
		}
		frame.add(drawingArea);
	}
}