package com.level_handling.generator;

import java.util.Random;

import com.data_structure.block.BreakableBlock;
import com.data_structure.block.EmptyBlock;
import com.data_structure.block.WallBlock;
import com.game.Constants;
import com.level_handling.Level;
import com.level_handling.generator.Generator;

public class RandomLevel 
{
	/**
	 * Creates totally random level on a space by space approach.
	 * After each block is placed, level is checked to be valid.
	 * If not, empty space will be placed.
	 * @return Random level consisting of walls, breakables, and empty spaces
	 */
		public static Level create()
		{
			Level level = new Level();
			Random random = new Random();
			for(int i = 1; i < Constants.WIDTH-1; i++)
			{
				for(int j = 1; j < Constants.HEIGHT-1; j++)
				{
					int type = random.nextInt(4);

					if(type == 0)
					{
						level.setBlock(i, j, new WallBlock());
					}
					else if(type == 1)
					{
						level.setBlock(i, j, new BreakableBlock());
					}
					else
					{
						level.setBlock(i, j, new EmptyBlock());
					}
					
					if(!Generator.isValid(level))
					{
						level.setBlock(i, j, new EmptyBlock());
					}					
				}
			}
			return level;
		}
}
