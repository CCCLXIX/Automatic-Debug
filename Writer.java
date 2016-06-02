import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.io.IOException;



public class Writer implements Output {

	private String path;
	private boolean replace = true;

	public Writer(String Path){
		path = Path;
	}

	public void write(String text) throws IOException{
		FileWriter write = new FileWriter(path, replace);
		PrintWriter print = new PrintWriter(write);

		print.printf(text+"\n");
		print.close();
	}
}