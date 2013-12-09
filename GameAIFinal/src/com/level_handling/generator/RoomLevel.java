package com.level_handling.generator;

import java.util.Random;

import com.data_structure.block.BreakableBlock;
import com.data_structure.block.EmptyBlock;
import com.data_structure.block.WallBlock;
import com.game.Constants;
import com.level_handling.Level;

/**
 *
 */
public class RoomLevel 
{
	private static int roomLength = 5;
	
	public static Level create()
	{
		Level level = new Level();
		for(int i = 0; i < Constants.WIDTH - 1; i+=roomLength)
		{
			for(int j = 0; j < Constants.HEIGHT -1; j+=roomLength)
			{
				level = createRoom(i, j, level);
			}
		}
		addBreakables(level);
		return level;
	}
	
	private static Level createRoom(int startX, int startY, Level level)
	{
		Level newLevel = new Level(level);
		Random random = new Random();
		
		int maxDoors = 2;
		int doorCount = 0;
		
		for (int x = startX; x <= startX + roomLength; x++)
		{
			for (int y = startY; y <= startY + roomLength; y++)
			{
				if (x == startX || x == startX + roomLength
					|| y == startY || y == startY + roomLength)
				{
					if (!level.getBlock(x, y).isWall())
					{
						int putDoor = random.nextInt(4);
						if (putDoor == 0 && doorCount <= maxDoors)
							doorCount = doorCount + 1;
						else
							newLevel.setBlock(x, y, new WallBlock());
							
							
					}
				}
			}
		}
		
		if (Generator.isValid(newLevel))
			return newLevel;
		else
			return createRoom(startX, startY, level);
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
					if((x % roomLength == 0 || y % roomLength == 0) && type != 0)
					{
						level.setBlock(x, y, new BreakableBlock());
					}
					else if(type == 2)
					{
						level.setBlock(x, y, new BreakableBlock());
					}
				}
			}
		}
		level.setBlock(1, 1, new EmptyBlock());
		level.setBlock(1, 2, new EmptyBlock());
		level.setBlock(2, 1, new EmptyBlock());
	}
}
