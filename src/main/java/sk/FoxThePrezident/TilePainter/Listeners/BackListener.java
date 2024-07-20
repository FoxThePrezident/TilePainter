package sk.FoxThePrezident.TilePainter.Listeners;

import sk.FoxThePrezident.TilePainter.Matrix;
import sk.FoxThePrezident.TilePainter.TilePainter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Click listener for step back
 */
public class BackListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// Deleting last step
		Matrix.back();
		// Recreating color matrix
		Matrix.construct();
		// Updating screen
		TilePainter.repaint();
	}
}
