package com.data_structure.character;

import java.util.Random;

import com.game.Constants;

public class AI extends Char 
{
	private static final long serialVersionUID = -5636317255908578346L;

	private int nextMove;
	private int timer;
	
	public AI(int x, int y) 
	{
		super(x, y);
		timer = 0;
		nextMove = 0;
	}

	@Override
	public void tick() 
	{
		if(timer > 0)
		{
			timer = timer - 1;
		}
		else
		{
			Random random = new Random();
			nextMove = random.nextInt(3);
			timer = Constants.AI_TIMER;
		}
	}
	
	public int getNextMove()
	{
		return nextMove;
	}

}
