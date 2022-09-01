package com.mycompany.proyectosia.ici2241;
import java.util.*;

public class Cliente {
    //Variables de instancia
    private String rut;
    private String nombre;
    private long telefono;
    //Podrían haber más variables
    private ArrayList<Plan> listaPlanes;
    
    //Constructor que no recibe variables
    public Cliente() {
        listaPlanes = new ArrayList();
    }
    
    public Cliente(CSV clientes, String linea)
    {
        this.setNombre(clientes.get_csvField(linea, 0));
        this.setTelefono(Integer.parseInt(clientes.get_csvField(linea, 1)));
        //aquí tendría que ver cómo hacerlos con los planes
    }
    

    //Getter para el nombre
    public String getNombre() {
        return nombre;
    }
    
    //Getter para el telefono del cliente
    public long getTelefono() {
        return telefono;
    }

    public String getRut() {
        return rut;
    }
    
    //Setter para el nombre del cliente
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }
    
    public void setTelefono(double telefono){
        this.telefono = (long) telefono;
    }

    public void setRut(String rut) {
        this.rut = rut;
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
