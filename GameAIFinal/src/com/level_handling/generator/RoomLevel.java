package com.level_handling.generator;

import java.util.Random;

import com.data_structure.block.WallBlock;
import com.game.Constants;
import com.level_handling.Level;

public class RoomLevel 
{
	private static int roomlength = 5;
	
	public static Level create()
	{
		Level level = new Level();
		for(int i = 0; i < Constants.WIDTH; i+=5)
		{
			for(int j = 0; j < Constants.HEIGHT; j+=5)
			{
				
			}
		}
		return level;
	}
	
	private static Level createRoom(int startX, int startY, Level level)
	{
		Level newLevel = level;
		Random random = new Random();
		
		int maxDoors = 2;
		int doorCount = 0;
		for (int x = startX; x < startX + roomlength; x++)
		{
			for (int y = startY; y < startY + roomlength; y++)
			{
				if (!level.getBlock(x, y).isWall())
				{
					int putDoor = random.nextInt(9);
					if (putDoor != 0 || doorCount == maxDoors)
						newLevel.setBlock(x, y, new WallBlock());
					else
						doorCount = doorCount + 1;
						
				}
			}
		}
		if (Generator.isValid(newLevel))
			return newLevel;
		else
			return createRoom(startX, startY, level);
	}
}
