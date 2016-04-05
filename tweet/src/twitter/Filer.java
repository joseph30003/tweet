package twitter;

import java.io.*;


public class Filer {


		public static void Readfile(String filename) throws IOException {
			// Construct BufferedReader from FileReader
			File file = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(file));
			PrintWriter fw1 = new PrintWriter("C:/Users/xf37538/Documents/tw.csv");
			PrintWriter fw2 = new PrintWriter("C:/Users/xf37538/Documents/TU.csv");
			PrintWriter fw3 = new PrintWriter("C:/Users/xf37538/Documents/user.csv");
			String line=null;
			String  twid;
			String  twcontent,userid,username,userlocation;
			while ((line = br.readLine()) != null) {
				if(!line.equals("")){
				twid = Reader.TextReader(line, "$.id");
				twcontent = Reader.TextReader(line, "$.text");
				userid = Reader.TextReader(line, "$.user.id");
			    username = Reader.TextReader(line, "$.user.name");
			    userlocation = Reader.TextReader(line, "$.user.location");
				fw1.write(twid+","+twcontent+"\n");
				fw2.write(userid+","+twid+"\n");
				fw3.write(userid+","+username+","+userlocation+"\n");
				}
			}
			fw1.close();
			fw2.close();
			fw3.close();
			br.close();
			
		}

		public static void writefile(String line) throws IOException {
			// Construct BufferedReader from FileReader
			//PrintWriter out = new PrintWriter(new FileWriter(log, true));
			PrintWriter fw0 = new PrintWriter(new FileWriter("C:/Users/xf37538/Documents/raw.txt",true));
			PrintWriter fw1 = new PrintWriter(new FileWriter("C:/Users/xf37538/Documents/tw.csv",true));
			PrintWriter fw2 = new PrintWriter(new FileWriter("C:/Users/xf37538/Documents/TU.csv",true));
			PrintWriter fw3 = new PrintWriter(new FileWriter("C:/Users/xf37538/Documents/user.csv",true));
			
			String  twid;
			String  twcontent,userid,username,userlocation;
			//String hashtags[];
			
				twid = Reader.TextReader(line, "$.id");
				twcontent = Reader.TextReader(line, "$.text");
				userid = Reader.TextReader(line, "$.user.id");
			    username = Reader.TextReader(line, "$.user.name");
			    userlocation = Reader.TextReader(line, "$.user.location");
			    //hashtags = Reader.TextReader(line, "$.entities.hashtags[*].text").split(",");
			    //for(int i=0;i<hashtags.length;i++){
			    //System.out.println(hashtags[i]);}
			    fw0.write(line+"\n");
			    fw0.close();
				fw1.write(twid+","+twcontent+"\n");
				fw2.write(userid+","+twid+"\n");
				fw3.write(userid+","+username+","+userlocation+"\n");
				fw1.close();
			    fw2.close();
			    fw3.close();
			
			
		}
		
		public static void writeraw_tweets(String line) throws IOException {
			// Construct BufferedReader from FileReader
			//PrintWriter out = new PrintWriter(new FileWriter(log, true));
			PrintWriter fw0 = new PrintWriter(new FileWriter("./rawtweets.txt",true));
			
			
		
			    fw0.write(line+"\n");
			    fw0.close();
				
			
			
		}
		
		public static void write_tofile(String line,String file) throws IOException {
			// Construct BufferedReader from FileReader
			//PrintWriter out = new PrintWriter(new FileWriter(log, true));
			PrintWriter fw0 = new PrintWriter(new FileWriter(file,true));
			
			fw0.write(line);
		
			fw0.close();
				
			
			
		}
	
}