package com.mycompany.proyectosia.ici2241;

public class Dispositivo {
    private String nombre;
    private String marca;
    private int precio;
    private int ram;
    private int memoria;
    private double pulgadas;
    private String conexion;
    
    public Dispositivo(){
        
    }
    
    public Dispositivo(CSV dispositivos, String linea){
        this.setNombre(dispositivos.get_csvField(linea, 0));
        this.setMarca(dispositivos.get_csvField(linea,1));
        this.setRam(Integer.parseInt(dispositivos.get_csvField(linea, 2)));
        this.setMemoria(Integer.parseInt(dispositivos.get_csvField(linea, 3)));
        this.setPrecio(Integer.parseInt(dispositivos.get_csvField(linea,4)));
        this.setPulgadas(Double.parseDouble(dispositivos.get_csvField(linea,5)));
        this.setConexion(dispositivos.get_csvField(linea,6));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getConexion() {
        return conexion;
    }

    public void setConexion(String conexion) {
        this.conexion = conexion;
    }  
}
