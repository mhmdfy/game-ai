package com.level_handling.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.game.Constants;
import com.level_handling.Level;

public class Generator {

	private static int width = Constants.WIDTH;
	private static int height = Constants.HEIGHT;
	
	
	
	public static Level generate(String fileName)
	{
		Level level = new Level();
		
		level = BasicLevel.create();
		
		saveToFile(fileName, level);
		return level;
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
