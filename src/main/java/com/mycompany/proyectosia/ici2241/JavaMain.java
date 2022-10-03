package com.mycompany.proyectosia.ici2241;
import com.mycompany.proyectosia.ici2241.ventanas.*;
import java.io.*;

/*
 * Autores: Rodrigo Araos
            Vicente Mercado
 */
public class JavaMain 
{
    public static void main(String[] args) throws IOException, NotTarifaException, TieneDispositivoException
    {
        //Variables de lectura
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresado;
        int opt = 1;
        ManejoColecciones colHandle = new ManejoColecciones(); //Instancia de ManejoColecciones.
        colHandle.importPlanes(); //Importación de planes.
        colHandle.importPrepago(); //Importación de prepagos.
        colHandle.importDispositivos(); //Importación de dispositivos.
        colHandle.importClientes(); //Importación de clientes.
        
        //Puesto que algunas funciones no alcanzaron a ser implementadas en ventanas,
        //se le da al usuario la opción de utilizarlas en la forma de consola
        System.out.println("Desea usar las funciones disponibles en consola? (s/n)");
        ingresado = lector.readLine();
        
        if (ingresado.equals("s")){
            while (opt != 0){
                System.out.println("(1) Administrar contratos");
                System.out.println("(0) Salir");
                System.out.println("Ingrese una opción:");
                opt = Integer.parseInt(lector.readLine());
                
                switch (opt){
                    case 1: colHandle.administrarContratos();
                            break;
                    case 0: colHandle.exportPrepago();
                            colHandle.exportPlanes();
                            colHandle.exportDispositivos();
                            colHandle.exportClientes();
                            return;
                }
            }
        } else{ //Si no lo quiere así, solo se abre la ventana principal
            VentanaPrincipal vp = new VentanaPrincipal(colHandle);
            vp.setVisible(true); //Se crea y visibiliza la ventana del menú principal. 
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            java.util.logging.Logger.getLogger(MostrarTodosClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            java.util.logging.Logger.getLogger(InsertRUT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            java.util.logging.Logger.getLogger(MostrarTodosClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            java.util.logging.Logger.getLogger(InsertRUT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            java.util.logging.Logger.getLogger(MostrarTodosClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            java.util.logging.Logger.getLogger(InsertRUT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            java.util.logging.Logger.getLogger(MostrarTodosClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            java.util.logging.Logger.getLogger(InsertRUT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        }
        
    }
}
