package com.data_structure.block;

public class BreakableBlock extends Block{

	private static final long serialVersionUID = 5513959124860880601L;

	@Override
	public boolean isBreakable() 
	{
		return true;
	}
	@Override
    public String print() 
	{    
		return "#";
    }

}
