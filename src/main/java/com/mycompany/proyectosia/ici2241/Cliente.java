package com.mycompany.proyectosia.ici2241;
import java.util.*;


public class Cliente {
    //Variables de instancia
    private String nombre;
    private long telefono;
    private ArrayList<Plan> listaPlanes;
    
    //Constructor que no recibe variables
    public Cliente() {
        listaPlanes = new ArrayList();
    }

    //Getter para el nombre
    public String getNombre() {
        return nombre;
    }
    
    //Getter para el telefono del cliente
    public long getTelefono() {
        return telefono;
    }
    
    //Setter para el nombre del cliente
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    
    public void setTelefono(int telefono){
        this.telefono = telefono;
    }
    
    //Método que añade un plan a la lista del cliente
    public boolean addPlan(Plan p)
    {
        if (listaPlanes.contains(p) == false) //Como no está en la lista, tan solo se agrega
        {
            listaPlanes.add(p);
            return true;
        }
        else
            return false;
    }
    
    //Método para buscar un plan del usuario
    public Plan getPlan(String nombre)
    {
        if (listaPlanes.isEmpty() == true)
            return null;
        
        int i = 0;
        while (i < listaPlanes.size())
        {
            Plan planToGet = listaPlanes.get(i);
            if (planToGet.getNombre().equals(nombre))
            {
                return planToGet;
            }
            else
                i++;
        }
        
        return null;
    }
}
