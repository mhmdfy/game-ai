package com.level_handling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import com.data_structure.block.BreakableBlock;
import com.data_structure.block.EmptyBlock;
import com.data_structure.block.WallBlock;
import com.game.Constants;
import com.data_structure.block.Block;

public class Generator {

	private static int width = Constants.WIDTH;
	private static int height = Constants.HEIGHT;
	
	
	
	public static Level generate(String fileName)
	{
		Level level = new Level();
		Random random = new Random();
		int chance = random.nextInt(3); // 0, 1, 2
		
		//if (chance == 0)
			level = basicLevel();
		
		saveToFile(fileName, level);
		return level;
	}
	
	private static Level basicLevel()
	{
		Level level = new Level();
		Random random = new Random();
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				int type = random.nextInt(4);
				
				if(i == 0 || j == 0 || i == width - 1 || j == width - 1)
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
	
	public void verify(int x, int y, Level level)
	{
		Block currentBlock = level.getBlock(x, y);
		if(!currentBlock.isEmpty())
		{
			return;
		}
		currentBlock.setFlag(true);
		verify(x - 1, y, level);
		verify(x + 1, y, level);
		verify(x, y - 1, level);
		verify(x, y + 1, level);
		return;
	}
	
	public boolean valid(Level level)
	{
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				if(level.getBlock(x, y).isEmpty() && !level.getBlock(x, y).isFlaged())
				{
					return false;
				}
			}
		}
		return true;
	}
	
	private static void saveToFile(String name, Level level)
	{
		String path = Constants.LVL_PATH + name + Constants.LVL_EXT;
		try {
			File file = new File(path);
			
			if(!file.exists())
				file.createNewFile();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(path));
			
			for(int i = 0; i < width; i++)
			{
				for(int j = 0; j < height; j++)
				{
					out.write(level.getBlock(i, j).print());
				}
				
				out.write("\n");
			}
			
			out.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
