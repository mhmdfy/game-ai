package com.data_structure.character;

import java.util.Random;

import com.game.Constants;

public class AI extends Char 
{
	private static final long serialVersionUID = -5636317255908578346L;

	private int nextMove; // Next move the AI should take.
	private int moveTimer; // The move timer for the AI to change direction.
	private int stepTimer; // the step timer for the AI.
	
	/**
	 * Constructor for AI enemy. Takes an x and a y coords,
	 * and creates an AI at that location with Constants values.
	 */
	public AI(int x, int y) 
	{
		super(x, y);
		moveTimer = 0;
		nextMove = 0;
		stepTimer = 0;
	}

	/**
	 * {@inheritDoc}
	 */
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
	
	/**
	 * Gets the next move of the AI.
	 * Up:    0
	 * Down:  1
	 * Left:  2
	 * Right: 3
	 * @return The next move.
	 */
	public int getNextMove()
	{
		return nextMove;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveUp()
	{
		if (stepTimer == 0)
			super.moveUp();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveDown()
	{
		if (stepTimer == 0)
			super.moveDown();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveLeft()
	{
		if (stepTimer == 0)
			super.moveLeft();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveRight()
	{
		if (stepTimer == 0)
			super.moveRight();
	}

}
