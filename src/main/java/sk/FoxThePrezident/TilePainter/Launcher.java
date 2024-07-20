package sk.FoxThePrezident.TilePainter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static sk.FoxThePrezident.TilePainter.utils.Files.loadImage;

/**
 * Launcher
 * Used to get basic configuration about size of drawing area
 */
public class Launcher {
	/**
	 * Display
	 */
	private static JFrame frame;
	/**
	 * Integer input for tileSize
	 */
	private static JSpinner tileSize;
	/**
	 * Integer input for scaling of tile
	 */
	private static JSpinner scale;

	public static void main(String[] args) {
		// Initializing window
		frame = new JFrame();
		frame.setTitle("Tile Painter Configuration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 150);
		frame.setLayout(new GridLayout(3, 2, 5, 5));

		// Tile size input
		JLabel tileSizeLabel = new JLabel("Tile Size:");
		SpinnerModel tileSizeModel = new SpinnerNumberModel(16, 8, 64, 1);
		tileSize = new JSpinner(tileSizeModel);
		frame.add(tileSizeLabel);
		frame.add(tileSize);

		// Scale input
		JLabel scaleLabel = new JLabel("Scale:");
		SpinnerModel scaleModel = new SpinnerNumberModel(10, 1, 15, 1);
		scale = new JSpinner(scaleModel);
		frame.add(scaleLabel);
		frame.add(scale);

		// Load image
		JButton createBtn = new JButton("Load image");
		createBtn.addActionListener(new LoadImageListener());
		frame.add(createBtn);

		// Create button
		createBtn = new JButton("Create Tile Painter");
		createBtn.addActionListener(new CreateDrawing());
		frame.add(createBtn);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static class LoadImageListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Initializing variables
			FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png");
			JFileChooser fileChooser = new JFileChooser();

			// Settings
			fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setFileFilter(filter);

			int r = fileChooser.showOpenDialog(null);

			// Successfully choosing save location
			if (r == JFileChooser.APPROVE_OPTION) {
				String file = fileChooser.getSelectedFile().getAbsolutePath();
				BufferedImage image = loadImage(file);

				try {
					Matrix.scale = (int) Launcher.scale.getValue();
				} catch (Exception ex) {
					Matrix.scale = 5;
				}

				Matrix.deconstructs(image);

				frame.setVisible(false);
				new TilePainter();
			}
		}
	}

	/**
	 * Click listener for creating drawing area
	 */
	private static class CreateDrawing implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				// Getting user inputted settings
				Matrix.tileSize = (int) Launcher.tileSize.getValue();
				Matrix.scale = (int) Launcher.scale.getValue();
				// Creating drawing area
				Matrix.init();
				frame.setVisible(false);
				new TilePainter();
			} catch (NumberFormatException ex) {
				// In case of some failure
				frame.setVisible(true);
				JOptionPane.showMessageDialog(frame, "Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}