package com.game;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import com.data_structure.block.EmptyBlock;
import com.data_structure.bomb.Bomb;
import com.data_structure.bomb.Flame;
import com.data_structure.character.AI;
import com.data_structure.character.Bomberman;
import com.data_structure.character.Char;
import com.level_handling.Level;


public class Game implements Serializable {
	
	private static final long serialVersionUID = -8053469542486898772L;
	
	private String state;
	
	private Level level;
	private Bomberman player;
	private ArrayList<AI> enemies;
	private ArrayList<Bomb> bombs;
	private ArrayList<Flame> flames;
	
	public Game(Level level)
	{
		this.level = level;
		this.player = new Bomberman(Constants.MAP_X, Constants.MAP_Y);
		this.enemies = initializeEnemies();
		this.bombs = new ArrayList<Bomb>();
		this.flames = new ArrayList<Flame>();
		
		this.state = Constants.GAME_ACTIVE;
	}
	
	private ArrayList<AI> initializeEnemies()
	{
		ArrayList<AI> list = new ArrayList<AI>();
		for(int i = 0; i < Constants.ENEMY_NUM; i++)
		{
			Random random = new Random();
			int x = 0;
			int y = 0;
			
			do
			{
				x = random.nextInt(Constants.WIDTH);
				y = random.nextInt(Constants.HEIGHT-4) + 4;
			}while(!level.getBlock(x, y).isEmpty());
			
			list.add(new AI(x*Constants.MAP_X, y*Constants.MAP_Y));
		}
		
		return list;
	}
	
	public String getState()
	{
		return state;
	}
	
	public Level getLevel() 
	{
		return level;
	}
	
	public Bomberman getPlayer()
	{
		return player;
	}
	
	public ArrayList<AI> getEnemies()
	{
		return enemies;
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
		if(player.getLifes() == 0)
			state = Constants.GAME_LOSE;
		if(enemies.size() == 0)
			state = Constants.GAME_WIN;
		
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
			int x = flames.get(i).getX();
			int y = flames.get(i).getY();
			
			if (player.getMapX() == x && player.getMapY() == y)
				player.die();
			
			for(int j = enemies.size()-1; j >= 0; j--)
			{
				if(enemies.get(j).getMapX() == x && enemies.get(j).getMapY() == y)
					enemies.remove(j);	
			}
			
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
