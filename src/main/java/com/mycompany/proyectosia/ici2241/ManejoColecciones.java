package com.mycompany.proyectosia.ici2241;
import java.util.*;
import java.io.*;

public class ManejoColecciones 
{
    //Mapa que contiene todos los planes disponibles en el mercado
    private HashMap <String,Plan> planesMap = new HashMap<String,Plan>();
    private HashMap <String,Prepago> prepagoMap = new HashMap<String,Prepago>();
    //Mapa que contiene los clientes
    private HashMap <String,Cliente> clientesMap = new HashMap<String,Cliente>();
    private HashMap <String,Dispositivo> celuMap = new HashMap<String,Dispositivo>();
    
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
    
    public void addDispositivo(Dispositivo toAdd){
        String nombre = toAdd.getNombre();
        
        if (celuMap.isEmpty() == true){
            celuMap.put(nombre, toAdd);
            return;
        }
        
        if (celuMap.containsKey(nombre) == false){
            celuMap.put(nombre, toAdd);
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
            System.out.println(linea);
            linea = null;
            linea = clientesCSV.nextLine();
            if (linea == null)
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
    
    public void importDispositivos() throws FileNotFoundException, IOException {
        CSV dispositivosCSV = new CSV("dispositivos");
        String linea = dispositivosCSV.firstLine();
        Dispositivo toAdd = new Dispositivo(dispositivosCSV, linea);
        
        this.addDispositivo(toAdd);
        
        while (true){
            linea = null;
            linea = dispositivosCSV.nextLine();
            if (linea == null)
                break;
            
            toAdd = new Dispositivo(dispositivosCSV, linea);
            this.addDispositivo(toAdd);
        }
    }
    
    //Método para mostrar los planes de un cliente en específico.
    public void showClientPlans() throws IOException{
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String clientName;
        System.out.println("Ingrese el RUT del cliente: ");
        clientName = read.readLine();
        Cliente searched = clientesMap.get(clientName);
        
        if(searched != null){
            searched.mostrarPlanes();
        }else{
            System.out.println("Lo sentimos, el cliente buscado no existe");
            return;
        }
    }
    
    public void mostrarDispositivos() throws IOException{
        if (celuMap.isEmpty()){
            System.out.println("No existen dispositivos para mostrar");
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese una opción:");
        System.out.println("(1) Ver todos los dispositivos disponibles");
        System.out.println("(2) Filtrar por marca");
        String opt = reader.readLine();
        
        if (opt.equals("1")){
            System.out.println("----------------------");
            for (Dispositivo iterator: celuMap.values()){
                System.out.println("Nombre: "+ iterator.getNombre());
                System.out.println("Marca: "+ iterator.getMarca());
                System.out.println("Precio: $" + iterator.getPrecio());
                System.out.println("Memoria RAM: " + iterator.getRam() + " GB");
                System.out.println("Espacio: " + iterator.getMemoria() + " GB");
                System.out.println("Pantalla de " + iterator.getPulgadas() + " pulgadas");
                System.out.println("Soporta conexión de hasta " + iterator.getConexion());
                System.out.println("\n");
            }
            System.out.println("----------------------");
        }
        
        if (opt.equals("2")){
            System.out.println("Ingrese qué marca de celular desea ver: ");
            String filtrarMarca = reader.readLine();
            for (Dispositivo iterator: celuMap.values()){
                if (iterator.getMarca().equals(filtrarMarca)){
                    System.out.println("Nombre: "+ iterator.getNombre());
                    System.out.println("Precio: $" + iterator.getPrecio());
                    System.out.println("Memoria RAM: " + iterator.getRam() + " GB");
                    System.out.println("Espacio: " + iterator.getMemoria() + " GB");
                    System.out.println("Pantalla de " + iterator.getPulgadas() + " pulgadas");
                    System.out.println("Soporta conexión de hasta " + iterator.getConexion());
                    System.out.println("\n");
                }
            }
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
        
        Plan newPlan = new Plan(namePlan,"Postpago",price,mbs,minutes);
        addNewPlan(newPlan);        
    }
}
