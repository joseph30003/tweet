

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.mysql.jdbc.PreparedStatement;

public class User_gender {


	public static HashMap<String, String> getHashTable(ResultSet rs) throws SQLException{
		
		HashMap<String, String> map = new HashMap<String, String>();
		while(rs.next()){
			
				map.put(rs.getString("name").toLowerCase(), rs.getString("gender"));
		}
		return map;
		
		
	}
	
	
	public static void update_users(Connection conn, String id, String gender)
	  {
	    try
	    {
	      
	     
	      // create the java mysql update preparedstatement
	      String query = "update Users set gender = ? where id = ?";
	      PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
	      preparedStmt.setString(1, gender);
	      preparedStmt.setString(2, id);
	 
	      // execute the java preparedstatement
	      preparedStmt.executeUpdate();
	       
	     
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	  }
	
	
	
	public static String search(String name, HashMap<String, String> map){
		
		if (map.containsKey(name)){
			return map.get(name);
		}else{
			return null;
		}
		
	}
	
public static String gender_search(String name){
	String result=null;
	String[] name_user = name.toLowerCase().split(" ");
	try
    {
      // create our mysql database connection
      
      String myUrl = "jdbc:mysql://is-server-027.ifsm.umbc.edu/Twitter";
     
      Connection conn = DriverManager.getConnection(myUrl, "joseph", "joseph");
       
      // our SQL SELECT query. 
      // if you only need a few columns, specify them by name instead of using "*"
      String query1 = "select gender from Gender where name='"+name_user[0]+"' and frequency =(select max(frequency) from Gender where name='"+name_user[0]+"') limit 0,1;";
      
      // create the java statement
      Statement st = conn.createStatement();
       
      // execute the query, and get a java resultset
      ResultSet rs = st.executeQuery(query1);
      
      
      // iterate through the java resultset
      while (rs.next())
      {
        
        
        result=rs.getString("gender");
        
       
         }
      //return null;
      
      st.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
	
	return result;
	}
	
	  public static void gender_update(String[] args)
	  {
	    try
	    {
	      // create our mysql database connection
	      
	      String myUrl = "jdbc:mysql://is-server-027.ifsm.umbc.edu/Twitter";
	      String[] name_user=null;
	      Connection conn = DriverManager.getConnection(myUrl, "joseph", "joseph");
	       
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query1 = "SELECT name,gender from Gender";
	      String query = "select id,name from Users";
	      // create the java statement
	      Statement st = conn.createStatement();
	       
	      // execute the query, and get a java resultset
	      ResultSet rs1 = st.executeQuery(query1);
	      HashMap<String, String> map = getHashTable(rs1);
	      ResultSet rs = st.executeQuery(query);
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        
	        
	        name_user = rs.getString("Name").split(" ");
	        
	        String gender=search(name_user[0].toLowerCase(),map);
	         
	        
	       
	              
	        	
	            update_users(conn,rs.getString("id"),gender);
	            //System.out.format("%s\n", gender);
	        
	         }
	      
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	  }
	}