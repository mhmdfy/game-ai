import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		saveToFile(fileName, level);
		
		return level;
	}
	
	private static void saveToFile(String name, Level level)
	{
		String path = Constants.LVL_PATH + name + Constants.LVL_EXT;
		try {
			File file = new File(path);
			
			if(!file.exists())
				file.createNewFile();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(path));
			
			for(int i = 0; i < width; i++)
			{
				for(int j = 0; j < height; j++)
				{
					out.write(level.getBlock(i, j).print());
				}
				
				out.write("\n");
			}
			
			out.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
