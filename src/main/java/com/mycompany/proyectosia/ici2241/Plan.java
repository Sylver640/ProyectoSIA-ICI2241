package com.mycompany.proyectosia.ici2241;

public class Plan {
    //Variables de instancia
    private String nombre;
    private int precio;
    private int minutos;
    private int megas;
    
    //Constructor de Plan sin variables recibidas
    public Plan(){
        
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

    public int getMegas() {
        return megas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getPrecio() {
        return precio;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setMegas(int megas) {
        this.megas = megas;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }
    
    //Sobrecarga en variables double
    public void setMinutos(double minutos) {
        this.minutos = (int) minutos;
    }
    
    public void setPrecio(int precio) {
        this.precio = precio;
    } 
    
    //Sobrecarga en variables double
    public void setPrecio(double precio) {
        this.precio = (int)precio;
    }
}
