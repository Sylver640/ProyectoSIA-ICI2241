package com.mycompany.proyectosia.ici2241;

public class DispositivoMovil {
    private String nombreEquipo;
    private int ram;
    private int memoria;
    private int precio;
    private int pulgadas;
    
    public DispositivoMovil(){
        
    }

    public int getMemoria() {
        return memoria;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
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

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
    
}
