/**
 * Food - A class blueprints for the food object which the snake eats. 
 */
package com.darylhowe.snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Food extends Entity {

	private Color snakeColor = new Color(19, 152, 154);
	
	/**
	 * A constructor for Food. 
	 * @param x      a float to store the entities x Position.
	 * @param y      a float to store the entities y Position.
	 * @param width  an in to store the entities width.
	 * @param height an in to store the entities height.
	 */
	public Food(float x, float y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {

		g.setColor(snakeColor);
		g.fillRect((int) x, (int) y, width, height);
	}

	public Rectangle getFoodBounds() {
		return (new Rectangle((int) x, (int) y, width, height));
	}

}
