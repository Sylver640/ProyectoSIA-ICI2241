package com.mycompany.proyectosia.ici2241;
import java.util.*;


public class Cliente {
    private String nombre;
    private long telefono;
    private Hashtable<String,Plan> listaPlanes;
    
    public Cliente() {
        listaPlanes = new Hashtable();
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
    
    public void addPlan(Plan p, String nombre)
    {
        listaPlanes.put(nombre, p);
    }
    
    public void addPlan(String nombre, Plan p)
    {
        listaPlanes.put(nombre, p);
    }
    
    public Plan getPlan(Plan p, String nombre)
    {
        return listaPlanes.get(nombre);
    }
    
    public Plan getPlan(String nombre, Plan p)
    {
        return listaPlanes.get(nombre);
    }
}
