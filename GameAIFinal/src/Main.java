import java.io.File;


public class Main {

	private static Level level;
	
	public static void main(String args[]) {
		validateArgs(args);
		
		if(fileExists(args[0]))
			level = Parser.parse(args[0]);
		else
			level = Generator.generate(args[0]);
		
		level.printMap();
	}
	
	private static void validateArgs(String args[]) {
		if (args.length != 1){
			System.err.println("ERROR: Wrong number of arguements");
			System.exit(1);
		}
	}
	
	private static boolean fileExists(String name) {
		File file = new File(Constants.MAP_PATH + name + Constants.MAP_EXT);
		return file.exists();
	}
}
