import java.util.Random;

public class Generator {

	private static int width = Constants.WIDTH;
	private static int height = Constants.HEIGHT;
	
	
	
	public static Level generate(String fileName)
	{
		Level level = new Level();
		Random random = new Random();
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				int type = random.nextInt(4);
				//Creates outer boarder of unbreakable wall
				if(i == 0 || j == 0 || i == width - 1 || j == width - 1)
				{
					level.setBlock(i, j, new WallBlock());
				}
				else if(i % 2 == 0 && j % 2 == 0)
				{
					level.setBlock(i, j, new WallBlock());
				}
				else if(type == 0)
				{
					level.setBlock(i, j, new BreakableBlock());	
				}
				else 
				{
					level.setBlock(i, j, new EmptyBlock());
				}
			}
		}
		return level;
	}
	
	//private void printToFile(int x, int y, Level level)
	
}
