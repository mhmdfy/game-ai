package com.game;

public class Constants 
{
	// File Handling
	public final static String LVL_PATH = "./levels/";
	public final static String LVL_EXT = ".lvl";
	public final static String IMG_PATH = "./imgs/";
	public final static String IMG_EXT = ".png";
	
	// Map information
	public final static int WIDTH = 21;
	public final static int HEIGHT = 21;
	public final static int MAP_X = 50; 
	public final static int MAP_Y = 50;
	
	// Character Statistics
	public final static int LIFES = 1;
	public final static int STEP = 50;
	public final static int BOMB_TIMER = 150;
	public final static int BOMB_RANGE = 3;
	public final static int FLAME_TIMER = 50;
	
	// Game Statuses
	public final static String GAME_ACTIVE = "active";
	public final static String GAME_LOSE = "lose";
	public final static String GAME_WIN = "win";
	
	// AI Constants
	public final static int ENEMY_NUM = 6;
	public final static int MOVE_TIMER = 100;
	public final static int STEP_TIMER = 20;
}
