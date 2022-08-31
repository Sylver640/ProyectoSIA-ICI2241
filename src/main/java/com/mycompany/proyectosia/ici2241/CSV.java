package com.mycompany.proyectosia.ici2241;

import java.io.*;
import java.util.Scanner;

public class CSV {
    private BufferedReader file;
    private String currentLine;

    public CSV(String name) throws FileNotFoundException
    {
      this.file =new BufferedReader(new FileReader("./"+name+".csv"));
    }
    
    public CSV(String ruta,String archivo) throws FileNotFoundException
    {
        this.file =new BufferedReader(new FileReader(ruta+archivo));
    }
    
    public CSV()
    {
    }

    public String firstLine() throws IOException
    {
        return nextLine();
    }
    
    public String nextLine() throws IOException
    {
        this.currentLine =this.file.readLine();
        return(this.currentLine);
    }
    
    public String get_csvField(String line,int field)
    {
        Scanner s = new Scanner(line);
        int index =(0);
        s.useDelimiter(",|\\n");
        String output;
        
        while(s.hasNext())
        {
            output =(s.next());
            if((output.charAt(0))==('\"'))
            {
                while (true)
                {
                    if ((output.charAt((output.length())-1))==('\"'))
                    {
                        break;
                    }
                    output =(output+","+(s.next()));
                }
            }
            
            if (index==field)
            {
                s.close();
                return(output);
            }
            index =(index+1);
        }
        s.close();
        return(null);
    }

    public String get_csvField(int field,String line)
    {
        Scanner s = new Scanner(line);
        int index =(0);
        s.useDelimiter(",|\\n");
        String output;
        
        while(s.hasNext())
        {
            output =(s.next());
            if((output.charAt(0))==('\"'))
            {
                while (true)
                {
                    output =(output+","+(s.next()));
                    if ((output.charAt((output.length())-1))==('\"'))
                    {
                        break;
                    }
                }
            }
            
            if (index==field)
            {
                return(output);
            }
            index =(index+1);
        }
        return(null);
    }
    public void close() throws IOException
    {
        file.close();
    }
}
