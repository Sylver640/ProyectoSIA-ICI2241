package com.mycompany.proyectosia.ici2241;

public class Prepago extends Tarifa{
    private long saldoActual;
    private long costoMinutos;
    private long costoSMS;
    
    public Prepago(){
        
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNombre() {
        return super.getNombre(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTipo(String tipo) {
        super.setTipo(tipo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTipo() {
        return super.getTipo(); //To change body of generated methods, choose Tools | Templates.
    }

    public void setSaldoActual(long saldoActual) {
        this.saldoActual = saldoActual;
    }
    
    public void setSaldoActual (double saldoActual){
        this.saldoActual = (long) saldoActual;
    }

    public long getSaldoActual() {
        return saldoActual;
    }

    public void setCostoMinutos(long costoMinutos) {
        this.costoMinutos = costoMinutos;
    }
    
    public void setCostoMinutos(double costoMinutos) {
        this.costoMinutos = (long) costoMinutos;
    }

    public long getCostoMinutos() {
        return costoMinutos;
    }

    public void setCostoSMS(long costoSMS) {
        this.costoSMS = costoSMS;
    }
    
    public void setCostoSMS(double costoSMS) {
        this.costoSMS = (long) costoSMS;
    }

    public long getCostoSMS() {
        return costoSMS;
    }
}
