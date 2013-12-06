package com.level_handling;

import java.io.Serializable;

import com.data_structure.block.Block;
import com.data_structure.block.BreakableBlock;
import com.data_structure.block.EmptyBlock;
import com.data_structure.block.WallBlock;
import com.game.Constants;


public class Level implements Serializable 
{	
	private static final long serialVersionUID = 1813773854725251446L;
	
	private int width = Constants. WIDTH;
	private int height = Constants.HEIGHT;
	
	private Block[][] level;
	
	public Level() 
	{
		level = new Block[width][height];
		initLevel();
	}
	
	public Level(Level other)
	{
		level = createCopy(other);
	}
	
	public Block getBlock(int x, int y)
    {
		validateXY(x, y);
		
		return level[x][y];
    }
	
	public Block getBlockCapped(int x, int y)
    {
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x >= width) x = width - 1;
        if (y >= height) y = height - 1;
        return level[x][y];
    }
	
	public void setBlock(int x, int y, Block b)
	{
        validateXY(x, y);
        
        level[x][y] = b;
    }

	public void printLevel()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				System.out.print(level[i][j].print());
			}
			
			System.out.println();
		}
	}
	
	public void setRow(Block[] row, int y)
	{
		validateY(y);
		
		for (int x = 0; x < width ; x++)
		{
			level[x][y] = row[x];
		}
	}
	
	public void resetFlag()
	{
		for(Block[] list : level)
			for(Block b : list)
			{
				b.setFlag(false);
			}
	}
	
	private Block[][] createCopy(Level other)
	{
		Block[][] lvl = new Block[width][height];
		
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				if(other.getBlock(i, j).isWall())
					lvl[i][j] = new WallBlock();
				else if (other.getBlock(i, j).isBreakable())
					lvl[i][j] = new BreakableBlock();
				else
					lvl[i][j] = new EmptyBlock();
			}
		}
		
		return lvl;
	}
	
	private void initLevel()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				if(i == 0 || j == 0 || i == Constants.WIDTH - 1 || j == Constants.HEIGHT - 1)
					level[i][j] = new WallBlock();
				else
					level[i][j] = new EmptyBlock();
			}
		}
	}
	
	private void validateXY(int x, int y)
	{
        validateX(x);
        validateY(y);
	}
	
	private void validateX(int x) 
	{
		if (x < 0 || x >= width)
        	throw new IllegalArgumentException("X has to be between 0-" + (width-1) + " Give: " + x);
	}
	
	private void validateY(int y)
	{
		if (y < 0 || y >= height) 
        	throw new IllegalArgumentException("Y has to be between 0-" + (height-1) + " Give: " + y);
	}
}
