package com.mycompany.proyectosia.ici2241;
import java.io.FileWriter;
import java.io.IOException;
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
    public Cliente(CSV cliente, String linea)
    {
        this.setRut(cliente.get_csvField(linea,0));
        this.setNombre(cliente.get_csvField(linea, 1));
        this.setTiempoEnMeses(Integer.parseInt(cliente.get_csvField(linea, 2)));
        this.listaTelefonos = new HashMap<String, Telefono>();
    }
    
    //Getter para el nombre
    public String getNombre() {
        return nombre;
    }
    
    public String[][] mostrarTodosLosTelefonos(){
        int i = 0;
        
        String[][] t = new String[listaTelefonos.size()][3];
        
        if(listaTelefonos.entrySet() == null){
            return t;
        }
        
        for (Map.Entry<String, Telefono> entry: listaTelefonos.entrySet()){
            Telefono tel = entry.getValue();
            t[i][0] = tel.getNumero();
            if(tel.getPlan() == null){
                t[i][1] = "Prepago";
            }
            if(tel.getPrepago() == null){
                t[i][1] = "Plan";
            }
            t[i][2] = tel.getDevice().getNombre();
            i+=1;
        }
        
        return t;
    }
    
    //Getter para un telefono especificado.
    public Telefono getTelefono(String telefono){
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
    
    public void addTelefono(Telefono toAdd, String numero){
        listaTelefonos.put(numero, toAdd);
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
    
    public boolean eliminarTelefono(String telefono){
        Telefono t = this.getTelefono(telefono);
        if (t == null){
            return false;
        }
        listaTelefonos.remove(telefono);
        return true;
    }
    
    public void exportTelefonos(FileWriter csv) throws IOException{
        //Se recibe el archivo ya abierto}
        //Primero guardamos cada telefono con sus datos para así evitar un desorden al exportarlos
        ArrayList<String> numeros = new ArrayList<String>();
        ArrayList<String> tarifas = new ArrayList<String>();
        ArrayList<String> nombreCelus = new ArrayList<String>();
        
        for (Telefono iterator: listaTelefonos.values()){
            numeros.add(iterator.getNumero());
            nombreCelus.add(iterator.getDevice().getNombre());
            if (iterator.getPlan() == null){
                tarifas.add("Prepago");
            } else{
                tarifas.add(iterator.getPlan().getNombre());
            }
        }
        
        //Ahora sí, tan solo recorremos cada lista y vamos copiando cada valor en el CSV
        csv.append("\"");
        for (int i = 0; i < numeros.size(); i++){
            csv.append(numeros.get(i));
            csv.append(",");
        }
        csv.append("\"");
        csv.append(",");
        csv.append("\"");
        for (int i = 0; i < tarifas.size(); i++){
            csv.append(tarifas.get(i));
            csv.append(",");
        }
        csv.append("\"");
        csv.append(",");
        csv.append("\"");
        for (int i = 0; i < nombreCelus.size(); i++){
            csv.append(nombreCelus.get(i));
            csv.append(",");
        }
        csv.append("\"");
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
            if (iterator.getPlan() != null)
                System.out.println(iterator.getPlan().getNombre());
        }
    }
    
    public void mostrarDatos(String a){
        System.out.println("----------------------");
        System.out.println("Nombre: "+this.getNombre());
        System.out.println("RUT: "+this.getRut());
        System.out.println("Tiempo que lleva en la compañia: "+this.getTiempoEnMeses()+" meses");
        System.out.println("Lista de telefonos:");
        for (Telefono iterator: listaTelefonos.values()){
            System.out.println(iterator.getNumero());
        }
    }
    
    public void mostrarDatos(int a){
        System.out.println("Nombre: "+this.getNombre());
        System.out.println("RUT: "+this.getRut());
        System.out.println("Tiempo que lleva en la compañia: "+this.getTiempoEnMeses()+" meses");
    }
    
    public void mostrarDatosTelefono(String toShow){
        if (!listaTelefonos.containsKey(toShow)){
            System.out.println("Número ingresado no existe en cliente "+rut);
            return;
        }
        
        Telefono t = listaTelefonos.get(toShow);
        System.out.println("Número: "+t.getNumero());
        if (t.isPlan()){
            System.out.println("Tipo de tarifa contratada: Plan");
            System.out.println("Nombre del plan: "+t.getPlan().getNombre());
            System.out.println("Precio de este plan para este telefono: $"+t.getPlan().getPrecio());
        }
        else{
            System.out.println("Tipo de tarifa contratada: Prepago");
            System.out.println("Saldo actual del telefono: $"+t.getPrepago().getSaldoActual());
            System.out.println("Costo de minutos: $"+t.getPrepago().getCostoMinutos());
            System.out.println("Costo de SMS: $"+t.getPrepago().getCostoSMS());
        }
        
        System.out.println("Dispositivo contratado: "+t.getDevice().getNombre());
    }
    
    public void crearNuevoContrato(String telefono, Plan p, Dispositivo d){
        Telefono nuevo = new Telefono(telefono, d, p);
        this.addTelefono(nuevo, telefono);
    }
    
    public void crearNuevoContrato(String telefono, Prepago p, Dispositivo d){
        Telefono nuevo = new Telefono(telefono, d, p);
        this.addTelefono(nuevo, telefono);
    }
    
    public boolean buscarMarcaEnTelefono(String marca) throws TieneDispositivoException{
        int cont = 0;
        for (Telefono iterator: listaTelefonos.values()){
            try{
                if (iterator.getDevice().getMarca().equals(marca)){
                    System.out.println("Cliente " + getRut() + " con teléfono "+iterator.getNumero()+" tiene vinculado un dispositivo marca "+marca);
                    cont++;
                }
            }
            catch (NullPointerException e){
                throw new TieneDispositivoException();
            }
        }
        
        if (cont == 0)
            return false;
        
        return true;
    }
    
    public boolean buscarPlanEnTelefono(String plan) throws NotTarifaException{
        int cont = 0;
        for (Telefono iterator: listaTelefonos.values()){
            try{
                if (iterator.getPlan() != null){
                    if (iterator.getPlan().getNombre().equals(plan)){
                        System.out.println("Cliente " + getRut() + " con teléfono "+iterator.getNumero()+" tiene contratado/a el plan "+plan);
                        cont++;
                    }
                }
            }
            catch (NullPointerException a){
                throw new NotTarifaException();
            }
            
        }
        
        if (cont == 0)
            return false;
        
        return true;
    }
}
