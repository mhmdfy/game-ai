package com.data_structure.block;

public class BreakableBlock extends Block
{
	private static final long serialVersionUID = 5513959124860880601L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isBreakable() 
	{
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public String print() 
	{    
		return "#";
    }

}
