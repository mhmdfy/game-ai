package com.game;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import processing.core.PApplet;
import processing.core.PImage;

public class Display extends PApplet 
{
	private static final long serialVersionUID = 3480668359190549352L;
	private Game game;
	
	// variables of all used images:
	PImage wall;
	PImage brick;
	PImage bomberman;
	PImage bomb;
	
	@Override
	public void setup() {
		size(1050,1050);
		
		game = deserializeString(args[0]);
		
		// Initialize all images
		wall = loadImage(Constants.IMG_PATH + "wall" + Constants.IMG_EXT);
		brick = loadImage(Constants.IMG_PATH + "brick" + Constants.IMG_EXT);
	}

	@Override
	public void draw() {
		// draw the green background
		background(0,100,0);
		
		for(int x = 0; x < Constants.WIDTH; x++) {
			for(int y = 0; y < Constants.HEIGHT; y++) {
				if(game.getLevel().getBlock(x, y).isWall())
					image(wall,x*50, y*50);
				if(game.getLevel().getBlock(x, y).isBreakable())
					image(brick,x*50, y*50);
			}
		}
	}
	
	private static Game deserializeString(String string) {
		// deserialize the object
		try {
			byte b[] = string.getBytes(); 
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			Game game = (Game) si.readObject();
			return game;
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		throw new RuntimeException("Wasn't able to deserialize String");
	}
}
