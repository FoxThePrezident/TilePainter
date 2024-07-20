package sk.FoxThePrezident.TilePainter.Listeners;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static sk.FoxThePrezident.TilePainter.utils.Files.saveImage;

/**
 * Click listener for saving image
 */
public class SaveListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// Initializing variables
		FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png");
		JFileChooser fileChooser = new JFileChooser();

		// Settings
		fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
		fileChooser.setFileFilter(filter);

		int r = fileChooser.showSaveDialog(null);

		// Successfully choosing save location
		if (r == JFileChooser.APPROVE_OPTION) {
			String file = fileChooser.getSelectedFile().getAbsolutePath();
			saveImage(file);
		}
	}
}
