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
				while(!isValid(level))
				{
					createRoom(i, j, level);
				}
			}
		}
		return level;
	}
}
