package com.game;
import java.io.Serializable;
import java.util.ArrayList;

import com.data_structure.bomb.Bomb;
import com.data_structure.character.Bomberman;
import com.data_structure.character.Char;
import com.level_handling.Level;


public class Game implements Serializable {
	
	private static final long serialVersionUID = -8053469542486898772L;
	
	private Level level;
	private Char player;
	private ArrayList<Char> enemies;
	private ArrayList<Bomb> bombs;
	
	public Game(Level level)
	{
		this.level = level;
		this.player = new Bomberman(Constants.MAP_X, Constants.MAP_Y);
		this.enemies = initializeEnemies();
		this.bombs = new ArrayList<Bomb>();
	}
	
	private ArrayList<Char> initializeEnemies()
	{
		// TODO code here
		
		return new ArrayList<Char>();
	}
	
	public Level getLevel() 
	{
		return level;
	}
	
	public Char getPlayer()
	{
		return player;
	}
	
	public void moveUp()
	{
		if (player.getY() % Constants.MAP_Y != 0)
		{
			player.moveUp();
			return;
		}
		
		int x = player.getMapX();
		int y = player.getMapY();
		
		if(y-1 < 0)
			return;
		
		if(player.getX() % Constants.MAP_X != 0)
		{
			if (level.getBlock(x, y-1).isEmpty() && level.getBlock(x+1, y-1).isEmpty())
				player.moveUp();
		}
		else
		{
			if (level.getBlock(x, y-1).isEmpty())
				player.moveUp();
		}
	}
	
	public void moveDown()
	{
		if (player.getY() % Constants.MAP_Y != 0)
		{
			player.moveDown();
			return;
		}
		
		int x = player.getMapX();
		int y = player.getMapY();
		
		if(y+1 >= Constants.HEIGHT)
			return;
		
		if(player.getX() % Constants.MAP_X != 0)
		{
			if (level.getBlock(x, y+1).isEmpty() && level.getBlock(x+1, y+1).isEmpty())
				player.moveDown();
		}
		else
		{
			if (level.getBlock(x, y+1).isEmpty())
				player.moveDown();
		}
	}
	
	public void moveLeft()
	{
		if (player.getX() % Constants.MAP_X != 0)
		{
			player.moveLeft();
			return;
		}
		
		int x = player.getMapX();
		int y = player.getMapY();
		
		if(x-1 < 0)
			return;
		
		if(player.getY() % Constants.MAP_Y != 0)
		{
			if (level.getBlock(x-1, y).isEmpty() && level.getBlock(x-1, y+1).isEmpty())
				player.moveLeft();
		}
		else
		{
			if (level.getBlock(x-1, y).isEmpty())
				player.moveLeft();
		}
	}
	
	public void moveRight()
	{
		if (player.getX() % Constants.MAP_X != 0)
		{
			player.moveRight();
			return;
		}
		
		int x = player.getMapX();
		int y = player.getMapY();
		
		if(x+1 >= Constants.WIDTH)
			return;
		
		if(player.getY() % Constants.MAP_Y != 0)
		{
			if (level.getBlock(x+1, y).isEmpty() && level.getBlock(x+1, y+1).isEmpty())
				player.moveRight();
		}
		else
		{
			if (level.getBlock(x+1, y).isEmpty())
				player.moveRight();
		}
	}
}
