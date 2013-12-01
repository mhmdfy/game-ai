package com.data_structure.character;

import java.io.Serializable;

import com.game.Constants;

public abstract class Char implements Serializable {

	private static final long serialVersionUID = 8526421839690242306L;
	
	private int x;
	private int y;
	
	public Char(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getMapX()
	{
		double rem = x % Constants.MAP_X;
		if(rem > Constants.MAP_X/2)
			return (int)Math.ceil(x/(double)Constants.MAP_X);
		
		return x / Constants.MAP_X;
	}
	
	public int getMapY()
	{
		double rem = y % Constants.MAP_Y;
		if(rem > Constants.MAP_Y/2)
			return (int)Math.ceil(y/(double)Constants.MAP_Y);
		
		return y / Constants.MAP_Y;
	}
	
	public abstract void tick();
	
	public void setCoords(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void moveUp()
	{
		this.y = y - Constants.STEP;
	}
	
	public void moveDown()
	{
		this.y = y + Constants.STEP;
	}
	
	public void moveLeft()
	{
		this.x = x - Constants.STEP;
	}
	
	public void moveRight()
	{
		this.x = x + Constants.STEP;
	}

}
