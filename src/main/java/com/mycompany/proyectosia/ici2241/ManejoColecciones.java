package com.mycompany.proyectosia.ici2241;
import java.util.*;
import java.io.*;

public class ManejoColecciones 
{
    //Mapa que contiene todos los planes disponibles en el mercado
    private HashMap <String,Plan> planesMap = new HashMap<String,Plan>();
    //Mapa que contiene cada número único que es prepago
    private HashMap <String,Prepago> prepagoMap = new HashMap<String,Prepago>();
    //Mapa que contiene los clientes
    private HashMap <String,Cliente> clientesMap = new HashMap<String,Cliente>();
    //Mapa con todos los dispositivos disponibles
    private HashMap <String,Dispositivo> celuMap = new HashMap<String,Dispositivo>();
    
    //Constructor
    public ManejoColecciones()
    {
    
    }
    
    public Cliente getCliente(String rut){
        if (clientesMap.containsKey(rut)){
            return clientesMap.get(rut);
        }
        return null;
    }
    
    public Plan getPlan(String nombre){
        if (planesMap.containsKey(nombre)){
            return planesMap.get(nombre);
        }
        return null;
    }
    
    public Dispositivo getDevice(String nombre){
        if (celuMap.containsKey(nombre)){
            return celuMap.get(nombre);
        }
        return null;
    }
    
    public Prepago getPrepago(String telefono){
        if (prepagoMap.containsKey(telefono)){
            return prepagoMap.get(telefono);
        }
        return null;
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
    
    public void addPrepago (Prepago toAdd){
        String numeroVinculado = toAdd.getNombre();
        
        if (prepagoMap.isEmpty() == true){
            prepagoMap.put(numeroVinculado, toAdd);
            return;
        }
        
        if (prepagoMap.containsKey(numeroVinculado) == false){
            prepagoMap.put(numeroVinculado, toAdd);
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
    
    //Método para cargar los teléfonos de un cliente por medio del CSV
    public void cargarTelefonosCliente(String telefonos, String tarifas, String dispositivos, Cliente c){
        //Se divide cada string en tokens para 
        StringTokenizer fonoToken = new StringTokenizer (telefonos,",");
        StringTokenizer tarifaToken = new StringTokenizer (tarifas,",");
        StringTokenizer celuToken = new StringTokenizer (dispositivos,",");
        
        while (fonoToken.hasMoreTokens()){
            String actualNum = fonoToken.nextToken();
            String actualTarifa = tarifaToken.nextToken();
            System.out.println(actualTarifa);
            String actualDevice = celuToken.nextToken();
            
            Telefono toAdd = new Telefono();
            
            toAdd.setNumero(actualNum);
            if (actualTarifa.equals("Prepago")){
                System.out.println("se agrega prepago");
                toAdd.setPrepago(this.getPrepago(actualNum));
            } else{
                toAdd.setPlan(this.getPlan(actualTarifa));
            }
            
            toAdd.setDevice(this.getDevice(actualDevice));
            
            c.addTelefono(toAdd, actualNum);
        }
    }
    
    //Método para la lectura del archivo clientes.csv ubicado en la raíz del programa
    public void importClientes() throws FileNotFoundException, IOException
    {
        CSV clientesCSV = new CSV("clientes");
        String linea = clientesCSV.firstLine();
        
        Cliente toAdd = new Cliente(clientesCSV, linea);
        this.cargarTelefonosCliente(clientesCSV.get_csvField(linea, 3).substring(1, clientesCSV.get_csvField(linea, 3).length()-1), 
                                    clientesCSV.get_csvField(linea, 4).substring(1, clientesCSV.get_csvField(linea, 4).length()-1), 
                                    clientesCSV.get_csvField(linea, 5).substring(1, clientesCSV.get_csvField(linea, 5).length()-1),
                                    toAdd);
        
        this.addCliente(toAdd);
        
        while (true)
        {
            linea = null;
            linea = clientesCSV.nextLine();
            if (linea == null)
            {
                break;
            }
            
            toAdd = new Cliente(clientesCSV, linea);
            this.cargarTelefonosCliente(clientesCSV.get_csvField(linea, 3).substring(1, clientesCSV.get_csvField(linea, 3).length()-1), 
                                        clientesCSV.get_csvField(linea, 4).substring(1, clientesCSV.get_csvField(linea, 4).length()-1), 
                                        clientesCSV.get_csvField(linea, 5).substring(1, clientesCSV.get_csvField(linea, 5).length()-1),
                                        toAdd);
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
            if (linea == null)
            {
                break;
            }
            
            toAdd = new Plan(planesCSV, linea);
            this.addNewPlan(toAdd);
        }
    }
    
    public void importPrepago() throws FileNotFoundException, IOException{
        CSV prepagoCSV = new CSV("prepagos");
        String linea = prepagoCSV.firstLine();
        Prepago toAdd = new Prepago(prepagoCSV, linea);
        this.addPrepago(toAdd);
        
        while (true){
            linea = null;
            linea = prepagoCSV.nextLine();
            if (linea == null){
                break;
            }
            
            toAdd = new Prepago(prepagoCSV, linea);
            this.addPrepago(toAdd);
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
    
    public void mostrarInfoClientes() throws IOException{
        if (clientesMap.isEmpty()){
            System.out.println("No se encontraron clientes");
            return;
        }
        
        BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
        
        String opt = "";
        
        while (!opt.equals("0")){
            System.out.println("Ingrese una opción:");
            System.out.println("(1) Mostrar datos de todos los clientes");
            System.out.println("(2) Mostrar información de un cliente");
            System.out.println("(0) Volver al menú principal");
            opt = reader.readLine();
            
            switch (opt){
                case "1": System.out.println("----------------------");
                          for (Cliente iterator: clientesMap.values()){
                              iterator.mostrarDatos(0);
                              System.out.println("\n");
                          }
                          System.out.println("----------------------");
                          break;
                case "2": System.out.println("Ingrese el RUT del cliente: ");
                          String toShow = reader.readLine();
                          Cliente c = getCliente(toShow);
                          if (c != null){
                              c.mostrarDatos("a");
                              System.out.println("Desea ver los datos del algún teléfono? (s/n)");
                              String fonoData = reader.readLine();
                              if (fonoData.equals("s")){
                                  System.out.println("Ingrese el número: ");
                                  fonoData = reader.readLine();
                                  c.mostrarDatosTelefono(fonoData);
                              }
                              System.out.println("----------------------\n");
                          }
                          else{
                              System.out.println("El cliente ingresado no existe\n");
                          }
                          break;
                          
            }
        }
    }
    
    
    public void mostrarDispositivos() throws IOException{
        if (celuMap.isEmpty()){
            System.out.println("No existen dispositivos para mostrar");
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String opt = "";
        
        while (!opt.equals("0")){
            System.out.println("Ingrese una opción:");
            System.out.println("(1) Ver todos los dispositivos disponibles");
            System.out.println("(2) Filtrar por marca");
            System.out.println("(0) Volver al menú principal");
            opt = reader.readLine();
            
            switch (opt){
                case "1": System.out.println("----------------------");
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
                           break;
                           
                case "2": System.out.println("Ingrese qué marca de celular desea ver: ");
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
                          break;
            } 
        }
    }
    
    public void editarContratos() throws IOException{
        if (clientesMap.isEmpty()){
            System.out.println("No existen clientes para editar!");
            return;
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el RUT de un cliente: ");
        String toEdit = reader.readLine();
        
        Cliente c = this.getCliente(toEdit);
        if (c == null){
            System.out.println("Cliente no encontrado");
            return;
        }
        
        
    }
    //Método para crear un nuevo plan a partir de la opción 1 en el menú.
    /*public void addManualPlan() throws IOException{ 
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
    }*/
}
