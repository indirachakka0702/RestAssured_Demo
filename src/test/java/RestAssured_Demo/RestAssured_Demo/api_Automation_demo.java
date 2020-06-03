package RestAssured_Demo.RestAssured_Demo;

import java.util.HashMap;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class api_Automation_demo {

	public static void main(String[] args) {
		String sHostName = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
		String URI = "/login";
		String URL = sHostName + URI;
		
		RestAssured.baseURI = URL;
		
		Response res = RestAssured.given().body("{\"username\":\"Indira@ta.com\",\"password\":\"Indira\"}").
		when().contentType("application/json").post();
		// if you don't want to add when that is also fine

		//Given --> <credentials> prerequisite
		//When --> i say login --> what need to be done
		// Then --> login should happen --> what needs to be validated after the action
		
		System.out.println(res.asString());
		System.out.println(res.statusCode());
		// parsing the json response of API
		String sToken = res.jsonPath().getString("token").toString();
		System.out.println(sToken);
		sToken = sToken.replaceAll("\\[", "").replaceAll("\\]", "");
		String sUserID = res.jsonPath().getString("userid").toString();
		sUserID = sUserID.replaceAll("\\[","").replaceAll("\\]", "");
		System.out.println(sToken);
		
		System.out.println("******************");
		
		URI ="/getdata";
		URL = sHostName + URI;
		System.out.println();
		HashMap<String, String> mapToken = new HashMap<String , String>();
		mapToken.put("token",sToken);
	
		RestAssured.baseURI = URL;
		Response res1 = RestAssured.given().contentType("application/json").headers(mapToken).get();
		
		/*System.out.println(res1.asString());
		System.out.println(res1.statusCode());
		System.out.println(res1.jsonPath().getString("data"));
		System.out.println(res1.jsonPath().getList("data[0].salary"));
		
		List<String> liSalary = res1.jsonPath().getList("data[0].salary");
		System.out.println(liSalary.size());*/
	    System.out.println("******************");	
       
	 
        System.out.println("*****************");
        
        URI ="/addData";
        URL = sHostName + URI;
        RestAssured.baseURI = URL;
        String sAccountNumber ="20113341";
        Response res3 = RestAssured.given().body("{\"accountno\":\""+sAccountNumber+"\",\"departmentno\":\"1\",\"salary\":\"30000\",\"pincode\":\"124567\"}").
        		        contentType("application/json").headers(mapToken).post();
        System.out.println(res3.asString());
        System.out.println(res3.statusCode());
        
        System.out.println("******************");
        
        System.out.println(res1.jsonPath().getList("data[0].accountno"));
        int setIndex =0;
        List<Integer> liAccount = res1.jsonPath().getList("data[0].accountno");
        for(int i=0;i<liAccount.size();i++)
        {
        	System.out.println(liAccount.get(i));
        	if(String.valueOf(liAccount.get(i)).equals(sAccountNumber))
        	{
        	  setIndex = i;
        	  break;
        	}
        }
		
        String id = res1.jsonPath().getString("data[0].id["+setIndex+"]");
        System.out.println(id);
        
        URI ="/deleteData";
        URL =sHostName + URI;
        RestAssured.baseURI = URL;
        Response res2 = RestAssured.given().body("{\"id\":\""+id+"\",\"userid\":\""+sUserID+"\"}").
        		contentType("application/json").headers(mapToken).delete();
        System.out.println(res2.asString());
        System.out.println(res2.statusCode());
        
        
       /* URI = "/updateData";
        URL = sHostName + URI;
        sAccountNumber = String.valueOf((Integer.parseInt(sAccountNumber))+1);
        RestAssured.baseURI = URL;
        Response res4 = RestAssured.given().
        		body("{\"accountno\":\""+sAccountNumber+"\",\"departmentno\":0,\"salary\":20000,\"pincode\":124555,\"userid\":\""+sUserID+"\",\"id\":\""+id+"\"}").
        		contentType("application/json").headers(mapToken).put();
        System.out.println(res4.asString());
        System.out.println(res4.statusCode());
        */
        URI ="/getdata";
        URL = sHostName + URI;
        RestAssured.baseURI = URL;
        res1 = RestAssured.given().contentType("application/json").headers(mapToken).get();
        System.out.println(res1.asString());
        
        
        URI ="/logout";
        URL = sHostName + URI;
        RestAssured.baseURI = URL;
        Response resLogout =RestAssured.given().body("{\"token\":null}").when().contentType("application/json")
        		.headers(mapToken).post();
        System.out.println(resLogout.asString());
        System.out.println(resLogout.statusCode());
        
	}

}
