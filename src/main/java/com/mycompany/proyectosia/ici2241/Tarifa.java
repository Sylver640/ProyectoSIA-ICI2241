package com.mycompany.proyectosia.ici2241;

public class Tarifa {
    private String nombre;
    private String tipo;
    
    public Tarifa(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void mostrarDetallesTarifa(){
        System.out.println("Nombre: "+getNombre());
        System.out.println("Tipo: "+getTipo());
    }
}
