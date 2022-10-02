package com.mycompany.proyectosia.ici2241;

public class Prepago extends Tarifa{
    //Variables de instancia.
    private long saldoActual;
    private long costoMinutos;
    private long costoSMS;
    
    //Constructor sin parámetros.
    public Prepago(){
        
    }
    
    //Constructor con parámetros insertados por el usuario.
    public Prepago(String telefono, String tipo, long saldo, long costoMinutos, long costoSMS){
        this.setNombre(telefono);
        this.setTipo(tipo);
        this.setSaldoActual(saldo);
        this.setCostoMinutos(costoMinutos);
        this.setCostoSMS(costoSMS);
    }
    
    //Constructor en base a CSV.
    public Prepago (CSV prepago, String linea){
        this.setNombre(prepago.get_csvField(linea, 0));
        this.setTipo(prepago.get_csvField(linea, 1));
        this.setSaldoActual(Integer.parseInt(prepago.get_csvField(linea, 2)));
        this.setCostoMinutos(Integer.parseInt(prepago.get_csvField(linea, 3)));
        this.setCostoSMS(Integer.parseInt(prepago.get_csvField(linea, 4)));
    }
    
    //Sobreescrituras.
    //Setter y Getter de nombre.
    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public String getNombre() {
        return super.getNombre(); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Setter y Getter de tipo.
    @Override
    public void setTipo(String tipo) {
        super.setTipo(tipo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTipo() {
        return super.getTipo(); //To change body of generated methods, choose Tools | Templates.
    }
    
    //Setter de saldo actual.
    public void setSaldoActual(long saldoActual) {
        this.saldoActual = saldoActual;
    }
    
    //Setter de saldo actual con parámetros. (Sobrecarga)
    public void setSaldoActual (double saldoActual){
        this.saldoActual = (long) saldoActual;
    }
   
    //Getter de saldo actual.
    public long getSaldoActual() {
        return saldoActual;
    }
    
    //Setter de costo por cada minuto.
    public void setCostoMinutos(long costoMinutos) {
        this.costoMinutos = costoMinutos;
    }
    
    //Setter de costo por cada minuto con parámetros. (Sobrecarga)
    public void setCostoMinutos(double costoMinutos) {
        this.costoMinutos = (long) costoMinutos;
    }
    
    //Getter de costo por cada minuto.
    public long getCostoMinutos() {
        return costoMinutos;
    }
    
    //Setter de costo por cada SMS con parámetro.
    public void setCostoSMS(long costoSMS) {
        this.costoSMS = costoSMS;
    }
    
    //Setter de costo por cada SMS con parámetro. (Sobrecarga)
    public void setCostoSMS(double costoSMS) {
        this.costoSMS = (long) costoSMS;
    }
    
    //Getter de costo por cada SMS.
    public long getCostoSMS() {
        return costoSMS;
    }
    
    //Muestra los detalles de la tarifa (sobreescritura).
    @Override
    public void mostrarDetallesTarifa(){
        System.out.println("Nombre: "+getNombre());
        System.out.println("Saldo actual del cliente: $"+getSaldoActual());
        System.out.println("Valor de cada minuto: $"+getCostoMinutos());
        System.out.println("Valor de cada mensaje: $"+getCostoSMS());
    }
}
