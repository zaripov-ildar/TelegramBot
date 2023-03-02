package ru.starstreet.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class JokeGenerator {
    public String generate() {
        URL obj = null;
        try {
            obj = new URL("https://geek-jokes.sameerkumar.website/api?format=json");

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String joke = response.toString();
            return joke.substring(9, joke.length() - 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
