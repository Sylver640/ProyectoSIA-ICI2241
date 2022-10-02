package com.mycompany.proyectosia.ici2241;

public class Tarifa {
    //Variables de instancia.
    private String nombre;
    private String tipo;
    
    //Constructor sin parámetros.
    public Tarifa(){
        
    }
    
    //Setter y Getter de nombre.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Setter y Getter de tipo.
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    //Muestra los detalles de la tarifa.
    public void mostrarDetallesTarifa(){
        System.out.println("Nombre: "+getNombre());
        System.out.println("Tipo: "+getTipo());
    }
}
