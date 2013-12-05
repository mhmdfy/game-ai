package com.level_handling.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.data_structure.block.Block;
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
		
		int[] startCoords = {1, 1};
		ArrayList<int[]> list = new ArrayList<int[]>();
		list.add(startCoords);
		queueFloodFill(list, new ArrayList<int[]>(), level);
		
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
	
	private static void queueFloodFill(ArrayList<int[]> queue, ArrayList<int[]> checked, Level level)
	{
		ArrayList<int[]> newQueue = new ArrayList<int[]>();
		
		for(int[] coords : queue)
		{
			int x = coords[0];
			int y = coords[1];
			
			checked.add(new int[]{x, y});
			
			if(level.getBlock(x, y).isWall())
			{
				level.getBlock(x, y).setFlag(false);
			}
			else
			{
				level.getBlock(x, y).setFlag(true);
			
				if (x > 0 && !checked.contains(new int[]{x - 1, y}))
					newQueue.add(new int[]{x - 1, y});
				if (x <= Constants.WIDTH && !checked.contains(new int[]{x + 1, y}))
					newQueue.add(new int[]{x + 1, y});
				if (y > 0 && !checked.contains(new int[]{x, y - 1}))
					newQueue.add(new int[]{x, y - 1});
				if (y <= Constants.HEIGHT && !checked.contains(new int[]{x, y + 1}))
					newQueue.add(new int[]{x, y + 1});
			}
		}
		
//		System.out.print("The array: ");
//		for (int[] coords : newQueue)
//			System.out.print("X= "+ coords[0] + " Y="+ coords[1] + "\t");
//		System.out.println();
		
		queueFloodFill(newQueue, checked, level);
	}
	
	private static void floodFill(int x, int y, Level level)
	{
		if (invalidXY(x, y))
			return;
		
		if(level.getBlock(x, y).isWall())
		{
			level.getBlock(x, y).setFlag(false);
		}
		else
		{
			level.getBlock(x, y).setFlag(true);
			
			floodFill(x - 1, y, level);
			floodFill(x + 1, y, level);
			floodFill(x, y - 1, level);
			floodFill(x, y + 1, level);
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
