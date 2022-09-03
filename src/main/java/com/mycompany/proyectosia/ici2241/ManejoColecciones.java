package com.mycompany.proyectosia.ici2241;
import java.util.*;
import java.io.*;
/*
 @author rodri
 */
public class ManejoColecciones 
{
    //Mapa que contiene todos los planes disponibles en el mercado
    private HashMap <String,Plan> planesMap = new HashMap<String,Plan>();
    //Mapa que contiene los clientes
    private HashMap <String,Cliente> clientesMap = new HashMap<String,Cliente>();
    
    //Constructor
    public ManejoColecciones()
    {
    
    }
    
    //Método para añadir un nuevo plan al mapa
    public void addNewPlan(Plan toAdd)
    {
        String nombre = toAdd.getNombre();
        
        if (planesMap.isEmpty() == true)
        {
            planesMap.put(nombre, toAdd);
            return;
        }
        
        if (planesMap.containsKey(nombre) == false)
        {
            planesMap.put(nombre, toAdd);
        }
    }
    
    //Método para añadir un plan existente al telefono de un cliente.
    public void addClientPlan() throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String Ingr;
        System.out.println("Por favor, inserte el nombre del plan a agregar al cliente: ");
        Ingr = read.readLine();
        
        if(planesMap.containsKey(Ingr) == true){
            
            Plan toAdd = planesMap.get(Ingr);
            System.out.println("Por favor, inserte el RUT del cliente: ");
            Ingr = read.readLine();
            
            if(clientesMap.containsKey(Ingr)==true){
                Cliente searched = clientesMap.get(Ingr);
                System.out.println("Por favor, inserte el teléfono del cliente en donde el plan tomará acción: ");
                Ingr = read.readLine();
                
                if(searched.getFono(Ingr) != null){
                    searched.addPlan(toAdd, Ingr);
                    
                }else{
                    System.out.println("Lo sentimos, el telefono buscado no existe");
                    return;
                }
                
            }else{
                System.out.println("Lo sentimos, el cliente buscado no existe");
                return;
            }
            
        }else{
            System.out.println("Lo sentimos, el plan buscado no existe");
            return;
        }
        
    }
    
    public void addCliente(Cliente toAdd)
    {
        String rut = toAdd.getRut();
        
        if (clientesMap.isEmpty() == true)
        {
            clientesMap.put(rut, toAdd);
            return;
        }
        
        if (clientesMap.containsKey(rut) == false)
        {
            clientesMap.put(rut, toAdd);
        }
    }
    
    //Método para la lectura del archivo clientes.csv ubicado en la raíz del programa
    public void importClientes() throws FileNotFoundException, IOException
    {
        CSV clientesCSV = new CSV("clientes");
        String linea = clientesCSV.firstLine();
        Cliente toAdd = new Cliente(clientesCSV, linea);
        this.addCliente(toAdd);
        
        while (true)
        {
            linea = null;
            linea = clientesCSV.nextLine();
            if (linea.equals("") || linea == null)
            {
                break;
            }
            
            toAdd = new Cliente(clientesCSV, linea);
            this.addCliente(toAdd);
        }
    }
    
    //Método para la lectura del archivo planes.csv ubicado en la carpeta raiz del programa
    public void importPlanes() throws FileNotFoundException, IOException
    {
        CSV planesCSV = new CSV("planes");
        String linea = planesCSV.firstLine();
        Plan toAdd = new Plan(planesCSV, linea);
        this.addNewPlan(toAdd);
        
        while (true)
        {
            linea = null;
            linea = planesCSV.nextLine();
            if (linea.equals("") || linea == null)
            {
                break;
            }
            
            toAdd = new Plan(planesCSV, linea);
            this.addNewPlan(toAdd);
        }
    }
    
    //Método para mostrar los planes de un cliente en específico.
    public void showClientPlans() throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String clientName;
        System.out.println("Por favor, inserte el nombre del plan a agregar al cliente: ");
        clientName = read.readLine();
        Cliente searched = clientesMap.get(clientName);
        
        if(searched != null){
            searched.mostrarPlanes();
        }else{
            System.out.println("Lo sentimos, el cliente buscado no existe");
            return;
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
        
        Plan newPlan = new Plan(namePlan,price,mbs,minutes);
        addNewPlan(newPlan);        
    }
}
