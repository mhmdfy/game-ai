package com.data_structure.block;

public class WallBlock extends Block{

	private static final long serialVersionUID = 311266082960982166L;

	@Override
	public boolean isWall()
	{
		return true;
	}
	
	@Override
    public String print() 
	{
		return "X";
    }

	
}
