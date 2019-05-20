/**
 * Universidad del Valle de Guatemala
 * Curso: Algoritmos y Estructuras de Datos
 * Nombre: Fernando José Garavito Ovando    Carné:18071
 * Hoja de Trabajo No. 9
 * Arco.java
 */
package com.company;

//Extraido del capitulo 16 de JavaStructures
public class Arco<V> {

    private double La_Distancia;
    public V LOL1;
    public V LOL2;
    public boolean Longitud;
    public boolean Conocido;
    public double Distancia;


//Constructor
    public Arco(V LOL1, V LOL2, double La_Distancia, boolean Longitud) {

        this.LOL1 = LOL1;
        this.LOL2 = LOL2;
        this.Longitud = Longitud;
        this.Conocido = false;
        this.Distancia = La_Distancia;
    }

    public V here() {
        return this.LOL1;
    }
    public V there(){
        return this.LOL2;
    }
    public boolean isVisited(){
        return Conocido;
    }
    public void reset(){
        Conocido = false;
    }
    public double label(){
        return this.Distancia;
    }

}