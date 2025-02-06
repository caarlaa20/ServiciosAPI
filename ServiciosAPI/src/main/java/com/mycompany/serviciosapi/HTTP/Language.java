/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serviciosapi.HTTP;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alumno
 */
public class Language {
    
    private static final Map<String, String> languages = new HashMap<>();

    static {
        languages.put("Español", "es");
        languages.put("Inglés", "en");
        languages.put("Francés", "fr");
        languages.put("Alemán", "de");
        languages.put("Italiano", "it");
        languages.put("Portugués", "pt");
    }

    public static Map<String, String> getLanguages() {
        return languages;
    }

}
