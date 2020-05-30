/**
 * MenuState - A state for the menu screen of the game. 
 */
package com.darylhowe.snake.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.darylhowe.gfx.Assets;
import com.darylhowe.snake.Game;

public class MenuState extends State {
	
	private Color snakeColor = new Color(19, 152, 154);
	private Font font = new Font("Serif", Font.BOLD, 32);
	
	public MenuState(Game game) {
		super(game);
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.menuImage, 0, 0, 400, 400, null);
		
		g.setColor(snakeColor);
		g.fillRect(50, 50, 200, 50);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("PLAY NOW", 64, 87);	
	}

}
