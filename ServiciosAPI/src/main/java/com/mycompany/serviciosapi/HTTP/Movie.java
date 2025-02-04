/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serviciosapi.HTTP;

/**
 *
 * @author Andrea R, Patri y Carla
 */
public class Movie {
    
  private String titulo;
    private String anyo;
    private String director;
    private String actores;
    private String sinopsis;
    private String posterUrl;

    public Movie(String titulo, String anyo, String director, String actores, String sinopsis, String posterUrl) {
        this.titulo = titulo;
        this.anyo = anyo;
        this.director = director;
        this.actores = actores;
        this.sinopsis = sinopsis;
        this.posterUrl = posterUrl;
    }

    public String getTitle() { return titulo; }
    public String getYear() { return anyo; }
    public String getDirector() { return director; }
    public String getActors() { return actores; }
    public String getPlot() { return sinopsis; }
    public String getPosterUrl() { return posterUrl; }
}
