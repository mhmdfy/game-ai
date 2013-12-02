package com.data_structure.block;

public abstract class Block 
{
	
	private boolean flag = false;
	
	public boolean isFlaged()
	{
		return flag;
	}
	
	public void setFlag(boolean value)
	{
		flag = value;
	}
	
	public abstract String print();
	
}
