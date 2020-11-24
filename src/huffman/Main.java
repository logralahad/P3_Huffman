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
            if(modo == 0){
                int[] frecuencias = new int[256];
                Scanner sc = new Scanner(System.in);
                System.out.println("\nEl archivo debe tener como nombre INPUT");
                System.out.print("\nEscriba la direccion del archivo: ");
                String direccion = sc.nextLine();

                File archivo = new File(direccion, "INPUT.TXT");           
                BufferedReader origen = new BufferedReader(new FileReader(archivo));
                BufferedReader copia = new BufferedReader(new FileReader(archivo));
                
                System.out.println("\nSon 2 archivis: OUTPUT & Tabla.");
                System.out.print("\nEscriba la direccion donde quiere guardar los archivos: ");
                String dest_dirr = sc.nextLine();

                File writer = new File(dest_dirr, "OUTPUT.TXT");
                File writer2 = new File(dest_dirr, "TABLA.TXT");
                FileWriter destino = new FileWriter(writer, false);
                FileWriter destino2 = new FileWriter(writer2, false);

                int byteInfo = 0;
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
                    destino.write(code);
                }
                
                groot.imprimirArbol(destino2);
                System.out.println("\nArchivo comprimido exitosamente.\nSu archivo esta en " + dest_dirr);
                origen.close();
                copia.close();
                destino.close();
                destino2.close();
            }
            else{
                Scanner sc = new Scanner(System.in);
                
                System.out.println("\nEl texto a descomprimir debe tener como nombre OUTPUT.");
                System.out.println("La tabla debe tener como nombre TABLA.");
                System.out.print("\nEscriba la direccion de los archivos: ");
                String direccion = sc.nextLine();

                File archivo = new File(direccion, "OUTPUT.TXT");           
                BufferedReader origen = new BufferedReader(new FileReader(archivo));
                
                File archivo2 = new File(direccion, "TABLA.TXT");           
                BufferedReader origen2 = new BufferedReader(new FileReader(archivo2));
                
                System.out.print("\nEscriba la direccion donde quiere guardar el archivo: ");
                String dest_dirr = sc.nextLine();

                File writer = new File(dest_dirr, "DESCOMPRIMIDO.TXT");
                FileWriter destino = new FileWriter(writer, false);
                
                Arbol groot = new Arbol();
                groot.cargarArbol(origen2);
                String s = origen.readLine();
                String ans = "";
                NodoArbol inicio = groot.raiz;
                
                for(int i = 0; i < s.length(); ++i){
                    if(s.charAt(i) == '0'){
                        inicio = inicio.izq;
                    }else{
                        inicio = inicio.der;
                    }
                    
                    if(inicio.der == null && inicio.izq == null){
                        ans += inicio.letra;
                        inicio = groot.raiz;
                    }
                }
                
                destino.write(ans);

                System.out.println("\nArchivo descomprimido exitosamente.\nSu archivo esta en " + dest_dirr);
                origen.close();
                origen2.close();
                destino.close();
            }
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
