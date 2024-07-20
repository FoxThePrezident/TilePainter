package sk.FoxThePrezident.TilePainter.panels;

import sk.FoxThePrezident.TilePainter.Listeners.BackListener;
import sk.FoxThePrezident.TilePainter.Listeners.EraseToolListener;
import sk.FoxThePrezident.TilePainter.Listeners.SaveListener;

import javax.swing.*;
import java.awt.*;

public class Controls extends JPanel {

	/**
	 * Drawing controls panel
	 */
	public Controls() {
		// Initializing variables
		JButton save = new JButton("Save image");
		JButton back = new JButton("Step back");
		JButton eraseTool = new JButton("Erase tool");

		// Acton listeners
		save.addActionListener(new SaveListener());
		back.addActionListener(new BackListener());
		eraseTool.addActionListener(new EraseToolListener());

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Centering buttons
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Adding things to panel
		this.add(Box.createVerticalGlue());
		this.add(save);
		this.add(Box.createVerticalStrut(50));
		this.add(back);
		this.add(Box.createVerticalStrut(50));
		this.add(eraseTool);
		this.add(Box.createVerticalGlue());
	}
}
