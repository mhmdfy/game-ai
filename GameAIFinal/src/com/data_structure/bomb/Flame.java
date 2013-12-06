package com.data_structure.bomb;

import java.io.Serializable;

import com.game.Constants;

public class Flame implements Serializable{

	private static final long serialVersionUID = 5614731087276259081L;

	private int x;
	private int y;
	private int timer;
	
	public Flame(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.timer = Constants.FLAME_TIMER;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
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
