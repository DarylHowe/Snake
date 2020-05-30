/**
 * Snake - The traditional snake game with menu screen.  
 @author Daryl Howe
 */
package com.darylhowe.snake;

public class Launcher {

	public static void main(String[] args) {
		
		Game game = new Game("Snake", 400, 400);
		game.start();

	}
}
