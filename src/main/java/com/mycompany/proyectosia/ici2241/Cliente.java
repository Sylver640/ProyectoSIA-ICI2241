package com.mycompany.proyectosia.ici2241;
import java.util.*;


public class Cliente {
    private String nombre;
    private long telefono;
    //private int numPlanes;
    ArrayList<String> listaPlanes;
    
    public Cliente() {
        listaPlanes = new ArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public long getTelefono() {
        return telefono;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
}
