package teweet;


import java.io.IOException;



import java.util.List;

import com.google.common.collect.Lists;


public class Twitter_data {

	public static void main(String[] args) throws InterruptedException, IOException{
		
		String cK = "irOjUmsfi5mjdnC2DkwcqDsIZ";
		String cS = "brXaxnAuo5U1NaASV08CCv2GGSAPoinxhfHttqbbln7nUiPYxF";
		String aT = "2980890963-hADyI32sy8wnNGsdrpJnxy3wX2kTC0srhnhkjqx";
		String aTS = "P9r2di51xZ0zgPbRNciZOhT0oCThwPlK0VKd9PWcUmuuY";
		String file_path="./BMW";
		//List<String> keyword = Lists.newArrayList("#BMW","@BMW","BMW");
		//Tweets_collection.run(cK, cS, aT, aTS,keyword);
		
		for(int i=1;i<=100;i++){
			Tweets_collection.Users_id (cK, cS, aT, aTS,"realDonaldTrump",i,200);
		}
		//Tweets_collection.tweets_id(cK, cS, aT, aTS, "667129988488028160");
	}
}
