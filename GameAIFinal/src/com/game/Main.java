package com.game;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;

import processing.core.PApplet;

import com.level_handling.Level;
import com.level_handling.Parser;
import com.level_handling.generator.Generator;


public class Main 
{
	public static void main(String args[]) 
	{
		validateArgs(args);
		
		Level level = new Level();
		
		if(!args[0].equals("random") && fileExists(args[0]))
			level = Parser.parse(args[0]);
		else
			level = Generator.generate(args[0]);
		level.printLevel();
		
		Game game = new Game(level);
		
		PApplet.main(new String[] { "--present", "com.game.Display", serializeGame(game)});
	}
	
	private static void validateArgs(String args[]) 
	{
		if (args.length != 1)
		{
			System.err.println("ERROR: Wrong number of arguements");
			System.exit(1);
		}
	}
	
	private static boolean fileExists(String name) 
	{
		File file = new File(Constants.LVL_PATH + name + Constants.LVL_EXT);
		return file.exists();
	}
	
	private static String serializeGame(Game game) 
	{
		String result = "";
		
		// serialize the object
		try 
		{
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream so = new ObjectOutputStream(bo);
			so.writeObject(game);
			so.flush();
			result = bo.toString();
			return result;
		} 
		catch (Exception e) 
		{
			System.out.println(e);
			System.exit(1);
		}
		
		throw new RuntimeException("Unable to serialize game");
	}
}
