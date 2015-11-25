



import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Lists followers' ids
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public class GetFollowersIDs1 {
    /**
     * Usage: java twitter4j.examples.friendsandfollowers.GetFollowersIDs [screen name]
     *
     * @param args message
     */
	private static String cK = "irOjUmsfi5mjdnC2DkwcqDsIZ";
	private static String cS = "brXaxnAuo5U1NaASV08CCv2GGSAPoinxhfHttqbbln7nUiPYxF";
	private static String aT = "2980890963-hADyI32sy8wnNGsdrpJnxy3wX2kTC0srhnhkjqx";
	private static String aTS = "P9r2di51xZ0zgPbRNciZOhT0oCThwPlK0VKd9PWcUmuuY";
    public static void main(String args[]) {
        try {
        	 ConfigurationBuilder cb = new ConfigurationBuilder();
        	    cb.setDebugEnabled(true)
        	            .setOAuthConsumerKey(cK)
        	            .setOAuthConsumerSecret(cS)
        	            .setOAuthAccessToken(aT)
        	            .setOAuthAccessTokenSecret(aTS);

        	TwitterFactory tf = new TwitterFactory(cb.build());
        	Twitter twitter = tf.getInstance();
            long cursor = -1;
            long IDS=2458351900L;
            IDs ids;
            System.out.println("Listing followers's ids.");
            do {
                ids = twitter.getFollowersIDs(IDS, cursor);

                for (long id : ids.getIDs()) {
                    System.out.println(id);
                }
            } while ((cursor = ids.getNextCursor()) != 0);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get followers' ids: " + te.getMessage());
            System.exit(-1);
        }
    }
}