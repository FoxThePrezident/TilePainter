package sk.FoxThePrezident;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	private static JFrame frame;
	private static JSpinner tileSize;
	private static JSpinner scale;
	private static JButton createBtn;

	public static void main(String[] args) {
		frame = new JFrame();
		frame.setTitle("Tile Painter Configuration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 150);
		frame.setLayout(new GridLayout(3, 2, 5, 5));

		// Tile Size Input
		JLabel tileSizeLabel = new JLabel("Tile Size:");
		SpinnerModel tileSizeModel = new SpinnerNumberModel(16, 8, 64, 1);
		tileSize = new JSpinner(tileSizeModel);
		frame.add(tileSizeLabel);
		frame.add(tileSize);

		// Scale Input
		JLabel scaleLabel = new JLabel("Scale:");
		SpinnerModel scaleModel = new SpinnerNumberModel(10, 1, 15, 1);
		scale = new JSpinner(scaleModel);
		frame.add(scaleLabel);
		frame.add(scale);

		// Create Button
		createBtn = new JButton("Create Tile Painter");
		createBtn.addActionListener(new listener());
		frame.add(new JLabel()); // Placeholder
		frame.add(createBtn);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static class listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int tileSize = (int) Main.tileSize.getValue();
				int scale = (int) Main.scale.getValue();
				frame.setVisible(false);
				new TilePainter(tileSize, scale);
			} catch (NumberFormatException ex) {
				frame.setVisible(true);
				JOptionPane.showMessageDialog(frame, "Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}