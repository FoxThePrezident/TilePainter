package sk.FoxThePrezident.TilePainter.Listeners;

import sk.FoxThePrezident.TilePainter.panels.ColorChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EraseToolListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ColorChooser.setColor(new Color(0,0, 0, 0));
	}
}
