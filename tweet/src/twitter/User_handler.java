package twitter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class User_handler {
	

	public static void main(String[] args) {

		String cK = "irOjUmsfi5mjdnC2DkwcqDsIZ";
		String cS = "brXaxnAuo5U1NaASV08CCv2GGSAPoinxhfHttqbbln7nUiPYxF";
		String aT = "2980890963-hADyI32sy8wnNGsdrpJnxy3wX2kTC0srhnhkjqx";
		String aTS = "P9r2di51xZ0zgPbRNciZOhT0oCThwPlK0VKd9PWcUmuuY";
		
		

		try{
			// create our mysql database connection
		      
			  String url = "jdbc:mysql://is-server-027.ifsm.umbc.edu/Twitter";
		      
		      Connection conn = DriverManager.getConnection(url, "joseph", "joseph");
		      
		      String query = "select Users.screen_name,Users.id from Users,Tweets,Result where Result.id=Tweets.id and Tweets.user_id = Users.id and Result.id";
		      Statement st = conn.createStatement();
		      ResultSet rs = st.executeQuery(query);
		      while (rs.next()){
		    	 	    	 
		    	 Tweets_collection.Users_id (cK, cS, aT, aTS,rs.getString(2),rs.getString(1),200,conn);
		    	 		    	 	    	  
		      }
		      
		      
		      
		      
		      conn.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		      e.printStackTrace();
		    }
		
		
		

	}

}
