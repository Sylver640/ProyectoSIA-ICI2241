package com.mycompany.proyectosia.ici2241;

public class Plan {
    private int precio;
    private int minutos;
    private int megas;
    
    public Plan(){
        
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

    public void setMegas(int megas) {
        this.megas = megas;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    } 
}
