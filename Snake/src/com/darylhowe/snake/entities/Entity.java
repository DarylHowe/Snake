/**
 * Entity - An abstract class to set the blueprint for all game entities. 
 */
package com.darylhowe.snake.entities;

import java.awt.Graphics;

public abstract class Entity {

	protected float x, y;
	protected int width, height;

	/**
	 * A constructor for Entity.
	 * 
	 * @param x      a float to store the entities x Position.
	 * @param y      a float to store the entities y Position.
	 * @param width  an in to store the entities width.
	 * @param height an in to store the entities height.
	 */
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// Abstract methods

	public abstract void tick();

	// Needs Graphics object passed in order to draw to canvas/screen.
	public abstract void render(Graphics g);

	// Setters Getters
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
