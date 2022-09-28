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
}
