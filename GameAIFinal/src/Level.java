
public class Level {
	
	//protected static final int BREAKABLE_BLOCK = 1;
	//protected static final int EMPTY_BLOCK = 2;
	//protected static final int WALL_BLOCK = 3;
	
	protected int width = Constants. WIDTH;
	protected int height = Constants.HEIGHT;
	
	
	private Block[][] map;
	
	public void initLevel()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; i < height; j++)
			{
				map[i][j] = new EmptyBlock();
			}
		}
	}
	
	public void printLevel()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; i < height; j++)
			{
				System.out.print(map[i][j]);
			}
		}
		return;
	}
	
	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		map = new Block[width][height];
	}
	
	public Block getBlockCapped(int x, int y)
    {
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x >= width) x = width - 1;
        if (y >= height) y = height - 1;
        return map[x][y];
    }
	
	public Block getBlock(int x, int y)
    {
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x >= width) x = width - 1;
        if (y >= height) y = height - 1;
        return map[x][y];
    }
	
	 public void setBlock(int x, int y, Block b)
	    {
	        if (x < 0) return;
	        if (y < 0) return;
	        if (x >= width) return;
	        if (y >= height) return;
	        map[x][y] = b;
	    }
	 
	 public int getWidth()
	 {
		 return width;
	 }
	 
	 public int getHeight()
	 {
		 return height;
	 }
	 
	 public Block[][] getMap()
	 {
		 return map;	 
	 }
	 
	 

	
}
