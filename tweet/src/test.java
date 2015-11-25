

import java.io.IOException;



public class test {

	public static void main(String[] args) throws InterruptedException, IOException{
		//Filer.Readfile("C:/Users/xf37538/Documents/cytoscape/0901/result");
		//String cK = "irOjUmsfi5mjdnC2DkwcqDsIZ";
		//String cS = "brXaxnAuo5U1NaASV08CCv2GGSAPoinxhfHttqbbln7nUiPYxF";
		//String aT = "2980890963-hADyI32sy8wnNGsdrpJnxy3wX2kTC0srhnhkjqx";
		//String aTS = "P9r2di51xZ0zgPbRNciZOhT0oCThwPlK0VKd9PWcUmuuY";
		//FilterStreamExample.run(cK, cS, aT, aTS);
		
		//User_gender.gender_update(null);
		String result=User_gender.gender_search("ann one");
		System.out.print(result);
	}

}
