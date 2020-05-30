/**
 * An class to create a window and canvas.
 */

package com.darylhowe.Display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;

	/**
	 * A constructor for Display. 
	 * @param title		A string to be displayed as the window's name/title. 
	 * @param width		An int setting the width of the window. 
	 * @param height	An int setting the height of the window. 
	 */
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay();
	}

	
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));

		// Ensures JFrame is the only thing that can have focus, without this computer may be listening only to the canvas (we have our KeyListener on the JFrame.)
		canvas.setFocusable(false);

		frame.add(canvas);

		// Ensures entire canvas can always be fully seen within frame by slightly adjusting frame size.
		frame.pack();
	}

	// Getters
	
	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getJFrame() {
		return frame;
	}
}
