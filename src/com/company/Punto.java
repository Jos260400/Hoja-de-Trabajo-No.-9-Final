/**
 * Universidad del Valle de Guatemala
 * Curso: Algoritmos y Estructuras de Datos
 * Nombre: Fernando José Garavito Ovando    Carné:18071
 * Hoja de Trabajo No. 9
 * Punto.java
 */
package com.company;

//Extraido del capitulo 16 de JavaStructures
public class Punto<E> {
    int Lugar;
    public boolean Conocido;
    public E Nombre;

//Constructor
    public Punto(E Nombre2, int Lugar2) {
        this.Lugar = Lugar2;
        this.Nombre = Nombre2;
        this.Conocido = false;
    }

    public void reset(){
        Conocido = false;
    }
    public boolean isVisited(){
        return Conocido;
    }
    public int getLugar(){
        return this.Lugar;
    }
}