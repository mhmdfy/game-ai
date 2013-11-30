package com.game;
import java.io.Serializable;
import java.util.ArrayList;

import com.data_structure.block.EmptyBlock;
import com.data_structure.bomb.Bomb;
import com.data_structure.bomb.Flame;
import com.data_structure.character.Bomberman;
import com.data_structure.character.Char;
import com.level_handling.Level;


public class Game implements Serializable {
	
	private static final long serialVersionUID = -8053469542486898772L;
	
	private Level level;
	private Char player;
	private ArrayList<Char> enemies;
	private ArrayList<Bomb> bombs;
	private ArrayList<Flame> flames;
	
	public Game(Level level)
	{
		this.level = level;
		this.player = new Bomberman(Constants.MAP_X, Constants.MAP_Y);
		this.enemies = initializeEnemies();
		this.bombs = new ArrayList<Bomb>();
		this.flames = new ArrayList<Flame>();
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
	
	public ArrayList<Bomb> getBombs()
	{
		return bombs;
	}
	
	public ArrayList<Flame> getFlames()
	{
		return flames;
	}
	
	public void tick()
	{
		player.tick();
		for(Char e : enemies)
		{
			e.tick();
		}
		
		for(int i = bombs.size()-1; i >= 0; i--)
		{
			if(bombs.get(i).tick())
				explode(i);
		}
		
		for(int i = flames.size()-1; i >= 0; i--)
		{
			if(flames.get(i).tick())
				flames.remove(i);
		}
	}
	
	private void explode(int i)
	{
		Bomb bomb = bombs.remove(i);
		
		for (int x = bomb.getX(); x < bomb.getX() + bomb.getRange(); x++)
		{
			if(level.getBlock(x, bomb.getY()).isBreakable())
			{
				level.setBlock(x, bomb.getY(), new EmptyBlock());
				flames.add(new Flame(x, bomb.getY()));
				break;
			}
			if(level.getBlock(x, bomb.getY()).isWall())
				break;
			
			flames.add(new Flame(x, bomb.getY()));
			
			if (player.getMapX() == x && player.getMapY() == bomb.getY())
			{
				// TODO: lose game
			}
		}
		
		for (int x = bomb.getX(); x > bomb.getX() - bomb.getRange(); x--)
		{
			if(level.getBlock(x, bomb.getY()).isBreakable())
			{
				level.setBlock(x, bomb.getY(), new EmptyBlock());
				flames.add(new Flame(x, bomb.getY()));
				break;
			}
			if(level.getBlock(x, bomb.getY()).isWall())
				break;
			
			flames.add(new Flame(x, bomb.getY()));
			
			if (player.getMapX() == x && player.getMapY() == bomb.getY())
			{
				// TODO: lose game
			}
		}
		
		for (int y = bomb.getY(); y < bomb.getY() + bomb.getRange(); y++)
		{
			if(level.getBlock(bomb.getX(), y).isBreakable())
			{
				level.setBlock(bomb.getX(), y, new EmptyBlock());
				flames.add(new Flame(bomb.getX(), y));
				break;
			}
			if(level.getBlock(bomb.getX(), y).isWall())
				break;
			
			flames.add(new Flame(bomb.getX(), y));
			
			if (player.getMapX() == bomb.getX() && player.getMapY() == y)
			{
				// TODO: lose game
			}
		}
		
		for (int y = bomb.getY(); y > bomb.getY() - bomb.getRange(); y--)
		{
			if(level.getBlock(bomb.getX(), y).isBreakable())
			{
				level.setBlock(bomb.getX(), y, new EmptyBlock());
				flames.add(new Flame(bomb.getX(), y));
				break;
			}
			if(level.getBlock(bomb.getX(), y).isWall())
				break;
			
			flames.add(new Flame(bomb.getX(), y));
			
			if (player.getMapX() == bomb.getX() && player.getMapY() == y)
			{
				// TODO: lose game
			}
		}
	}
	
	public void playerBomb()
	{
		if(player.canBomb())
		{
			player.addTimer(Constants.BOMB_TIMER);
			Bomb bomb = new Bomb(player.getMapX(), player.getMapY());
			bombs.add(bomb);
		}
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
