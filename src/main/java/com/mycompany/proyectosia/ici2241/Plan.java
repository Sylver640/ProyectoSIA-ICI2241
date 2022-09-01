package com.mycompany.proyectosia.ici2241;

public class Plan {
    //Variables de instancia
    private String nombre;
    private long precio;
    private long minutos;
    private long megas;
    
    //Constructor de Plan sin variables recibidas
    public Plan(){
        
    }
    
    //Constructor de Plan que recibe datos ingresados por usuario.
    public Plan(String nombrePlan, long precio, long megas, long minutos){
        this.setNombre(nombrePlan);
        this.setPrecio(precio);
        this.setMinutos(minutos);
        this.setMegas(megas);
    }
    
    //Constructor de Plan que recibe datos desde un archivo CSV
    public Plan(CSV planes, String linea)
    {
        this.setNombre(planes.get_csvField(linea, 0));
        this.setPrecio(Integer.parseInt(planes.get_csvField(linea, 1)));
        this.setMinutos(Integer.parseInt(planes.get_csvField(linea, 2)));
        this.setMegas(Integer.parseInt(planes.get_csvField(linea, 3)));
    }
    
    //Getters y setters de cada variable de instancia
    
    public String getNombre(){
        return nombre;
    }

    public long getMegas() {
        return megas;
    }

    public long getMinutos() {
        return minutos;
    }

    public long getPrecio() {
        return precio;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMegas(long megas) {
        this.megas = megas;
    }

    public void setMinutos(long minutos) {
        this.minutos = minutos;
    }
    
    public void setPrecio(long precio) {
        this.precio = precio;
    }
    
    //Sobrecarga en variables double
    public void setMinutos(double minutos) {
        this.minutos = (long) minutos;
    } 
    
    public void setPrecio(double precio) {
        this.precio = (long) precio;
    }
    
    public void setMegas(double megas) {
        this.megas = (long) megas;
    }
}
