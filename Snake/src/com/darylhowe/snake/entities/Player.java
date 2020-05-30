/**
 * Player - A class for the snake entity. 
 */
package com.darylhowe.snake.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.darylhowe.snake.Game;

// NOTE: Extends Entity
public class Player extends Entity {

	private int xCo = 0, yCo = 150;
	private int bodyPieceOffset = 12;
	private int bodyDistanceX = bodyPieceOffset;
	private int bodyDistanceY = yCo;
	
	private int tick = 0;
	private double tickSpeed = 11;

	private boolean isGoingLeft = false;
	private boolean isGoingRight = true;
	private boolean isGoingUp = false;
	private boolean isGoingDown = false;
	
	private Game game;
	private BodyPart bodyPart;
	private ArrayList<BodyPart> snake;

	
	/**
	 * A constructor for Player which creates a snake like player with 5 body parts. 
	 * @param game	
	 * @param x      a float to store the entities x Position.
	 * @param y      a float to store the entities y Position.
	 * @param width  an in to store the entities width.
	 * @param height an in to store the entities height.
	 */
	public Player(Game game, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.game = game;
		snake = new ArrayList<BodyPart>();
		
		// Create a snake with 5 body parts

		// Create a bodyPart
		// Add the part to the snake ArrayList.
		bodyPart = new BodyPart(xCo - bodyPieceOffset, yCo, width, height);
		snake.add(bodyPart);

		// Create a bodyPart -12 pixels further back from the previous
		// Add the part to the snake ArrayList.
		bodyPart = new BodyPart(xCo - (bodyPieceOffset + 12), yCo, width, height);
		snake.add(bodyPart);

		bodyPart = new BodyPart(xCo - (bodyPieceOffset + 24), yCo, width, height);
		snake.add(bodyPart);

		bodyPart = new BodyPart(xCo - (bodyPieceOffset + 36), yCo, width, height);
		snake.add(bodyPart);

		bodyPart = new BodyPart(xCo - (bodyPieceOffset + 48), yCo, width, height);
		snake.add(bodyPart);
	}
	
	
	@Override
	public void tick() {
		tick++;

		if (tick > tickSpeed) {

			// If player presses 'a' key..
			if (game.getKeyManager().left && !isGoingRight) {
				isGoingLeft = true;
				isGoingRight = false;
				isGoingUp = false;
				isGoingDown = false;
			}

			// If player presses 'd' key..
			if (game.getKeyManager().right && !isGoingLeft) {
				isGoingLeft = false;
				isGoingRight = true;
				isGoingUp = false;
				isGoingDown = false;
			}

			// If player presses 'w' key..
			if (game.getKeyManager().up && !isGoingDown) {
				isGoingLeft = false;
				isGoingRight = false;
				isGoingUp = true;
				isGoingDown = false;
			}

			// If player presses 's' key..
			if (game.getKeyManager().down && !isGoingUp) {
				isGoingLeft = false;
				isGoingRight = false;
				isGoingUp = false;
				isGoingDown = true;
			}

			// If player wants to go left..
			if (isGoingLeft) {

				// Create a new body piece at the left of the snake. 
				bodyDistanceX -= 12;
				bodyPart = new BodyPart(bodyDistanceX, bodyDistanceY, 10, 10);

			} else if (isGoingUp) {

				bodyDistanceY -= 12;
				bodyPart = new BodyPart(bodyDistanceX, bodyDistanceY, 10, 10);

			} else if (isGoingDown) {

				bodyDistanceY += 12;
				bodyPart = new BodyPart(bodyDistanceX, bodyDistanceY, 10, 10);

			} else if (isGoingRight) {

				bodyDistanceX += 12;
				bodyPart = new BodyPart(bodyDistanceX, bodyDistanceY, 10, 10);
			}

			// Add the new bodyPart to the snake ArrayList.
			snake.add(bodyPart);
			
			// Remove the last body part of the snake (IE the piece at the end of the snakes 'tail'). 
			snake.remove(0);
			
			tick = 0;
		}
	}

	@Override
	public void render(Graphics g) {

		// Draw the snake.
		for (int i = 0; i < snake.size(); i++) {
			
			// Get the body part at index i and render it. 
			snake.get(i).render(g);
		}
	}

	/**
	 * A method which returns the bounds of the player's/snake's head.
	 * @return	a rectangle 
	 */
	public Rectangle getPlayerBounds() {
		
		// The snakes head will always be the last element in the 'snake' ArrayList.
		float headX = snake.get(snake.size() - 1).getX();
		float headY = snake.get(snake.size() - 1).getY();

		return (new Rectangle((int) headX, (int) headY, width, height));
	}

	/**
	 * A method to add a body piece to the end of the snake. 
	 */
	public void addBodyPiece() {

		// If the snake is moving left or right..
		if (isGoingLeft || isGoingRight) {

			bodyPart = new BodyPart(bodyDistanceX - 12, bodyDistanceY, 10, 10);
		} else {
			bodyPart = new BodyPart(bodyDistanceX - 0, bodyDistanceY - 12, 10, 10);
		}
		
		// Add the body part to the 'snake' ArrayList at index 0. 
		snake.add(0, bodyPart);
	}
	
	/**
	 * A method to incrementally increase the speed of the snake.
	 */
	public void increaseMoveSpeed() {

		if (tickSpeed > 5) {

			tickSpeed -= .5;
		} else if (tickSpeed <= 5 && tickSpeed > 3.5) {

			tickSpeed -= .25;
		} else if (tickSpeed <= 3.5 && tickSpeed > 1.1) {

			tickSpeed -= .1;
		} else if (tickSpeed <= 1.1) {

			tickSpeed = 1.05;
		}
	}
	
	/**
	 * A method which stops the snakes movement. 
	 */
	public void stopMovement() {
		tick = 0;
	}

	public void setXCo(int bodyDistanceX) {
		this.bodyDistanceX = bodyDistanceX;
	}

	public void setYCo(int bodyDistanceY) {
		this.bodyDistanceY = bodyDistanceY;
	}

	public ArrayList<BodyPart> getSnake() {
		return snake;
	}


	/**
	 * A method which is called when the game has been lost to reset the game for the next play. 
	 */
	public void reset() {

		// Remove all snake bodyPart's from the 'snake' ArrayList.
		for (int i = 0; i < snake.size() - 1; i++) {
			snake.remove(i);
		}

		xCo = 0;
		yCo = 150;
		bodyPieceOffset = 12;
		tickSpeed = 11;

		snake = new ArrayList<BodyPart>();

		bodyPart = new BodyPart(xCo - bodyPieceOffset, yCo, width, height);
		snake.add(bodyPart);

		bodyPart = new BodyPart(xCo - (bodyPieceOffset + 12), yCo, width, height);
		snake.add(bodyPart);

		bodyPart = new BodyPart(xCo - (bodyPieceOffset + 24), yCo, width, height);
		snake.add(bodyPart);

		bodyPart = new BodyPart(xCo - (bodyPieceOffset + 36), yCo, width, height);
		snake.add(bodyPart);

		bodyPart = new BodyPart(xCo - (bodyPieceOffset + 48), yCo, width, height);
		snake.add(bodyPart);
		
	}
}
