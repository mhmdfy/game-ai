package com.data_structure.block;
import java.io.Serializable;

public abstract class Block implements Serializable 
{
	private static final long serialVersionUID = 8173885238036082385L;

	// flag value of the block, used for floodfill algorithm.
	private boolean flag = false;

	/**
	 * Set the value of the flag of the block to either true or false.
	 */
	public void setFlag(boolean value)
	{
		flag = value;
	}
	
	/**
	 * Checked whether the block is flagged or not.
	 * @return True if flagged, False otherwise
	 */
	public boolean isFlaged()
	{
		return flag;
	}
	
	/**
	 * Check whether the block is a wall
	 * @return True if block is a wall, false otherwise
	 */
	public boolean isWall() 
	{
		return false;
	}
	
	/**
	 * Check whether the block is a breakable
	 * @return True if block is a breakable, false otherwise
	 */
	public boolean isBreakable() 
	{
		return false;
	}
	
	/**
	 * Check whether the block is empty
	 * @return True if block is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return false;
	}
	
	/**
	 * Prints a text version of the block.
	 * Wall: X
	 * Breakable: #
	 * Empty: .
	 * @return The string value of the block.
	 */
	public abstract String print();
	
}
