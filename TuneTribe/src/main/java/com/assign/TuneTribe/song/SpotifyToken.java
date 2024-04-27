
package com.assign.TuneTribe.song;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mauricio
 */
public class SpotifyToken {
    
    public String accessToken;
    
    public String getAcessToken() throws IOException, JSONException{
        //Endpoints is where my Client Id and Secret are stored along with URL for the post request
        URL url = new URL(EndPoints.TOKEN); //Url stores the URL for post request
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        
        http.setRequestMethod("POST"); //post request required to get accessToken
        http.setDoOutput(true);
        http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
        
        String data = "grant_type=client_credentials&client_id=" + 
                EndPoints.CLIENT_ID + "&client_secret=" + EndPoints.CLIENT_SECRET + "";
        
        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        
        BufferedReader Lines = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String currentLine = Lines.readLine();
        JSONObject tokenObject = new JSONObject(currentLine);
        this.accessToken = tokenObject.getString("access_token");
        System.out.println(this.accessToken);
        http.disconnect();
        return this.accessToken;
    }
    
    
}
