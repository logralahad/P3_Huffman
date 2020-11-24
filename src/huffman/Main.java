/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author logra
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    public static final Integer EOF = -1;
    public static final Integer EOL = 10;
    public static final Integer SPACE = 32;

    /**
     * @param args the command line arguments
     */
       
    public static void Huffman(Lista lista, int modo){        
        try{
            
            int[] frecuencias = new int[256];
            Scanner sc = new Scanner(System.in);
            System.out.print("\nEscriba la direccion del archivo: ");
            String direccion = sc.nextLine();
            
            File archivo = new File(direccion, "INPUT.TXT");           
            BufferedReader origen = new BufferedReader(new FileReader(archivo));
            BufferedReader copia = new BufferedReader(new FileReader(archivo));
            
            System.out.print("\nEscriba la direccion donde quiere guardar el archivo: ");
            String dest_dirr = sc.nextLine();
            
            File writer = new File(dest_dirr, "OUTPUT.TXT");
            FileWriter destino = new FileWriter(writer, false);
            
            int byteInfo = 0;
            if(modo == 0){
                while( (byteInfo = origen.read()) != EOF){
                    frecuencias[byteInfo]++;
                }
                
                for(int j = 0; j < 256; ++j){
                    if(frecuencias[j] <= 0) continue;
                    else lista.insertar(frecuencias[j], (char)j);
                }
                
                lista.codigoArbol();
                Arbol groot = lista.inicio.arbol;
                groot.recorrer(Recorrido.CODIGOS);

                String[] cosas = groot.codes;
                while( (byteInfo = copia.read()) != EOF){
                    String code = cosas[byteInfo];
                    Integer codigo = Integer.parseInt(code);
                    byte op = codigo.byteValue();
                    destino.write(op);
                }
                
                for(int j = 0; j < 256; ++j){
                    if(cosas[j] == null) continue;
                    else{
                        Integer codigo = Integer.parseInt(cosas[j]);
                        byte op = codigo.byteValue();
                        System.out.print(op + " ");
                        System.out.println((char)j + ": " + cosas[j] + " ");
                    }
                }
                
            }
            
            else{
                
                System.out.println("\nArchivo desencriptado exitosamente.\nSu archivo esta en " + dest_dirr);
            }
            
            
            origen.close();
            copia.close();
            destino.close();
            
        }
        catch(Exception e){
            System.out.println("Hubo un error");
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Lista listita = new Lista();
        System.out.println("   Algoritmo de Huffman");
        System.out.println("[0]Comprimir\t[1]Descomprimir");
        
        Scanner opc = new Scanner(System.in);
        System.out.print("Seleccione el modo de operacion: ");
        int modo = opc.nextInt();
        //C:\Users\logra\Desktop
        Huffman(listita, modo);
        
    }
    
}
