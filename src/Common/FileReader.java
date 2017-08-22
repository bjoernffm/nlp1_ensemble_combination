package Common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
	String content;
	
	public FileReader(String filename) throws IOException
	{
		this.content = new String(Files.readAllBytes(Paths.get(filename)));
	}
	
	public String getContent()
	{
		return this.content;
	}
}
