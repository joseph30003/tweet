package teweet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

public class Tweets_collection {
	
	
	public static void run(String consumerKey, String consumerSecret, String token, String secret,String file_path,List<String> keyword,String tag) throws InterruptedException, IOException {
	    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
	    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
	    	    
	    endpoint.trackTerms(keyword);
	   
	    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
	    
	    Client client = new ClientBuilder()
	            .hosts(Constants.STREAM_HOST)
	            .endpoint(endpoint)
	            .authentication(auth)
	            .processor(new StringDelimitedProcessor(queue))
	            .build();

	    
	    client.connect();

	    
	    while(true)
	    {
	      String msg = queue.take();
	      Files_executor.writeTofile(msg, file_path);
	      Database_update.run(msg,tag); 	      
	    }

	    //client.stop();

	}
	
		
	public static void tweets_id(String CONSUMER_KEY,String CONSUMER_KEY_SECRET,String TWITTER_TOKEN,String TWITTER_TOKEN_SECRET,String tweetID){ 
		
		
		final Twitter twitter = new TwitterFactory().getInstance();
	    twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
	    AccessToken accessToken = new AccessToken(TWITTER_TOKEN,TWITTER_TOKEN_SECRET);
	    twitter.setOAuthAccessToken(accessToken);
	    try {
	        twitter4j.Status status = twitter.showStatus(Long.parseLong(tweetID));
	        if (status == null) { // 
	            // don't know if needed - T4J docs are very bad
	        } else {
	            System.out.println("@" + status.getUser().getScreenName()
	                        + " - " + status.getText());
	            if(status.isRetweet()){
	        		System.out.println("retweet is "+status.getRetweetedStatus().getText());
	        	}
	        }
	    } 
	    catch (TwitterException e) {
	        System.err.print("Failed to search tweets: " + e.getMessage());
	        // e.printStackTrace();
	        // DON'T KNOW IF THIS IS THROWN WHEN ID IS INVALID
	    
	}
}
	
	public static void run(String consumerKey, String consumerSecret, String token, String secret,List<String> keyword) throws InterruptedException, IOException {
	    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
	    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
	    	    
	    endpoint.trackTerms(keyword);
	   
	    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
	    
	    Client client = new ClientBuilder()
	            .hosts(Constants.STREAM_HOST)
	            .endpoint(endpoint)
	            .authentication(auth)
	            .processor(new StringDelimitedProcessor(queue))
	            .build();

	    
	    client.connect();

	    
	    while(true)
	    {
	      String msg = queue.take();
	      System.out.println(msg);	      
	    }

	    //client.stop();

	}	
	
	
public static void Users_id(String CONSUMER_KEY,String CONSUMER_KEY_SECRET,String TWITTER_TOKEN,String TWITTER_TOKEN_SECRET,String userID,int page){ 
		
		
		final Twitter twitter = new TwitterFactory().getInstance();
	    twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_KEY_SECRET);
	    AccessToken accessToken = new AccessToken(TWITTER_TOKEN,TWITTER_TOKEN_SECRET);
	    twitter.setOAuthAccessToken(accessToken);
	  

	   
	    List<twitter4j.Status> statuses = new ArrayList<twitter4j.Status>();
	    

	    

	      try {
            
	    	Paging paging = new Paging(1,page);
	    	statuses = twitter.getUserTimeline(userID,paging);
	        for(int i=0;i<statuses.size();i++){
	        
	        	System.out.println(i+"--------@" + statuses.get(i).getUser().getScreenName()
                        + " - " + statuses.get(i).getText()+statuses.get(i).isRetweet());
	        	if(statuses.get(i).isRetweet()){
	        		System.out.println("retweet"+statuses.get(i).getText()+statuses.get(i).getRetweetedStatus().getText());
	        	}
	        }
	    	
	      }
	      catch(TwitterException e) {

	        e.printStackTrace();
	      }
	    
	      
	   
	    
}
	
}
