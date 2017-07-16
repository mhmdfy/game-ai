package com.game;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

import processing.core.PApplet;
import processing.core.PImage;

import com.data_structure.bomb.Bomb;
import com.data_structure.bomb.Flame;
import com.data_structure.character.Char;

public class Display extends PApplet 
{
	private static final long serialVersionUID = 3480668359190549352L;
	private Game game;
	
	// variables of all used images:
	PImage wall;
	PImage brick;
	PImage bomberman;
	PImage enemy;
	PImage bomb;
	PImage flame;
	
	PImage gameOver;
	PImage gameWin;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setup() 
	{
		size(1050,1050);
		
		game = deserializeString(args[0]);
		
		// Initialize all images
		wall = loadImage(Constants.IMG_PATH + "wall" + Constants.IMG_EXT);
		brick = loadImage(Constants.IMG_PATH + "brick" + Constants.IMG_EXT);
		bomberman = loadImage(Constants.IMG_PATH + "bomberman" + Constants.IMG_EXT);
		enemy = loadImage(Constants.IMG_PATH + "enemy" + Constants.IMG_EXT);
		bomb = loadImage(Constants.IMG_PATH + "bomb" + Constants.IMG_EXT);
		flame = loadImage(Constants.IMG_PATH + "flame" + Constants.IMG_EXT);
		
		gameOver = loadImage(Constants.IMG_PATH + "gameOver" + Constants.IMG_EXT);
		gameWin = loadImage(Constants.IMG_PATH + "gameWin" + Constants.IMG_EXT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw() 
	{
		// draw the green background
		background(0,100,0);
		
		for(int x = 0; x < Constants.WIDTH; x++) 
		{
			for(int y = 0; y < Constants.HEIGHT; y++) 
			{
				if(game.getLevel().getBlock(x, y).isWall())
					image(wall,x*Constants.MAP_X, y*Constants.MAP_Y);
				
				if(game.getLevel().getBlock(x, y).isBreakable())
					image(brick,x*Constants.MAP_X, y*Constants.MAP_Y);
			}
		}
		
		for (Bomb b : game.getBombs())
			image(bomb, b.getX()*Constants.MAP_X, b.getY()*Constants.MAP_Y);
		
		for (Flame f : game.getFlames()) 
			image(flame, f.getX()*Constants.MAP_X, f.getY()*Constants.MAP_Y);
		
		image(bomberman, game.getPlayer().getX(), game.getPlayer().getY());
		
		for (Char c : game.getEnemies())
			image(enemy, c.getX(), c.getY());
		
		game.tick();
		
		if(game.getState() == Constants.GAME_LOSE)
			image(gameOver, 0, 0);
		
		else if(game.getState() == Constants.GAME_WIN)
			image(gameWin, 0, 0);
			
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed() 
	{
		if (key == 32) 
			game.playerBomb();
		
		if (key == CODED) 
		{
			if (keyCode == UP) 
				game.move("up");
			
			else if (keyCode == DOWN) 
				game.move("down");
			
			else if (keyCode == RIGHT) 
				game.move("right");
			
			else if (keyCode == LEFT) 
				game.move("left");
		}
	}
	
	/**
	 * Gets a serialized string of the game, and creates a Game
	 * Object by deserializing the string.
	 * @return The Game of the serialized string.
	 */
	private static Game deserializeString(String string) 
	{
		// deserialize the object
		try 
		{
			byte b[] = Base64.getDecoder().decode(string.getBytes());
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			Game game = (Game) si.readObject();
			return game;
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			System.exit(1);
		}
		throw new RuntimeException("Wasn't able to deserialize String");
	}
}
