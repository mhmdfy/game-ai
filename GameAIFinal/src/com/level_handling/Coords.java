package com.level_handling;

public class Coords 
{
	private int x;
	private int y;

	public Coords(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (other instanceof Coords)
		{
			Coords that = (Coords) other;
			
			if(this.x == that.x && this.y == that.y)
				return true;
		}
		
		return false;
	}
}
