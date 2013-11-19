import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
	
	private static Level level;
	
	public static Level parse(String name) {
		String path = Constants.LVL_PATH + name + Constants.LVL_EXT;
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		String currentLine = "";
		int lineNum = 0;
		
		while ((currentLine = br.readLine()) != null) {
			addRow(currentLine);
			lineNum = lineNum + 1;
		}
		
		if (lineNum != Constants.HIEGHT)
			wrongFormat();
		
//		try {
//			
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//		finally {
//			br.close();
//		}
	}
	
	private static void addRow(String line) {
		if(line.length() != Constants.WIDTH)
			wrongFormat();
		
		Block[] row = new Block[Constants.WIDTH];
		
		for (int i = 0; i < Constants.WIDTH; i ++) {
			char c = line.charAt(i); 
			if(c == '.')
				row[i] = new EmptyBlock();
			else if(c == '#')
				row[i] = new BreakableBlock();
			else if(c == 'x')
				row[i] = new WallBlock();
			else
				wrongFormat();
		}
		
		level.addRow(row);
	}
	
	private static void wrongFormat(){
		System.err.println("ERROR: Wrong level format");
		System.exit(2);
	}
}
