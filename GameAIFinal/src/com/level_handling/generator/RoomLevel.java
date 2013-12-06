package com.level_handling.generator;

import java.util.Random;

import com.data_structure.block.BreakableBlock;
import com.data_structure.block.EmptyBlock;
import com.data_structure.block.WallBlock;
import com.game.Constants;
import com.level_handling.Level;
import com.level_handling.generator.Generator;

public class RoomLevel 
{
	public static Level create()
	{
		Random random = new Random();
		Level level = new Level();
		for(int i = 0; i < Constants.WIDTH - 1; i+=5)
		{
			for(int j = 0; j < Constants.HEIGHT -1; j+=5)
			{
				createRoom(i, j, level);
				while(!Generator.isValid(level))
				{
					createRoom(i, j, level);
				}
			}
		}
		addBreakables(level);
		return level;
	}
	
	private static void addBreakables(Level level)
	{
		Random random = new Random();
		for(int x = 0; x < Constants.WIDTH; x++)
		{
			for(int y = 0; y < Constants.HEIGHT; y++)
			{
				int type = random.nextInt(3);
				if(level.getBlock(x, y).isEmpty())
				{
					if(type == 2)
					{
						level.setBlock(x, y, new BreakableBlock());
					}
					//Make sure start area is empty
					level.setBlock(1, 1, new EmptyBlock());
					level.setBlock(1, 2, new EmptyBlock());
					level.setBlock(2, 1, new EmptyBlock());
				}
			}
		}
	}
}
