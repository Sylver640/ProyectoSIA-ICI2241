package com.mycompany.proyectosia.ici2241;
import java.util.ArrayList;


public class Cliente {
    private String nombre;
    private long telefono;
    private int numPlanes;
    ArrayList<String> listaPlanes;
    
    public Cliente() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumPlanes() {
        return numPlanes;
    }

    public long getTelefono() {
        return telefono;
    }

    public ArrayList<String> getListaPlanes() {
        return listaPlanes;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumPlanes(int numPlanes) {
        this.numPlanes = numPlanes;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setListaPlanes(ArrayList<String> listaPlanes) {
        this.listaPlanes = listaPlanes;
    }    
}
