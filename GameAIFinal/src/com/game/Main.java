package com.game;
import java.io.File;

import com.level_handling.Generator;
import com.level_handling.Level;
import com.level_handling.Parser;


public class Main {

	private static Game game;
	
	public static void main(String args[]) {
		validateArgs(args);
		
		Level level = new Level();
		
		if(fileExists(args[0]))
			level = Parser.parse(args[0]);
		else
			level = Generator.generate(args[0]);
		
		level.printLevel();
		
		game.display();
	}
	
	private static void validateArgs(String args[]) {
		if (args.length != 1){
			System.err.println("ERROR: Wrong number of arguements");
			System.exit(1);
		}
	}
	
	private static boolean fileExists(String name) {
		File file = new File(Constants.LVL_PATH + name + Constants.LVL_EXT);
		return file.exists();
	}
}
