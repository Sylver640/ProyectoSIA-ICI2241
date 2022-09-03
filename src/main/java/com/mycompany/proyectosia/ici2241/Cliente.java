package com.mycompany.proyectosia.ici2241;
import java.util.*;

public class Cliente {
    //Variables de instancia
    private String rut;
    private String nombre;
    //clase telefono con 3 atributos, uno para el numero, otro para el plan, y otro para el dispositivo
    //Podrían haber más variables
    private HashMap<String, Telefono> listaTelefonos;
    
    //Constructor que no recibe variables
    public Cliente() {
        this.listaTelefonos = new HashMap<String, Telefono>();
    }
    
    public Cliente(CSV clientes, String linea)
    {
        this.setNombre(clientes.get_csvField(linea, 0));
        this.setRut(clientes.get_csvField(linea,1));
        //aquí tendría que ver cómo hacerlos con los planes
    }
    
    //Getter para el nombre
    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }
    
    //Setter para el nombre del cliente
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRut(String rut) {
        this.rut = rut;
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
        if (listaTelefonos.isEmpty() == true)
        {
            System.out.println("Este cliente no tiene planes contratados");
            return;
        }
                    
        for(Telefono iterator: listaTelefonos.values()) 
        {
            System.out.println(iterator.getPlan().getNombre());
        }
    }
}
