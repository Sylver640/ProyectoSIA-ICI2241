package com.mycompany.proyectosia.ici2241;
/*
 @author Vicente Mercado
 @author Rodrigo Araos
 */
public class Telefono {
    private String numero;
    private Dispositivo device;
    private Plan plan;
    private Prepago prepago;
    
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

    public Prepago getPrepago() {
        return prepago;
    }

    public void setPrepago(Prepago prepago) {
        this.prepago = prepago;
    }
    
    public boolean isPlan(){
        if (this.getPlan() == null){
            return false;
        }
        else{
            return true;
        }
    }
}
