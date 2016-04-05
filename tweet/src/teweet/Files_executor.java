package teweet;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Files_executor {

		
	public static void writeTofile(String line,String file_path) throws IOException {
		// Construct BufferedReader from FileReader
		//PrintWriter out = new PrintWriter(new FileWriter(log, true));
		PrintWriter fw0 = new PrintWriter(new FileWriter(file_path,true));
		fw0.write(line+"\n");
		fw0.close();
			
		
	}
	
}
