package com.game;
import java.io.Serializable;
import java.util.ArrayList;

import com.data_structure.bomb.Bomb;
import com.data_structure.character.Bomberman;
import com.data_structure.character.Char;
import com.level_handling.Level;


public class Game implements Serializable {
	
	private static final long serialVersionUID = -8053469542486898772L;
	
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
		System.out.println("displaying");
	}
	
	public Level getLevel() 
	{
		return level;
	}
}
