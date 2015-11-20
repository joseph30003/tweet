

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


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
}
