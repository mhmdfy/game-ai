package com.data_structure.character;

import com.game.Constants;

public class Bomberman extends Char {

	private static final long serialVersionUID = -8640148391806602267L;
	
	private int lifes;
	private int timer;
	
	public Bomberman(int x, int y)
	{
		super(x, y);
		lifes = Constants.LIFES;
		timer = 0;
	}
	
	@Override
	public void tick()
	{
		timer = timer - 1;
	}
	
	public boolean canBomb()
	{
		if (timer > 0)
			return false;
		else
			return true;
	}
	
	public void addTimer(int t)
	{
		timer = t;
	}
	
	public int getLifes()
	{
		return lifes;
	}
	
	public void die()
	{
		if (lifes > 0)
		{
			lifes = lifes - 1;
			setCoords(Constants.MAP_X, Constants.MAP_Y);
		}
		
	}

}
