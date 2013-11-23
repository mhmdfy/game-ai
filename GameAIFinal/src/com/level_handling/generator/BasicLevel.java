package com.level_handling.generator;

import java.util.Random;

import com.data_structure.block.BreakableBlock;
import com.data_structure.block.EmptyBlock;
import com.data_structure.block.WallBlock;
import com.game.Constants;
import com.level_handling.Level;

public class BasicLevel 
{
	public static Level create()
	{
		Level level = new Level();
		Random random = new Random();
		for(int i = 0; i < Constants.WIDTH; i++)
		{
			for(int j = 0; j < Constants.HEIGHT; j++)
			{
				int type = random.nextInt(4);
				
				if(i == 0 || j == 0 || i == Constants.WIDTH - 1 || j == Constants.HEIGHT - 1)
				{
					level.setBlock(i, j, new WallBlock());
				}
				else if(i % 2 == 0 && j % 2 == 0)
				{
					level.setBlock(i, j, new WallBlock());
				}
				else if(type == 0)
				{
					level.setBlock(i, j, new BreakableBlock());	
				}
				else 
				{
					level.setBlock(i, j, new EmptyBlock());
				}
			}
		}
		
		return level;
	}
}
