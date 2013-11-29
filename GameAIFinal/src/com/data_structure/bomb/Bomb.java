package com.data_structure.bomb;

import java.io.Serializable;

import com.game.Constants;

public class Bomb implements Serializable {

	private static final long serialVersionUID = -906756254912683591L;

	private int x;
	private int y;
	private int timer;
	private int range;
	
	public Bomb(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.timer = Constants.TIMER;
		this.range = Constants.RANGE;
	}
	
	public Bomb(int x, int y, int timer)
	{
		this.x = x;
		this.y = y;
		this.timer = timer;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getRange()
	{
		return range;
	}
	
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
