package com.mycompany.proyectosia.ici2241;
import java.util.*;
import java.io.*;
/*
 @author rodri
 */
public class ManejoColecciones 
{
    //Mapa que contiene todos los planes disponibles en el mercado
    private Hashtable <String,Plan> planesMap = new Hashtable();
    
    //Constructor
    public ManejoColecciones()
    {
    
    }
    
    //Se obtiene todo el mapa de planes
    public Hashtable getPlanes()
    {
        return planesMap;
    }
    
    //Método para añadir un nuevo plan al mapa
    public void addPlan(Plan toAdd)
    {
        String nombre = toAdd.getNombre();
        
        if (planesMap.isEmpty() == true)
        {
            planesMap.put(nombre, toAdd);
            return;
        }
        
        if (planesMap.contains(nombre) == false)
        {
            planesMap.put(nombre, toAdd);
        }
    }
    
    //Método para la lectura del archivo planes.csv ubicado en la carpeta raiz del programa
    public void importPlanes() throws FileNotFoundException, IOException
    {
        CSV planesCSV = new CSV("planes");
        String linea = planesCSV.firstLine();
        Plan toAdd = new Plan(planesCSV, linea);
        this.addPlan(toAdd);
        
        while (true)
        {
            linea = null;
            linea = planesCSV.nextLine();
            if (linea.equals("") || linea == null)
            {
                break;
            }
            
            toAdd = new Plan(planesCSV, linea);
            this.addPlan(toAdd);
        }
    }
}
