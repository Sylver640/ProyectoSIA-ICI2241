package com.mycompany.proyectosia.ici2241;

public class NotTarifaException extends Exception{
    //Expepción que se retorna si un teléfono no posee plan.
    public NotTarifaException(){
        super("No contiene plan!");
    }
}
