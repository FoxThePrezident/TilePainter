package sk.FoxThePrezident.TilePainter.utils;

import sk.FoxThePrezident.TilePainter.Matrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static sk.FoxThePrezident.TilePainter.Matrix.tileSize;

/**
 * Class for manipulating with files
 */
public class Files {
	/**
	 * Saving image to a Downloads folder
	 */
	public static void saveImage(String filePath) {
		// Buffered image, that will hold pixel information
		BufferedImage image = new BufferedImage(tileSize, tileSize, BufferedImage.TYPE_INT_ARGB);

		// Looping through matrix and painting it onto image
		for (int y = 0; y < tileSize; y++) {
			for (int x = 0; x < tileSize; x++) {
				Color color = Matrix.matrix[y][x];
				image.setRGB(x, y, color.getRGB());
			}
		}

		if (!(filePath.endsWith(".png"))) {
			filePath += ".png";
		}

		// Saving it to a drive
		File file = new File(filePath);
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException ignored) {
		}
	}

	public static BufferedImage loadImage(String filePath) {
		BufferedImage image;

		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		}

		return image;
	}
}
