/**
 * GameManager - A class to control the game's state. Ie menu screen, game in play. 
 */

package com.darylhowe.GameManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.darylhowe.snake.Game;
import com.darylhowe.snake.state.State;

// NOTE: Extends Mouse Apadter and implements MosueListener
public class GameManager extends MouseAdapter implements MouseListener {

	private int xPosition;
	private int yPosition;

	private Game game;

	// A variable to store the current state.
	private static State currentState = null;

	/**
	 * A constructor for GameManager.
	 * @param game A Game object to pass. 	
	 */
	public GameManager(Game game) {
		this.game = game;
	}

	/**
	 * A method which is called when the user clicks on screen.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		// Get X position of mouse pointer.
		// Get Y position of mouse pointer.
		xPosition = e.getX();
		yPosition = e.getY();

		// If the user clicks on the 'Play' button area.
		if (xPosition > 48 && xPosition < 252 && yPosition > 48 && yPosition < 102) {
			game.setToGameState();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	// Setters, Getters

	public static void setState(State state) {
		currentState = state;
	}

	public static State getState() {
		return currentState;
	}

	public int getMouseXPosition() {
		return xPosition;
	}

	public int getMouseYPosition() {
		return yPosition;
	}

}
