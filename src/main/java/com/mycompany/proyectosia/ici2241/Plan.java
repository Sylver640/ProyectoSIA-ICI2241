package com.mycompany.proyectosia.ici2241;

public class Plan extends Tarifa{
    //Variables de instancia
    private long precio;
    private long minutos;
    private long megas;
    private int SMS;
    
    //Constructor de Plan sin variables recibidas
    public Plan(){
        
    }
    
    //Constructor de Plan que recibe datos ingresados por usuario.
    public Plan(String nombrePlan, String tipo, long precio, long megas, long minutos){
        this.setNombre(nombrePlan);
        this.setTipo(tipo);
        this.setPrecio(precio);
        this.setMinutos(minutos);
        this.setMegas(megas);
        this.setSMS(SMS);
    }
    
    //Constructor de Plan que recibe datos desde un archivo CSV
    public Plan(CSV planes, String linea)
    {
        this.setNombre(planes.get_csvField(linea, 0));
        this.setTipo(planes.get_csvField(linea,1));
        this.setPrecio(Integer.parseInt(planes.get_csvField(linea, 2)));
        this.setMinutos(Integer.parseInt(planes.get_csvField(linea, 3)));
        this.setMegas(Integer.parseInt(planes.get_csvField(linea, 4)));
        this.setSMS(Integer.parseInt(planes.get_csvField(linea, 5)));
    }
    
    //Getters y setters de cada variable de instancia
    
    @Override
    public String getNombre() {
        return super.getNombre(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getTipo() {
        return super.getTipo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTipo(String tipo) {
        super.setTipo(tipo); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = (long) precio;
    }
    
    public long getPrecio() {
        return precio;
    }
    
    public void setMinutos(long minutos) {
        this.minutos = minutos;
    }
    
    public void setMinutos(double minutos) {
        this.minutos = (long) minutos;
    }
    
    public long getMinutos() {
        return minutos;
    }
    
    public void setMegas(long megas) {
        this.megas = megas;
    }
    
    public void setMegas(double megas) {
        this.megas = (long) megas;
    }
    
    public long getMegas() {
        return megas;
    }

    public void setSMS(int SMS) {
        this.SMS = SMS;
    }

    public int getSMS() {
        return SMS;
    }
}
