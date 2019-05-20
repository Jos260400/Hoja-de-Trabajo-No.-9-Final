/**
 * Universidad del Valle de Guatemala
 * Curso: Algoritmos y Estructuras de Datos
 * Nombre: Fernando José Garavito Ovando    Carné:18071
 * Hoja de Trabajo No. 9
 * Grafo.java
 */
package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementación del grafo: https://algorithms.tutorialhorizon.com/graph-implementation-adjacency-matrix-set-3/
 * Extraido del capitulo 16 de JavaStructures
 */

public class Grafo<V> {

    public int Medida;
    Double[][] Valores;
    public Map<V,Punto<V>> vert;
    public List<Integer> freeList;
    public List<String> Lista;

//Constructor
    public Grafo(int Medida) {

        this.Medida = Medida;
        this.vert = new HashMap<>(Medida);
        this.freeList = new ArrayList<>();
        this.Valores = new Double[Medida][Medida];
        this.Lista = new ArrayList<>();

        for (int row = Medida-1; row >= 0; row--) { freeList.add(row); }
        for(int i = 0; i < Valores.length; i++) {
            for(int j = 0; j < Valores.length; j++) {
                Valores[i][j] = Double.POSITIVE_INFINITY;
                if(i==j){
                    Valores[i][j] = 0.0;
                }
            }
        }
    }

    public String addEdge(V vrt1, V vrt2, double distancia) {
        if(vert.get(vrt1) == null || vert.get(vrt2) == null) {
            return "No se puede realizar la union";
        }else {
            Valores[vert.get(vrt1).getLugar()][vert.get(vrt2).getLugar()] = distancia;
            return "Union realizada";
        }
    }

    public String removeEdge(V vrt1, V vrt2){
        if(vert.get(vrt1) == null || vert.get(vrt1) == null) {
            return "No se puede realizar la Opcion";
        }else {
            Valores[vert.get(vrt1).getLugar()][vert.get(vrt2).getLugar()] = Double.POSITIVE_INFINITY;
            return "Se ha quitado el camino";
        }
    }


    public void add(V nombre) {
        if (!vert.containsKey(nombre)) {
            int pos = freeList.remove(0);
            vert.put(nombre, new Punto<>(nombre,pos));
            Lista.add(nombre.toString());
        }
    }

    public void Floyd()
    {
        int n=Valores.length;
        Double D[][]=Valores;

        String enlaces[][]=new String [n][n];
        String[][] aux_enlaces=new String[Valores.length][Valores.length];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                enlaces[i][j]="";
                aux_enlaces[i][j]="";
            }
        }
        String enl_rec="";
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Double aux=D[i][j];
                    Double aux2=D[i][k];
                    Double aux3=D[k][j];
                    Double aux4=aux2+aux3;
                    Double res=Math.min(aux,aux4);
                    if(aux!=aux4)
                    {
                        if(res==aux4)
                        {
                            enl_rec="";
                            aux_enlaces[i][j]=k+"";
                            enlaces[i][j]=enlaces(i,k,aux_enlaces,enl_rec)+(k+1);
                        }
                    }
                    D[i][j]=(Double) res;
                }
            }
        }
        Valores=D;
    }


    public String enlaces(int i,int k,String[][] aux_enlaces,String enl_rec)
    {
        if(aux_enlaces[i][k].equals(""))
        {
            return "";
        }
        else
        {
            enl_rec+=enlaces(i,Integer.parseInt(aux_enlaces[i][k].toString()),aux_enlaces,enl_rec)+(Integer.parseInt(aux_enlaces[i][k].toString())+1)+" , ";
            return enl_rec;
        }
    }





    public String centro(){

        int[] Pila = new int[Valores.length];
        for(int i = 0; i < Valores.length; i++) {
            Double max = Valores[i][0];
            int pos = 0;
            for(int j = 0; j < Valores.length; j++) {
                if (i!=j){
                    Double temp = Valores[i][j];
                    if(temp > max) {
                        max = temp;
                        pos = j;
                    }
                }
            }
            Pila[i] = pos;
        }

        int minPos = 0;
        Number min = Pila[0];
        for(int x = 0; x < Pila.length; x++) {
            Number temp = Pila[x];
            if(temp.doubleValue() < min.doubleValue()) {
                min = temp;
                minPos = x;
            }
        }
        return Lista.get(Lista.size()-1-minPos);
    }

    public Double minDist(V nom1, V nom2) {
        if (vert.get(nom1) == null || vert.get(nom2) == null){
            return null;
        }
        return this.Valores[vert.get(nom1).getLugar()][vert.get(nom2).getLugar()];
    }

    public void Mostrar() {
    }
}