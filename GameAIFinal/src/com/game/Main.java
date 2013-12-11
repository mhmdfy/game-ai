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
	/**
	 * Main method of the game.
	 * Takes the name of the level as the first argument, if the file
	 * exists, it parses it and displays it. If the file doesn't exist
	 * or "random" was passed, it generates a new level to play with.
	 */
	public static void main(String args[]) 
	{
		validateArgs(args);
		
		Level level = new Level();
		
		if(!args[0].equals("random") && fileExists(args[0]))
			level = Parser.parse(args[0]);
		else
			level = Generator.generate(args[0]);
		
		Game game = new Game(level);
		
		PApplet.main(new String[] { "--present", "com.game.Display", serializeGame(game)});
	}
	
	/**
	 * the arguments have to be of size 1 (the name of the level).
	 * @param args
	 */
	private static void validateArgs(String args[]) 
	{
		if (args.length != 1)
		{
			System.err.println("ERROR: Wrong number of arguements");
			System.exit(1);
		}
	}
	
	/**
	 * Checks if the file exists in the levels directory.
	 * @return True if the file exists, False otherwise.
	 */
	private static boolean fileExists(String name) 
	{
		File file = new File(Constants.LVL_PATH + name + Constants.LVL_EXT);
		return file.exists();
	}
	
	/**
	 * Takes the game and gets a serialized string of the game
	 * to pass it to Display.
	 * @return The serialized string of the game.
	 */
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
