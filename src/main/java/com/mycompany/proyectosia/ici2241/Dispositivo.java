package com.mycompany.proyectosia.ici2241;

public class Dispositivo {
    private String nombre;
    private int ram;
    private int memoria;
    private int precio;
    private int pulgadas;
    
    public Dispositivo(){
        
    }

    public int getMemoria() {
        return memoria;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getPulgadas() {
        return pulgadas;
    }

    public int getRam() {
        return ram;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }
    
    public void setMemoria(double memoria) {
        this.memoria = (int) memoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }
    
    public void setPulgadas(long pulgadas) {
        this.pulgadas = (int) pulgadas;
    }
    
    public void setPulgadas(double pulgadas) {
        this.pulgadas = (int) pulgadas;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
    
}
