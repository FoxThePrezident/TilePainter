package sk.FoxThePrezident.TilePainter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Math.max;

/**
 * Color matrix for holding position and colors
 */
public class Matrix {
	/**
	 * Size of a tile
	 */
	public static int tileSize;
	/**
	 * Scale factor
	 */
	public static int scale;
	/**
	 * Color matrix
	 */
	public static Color[][] matrix;
	/**
	 * Steps for drawing
	 */
	private static ArrayList<Pixel> steps;

	public static void init() {
		// Initializing matrix, to be every pixel White
		matrix = new Color[tileSize][tileSize];
		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				// Setting matrix to be transparent
				matrix[y][x] = new Color(0, 0, 0, 0);
			}
		}

		// Initializing steps
		steps = new ArrayList<>();
	}

	/**
	 * Constructing matrix from steps
	 */
	public static void construct() {
		// Resetting color to transparent
		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				matrix[y][x] = new Color(0, 0, 0, 0);
			}
		}

		// Drawing to matrix
		for (Pixel pixel : steps) {
			matrix[pixel.y][pixel.x] = pixel.color;
		}
	}

	/**
	 * Constructing steps from matrix
	 */
	public static void deconstructs(BufferedImage image) {
		tileSize = max(image.getWidth(), image.getHeight());
		Matrix.init();

		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				matrix[y][x] = new Color(image.getRGB(x, y));
			}
		}
		
		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				Pixel pixel = new Pixel(y, x, matrix[y][x]);
				steps.add(pixel);
			}
		}
	}

	/**
	 * Step back in drawing
	 */
	public static void back() {
		// Remove last, if it could
		if (!steps.isEmpty()) {
			steps.remove(steps.getLast());
		}
	}

	/**
	 * Adding new drawn pixel to steps
	 *
	 * @param pixel containing information like position and its color
	 */
	public static void add(Pixel pixel) {
		// Checking, if it is inside drawing board
		if (!((0 <= pixel.y) && (pixel.y < tileSize))) return; // Y-axis
		if (!((0 <= pixel.x) && (pixel.x < tileSize))) return; // X-axis

		// For case steps are empty
		if (steps.isEmpty()) {
			steps.add(pixel);
			return;
		}

		// Checking, it that pixel was not already added
		Pixel last = steps.getLast();
		if (!pixel.equals(last)) {
			steps.add(pixel);
		}
	}

	public record Pixel(int y, int x, Color color) {
		/**
		 * Holder class containing information about pixel
		 *
		 * @param y     integer position
		 * @param x     integer position
		 * @param color of that pixel
		 */
		public Pixel {
		}

			/**
			 * Custom equals method for comparing pixels
			 *
			 * @param obj which we want to compare
			 * @return true if they are same, false otherwise
			 */
			@Override
			public boolean equals(Object obj) {
				if (this == obj) return true;
				if (obj == null || getClass() != obj.getClass()) return false;
				Pixel pixel = (Pixel) obj;
				return y == pixel.y && x == pixel.x && color.equals(pixel.color);
			}

	}
}
