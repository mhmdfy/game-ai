package com.data_structure.character;

import java.io.Serializable;

import com.game.Constants;

public abstract class Char implements Serializable {

	private static final long serialVersionUID = 8526421839690242306L;
	
	private int x; // x coord of the Char
	private int y; // y coord of the Char
	
	/**
	 * Constructor for character. Takes an x and a y coords,
	 * and creates a character at that location.
	 */
	public Char(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x coords of the character in display scale
	 * @return the x coords
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * Gets the y coords of the character in display scale
	 * @return the y coords
	 */
	public int getY()
	{
		return y;
	}
	
	/**
	 * Gets the x coords of the character in map scale, as in 
	 * what block the character is mostly at.
	 * @return the x coords of the block the character is at.
	 */
	public int getMapX()
	{
		double rem = x % Constants.MAP_X;
		if(rem > Constants.MAP_X/2)
			return (int)Math.ceil(x/(double)Constants.MAP_X);
		
		return x / Constants.MAP_X;
	}
	
	/**
	 * Gets the y coords of the character in map scale, as in 
	 * what block the character is mostly at.
	 * @return the y coords of the block the character is at.
	 */
	public int getMapY()
	{
		double rem = y % Constants.MAP_Y;
		if(rem > Constants.MAP_Y/2)
			return (int)Math.ceil(y/(double)Constants.MAP_Y);
		
		return y / Constants.MAP_Y;
	}
	
	/**
	 * Tick method for the character.
	 * The character could move, place bomb, or
	 * progress the time.
	 */
	public abstract void tick();

	/**
	 * Set the coords of the character to new given x and y.
	 */
	public void setCoords(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Moves the character upward
	 */
	public void moveUp()
	{
		this.y = y - Constants.STEP;
	}
	
	/**
	 * Moves the character down
	 */
	public void moveDown()
	{
		this.y = y + Constants.STEP;
	}
	
	/**
	 * Moves the character to the left
	 */
	public void moveLeft()
	{
		this.x = x - Constants.STEP;
	}
	
	/**
	 * Moves the character to the right
	 */
	public void moveRight()
	{
		this.x = x + Constants.STEP;
	}

}
