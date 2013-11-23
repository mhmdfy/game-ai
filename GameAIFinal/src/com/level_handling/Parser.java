package com.level_handling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.data_structure.block.Block;
import com.data_structure.block.BreakableBlock;
import com.data_structure.block.EmptyBlock;
import com.data_structure.block.WallBlock;
import com.game.Constants;

public class Parser {
	
	public static Level parse(String name) {
		String path = Constants.LVL_PATH + name + Constants.LVL_EXT;
		Level level = new Level();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String currentLine = "";
			int lineNum = 0;
			
			while ((currentLine = br.readLine()) != null) {
				Block[] row = getRow(currentLine);
				level.setRow(row, lineNum);
				lineNum = lineNum + 1;
			}
			
			if (lineNum != Constants.HEIGHT)
				wrongFormat();
			
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return level;
	}
	
	private static Block[] getRow(String line) {
		if(line.length() != Constants.WIDTH)
			wrongFormat();
		
		Block[] row = new Block[Constants.WIDTH];
		
		for (int i = 0; i < Constants.WIDTH; i ++) {
			char c = line.charAt(i); 
			if(c == '.')
				row[i] = new EmptyBlock();
			else if(c == '#')
				row[i] = new BreakableBlock();
			else if(c == 'X')
				row[i] = new WallBlock();
			else
				wrongFormat();
		}
		
		return row;
	}
	
	private static void wrongFormat(){
		System.err.println("ERROR: Wrong level format");
		System.exit(2);
	}
}
