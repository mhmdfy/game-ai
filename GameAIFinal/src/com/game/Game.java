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


public class Game implements Serializable 
{	
	private static final long serialVersionUID = -8053469542486898772L;
	
	private String state; // The state of the game
	
	private Level level; // The level being used
	private Bomberman player; // The player object
	private ArrayList<AI> enemies; // The enemies
	private ArrayList<Bomb> bombs; // The bombs
	private ArrayList<Flame> flames; // The flames
	
	/**
	 * Constructor for the game. Takes in a level and creates a game
	 * with the player on the spawning location and enemies generated.
	 */
	public Game(Level level)
	{
		this.level = level;
		this.player = new Bomberman(Constants.MAP_X, Constants.MAP_Y);
		this.enemies = initializeEnemies();
		this.bombs = new ArrayList<Bomb>();
		this.flames = new ArrayList<Flame>();
		
		this.state = Constants.GAME_ACTIVE;
	}
	
	/**
	 * Generate enemies and place them on random empty locations
	 * on the map.
	 * @return
	 */
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
			}
			while(!level.getBlock(x, y).isEmpty());
			
			list.add(new AI(x*Constants.MAP_X, y*Constants.MAP_Y));
		}
		
		return list;
	}
	
	/**
	 * Gets the state of the game.
	 * Active, Win, Lose
	 * @return The state of the game
	 */
	public String getState()
	{
		return state;
	}
	
	/**
	 * Gets the level of the game.
	 * @return The level of the game
	 */
	public Level getLevel() 
	{
		return level;
	}
	
	/**
	 * Gets the player of the game.
	 * @return The player of the game
	 */
	public Bomberman getPlayer()
	{
		return player;
	}
	
	/**
	 * Gets the list of enemies
	 * @return The list of enemies
	 */
	public ArrayList<AI> getEnemies()
	{
		return enemies;
	}
	
	/**
	 * Gets the list of bombs
	 * @return The list of bombs
	 */
	public ArrayList<Bomb> getBombs()
	{
		return bombs;
	}
	
	/**
	 * Gets the list of flames
	 * @return The list of flames
	 */
	public ArrayList<Flame> getFlames()
	{
		return flames;
	}
	
	/**
	 * Tick function of the game:
	 * Check for win and lose condition, and run the
	 * tick functions of all objects in the game.
	 * 
	 * Win condition: all enemies are dead.
	 * Lose condition: player has no lifes left
	 */
	public void tick()
	{
		if(player.getLifes() == 0)
			state = Constants.GAME_LOSE;
		if(enemies.size() == 0)
			state = Constants.GAME_WIN;
		
		player.tick();
		for(int i = 0; i < enemies.size(); i++)
		{
			if(enemies.get(i).getMapX() == player.getMapX() 
				&& enemies.get(i).getMapY() == player.getMapY())
				player.die();
				
			enemies.get(i).tick();
			moveAI(i);
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
	
	/**
	 * Move the player in the direction of the key
	 */
	public void move(String key)
	{
		if (key == "up" && canMoveUp(player))
			player.moveUp();
		
		else if (key == "down" && canMoveDown(player)) 
			player.moveDown();
		
		else if (key == "right" && canMoveRight(player)) 
			player.moveRight();
		
		else if (key == "left" && canMoveLeft(player))
			player.moveLeft();
	}
	
	/**
	 * Create a bomb on the player's map location if the player 
	 * is able to bomb at this time.
	 */
	public void playerBomb()
	{
		if(player.canBomb())
		{
			player.setTime(Constants.BOMB_TIMER);
			Bomb bomb = new Bomb(player.getMapX(), player.getMapY());
			bombs.add(bomb);
		}
	}
	
	/**
	 * Move the AI in the direction of the next move.
	 */
	private void moveAI(int i)
	{
		AI ai = enemies.get(i);		
		
		if(ai.getNextMove() == 0 && canMoveUp(ai))
			ai.moveUp();
		if(ai.getNextMove() == 1 && canMoveDown(ai))
			ai.moveDown();
		if(ai.getNextMove() == 2 && canMoveLeft(ai))
			ai.moveLeft();
		if(ai.getNextMove() == 3 && canMoveRight(ai))
			ai.moveRight();
	}
	
	// TODO Refactor (maybe)
	/**
	 * The bomb explodes, creating flames on areas effected.
	 * The effected blocks are the areas adjecent to the bomb in all 
	 * directions and it reaches to the range of the bomb or when it 
	 * reaches a wall. If breakables are in the affected blocks, destroy
	 * it and stop the spread.
	 */
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
	
	// TODO Refacter to canMove(Char, String)
	/**
	 * Following 4 methods move the given character to the direction
	 * that the method indicates if that block is free.
	 * @return True if was able to move, False otherwise.
	 */
	private boolean canMoveUp(Char c)
	{
		if (c.getY() % Constants.MAP_Y != 0)
			return true;
		
		int x = c.getMapX();
		int y = c.getMapY();
		
		if(y-1 < 0)
			return false;
		
		if(c.getX() % Constants.MAP_X != 0)
		{
			if (freeBlock(x, y-1) && freeBlock(x+1, y-1))
				return true;
		}
		else
		{
			if (freeBlock(x, y-1))
				return true;
		}
		
		return false;
	}
	
	public boolean canMoveDown(Char c)
	{
		if (c.getY() % Constants.MAP_Y != 0)
			return true;
		
		int x = c.getMapX();
		int y = c.getMapY();
		
		if(y+1 >= Constants.HEIGHT)
			return false;;
		
		if(c.getX() % Constants.MAP_X != 0)
		{
			if (freeBlock(x, y+1) && freeBlock(x+1, y+1))
				return true;
		}
		else
		{
			if (freeBlock(x, y+1))
				return true;
		}
		
		return false;
	}
	
	public boolean canMoveLeft(Char c)
	{
		if (c.getX() % Constants.MAP_X != 0)
			return true;
		
		int x = c.getMapX();
		int y = c.getMapY();
		
		if(x-1 < 0)
			return false;
		
		if(c.getY() % Constants.MAP_Y != 0)
		{
			if (freeBlock(x-1, y) && freeBlock(x-1, y+1))
				return true;
		}
		else
		{
			if (freeBlock(x-1, y))
				return true;
		}
		
		return false;
	}
	
	public boolean canMoveRight(Char c)
	{
		if (c.getX() % Constants.MAP_X != 0)
			return true;
		
		int x = c.getMapX();
		int y = c.getMapY();
		
		if(x+1 >= Constants.WIDTH)
			return false;
		
		if(c.getY() % Constants.MAP_Y != 0)
		{
			if (freeBlock(x+1, y) && freeBlock(x+1, y+1))
				return true;
		}
		else
		{
			if (freeBlock(x+1, y))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if the block located on the given x and y is free,
	 * as in empty and has no bombs.
	 * @return True if the block is free, False otherwise
	 */
	private boolean freeBlock(int x, int y)
	{
		for(Bomb b: bombs)
		{
			if(b.getX() == x && b.getY() == y)
				return false;
		}
		
		return level.getBlock(x, y).isEmpty();
		
	}
}
