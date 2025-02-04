/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serviciosapi.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Andrea R, Patri, Carla
 */
public class MovieService {
    
   private static final String API_KEY = "TU_API_KEY"; // Reemplaza con tu API Key

    public static Movie getMovie(String title) {
        try {
            String urlString = "http://www.omdbapi.com/?t=" + title.replace(" ", "+") + "&apikey=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(response.toString());
            if (json.has("Response") && json.getString("Response").equals("False")) {
                return null;
            }

            return new Movie(
                json.getString("Titulo"),
                json.getString("Anyo"),
                json.getString("Director"),
                json.getString("Actores"),
                json.getString("Sinopsis"),
                json.getString("Poster")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
