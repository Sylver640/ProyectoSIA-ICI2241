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
    
    //Constructor sin parámetros.
    public Telefono(){
        
    }
    
    //Sobrecarga de constructor (Plan)
    public Telefono(String numero, Dispositivo device, Plan plan){
        this.setNumero(numero);
        this.setDevice(device);
        this.setPlan(plan);
        this.setPrepago(null);
    }
    
    //Sobrecarga de constructor (Prepago)
    public Telefono(String numero, Dispositivo device, Prepago prepago){
        this.setNumero(numero);
        this.setDevice(device);
        this.setPlan(null);
        this.setPrepago(prepago);
    }
    
    //Setter y Getter de dispositivo.
    public Dispositivo getDevice() {
        return device;
    }

    public void setDevice(Dispositivo device) {
        this.device = device;
    }
    
    //Setter y Getter de numero de teléfono.
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    //Setter y Getter de plan.
    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    
    //Setter y Getter de prepago.
    public Prepago getPrepago() {
        return prepago;
    }

    public void setPrepago(Prepago prepago) {
        this.prepago = prepago;
    }
    
    //Verifica si el dispositivo posee plan o prepago.
    public boolean isPlan(){
        if (this.getPlan() == null){
            return false;
        }
        else{
            return true;
        }
    }
}
