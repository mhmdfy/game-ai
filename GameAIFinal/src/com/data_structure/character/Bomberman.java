package com.data_structure.character;

import com.game.Constants;

public class Bomberman extends Char {

	private static final long serialVersionUID = -8640148391806602267L;
	
	private int lifes;
	public Bomberman(int x, int y)
	{
		super(x, y);
		lifes = Constants.LIFES;
	}
	
	public int getLifes()
	{
		return lifes;
	}
	
	public void die()
	{
		if (lifes > 0)
			lifes = lifes - 1;
		
	}

}
