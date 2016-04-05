package teweet;


import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;


import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class FilterStreamExample {

  public static void run(String consumerKey, String consumerSecret, String token, String secret) throws InterruptedException, IOException {
    BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
    StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
    // add some track terms
    //List<Long> followings = Lists.newArrayList(2458351900L, 566788L);
    //List<String> terms = Lists.newArrayList("twitter", "api");
    //endpoint.followings(followings);
    //endpoint.trackTerms(terms);
    endpoint.trackTerms(Lists.newArrayList("#HillaryClinton","@HillaryClinton","HillaryClinton","Hillary Clinton"));
   
    
    Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);
    //Authentication auth = new BasicAuth(username, password);

    // Create a new BasicClient. By default gzip is enabled.
    Client client = new ClientBuilder()
            .hosts(Constants.STREAM_HOST)
            .endpoint(endpoint)
            .authentication(auth)
            .processor(new StringDelimitedProcessor(queue))
            .build();

    // Establish a connection
    client.connect();

    // Do whatever needs to be done with messages
    //for (int msgRead = 0; msgRead < 1000; msgRead++) 
    while(true)
    {
      String msg = queue.take();
      //Filer.writefile(msg);
      Prepared.sql_input(msg,"HillaryClinton");
      //System.out.println(Reader.TextReader(msg, "$.*"));
      
    }

    //client.stop();

  }
}