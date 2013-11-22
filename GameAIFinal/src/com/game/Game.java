package com.game;
import java.util.ArrayList;

import com.data_structure.bomb.Bomb;
import com.data_structure.character.Bomberman;
import com.data_structure.character.Char;
import com.level_handling.Level;


public class Game {
	
	private Level level;
	private Char player;
	private ArrayList<Char> enemies;
	private ArrayList<Bomb> bombs;
	
	public Game(Level level)
	{
		this.level = level;
		this.player = new Bomberman();
		this.enemies = initializeEnemies();
		this.bombs = new ArrayList<Bomb>();
	}
	
	private ArrayList<Char> initializeEnemies()
	{
		// TODO code here
		
		return new ArrayList<Char>();
	}
	
	public void display()
	{
		
	}
}
