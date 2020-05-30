/**
 * GameState - A state for the main game screen. 
 */

package com.darylhowe.snake.state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.darylhowe.snake.Game;
import com.darylhowe.snake.entities.Food;
import com.darylhowe.snake.entities.Player;

public class GameState extends State {

	
	private int collide = 0;

	private Player player;
	private Food food;

	/**
	 * A constructor for GameState.
	 * @param game
	 */
	public GameState(Game game) {

		super(game);
		player = new Player(game, 50, 50, 10, 10);
		food = new Food(100, 200, 10, 10);
	}

	@Override
	public void tick() {

		player.tick();
		foodCollision();
		bodyCollision();
		snakeBounds();
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 400, 400);

		player.render(g);
		food.render(g);
	}

	/**
	 * A method which checks to see if the snake has moved off screen and adjusts the snake's position appropriate to
	 * the typical snake movement.
	 */
	public void snakeBounds() {

		Rectangle playerHead = player.getPlayerBounds();

		// If the snakes head hits the right wall/bound..
		if (playerHead.getX() >= game.getWidth()) {

			player.setXCo(0);

			// If the snakes head hits the left wall/bound..
		} else if (playerHead.getX() < 0) {

			player.setXCo(game.getWidth());
		}

		// If the snakes head hits the bottom wall/bound..
		if (playerHead.getY() >= game.getHeight()) {

			player.setYCo(0);

			// If the snakes head hits the top wall/bound..
		} else if (playerHead.getY() < 0) {

			player.setYCo(game.getHeight());
		}
	}

	/**
	 * A method which checks if the snake has collided with a piece of food or with itself.
	 */
	public void foodCollision() {

		Rectangle playerHead = player.getPlayerBounds();
		Rectangle foodBounds = food.getFoodBounds();

		// If the snakes head touches food..
		if (playerHead.intersects(foodBounds)) {

			// Ensures snake only eats one food at a time.
			if (collide == 0) {
				
				int x;
				int y;
				
				// Generate semi-random x,y co-ordinates.
				do {
					Random rand = new Random();
					x = rand.nextInt(game.getWidth() - 24);
					y = rand.nextInt(game.getHeight() - 24);
				} while (x % 12 != 0 && y % 12 != 0);

				// Move the food to x,y co-ordinates.
				food.setX(x);
				food.setY(y);

				collide = 1;

				player.addBodyPiece();
				player.increaseMoveSpeed();
			}
		}

		// If the snakes head is not touching food..
		if (!playerHead.intersects(foodBounds)) {
			collide = 0;
		}
	}
	
	
	/**
	 * A method which checks to see if the snakes head has collided with its body.
	 */
	public void bodyCollision() {
		
		Rectangle playerHead = player.getPlayerBounds();
		
		for (int i = 0; i < player.getSnake().size() - 1; i++) {

			if (playerHead.intersects(player.getSnake().get(i).getBodyPartBounds())) {

				player.stopMovement();
				player.reset();
				game.setToMenuState();
			}
		}
	}

}
