package com.data_structure.character;

import com.game.Constants;

public class Bomberman extends Char 
{
	private static final long serialVersionUID = -8640148391806602267L;
	
	private int lifes; // how many lifes the bomberman has
	private int timer; // the timer for bomberman to place a bomb
	
	/**
	 * Constructor for bomberman. Takes an x and a y coords,
	 * and creates a bomberman at that location with Constants values.
	 */
	public Bomberman(int x, int y)
	{
		super(x, y);
		lifes = Constants.LIFES;
		timer = 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void tick()
	{
		timer = timer - 1;
	}
	
	/**
	 * Checks whether bomberman can place a bomb 
	 * @return True if can bomb, False otherwise
	 */
	public boolean canBomb()
	{
		if (timer > 0)
			return false;
		else
			return true;
	}
	
	/**
	 * Sets the timer for bomberman to the given time
	 */
	public void setTime(int t)
	{
		timer = t;
	}
	
	/**
	 * Gets the number of lifes left on bomberman
	 * @return the number of lifes.
	 */
	public int getLifes()
	{
		return lifes;
	}
	
	/**
	 * Bomberman dies; loses a life and respawn at respawn spot.
	 */
	public void die()
	{
		if (lifes > 0)
		{
			lifes = lifes - 1;
			setCoords(Constants.MAP_X, Constants.MAP_Y);
		}
	}

}
