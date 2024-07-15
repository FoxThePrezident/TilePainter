package sk.FoxThePrezident;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Settings {
	private static JFrame frame;
	private JPanel panel;
	private JButton save;
	private static JColorChooser colorChooser;
	public Settings() {
		frame = new JFrame("Options");
		panel = new JPanel();
		colorChooser = new JColorChooser();
		save = new JButton("Save image");

		// Fancy code to have Swatches for choosing colors
		AbstractColorChooserPanel[] panels=colorChooser.getChooserPanels();
		for(AbstractColorChooserPanel p:panels) {
			String displayName = p.getDisplayName();
			if(!Objects.equals(displayName, "Swatches")) {
				colorChooser.removeChooserPanel(p);
			}
		}
		colorChooser.setColor(Color.BLACK);

		save.addActionListener(new Listener());

		panel.add(colorChooser);
		panel.add(save);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	public static Color getColor() {
		return colorChooser.getColor();
	}

	private static class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean result = DrawingPanel.saveImage();
			if (result) {
				JOptionPane.showMessageDialog(frame, "Image was successfully saved!");
			} else {
				JOptionPane.showMessageDialog(frame, "There was an error while saving an image!");
			}
		}
	}
}