package com.mycompany.proyectosia.ici2241;
import java.io.*;

/*
 * Autores: Rodrigo Araos
            Vicente Mercado
            Nicolás Mery
 */
public class JavaMain 
{
    public static void main(String[] args) throws IOException 
    {
        //Se presume que quien usarío esta aplicación es una empresa, por lo que no existe un inicio de sesión
        //Variables de lectura
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresado;
        int opt = 1;
        ManejoColecciones colHandle = new ManejoColecciones();
        
        //Primero se podrían importar los planes, y después los clientes. Así al momento de hacer esto último
        //los planes ya existirían y bastaría con buscarlos en el mapa, y agregar estos a la lista de cada cliente.
        
        //Creación del menú
        while (opt != 0)
        {
            System.out.println("-----MENÚ-----");
            System.out.println("1. Agregar nuevos planes");
            System.out.println("2. Planes disponibles");
            System.out.println("3. Agregar plan a cliente");
            System.out.println("4. Mostrar planes de cliente");
            System.out.println("5. Dispositivos disponibles");
            //Opción: Administrar clientes??
            System.out.println("0. Salir");
            System.out.println("--------------");
            System.out.println("Ingrese una opción: ");
            ingresado = lector.readLine();
            opt = Integer.parseInt(ingresado);
            
            switch(opt)
            {
                case 1: colHandle.addManualPlan();
                        break;
                case 2: System.out.println("MÉTODO NO IMPLEMENTADO");
                        break;
                case 3: System.out.println("MÉTODO NO IMPLEMENTADO");
                        break;
                case 4: System.out.println("METODO NO IMPLEMENTADO");
                        break;
                case 5: System.out.println("METODO NO IMPLEMENTADO");
                        break;
                case 0: break;
            }
        }
    }
}
