package twitter;



import java.io.IOException;


import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;






public class User_personality {
	
	
	public static void Personality_update(String User_id,String name, double Percentage, double SamplingError,Connection conn){
		

		try{
			
			PreparedStatement pst_user =  (PreparedStatement) conn.prepareStatement("INSERT INTO User_personality(User_id,Personality,Percentage,SamplingError) VALUES(?,?,?,?)");
	        pst_user.setString(1, User_id);
	        pst_user.setString(2, name);
	        pst_user.setDouble(3, Percentage);
	        pst_user.setDouble(4, SamplingError);
	        pst_user.execute();
			
			
			
			
			}
		catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      e.printStackTrace();
	    }
		
		
		
	}
	
	

	 public static void run(String User_id,String myProfile,Connection conn) throws IOException {
		 PersonalityInsights service = new PersonalityInsights();
		 service.setUsernameAndPassword("79a49b79-c966-4730-934d-b731c54fa5a1","U5sZJgoKEsfC");
		 
		 
		 Profile personalityProfile = service.getProfile(myProfile);
		 
		
		 //System.out.println(personalityProfile.getTree());
		 
		 for(int i=0;i<(personalityProfile.getTree().getChildren().size());i++){
			// System.out.println(personalityProfile.getTree().getChildren().get(i).getId());
			 if(personalityProfile.getTree().getChildren().get(i).getId().equals("personality")){
				 
			 for(int j=0;j<personalityProfile.getTree().getChildren().get(i).getChildren().size();j++){
				// System.out.println(personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getCategory());
				 if(personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getCategory().equals("personality")){
					 Personality_update(User_id,personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getId(),personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getPercentage(),personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getSamplingError(),conn);
				 for(int k=0;k<personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getChildren().size();k++){
					 if(personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getChildren().get(k).getCategory().equals("personality")){
						 
						 Personality_update(User_id,
								 personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getChildren().get(k).getId(),
						         personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getChildren().get(k).getPercentage(),
						         personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getChildren().get(k).getSamplingError(),
						         conn);
					 for(int l=0;l<personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getChildren().get(k).getChildren().size();l++){
				if(personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getChildren().get(k).getChildren().get(l).getCategory().equals("personality")){
					 Personality_update(User_id,personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getChildren().get(k).getChildren().get(l).getId(),
		 personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getChildren().get(k).getChildren().get(l).getPercentage(),
		 personalityProfile.getTree().getChildren().get(i).getChildren().get(j).getChildren().get(k).getChildren().get(l).getSamplingError(),
		 conn);
					 }
					 }
					 }
				     }
				 }
		   }
		 }
		}
	}
}
