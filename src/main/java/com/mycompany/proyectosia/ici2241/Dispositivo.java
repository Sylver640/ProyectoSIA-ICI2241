package com.mycompany.proyectosia.ici2241;

public class Dispositivo {
    //Variables de instancia.
    private String nombre;
    private String marca;
    private int precio;
    private int ram;
    private int memoria;
    private double pulgadas;
    private String conexion;
    
    //Constructor de la clase sin parámetros.
    public Dispositivo(){
        
    }
    
    //Constructor de la clase (en base a CSV).
    public Dispositivo(CSV dispositivos, String linea){
        this.setNombre(dispositivos.get_csvField(linea, 0));
        this.setMarca(dispositivos.get_csvField(linea,1));
        this.setRam(Integer.parseInt(dispositivos.get_csvField(linea, 2)));
        this.setMemoria(Integer.parseInt(dispositivos.get_csvField(linea, 3)));
        this.setPrecio(Integer.parseInt(dispositivos.get_csvField(linea,4)));
        this.setPulgadas(Double.parseDouble(dispositivos.get_csvField(linea,5)));
        this.setConexion(dispositivos.get_csvField(linea,6));
    }
    
    //Setters y Getters de nombre.
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Setters y Getters de marca.
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    //Setters y Getters de RAM.
    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
    
    //Setters y Getters de memoria.
    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }
    
    //Setters y Getters de precio.
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    //Setters y Getters de pulgadas.
    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }
    
    //Setters y Getters de conexión.
    public String getConexion() {
        return conexion;
    }

    public void setConexion(String conexion) {
        this.conexion = conexion;
    }  
}
