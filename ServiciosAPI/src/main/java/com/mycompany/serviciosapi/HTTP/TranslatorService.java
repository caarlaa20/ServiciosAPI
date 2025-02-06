/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serviciosapi.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;

/**
 *
 * @author alumno
 */
public class TranslatorService {
    
private static final String API_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=";

    public static String translate(String text, String sourceLang, String targetLang) {
        try {
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
            String urlString = API_URL + sourceLang + "&tl=" + targetLang + "&dt=t&q=" + encodedText;
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

            // Procesar la respuesta JSON
            JSONArray jsonArray = new JSONArray(response.toString());
            return jsonArray.getJSONArray(0).getJSONArray(0).getString(0);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error en la traducción.";
        }
    }
}
