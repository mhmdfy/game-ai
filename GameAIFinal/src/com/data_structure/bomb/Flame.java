package com.data_structure.bomb;

import java.io.Serializable;

import com.game.Constants;

public class Flame implements Serializable{

	private static final long serialVersionUID = 5614731087276259081L;

	private int x; // x coord of the flame
	private int y; // y coord of the flame
	private int timer; // timer to disappear
	
	/**
	 * Constructor for flame. Takes an x and a y coords,
	 * and creates a flame at that location with Constants values.
	 */
	public Flame(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.timer = Constants.FLAME_TIMER;
	}
	
	/**
	 * Gets the x coord of the flame
	 * @return the x coord
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Gets the y coord of the flame
	 * @return the y coord
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Tick method for the flame, reduces the timer by 1 
	 * until it reaches 0. Returns whether it should disappear or not.
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
