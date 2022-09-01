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
    
    //Método para crear un nuevo plan a partir de la opción 1 en el menú.
    public void addManualPlan() throws IOException{ 
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String dataPlan;
        String namePlan;
        long price;
        int minutes;
        int mbs;
        System.out.println("Por favor inserte el nombre de su plan: ");
        dataPlan = read.readLine();
        namePlan = dataPlan;
        
        System.out.println("Por favor inserte el precio de su plan: ");
        dataPlan = read.readLine();
        price = Integer.parseInt(dataPlan);
        
        System.out.println("Por favor inserte la cantidad de megas en su plan: ");
        dataPlan = read.readLine();
        mbs = Integer.parseInt(dataPlan);
        
        System.out.println("Por favor inserte la cantidad de minutos en su plan: ");
        dataPlan = read.readLine();
        minutes = Integer.parseInt(dataPlan);
        
        Plan agregarPlan = new Plan(namePlan,price,mbs,minutes);
        addPlan(agregarPlan);        
    }
}
