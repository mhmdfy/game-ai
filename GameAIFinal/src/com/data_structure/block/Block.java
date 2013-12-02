package com.data_structure.block;
import java.io.Serializable;

public abstract class Block implements Serializable 
{
	
	private static final long serialVersionUID = 8173885238036082385L;

	private boolean flag = false;

	public boolean isFlaged()
	{
		return flag;
	}

	public void setFlag(boolean value)
	{
		flag = value;
	}
	public boolean isWall() {
		return false;
	}
	public boolean isBreakable() {
		return false;
	}
	public boolean isEmpty() {
		return false;
	}
	public abstract String print();
	
}
