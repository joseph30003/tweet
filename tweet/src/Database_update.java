

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database_update {

	public static String Gender_search(String name, Connection conn){
		String result=null;	
		String[] name_user = name.toLowerCase().split(" ");
		try
	    {
	      String name_trim=name_user[0].replaceAll("[^A-Za-z0-9]", "");
	      String query1 = "select gender from Gender where name='"+name_trim+"' and frequency =(select max(frequency) from Gender where name='"+name_trim+"') limit 0,1";
	      
	      Statement st = conn.createStatement();
	       
	      ResultSet rs = st.executeQuery(query1);
	      
	      while (rs.next())
	      {
	        
	        result=rs.getString("gender");
	        
	         }
	      
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		return result;
		}
	public static boolean User_search(String id, Connection conn){
		boolean result=false;	
		try
	    {
	      
	      String query1 = "select id from Users where id='"+id+"'";
	         
	      Statement st = conn.createStatement();
	       
	      ResultSet rs = st.executeQuery(query1);
	      
	      while (rs.next())
	      {
	        
	        result=true;
	        
	         }
	      
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		return result;
		}
	
	public static void run(String msg,String tag) throws IOException {

       
		String url = "jdbc:mysql://is-server-027.ifsm.umbc.edu/Twitter";
        String user = "joseph";
        String password = "joseph";
		Connection con = null;
        PreparedStatement pst = null;
        PreparedStatement pst_user = null;
        PreparedStatement pst_hashtags = null;
        PreparedStatement pst_mentions = null;
        String tw_id = Reader.TextReader(msg, "$.id");
		String tw_content = Reader.TextReader(msg, "$.text");
		String create_at = Reader.TextReader(msg, "$.created_at");
		String geo = Reader.TextReader(msg, "$.geo"); 
		String retweeted = Reader.TextReader(msg, "$.retweeted");
		String user_id = Reader.TextReader(msg, "$.user.id");
		String user_name = Reader.TextReader(msg, "$.user.name");
	    String user_screen_name = Reader.TextReader(msg, "$.user.screen_name");
	    String user_location = Reader.TextReader(msg, "$.user.location");
	    String user_des = Reader.TextReader(msg, "$.user.description");
	    String user_created_at = Reader.TextReader(msg, "$.user.created_at");
        String hashtags = Reader.TextReader(msg, "$.entities.hashtags[*].text");
        String mentions = Reader.TextReader(msg, "$.entities.user_mentions[*].id");
        String HS[],MN[];   
        String tr_hs,tr_mn;
             
               

        try {

            con = DriverManager.getConnection(url, user, password);
            
			if( !(User_search(user_id,con)) ){
            pst_user =  con.prepareStatement("INSERT INTO Users(id,name,screen_name,location,description,created_at,gender) VALUES(?,?,?,?,?,?,?)");
            pst_user.setString(1, user_id);
            pst_user.setString(2, user_name);
            pst_user.setString(3, user_screen_name);
            pst_user.setString(4, user_location);
            pst_user.setString(5, user_des);
            pst_user.setString(6, user_created_at);
            pst_user.setString(7, Gender_search(user_name,con));
            pst_user.execute();
            }
			
            pst = con.prepareStatement("INSERT INTO Tweets(id,create_at,text,geo,user_id,retweeted,tag) VALUES(?,?,?,?,?,?,?)");
            pst.setString(1, tw_id);
            pst.setString(2, create_at);
            pst.setString(3, tw_content);
            pst.setString(4, geo);
            pst.setString(5, user_id);
            pst.setString(6, retweeted);
            pst.setString(7, tag);
            pst.execute();
            pst_hashtags = con.prepareStatement("INSERT INTO Hashtags(id,Hashtag) VALUES(?,?)");
            pst_hashtags.setString(1, tw_id);
            if(hashtags == null){
            pst_hashtags.setString(2, null);
            pst_hashtags.execute();
            }else{
            HS=hashtags.split(",");
            for(int i=0;i<HS.length;i++){
            	tr_hs=HS[i].replace("[", "").replace("]", "").replace("\"", "");
            	pst_hashtags.setString(2, tr_hs);
            	pst_hashtags.execute();
            }
            	
            }
            
            pst_mentions = con.prepareStatement("INSERT INTO Mentions(id,user_id) VALUES(?,?)");
            pst_mentions.setString(1, tw_id);
            if(mentions == null){
            	pst_mentions.setString(2, null);
            pst_mentions.execute();
            }else{
            MN=mentions.split(",");
            for(int i=0;i<MN.length;i++){
            	tr_mn=MN[i].replace("[","").replace("]","");
            	pst_mentions.setString(2, tr_mn);
            	pst_mentions.execute();
            }
            	
            }
            
            
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Prepared.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Prepared.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}
