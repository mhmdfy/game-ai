package com.level_handling.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.game.Constants;
import com.level_handling.Level;

public class Generator
{
	private static int width = Constants.WIDTH;
	private static int height = Constants.HEIGHT;	
	
	public static Level generate(String fileName)
	{
		Level level = new Level();
		
		level = BasicLevel.create();
		
		saveToFile(fileName, level);
		return level;
	}
	
	public static boolean isValid(Level level)
	{
		if(!(level.getBlock(1, 1).isEmpty() && level.getBlock(1, 2).isEmpty()
			&& level.getBlock(2, 1).isEmpty()))
			return false;
		
		floodFill(1, 1, level);
		
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				if(!level.getBlock(x, y).isWall() && !level.getBlock(x, y).isFlaged())
					return false;
			}
		}
		return true;
	}
	
	private static void floodFill(int x, int y, Level level)
	{
		if(invalidXY(x, y))
		{
			return;
		}
		if(!level.getBlock(x, y).isFlaged() && !level.getBlock(x, y).isWall())
		{
			level.getBlock(x, y).setFlag(true);
			floodFill(x - 1, y, level);
			floodFill(x + 1, y, level);
			floodFill(x, y - 1, level);
			floodFill(x, y + 1, level);
		}
		else
		{
			return;
		}
	}
	
	private static boolean invalidXY(int x, int y)
	{
		if ( x < 0 || x >= Constants.WIDTH)
			return true;
		
		if ( y < 0 || y >= Constants.HEIGHT)
			return true;
		
		return false;
	}
	
	private static void saveToFile(String name, Level level)
	{
		String path = Constants.LVL_PATH + name + Constants.LVL_EXT;
		try 
		{
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
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
