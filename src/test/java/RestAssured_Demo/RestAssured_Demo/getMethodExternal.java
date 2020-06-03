package RestAssured_Demo.RestAssured_Demo;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class getMethodExternal {

	public static void main(String[] args) {
		RestAssured.baseURI ="http://jsonplaceholder.typicode.com/users";
		Response res = RestAssured.given().get();
		System.out.println(res.asString());
		System.out.println(res.statusCode());
        List<String> liUsername = res.jsonPath().getList("username");
        for(String uname:liUsername)
        {
        	System.out.println(uname);
       }
        System.out.println(res.jsonPath().getList("username"));
        System.out.println(res.jsonPath().getString("company.name[4]"));
       
       List<Map<String , String>> mapCompany = res.jsonPath().getList("company");
       System.out.println(mapCompany);
       for(int i=0;i<mapCompany.size();i++)
       {
    	   System.out.println(mapCompany.get(i).get("name")+"\t \\"+mapCompany.get(i).get("catchPhrase")+"\t \\"
    			   +mapCompany.get(i).get("bs"));
       }
       
	}

}
