
package com.MyClass.apiPrototype;

import com.fasterxml.jackson.core.JsonParser;
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
    public String expiresIn;
    public String track;
    
    public String getAcessToken() throws IOException, JSONException{
        //Endpoints is where my Client Id and Secret are stored along with URL for the post request
        URL url = new URL(EndPoints.TOKEN); //Url stores the URL for post request
        
        //used to interact with webservers. 
        //Forms link between use app and a resource on the web (spotify)
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

        //this.accessToken = String.valueOf(JsonParser.parseString(String.valueOf(response)).getAsJsonObject().getAsPrimitive("access_token"));
        //this.expiresIn = String.valueOf(JsonParser.parseString(String.valueOf(response)).getAsJsonObject().getAsPrimitive("expires_in"));

        http.disconnect();
        return this.accessToken;
    }
    

    public void getRecommendation() throws IOException, JSONException{
        
        /*
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/recommendations?limit=1&seed_tracks=35xvhWIZMpsDcJxr14Ukbx")
                .method("GET", body)
                .addHeader("Authorization", "Bearer BQAqkipYLWUqMC2Xi8fMVLDsIoq8qPtJEMOr16JrvHNYKyd7jCm7ouShQfRlVcLm7shMv7242Qnq6dcx4LhYUY58K7QskXfgq3yCq3P1k0uC6JGNFtw")
                .build();
        Response response = client.newCall(request).execute();*/


        
        String limit = "1"; //append the limit
        String seedTracks = "35xvhWIZMpsDcJxr14Ukbx";
        URL url = new URL("https://api.spotify.com/v1/recommendations?limit=" +
                limit + "&seed_tracks=" + seedTracks);
        
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        
        http.setRequestMethod("GET"); //post request required to get accessToken
        http.setDoOutput(true);
        http.setRequestProperty("Authorization", "Bearer " + accessToken);
        
        //String data = "grant_type=client_credentials&client_id=" + 
                //EndPoints.CLIENT_ID + "&client_secret=" + EndPoints.CLIENT_SECRET + "";
        
        //byte[] out = data.getBytes(StandardCharsets.UTF_8);
        //OutputStream stream = http.getOutputStream();
        //stream.write(out);
        
        BufferedReader Lines = new BufferedReader(new InputStreamReader(http.getInputStream()));
        while(!Lines.readLine().isBlank())
        {
            System.out.println(Lines.readLine());
        }
        String currentLine = Lines.readLine();
        System.out.println("123 "+currentLine);
        JSONObject tokenObject = new JSONObject(currentLine);
        //this.track = tokenObject.getString("access_token");

        //this.accessToken = String.valueOf(JsonParser.parseString(String.valueOf(response)).getAsJsonObject().getAsPrimitive("access_token"));
        //this.expiresIn = String.valueOf(JsonParser.parseString(String.valueOf(response)).getAsJsonObject().getAsPrimitive("expires_in"));

        http.disconnect();
        
    }
}
