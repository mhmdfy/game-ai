package com.data_structure.block;


public class EmptyBlock extends Block
{
	private static final long serialVersionUID = -3418225495898700741L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() 
	{
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public String print() 
	{
		return ".";
    }
	
}
