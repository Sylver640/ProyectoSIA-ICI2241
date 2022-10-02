package com.mycompany.proyectosia.ici2241;

public class TieneDispositivoException extends Exception{
    //Excepción que se retorna si un teléfono no posee un dispositivo vinculado.
    public TieneDispositivoException(){
        super("Este telefono no tiene dispositivo");
    }
}
