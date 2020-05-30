package com.darylhowe.snake;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.darylhowe.Display.Display;
import com.darylhowe.GameManager.GameManager;
import com.darylhowe.snake.input.KeyManager;

import com.darylhowe.snake.state.GameState;
import com.darylhowe.snake.state.MenuState;
import com.darylhowe.snake.state.State;

// NOTE: Implements Runnable
public class Game implements Runnable {

	private int width, height;
	private String title;
	private boolean running = false;
	
	private Display display;

	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	private State gameState;
	private State menuState;

	private KeyManager keyManager;
	private GameManager gameManager;

	/**
	 * A constructor for Game.
	 * @param title		A string to be displayed as the window's name/title. 
	 * @param width		An int setting the width of the window. 
	 * @param height	An int setting the height of the window. 
	 */
	public Game(String title, int width, int height) {

		this.title = title;
		this.width = width;
		this.height = height;

		keyManager = new KeyManager();
		
		// Pass 'this' class in to the gameManager paramenter.
		gameManager = new GameManager(this);
	}


	/**
	 * A method to start the thread. 
	 */
	public synchronized void start() {

		if (running == true) {
			return;
		} else {

			running = true;

			// Create a new 'Game' thread object. 'this' refers to the class 'this' is in - in this case its Game.
			thread = new Thread(this);
			
			// Start the thread, this will invoke the 'run()' method.
			thread.start();
		}
	}

	/**
	 * A method to stop the thread.
	 */
	public synchronized void stop() {

		if (running == false) {
			return;
		} else {

			running = false;

			// How to stop a thread..
			try {

				// Stop the thread.
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * A method to act as the game clock. This method calls 'tick()' and 'render()' every frame. 
	 *
	 */
	@Override
	public void run() {
		
		init();

		int fps = 60;
		
		// TimePerTick measured in nano seconds (much more specific).
		
		double timePerTick = 1_000_000_000 / fps;
		double delta = 0;
		long now;

		// A clock in nano time.
		long lastTime = System.nanoTime();

		// Continually invoke 'tick()' and 'render()'.
		while (running == true) {

			// Another clock in nano time.
			now = System.nanoTime();

			// (now - lastTime) will give the amount of time passed since this code was last call.
			delta += (now - lastTime) / timePerTick;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();

				// Reset delta.
				delta = delta - 1;
			}
		}

		// When loop is over.
		stop();
	}

	/**
	 * A method that is only called once at start-up.
	 */
	private void init() {

		display = new Display(title, width, height);

		// Get the JFrame and add a Key Listener to it (allows access to the keyboard).
		// Legal to pass in 'keyManager' as 'Key Manager' class implements 'KeyListener'.
		display.getJFrame().addKeyListener(keyManager);

		// Get the canvas and add the 'gameManager' mouse listener. 
		// This makes the canvas listen for mouse clicks. 
		display.getCanvas().addMouseListener(gameManager);

		// Create a GameState and MenuState and pass in 'this' instance of the 'Game' class.
		gameState = new GameState(this);
		menuState = new MenuState(this);

		// Set the current state. Therefore 'menuState' will be loaded first at startup.  
		GameManager.setState(menuState);
	}

	/**
	 * A method which is called on every frame update. 
	 */
	private void tick() {

		// Update the 'keyManager.tick()' every tick.
		keyManager.tick();

		// Once the game state is not empty IE once there is a state that exists..
		if (GameManager.getState() != null) {

			// Get the current game state and run its 'tick()'.
			GameManager.getState().tick();
		}
	}

	/**
	 * A method to draw/paint to the screen/canvas. 
	 */
	private void render() {

		bs = display.getCanvas().getBufferStrategy();

		// If the canvas doesn't have a buffer strategy, create one and set number of buffers..
		if (bs == null) {
			
			// Note: 3 buffers set. 
			display.getCanvas().createBufferStrategy(3);
			return; 
		}

		// g IE 'Graphics' allows content to be drawn to the canvas.
		// Creates the 'paint brush'.
		g = bs.getDrawGraphics();

		// Clear the entire screen. 
		g.clearRect(0, 0, width, height);

		// Once the game state is not empty..
		if (GameManager.getState() != null) {

			// Get the current game state and run its 'render()' while passing in the Graphics 'g' object.
			GameManager.getState().render(g);
		}

		// Stop drawing. These two lines tell Java we are finished drawing.
		bs.show();
		g.dispose();
	}

	
	// Setters Getters
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setToGameState() {
		GameManager.setState(gameState);
	}

	public void setToMenuState() {
		GameManager.setState(menuState);
	}

	// Gives other classes access to the 'keyManager'. (Player class)
	public KeyManager getKeyManager() {
		return keyManager;
	}
}
