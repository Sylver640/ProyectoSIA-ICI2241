package com.mycompany.proyectosia.ici2241;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public String[][] mostrarTodosLosClientes(){
        int i = 0;
        
        String[][] tabla =new String[clientesMap.size()][3];
        
        if(clientesMap.entrySet() == null){
            return tabla;
        }
        
        for (Map.Entry<String, Cliente> entry: clientesMap.entrySet()){
            Cliente cliente = entry.getValue();
            tabla[i][0] = cliente.getNombre();
            tabla[i][1] = cliente.getRut();
            tabla[i][2] = "" + cliente.getTiempoEnMeses(); 
            i+=1;
        }
        
        return tabla;
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
    /*public void addClientPlan() throws IOException
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
                
                if(searched.getTelefono(Ingr) != null){
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
        
    }*/
    
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
            String actualDevice = celuToken.nextToken();
            
            Telefono toAdd = new Telefono();
            
            toAdd.setNumero(actualNum);
            if (actualTarifa.equals("Prepago")){
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
        
        clientesCSV.close();
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
        
        planesCSV.close();
    }
    
    public void exportPlanes() throws FileNotFoundException, IOException{
        File delete = new File("planes.csv");
        delete.delete();
        
        FileWriter nuevoPlanes = new FileWriter("planes.csv");
        
        for (Plan iterator: planesMap.values()){
            nuevoPlanes.append(iterator.getNombre());
            nuevoPlanes.append(",");
            nuevoPlanes.append(iterator.getTipo());
            nuevoPlanes.append(",");
            nuevoPlanes.append(""+iterator.getPrecio());
            nuevoPlanes.append(",");
            nuevoPlanes.append(""+iterator.getMinutos());
            nuevoPlanes.append(",");
            nuevoPlanes.append(""+iterator.getMegas());
            nuevoPlanes.append(",");
            nuevoPlanes.append(""+iterator.getSMS());
            nuevoPlanes.append("\n");
        }
        
        nuevoPlanes.flush();
        nuevoPlanes.close();
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
        
        prepagoCSV.close();
    }
    
    public void exportPrepago() throws FileNotFoundException, IOException{
        File delete = new File("prepagos.csv");
        delete.delete();
        
        FileWriter nuevoPrepagos = new FileWriter("prepagos.csv");
        
        for (Prepago iterator: prepagoMap.values()){
            nuevoPrepagos.append(iterator.getNombre());
            nuevoPrepagos.append(",");
            nuevoPrepagos.append(iterator.getTipo());
            nuevoPrepagos.append(",");
            nuevoPrepagos.append(""+iterator.getSaldoActual());
            nuevoPrepagos.append(",");
            nuevoPrepagos.append(""+iterator.getCostoMinutos());
            nuevoPrepagos.append(",");
            nuevoPrepagos.append(""+iterator.getCostoSMS());
            nuevoPrepagos.append("\n");
        }
        
        nuevoPrepagos.flush();
        nuevoPrepagos.close();
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
        
        dispositivosCSV.close();
    }
    
    public void exportDispositivos() throws FileNotFoundException, IOException{
        File delete = new File("dispositivos.csv");
        delete.delete();
        
        FileWriter nuevoDispositivos = new FileWriter("dispositivos.csv");
        for (Dispositivo iterator: celuMap.values()){
            nuevoDispositivos.append(iterator.getNombre());
            nuevoDispositivos.append(",");
            nuevoDispositivos.append(iterator.getMarca());
            nuevoDispositivos.append(",");
            nuevoDispositivos.append(""+iterator.getRam());
            nuevoDispositivos.append(",");
            nuevoDispositivos.append(""+iterator.getMemoria());
            nuevoDispositivos.append(",");
            nuevoDispositivos.append(""+iterator.getPrecio());
            nuevoDispositivos.append(",");
            nuevoDispositivos.append(""+iterator.getPulgadas());
            nuevoDispositivos.append(",");
            nuevoDispositivos.append(iterator.getConexion());
            nuevoDispositivos.append("\n");
        }
        
        nuevoDispositivos.flush();
        nuevoDispositivos.close();
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
            System.out.println("(2) Mostrar información de un cliente");
            System.out.println("(0) Volver al menú principal");
            opt = reader.readLine();
            
            switch (opt){
                case "2": System.out.println("Ingrese el RUT del cliente: ");
                          String toShow = reader.readLine();
                          Cliente c = getCliente(toShow);
                          if (c != null){
                              c.mostrarDatos("a");
                              System.out.println("Desea ver los datos de algún teléfono? (s/n)");
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
    
    public Prepago agregarNuevoPrepago(String telefono) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el saldo del teléfono:");
        long saldo = (Integer.parseInt(reader.readLine()));
        System.out.println("Ingrese el costo de cada minuto:");
        long costoM = (Integer.parseInt(reader.readLine()));
        System.out.println("Ingrese el costo de cada mensaje:");
        long costoS = (Integer.parseInt(reader.readLine()));
        
        Prepago p = new Prepago(telefono,"Prepago",saldo,costoM,costoS);
        this.addPrepago(p);
        return p;
    }
    
    public void editarPrepago (Telefono t) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese el nuevo saldo del teléfono:");
        long newSaldo = Integer.parseInt(reader.readLine());
        t.getPrepago().setSaldoActual(newSaldo);
        System.out.println("Ingrese el nuevo costo de los minutos:");
        long newMinutos = Integer.parseInt(reader.readLine());
        t.getPrepago().setCostoMinutos(newMinutos);
        System.out.println("Ingrese el nuevo costo de los mensajes:");
        long newSMS = Integer.parseInt(reader.readLine());
        t.getPrepago().setCostoSMS(newSMS);
        
        prepagoMap.replace(t.getNumero(), t.getPrepago());
        System.out.println("Prepago editado correctamente");
    }
    
    public void cambiarPlan(Telefono t) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opt = "0";
        while (opt.length() == 1){
            System.out.println("Ingrese el nombre del nuevo plan para el teléfono: ");
            System.out.println("Si desea ver los nombres de los planes disponibles, presione 1");
            opt = reader.readLine();
            if (opt.equals("1")){
                System.out.println("Planes disponibles:");
                for (Plan iterator: planesMap.values()){
                    System.out.println("- "+iterator.getNombre());
                }
            } else{
                Plan p = this.getPlan(opt);
                if (p == null){
                    System.out.println("Plan ingresado no existe");
                }
                t.setPlan(p);
                System.out.println("Plan cambiado con éxito");
            }
        }
        
    }
    
    public void switchTarifa(Telefono t) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (t.getPlan() == null){
            System.out.println("Ingrese el nombre del nuevo plan para el teléfono prepago:");
            String nombrePlan = reader.readLine();
            Plan p = this.getPlan(nombrePlan);
            if (p == null){
                System.out.println("Plan ingresado no existe");
                return;
            }
            t.setPrepago(null);
            t.setPlan(p);
            prepagoMap.remove(t.getNumero());
            return;
        }
        
        if (t.getPrepago() == null){
            System.out.println("Ahora ingrese los detalles para el nuevo prepago:\n");
            Prepago p = this.agregarNuevoPrepago(t.getNumero());
            t.setPrepago(p);
            t.setPlan(null);
        }
    }
    
    public boolean editarTarifaCliente(String telefono, Cliente c) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Telefono editTar = c.getTelefono(telefono);
        if (editTar == null){
            return false;
        }
        
        if (editTar.getPlan() == null && editTar.getPrepago() != null){
            System.out.println("El teléfono "+editTar.getNumero()+" está contratado como prepago");
            System.out.println("Qué desea hacer?");
            System.out.println("(1) Editar prepago");
            System.out.println("(2) Cambiar de tipo de tarifa");
            System.out.println("(0) Volver");
            String opt = reader.readLine();
            
            switch(opt){
                case "1": editarPrepago(editTar);
                case "2": switchTarifa(editTar);
            }
        }
        
        if (editTar.getPlan() != null && editTar.getPrepago() == null){
            System.out.println("El teléfono "+editTar.getNumero()+" está contratado como plan");
            System.out.println("Qué desea hacer?");
            System.out.println("(1) Cambiar a otro plan");
            System.out.println("(2) Cambiar de tipo de tarifa");
            System.out.println("(0) Volver");
            String opt = reader.readLine();
            
            switch(opt){
                case "1": cambiarPlan(editTar);
                case "2": switchTarifa(editTar);
            }
        }
        
        
        return true;
    }
    
    public void administrarContratos() throws IOException{
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
        System.out.println("\n");
        c.mostrarDatos(0);
        String opt = "";
        
        
        while (!opt.equals("0")){
            System.out.println("Qué desea realizar?");
            System.out.println("(1) Agregar un nuevo contrato");
            System.out.println("(2) Eliminar un contrato existente");
            System.out.println("(3) Editar un contrato");
            System.out.println("(0) Volver");
            opt = reader.readLine();
            
            switch(opt){
                case "1": System.out.println("Ingrese el nuevo número:");
                          String toAdd = reader.readLine();
                          System.out.println("Qué tipo de tarifa contiene el nuevo contrato? (Plan / Prepago)");
                          String toAddTarifa = reader.readLine();
                          System.out.println("Qué dispositivo celular tendrá este contrato? Ingrese el nombre del modelo:");
                          String toAddDispositivo = reader.readLine();
                          Dispositivo d = this.getDevice(toAddDispositivo);
                          
                          if (d == null){
                              System.out.println("Dispositivo no se encuentra en el catálogo");
                              break;
                          }
                          
                          if (toAddTarifa.equals("Prepago")){
                              Prepago toAddPrepago = this.agregarNuevoPrepago(toAdd);
                              c.crearNuevoContrato(toAdd, toAddPrepago, d);
                              System.out.println("Contrato agregado correctamente");
                              break;
                          }
                          
                          if (toAddTarifa.equals("Plan")){
                              System.out.println("Qué plan tendrá este contrato?");
                              String toAddPlan = reader.readLine();
                              if (this.getPlan(toAddPlan) == null){
                                  System.out.println("Plan indicado no existe");
                                  break;
                              }
                              
                              c.crearNuevoContrato(toAdd, this.getPlan(toAddPlan), d);
                              System.out.println("Contrato agregado correctamente");
                              
                              break;
                          }
                          
                          System.out.println("Tipo de tarifa no válida");
                          break;
                          
                case "2": System.out.println("Indique el número del contrato que desea eliminar:");
                          String toDelete = reader.readLine();
                          if (c.eliminarTelefono(toDelete)){
                              System.out.println("Teléfono eliminado correctamente");
                              break;
                          }
                          System.out.println("El cliente "+c.getRut()+" no tiene este teléfono contratado");
                          break;
                          
                case "3": System.out.println("Ingrese el número que desea editar:");
                          String tarifaEditar = reader.readLine();
                          if (this.editarTarifaCliente(tarifaEditar, c)){
                              break;
                          }
                          System.out.println("El cliente "+c.getRut()+" no tiene este teléfono contratado");
                          break;
            }
        }
    }
    
    public void mostrarClientesMarca() throws IOException, TieneDispositivoException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int contTotal = 0;
        System.out.println("Ingrese la marca a buscar:");
        String showMarca = reader.readLine();
        
        System.out.println("----------------------");
        
        for (Cliente iterator: clientesMap.values()){
            if (iterator.buscarMarcaEnTelefono(showMarca)){
                contTotal++;
            }
        }
        
        if (contTotal == 0){
            System.out.println("Ningún cliente tiene contratado un dispositivo de esta marca!");
        }
        
        System.out.println("Se ha(n) encontrado "+contTotal+" cliente(s) con teléfonos que tienen contratados dispositivos de esta marca!");
        
        System.out.println("----------------------");
    }
    
    public void mostrarClientesPlanes() throws IOException, NotTarifaException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int contTotal = 0;
        System.out.println("Ingrese el plan a buscar:");
        String showPlan = reader.readLine();
        
        System.out.println("----------------------");
        
        for (Cliente iterator: clientesMap.values()){
            if (iterator.buscarPlanEnTelefono(showPlan)){
                contTotal++;
            }
        }
        
        if (contTotal == 0){
            System.out.println("Ningún cliente tiene contratado este plan!");
        }
        
        System.out.println("Se ha(n) encontrado "+contTotal+" cliente(s) con teléfonos que tienen contratados este plan!");
        System.out.println("----------------------");
    }
    
    public void mostrarClientesMeses() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int contTotal = 0;
        System.out.println("Ingrese la cantidad de meses:");
        int showMeses = Integer.parseInt(reader.readLine());
        
        System.out.println("----------------------");
        
        for (Cliente iterator: clientesMap.values()){
            if (iterator.getTiempoEnMeses() >= showMeses){
                System.out.println("Cliente "+iterator.getRut()+ " lleva "+ iterator.getTiempoEnMeses() + " meses en la compañia");
                contTotal++;
            }
        }
        
        if (contTotal == 0){
            System.out.println("Ningún cliente lleva más de "+ showMeses + " meses en la compañia");
        }
        
        System.out.println("Se ha(n) encontrado "+contTotal+" cliente(s) que llevan más de "+showMeses+" meses en la compañia!");
        System.out.println("----------------------");
    }
    
    public void filtrarClientes() throws IOException, NotTarifaException, TieneDispositivoException{
        if (clientesMap.isEmpty()){
            System.out.println("No existen clientes para listar!");
            return;
        }
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opt = "";
        
        while (!opt.equals("0")){
            System.out.println("Qué desea realizar?");
            System.out.println("(1) Seleccionar clientes con marca de teléfono contratado");
            System.out.println("(2) Seleccionar clientes que tengan cierto plan contratado");
            System.out.println("(3) Mostrar clientes que lleven más de ciertos meses determinados");
            System.out.println("(0) Volver al menú principal");
            opt = reader.readLine();
            
            switch (opt){
                case "1": this.mostrarClientesMarca();
                          break;
                case "2": this.mostrarClientesPlanes();
                          break;
                case "3": this.mostrarClientesMeses();
                          break;
            }
        }
    }
    
    public void generarReporte(){
        
    }
}
