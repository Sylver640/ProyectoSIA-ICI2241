package com.mycompany.proyectosia.ici2241;
import java.util.*;
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
        /*La idea que tengo (hasta el momento, claro skdjksd) es la siguiente:
        
        1. Una lista (o mapa??) que contenga todos los planes. Esta se va a cargar con un csv o txt que se va a abrir
        apenas empiece el programa.
        2. El cliente deberá iniciar sesión. Así se cargan sus datos, y al mismo tiempo el de todos los demás guardándose en
        sus respectivos objetos de clase "Cliente".
        3. Un menú que tenga las siguientes opciones:
            a. Planes disponibles para contratar
            b. Manejar mis planes
            c. Contratar un teléfono
            d. 
        */
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresado;
        int opt = 1;
        System.out.println("Esto es una prueba");
        while (opt != 0)
        {
            System.out.println("-----MENÚ-----");
            System.out.println("1. Agregar nuevos planes");
            System.out.println("2. Planes disponibles");
            System.out.println("3. Dispositivos disponibles");
            System.out.println("0. Salir");
            System.out.println("--------------");
            System.out.println("Ingrese una opción: ");
            ingresado = lector.readLine();
            opt = Integer.parseInt(ingresado);
            
            switch(opt)
            {
                case 1: System.out.println("MÉTODO NO IMPLEMENTADO");
                        break;
                case 2: System.out.println("MÉTODO NO IMPLEMENTADO");
                        break;
                case 3: System.out.println("MÉTODO NO IMPLEMENTADO");
                        break;
                case 0: break;
            }
        }
    }
}
