/**
 * KeyManager - A class to register user inputs on the keyboard. 
 */

package com.darylhowe.snake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//NOTE: Implements KeyListener
public class KeyManager implements KeyListener {

	// An array of boolean values.
	private boolean[] keys;
	public boolean up, down, left, right;

	/**
	 * A constructor for KeyManager.
	 */
	public KeyManager() {

		// Create a boolean array with 256 spaces.
		keys = new boolean[256];
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// Put the pressed key id value = true.
		keys[e.getExtendedKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {

		// When a key is not being pressed = false.
		keys[e.getExtendedKeyCode()] = false;
	}

	public void tick() {

		// Link boolean variable with a keyboard key.
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
	}

}