import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

public class Connection {

	private final String USER_AGENT = "Chrome";
        
        List<String> currency = new ArrayList(); 
        List<String> cuntry = new ArrayList();
        JSONObject response = new JSONObject();
        
	public static void main(String[] args) throws Exception {
		
	}
        
	// HTTP GET request
	public JSONObject sendGet() throws Exception {
           
            String url = "https://openexchangerates.org/api/currencies.json";
            
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            //System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
	        new InputStreamReader(con.getInputStream()));
            String inputLine;
          
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith("{") || inputLine.startsWith("}"))
                    continue;
	
               String[] arrOfStr = inputLine.trim().replace("\"", "").split("[:,]");
                       
               currency.add(arrOfStr[0]);
               cuntry.add(arrOfStr[1]);
                     
            }
            in.close();
        
            
            String[] curr = currency.toArray(new String[0]);
            String[] cunt = cuntry.toArray(new String[0]);
          
            for(int i=0;i<curr.length;i++)
                response.put(curr[i], cunt[i]);
            
        return response;
    }
        
        
	
}
