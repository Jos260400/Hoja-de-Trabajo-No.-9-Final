/**
 * Universidad del Valle de Guatemala
 * Curso: Algoritmos y Estructuras de Datos
 * Nombre: Fernando José Garavito Ovando    Carné:18071
 * Hoja de Trabajo No. 9
 * Main.java
 */
package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.lang.*;

public class Main {
    private static Scanner input;
    public static void main(String[] args) throws IOException {
        input = new Scanner(System.in);

        String Archivo = "guategrafo.txt";

        int Medida = Tamaño(Archivo);

        Grafo<String> El_Grafo = new Grafo<>(Medida);
        El_Grafo = LeerElDocumento(Archivo ,El_Grafo);
        El_Grafo.Floyd();
        El_Grafo.Mostrar();

        boolean Seguir = true;

        while (Seguir){
            System.out.println(""
                    +"\n"
                    + "1. Distancia entre las ciudades: \n"
                    + "2. Ciudad en el centro del Grafo: \n"
                    + "3. Cambiar el Grafo: \n"
                    + "4. Salir: \n"
                    + "\n"
                    + "Elija una opcion: ");
            String Opcion = (input.nextLine());

            switch (Opcion){
                case "1":
                    System.out.println("Introduzca la primera ciudad: ");
                    String Primera_Ciudad = (input.nextLine());

                    if(El_Grafo.Lista.contains(Primera_Ciudad)){
                        System.out.println("Introduzca la segunda ciudad: ");
                        String Segunda_Ciudad = (input.nextLine());

                        if(El_Grafo.Lista.contains(Segunda_Ciudad)){
                            Double Recorrido = El_Grafo.minDist(Primera_Ciudad,Segunda_Ciudad);

                            if (Recorrido != Double.POSITIVE_INFINITY){
                                System.out.println("\nDesde " +Primera_Ciudad+" a "+Segunda_Ciudad+" la distancia es de "+Recorrido+ "KM");

                            }else{
                                System.out.println("Estas ciudades no estan conectadas");
                            }

                        }else {
                            System.out.println("Esa ciudad no se encuentra disponible");
                        }

                    }else {
                        System.out.println("Esa ciudad no se encuentra disponible");
                    }

                    break;

                case "2":
                    System.out.println("La ciudad que es el centro del grafo es: "+El_Grafo.centro());
                    break;

                case "3":
                    System.out.println(""
                            +"\n"
                            + "Elija una opcion:\n"
                            + " 1. Agregar una comunicacion\n"
                            + " 2. Eliminar una comunicacion\n"
                            + "Opcion:");
                    String con = (input.nextLine());

                    switch (con){

                        case "1":
                            System.out.println("Introduzca la primera ciudad:");
                            String Buscar_La_Primera_Ciudad = (input.nextLine());

                            if(El_Grafo.Lista.contains(Buscar_La_Primera_Ciudad)){

                                System.out.println("Introduzca la segunda ciudad:");
                                String Buscar_La_Segunda_Ciudad = (input.nextLine());

                                if(El_Grafo.Lista.contains(Buscar_La_Segunda_Ciudad)){

                                    System.out.println("Introduzca la distancia entre ambas ciudades:");
                                    double dis = (input.nextDouble());

                                    if(dis>0){
                                        El_Grafo.addEdge(Buscar_La_Primera_Ciudad,Buscar_La_Segunda_Ciudad,dis);

                                    }else{
                                        System.out.println("Solo se pueden ingresar valores positivos");
                                    }

                                }else{
                                    System.out.println("Ciudad incorrecta");
                                }

                            }else{
                                System.out.println("Ciudad incorrecta");
                            }

                            El_Grafo.Floyd();
                            El_Grafo.Mostrar();
                            break;

                        case "2":

                            System.out.println("Introduzca la primera ciudad:");
                            String Buscar1 = (input.nextLine());
                            if(El_Grafo.Lista.contains(Buscar1)){

                                System.out.println("Introduzca la segunda ciudad:");
                                String Buscar2 = (input.nextLine());
                                if(El_Grafo.Lista.contains(Buscar2)){

                                    El_Grafo.removeEdge(Buscar1,Buscar2);
                                    System.out.println("Se ha eliminado la conexion entre "+Buscar1+" y "+Buscar2);

                                }else{
                                    System.out.println("Ciudad incorrecta");
                                }

                            }else{
                                System.out.println("Ciudad incorrecta");
                            }

                            El_Grafo.Floyd();
                            El_Grafo.Mostrar();
                            break;
                        default:
                            System.out.println("La opcion no es valida");
                            break;
                    }
                    break;

                case "4":

                    Seguir = false;
                    break;
                default:
                    System.out.println("La opcion no es valida");
                    break;
            }
        }
    }


    /**
     * Extraido de: https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
     * Capitulo 16 de JavaStructures
     */
    private static Grafo<String> LeerElDocumento(String nombre, Grafo Grafo2) throws IOException {

        File file = new File(nombre);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while((line = br.readLine()) != null){
            String[] parts = line.split(" ");
            String Primero = parts[0];

            if(!Grafo2.Lista.contains(Primero)){
                Grafo2.add(Primero);
            }

            String Segundo = parts[1];
            if(!Grafo2.Lista.contains(Segundo)){
                Grafo2.add(Segundo);
            }

            int Distancia = Integer.parseInt(parts[2]);
            Grafo2.addEdge(Primero,Segundo,Distancia);
        }
        return Grafo2;
    }
    /**
     * Extraido de: https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
     * Capitulo 16 de JavaStructures
     */

    private static int Tamaño(String nombre) throws IOException {

        File file = new File(nombre);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        ArrayList<String> palabras = new ArrayList<>();
        while((line = br.readLine()) != null){

            String[] parts = line.split(" ");
            String Primero = parts[0];
            if(!palabras.contains(Primero)){
                palabras.add(Primero);
            }
            String Segundo = parts[1];
            if(!palabras.contains(Segundo)){
                palabras.add(Segundo);
            }
        }
        return palabras.size();
    }

}