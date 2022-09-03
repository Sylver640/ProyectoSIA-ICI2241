package com.mycompany.proyectosia.ici2241;

/*
 @author rodri
 */
public class Telefono {
    private String numero;
    private Dispositivo device;
    private Plan plan;
    
    public Telefono(){
        
    }

    public Dispositivo getDevice() {
        return device;
    }

    public void setDevice(Dispositivo device) {
        this.device = device;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

}
