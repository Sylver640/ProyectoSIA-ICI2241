package com.mycompany.proyectosia.ici2241;
import java.io.*;

/*
 * Autores: Rodrigo Araos
            Vicente Mercado
 */
public class JavaMain 
{
    public static void main(String[] args) throws IOException, NotTarifaException, TieneDispositivoException
    {
        //Se presume que quien usarío esta aplicación es una empresa, por lo que no existe un inicio de sesión
        //Variables de lectura
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresado;
        int opt = 1;
        ManejoColecciones colHandle = new ManejoColecciones();
        colHandle.importPlanes();
        colHandle.importPrepago();
        colHandle.importDispositivos();
        colHandle.importClientes();
        
        //Creación del menú
        while (opt != 0)
        {
            System.out.println("-----MENÚ-----");
            System.out.println("1. Mostrar información de clientes");
            System.out.println("2. Editar contratos de un cliente");
            System.out.println("3. Generar reporte actual de clientes");
            System.out.println("4. Catálogo de dispositivos");
            System.out.println("5. Filtrar clientes");
            System.out.println("0. Salir");
            System.out.println("--------------");
            System.out.println("Ingrese una opción: ");
            ingresado = lector.readLine();
            opt = Integer.parseInt(ingresado);
            
            switch(opt)
            {
                case 1: colHandle.mostrarInfoClientes();
                        break;
                case 2: colHandle.administrarContratos();
                        break;
                case 3: colHandle.generarReporte();
                        break;
                case 4: colHandle.mostrarDispositivos();
                        break;
                case 5: colHandle.filtrarClientes();
                        break;
                case 0: break;
            }
        }
    }
}
