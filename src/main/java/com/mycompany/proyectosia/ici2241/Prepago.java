package com.mycompany.proyectosia.ici2241;

public class Prepago extends Tarifa{
    private long saldoActual;
    private long costoMinutos;
    private long costoSMS;
    
    public Prepago(){
        
    }
    
    public Prepago(String telefono, String tipo, long saldo, long costoMinutos, long costoSMS){
        this.setNombre(telefono);
        this.setTipo(tipo);
        this.setSaldoActual(saldo);
        this.setCostoMinutos(costoMinutos);
        this.setCostoSMS(costoSMS);
    }
    
    public Prepago (CSV prepago, String linea){
        this.setNombre(prepago.get_csvField(linea, 0));
        this.setTipo(prepago.get_csvField(linea, 1));
        this.setSaldoActual(Integer.parseInt(prepago.get_csvField(linea, 2)));
        this.setCostoMinutos(Integer.parseInt(prepago.get_csvField(linea, 3)));
        this.setCostoSMS(Integer.parseInt(prepago.get_csvField(linea, 4)));
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
