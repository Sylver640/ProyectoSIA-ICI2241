package com.mycompany.proyectosia.ici2241;
import java.util.*;

public class Cliente {
    //Variables de instancia
    private String rut;
    private String nombre;
    //lista de teléfonos vinculados al usuario
    private HashMap<String, Telefono> listaTelefonos;
    private int tiempoEnMeses;
    
    //Constructor que no recibe variables
    public Cliente() {
        this.listaTelefonos = new HashMap<String, Telefono>();
    }
    
    //Constructor a base de un archivo CSV
    public Cliente(CSV clientes, String linea)
    {
        this.setRut(clientes.get_csvField(linea,0));
        this.setNombre(clientes.get_csvField(linea, 1));
        //aquí tendría que ver cómo hacerlos con los planes
    }
    
    //Getter para el nombre
    public String getNombre() {
        return nombre;
    }
    
    //Getter para un telefono especificado.
    public Telefono getFono(String telefono){
        Telefono fonoTele = listaTelefonos.get(telefono);
        return fonoTele;
    }

    public String getRut() {
        return rut;
    }

    public int getTiempoEnMeses() {
        return tiempoEnMeses;
    }
    
    //Setter para el nombre del cliente
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setTiempoEnMeses(int tiempoEnMeses) {
        this.tiempoEnMeses = tiempoEnMeses;
    }
    
    public void setTiempoEnMeses(double tiempoEnMeses){
        this.tiempoEnMeses = (int) tiempoEnMeses;
    }
    
    //Método que añade un plan a la lista del cliente
    //Se requiere que el cliente tenga al menos el telefono creado
    public boolean addPlan(Plan toAdd, String telefono)
    {
        if (listaTelefonos.containsKey(telefono))
        {
            Telefono search = listaTelefonos.get(telefono);
            search.setPlan(toAdd);
            return true;
        }
        else
            return false;
    }
    
    public boolean addPlan(String telefono, Plan toAdd)
    {
        if (listaTelefonos.containsKey(telefono))
        {
            Telefono search = listaTelefonos.get(telefono);
            search.setPlan(toAdd);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //Método para buscar un plan del usuario
    public Plan getPlan(String nombre)
    {
        if (listaTelefonos.isEmpty() == true)
            return null;
        
        for (Telefono iterator: listaTelefonos.values())
        {
            if (iterator.getPlan().getNombre().equals(nombre))
                return iterator.getPlan();
        }
        
        return null;
    }
    
    public void mostrarPlanes()
    {
        try{
            if (listaTelefonos.isEmpty() == true){
                System.out.println("Este cliente no tiene ningún teléfono");
                return;
            }
        }catch(NullPointerException e){
            System.out.println("Este cliente no tiene ningún teléfono");
            return;
        }
                    
        for(Telefono iterator: listaTelefonos.values()) 
        {
            System.out.println(iterator.getPlan().getNombre());
        }
    }
}
