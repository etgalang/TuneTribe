package com.MyClass.apiPrototype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.MyClass.apiPrototype.SpotifyToken;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONException;

@SpringBootApplication
public class ApiPrototypeApplication {
    
    /*
        GET SPOTIFY KEY HERE
    */

	public static void main(String[] args) throws IOException, JSONException {
		//SpringApplication.run(ApiPrototypeApplication.class, args);
                SpotifyToken token = new SpotifyToken();
                String key = token.getAcessToken();
                //Search song = new Search();
                

                
	}

}
