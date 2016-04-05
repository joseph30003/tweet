package teweet;


import java.util.List;

import com.jayway.jsonpath.JsonPath;

public class Reader {

	
	 public static String TextReader(String json1,String jsonPath) {

		// TODO Auto-generated method stub

 		if (json1 != null) {
		
 			Object jsonPathResult = JsonPath.read(json1, jsonPath);

		if (null == jsonPathResult) {

			return null;

		} else if (jsonPathResult instanceof List

				&& ((List<?>) jsonPathResult).isEmpty()) {

			return null;

		} else {

			return jsonPathResult.toString();

		}
 		}else{
 			return null;
 		}
	}

	
	 
	 
	 
}
