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
		for(int i = 0; i < Constants.WIDTH; i+=4)
		{
			for(int j = 0; j < Constants.HEIGHT; j+=4)
			{
				level.setBlock(i, j, new WallBlock());
				for(int k = i+1; k < i+4; k++)
				{
					for(int l = j+1; l < j+4; l++)
					{
						int type = random.nextInt(2);
						if(type == 1)
						{
							level.setBlock(k, l, new EmptyBlock());
						}
					}
				}
			}
		}
		return level;
		
	}
}
