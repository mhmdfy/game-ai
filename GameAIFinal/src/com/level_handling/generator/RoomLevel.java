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
		for(int i = 0; i < Constants.WIDTH; i+=5)
		{
			for(int j = 0; j < Constants.HEIGHT; j+=5)
			{
				
			}
		}
		return level;
		
	}
}
