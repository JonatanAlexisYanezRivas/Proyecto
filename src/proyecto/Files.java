/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Batman
 */
public class Files {
    
    private final String NomArch = "Final.Tesoem";
    List<Atributos> Datos = new ArrayList<>();
    
     public boolean VerificaArch(){
        File arhivo = new File(NomArch);
        if (!arhivo.exists()) return false;
        else return true;
    }
 //---------------------GRABAR----------------------------------    
    public boolean Grabar(List<Atributos> datos, int accion){
        FileWriter archivo;
        try{
            if(accion == 0 ){
                archivo = new FileWriter(NomArch,true);
            }else{
                archivo = new FileWriter(NomArch);
            }
            
            try(BufferedWriter bw = new BufferedWriter(archivo)){
                for(Atributos dato : datos){
                    bw.write(Conviertegson(dato) + "\n");
                }
                bw.close();
            }
            archivo.close();
        }catch(Exception ex){
            return false;
        }
        return true;
    }
 //----------------------lEER-----------------------------------------
    public boolean Leer(){
        String cadena = " ";
            try{
                FileReader archivo = new FileReader(NomArch);
                BufferedReader br = new BufferedReader(archivo);
                while ((cadena = br.readLine())!= null){
                Datos.add(ConvierteClase(cadena));
            }
                br.close();
                archivo.close();
            }catch(Exception ex){
                return false;
            }
            return true;
    }
    
 //---------------------AGREGAR--------------------------------------------
    public void Agregar(Atributos dato){
        Datos.add(dato);
    }
 //---------------------CONVERTIR GSON--------------------------------------
    private String Conviertegson(Atributos dato){
        Gson gson = new Gson();
        return gson.toJson(dato);
        
    }
  //--------------------CONVIERTE CLASE------------------------------------- 
    private Atributos ConvierteClase(String dato){
        Gson gson = new Gson();
        return gson.fromJson(dato, Atributos.class);
        
    }
    
    public List<Atributos> getDatos(){
        return Datos;
    }
    
}
