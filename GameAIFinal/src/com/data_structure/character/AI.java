package com.data_structure.character;

import java.util.Random;

import com.game.Constants;

public class AI extends Char 
{
	private static final long serialVersionUID = -5636317255908578346L;

	private int nextMove;
	private int moveTimer;
	private int stepTimer;
	
	public AI(int x, int y) 
	{
		super(x, y);
		moveTimer = 0;
		nextMove = 0;
		stepTimer = 0;
	}

	@Override
	public void tick() 
	{
		if(stepTimer > 0)
		{
			stepTimer = stepTimer - 1;
		}
		else
		{
			stepTimer = Constants.STEP_TIMER;
		}
		
		if(moveTimer > 0)
		{
			moveTimer = moveTimer - 1;
		}
		else
		{
			Random random = new Random();
			nextMove = random.nextInt(4);
			moveTimer = Constants.MOVE_TIMER;
		}
	}
	
	public int getNextMove()
	{
		return nextMove;
	}
	
	@Override
	public void moveUp()
	{
		if (stepTimer == 0)
			super.moveUp();
	}
	
	@Override
	public void moveDown()
	{
		if (stepTimer == 0)
			super.moveDown();
	}
	
	@Override
	public void moveLeft()
	{
		if (stepTimer == 0)
			super.moveLeft();
	}
	
	@Override
	public void moveRight()
	{
		if (stepTimer == 0)
			super.moveRight();
	}

}
