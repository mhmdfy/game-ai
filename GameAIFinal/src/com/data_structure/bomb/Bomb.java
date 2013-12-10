package com.data_structure.bomb;

import java.io.Serializable;

import com.game.Constants;

public class Bomb implements Serializable {

	private static final long serialVersionUID = -906756254912683591L;

	private int x; // x coord of the bomb
	private int y; // y coord of the bomb
	private int timer; // timer to explode
	private int range; // range of explosion
	
	/**
	 * Constructor for bomb. Takes an x and a y coords,
	 * and creates a bomb at that location with Constants values.
	 */
	public Bomb(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.timer = Constants.BOMB_TIMER;
		this.range = Constants.BOMB_RANGE;
	}
	
	/**
	 * Gets the x coord of the bomb
	 * @return the x coord
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Gets the y coord of the bomb
	 * @return the y coord
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Gets the range of the bomb
	 * @return the range
	 */
	public int getRange()
	{
		return range;
	}
	
	/**
	 * Tick method for the bomb, reduces the timer by 1 
	 * until it reaches 0. Returns whether it should explode or not.
	 * @return True if timer reaches 0, false otherwise.
	 */
	public boolean tick()
	{
		if(timer > 0)
		{
			timer = timer - 1;
			return false;
		}
		else
			return true;
	}
}
