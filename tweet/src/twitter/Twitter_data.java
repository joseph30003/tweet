package twitter;

import java.io.IOException;



import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;


public class Twitter_data {

	public static void main(String[] args) throws InterruptedException, IOException{
		
		String cK = "irOjUmsfi5mjdnC2DkwcqDsIZ";
		String cS = "brXaxnAuo5U1NaASV08CCv2GGSAPoinxhfHttqbbln7nUiPYxF";
		String aT = "2980890963-hADyI32sy8wnNGsdrpJnxy3wX2kTC0srhnhkjqx";
		String aTS = "P9r2di51xZ0zgPbRNciZOhT0oCThwPlK0VKd9PWcUmuuY";
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter a file path");
		String file_path=in.nextLine();
		
		List<String> keyword = Lists.newArrayList();
		System.out.println("Enter Keywords");
		String keywords=in.nextLine();
		for(int i=0;i<keywords.split(",").length;i++){
			keyword.add(keywords.split(",")[i]);
		}
		System.out.println("Enter tag");
		String tag=in.nextLine();
		Tweets_collection.run(cK, cS, aT, aTS,file_path,keyword,tag);
		
	
	}
}
