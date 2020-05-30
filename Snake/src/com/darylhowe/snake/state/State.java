/**
 * State - an abstract class. A state can be thought of as different screens eg menu, settings, game etc. 
 * Each state all have a certain number of behaviors in common. 
 * For example, they all need a 'tick()' so they can update their variables. 
 * They all need a 'render()' so they update the screen graphics .ie draw to canvas
 */
package com.darylhowe.snake.state;

import java.awt.Graphics;

import com.darylhowe.gfx.Assets;
import com.darylhowe.snake.Game;


public abstract class State {

	protected Game game;

	/**
	 * A constructor for State.
	 * @param game
	 */
	public State(Game game) {
		this.game = game;
		
		// Create the assets needed for the game. 
		Assets assets = new Assets();
	}

	public abstract void tick();

	public abstract void render(Graphics g);

}
