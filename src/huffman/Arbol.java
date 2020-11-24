/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author logra
 */
public class Arbol{
    
    NodoArbol raiz = null;
    public String[] codes = new String[256];
    
    public void insertar(int dato, char letra){
        NodoArbol nuevo = crearNodo(dato, letra);
        
        if(raiz == null){
            raiz = nuevo;
        }else{
            NodoArbol aux  = raiz;
            NodoArbol ant = null;
            while(aux!=null){
                ant = aux;
                if(nuevo.dato > aux.dato){
                    aux = aux.der;
                }else{
                    aux = aux.izq;
                }    
            }
            if(nuevo.dato > ant.dato){
                ant.der = nuevo;
            }else{
                ant.izq = nuevo;
            }
        }
    }
    
    public void recorrer(Recorrido tipo){
        switch(tipo){
            case INORDER:
                inOrden(this.raiz);
                System.out.print("\n");
                break;
            case POSTORDER:
                postOrden(this.raiz);
                System.out.print("\n");
                break;
            case PREORDER:
                preOrden(this.raiz);
                System.out.print("\n");
                break;
            case HOJAS:
                soloHojas(this.raiz);
                System.out.print("\n");
                break;
            case NIVELES:
                niveles(this.raiz);
                System.out.print("\n");
                break;
            case CODIGOS:
                String nuevo = new String();
                codigos(this.raiz, nuevo,nuevo);
                System.out.print("\n");
                break;
        }
    }
    
    public NodoArbol minimo(NodoArbol aux){
        while(aux.izq != null){
            aux = aux.izq;
        }
        return aux;
    }
    
    public void soloHojas(NodoArbol aux){
        if(aux != null){
            soloHojas(aux.izq);
            
            if(aux.izq == null && aux.der == null){
                System.out.print(aux.letra + ": " + aux.dato + " ");
            }
            
            soloHojas(aux.der);
        }
    }
    
    public void codigos(NodoArbol aux, String ruta, String num){
        ruta = ruta + num;
        if (aux != null) {
            codigos(aux.izq, ruta, "0");
            codigos(aux.der, ruta, "1");
            if(aux.der == null && aux.izq == null){
                codes[(int)aux.letra] = ruta;
            }
        }
    }
    
    public void imprimirArbol(FileWriter fp) throws IOException{
        escribirArbol(raiz, fp);
    }
    
    public void escribirArbol(NodoArbol aux, FileWriter fp) throws IOException{
        if(aux == null){
            fp.write("#");
            return;
        }
        fp.write(aux.letra);
        escribirArbol(aux.izq, fp);
        escribirArbol(aux.der, fp);
    }
    
    public void cargarArbol(BufferedReader fp) throws IOException{
        raiz = crearArbol(raiz, fp);
    }
    
    public NodoArbol crearArbol(NodoArbol aux, BufferedReader fp) throws IOException{
        char l = (char)fp.read();
        if(l == '#' || (int)l == -1){
            return null;
        }
        aux = crearNodo(1, l);
        aux.izq = crearArbol(aux.izq, fp);
        aux.der = crearArbol(aux.der, fp);
        return aux;
    }
       
    public void preOrden(NodoArbol aux){
        if (aux != null) {
            System.out.print(aux.dato + " ");
            preOrden(aux.izq);
            preOrden(aux.der);
        } 
    }

    public void inOrden(NodoArbol aux){
        if (aux != null) {
            inOrden(aux.izq);
            System.out.print(aux.dato + " ");
            inOrden(aux.der);
        } 
    }

    public void postOrden(NodoArbol aux){
        if (aux != null) {
            postOrden(aux.izq);
            postOrden(aux.der);
            System.out.print(aux.dato + " ");
        } 
    }
    
    public void niveles(NodoArbol aux){
        Cola colita = new Cola();
        if(aux != null){
            colita.push(aux);
            while(!colita.isEmpty()){
                NodoArbol hoja = colita.pop();
                System.out.print(hoja.dato + " ");
                if(hoja.izq != null){
                    colita.push(aux.izq);
                }
                if(hoja.der != null){
                    colita.push(aux.der);
                }
            }
        }
    }

    public NodoArbol encontrar(NodoArbol aux, int dato){
        if (aux == null || dato == aux.dato) return aux;
        if (dato < aux.dato) return encontrar(aux.izq, dato);
        return encontrar(aux.der, dato);
    }
    
    public void buscarNumero(int dato){
	NodoArbol aux = encontrar(raiz, dato);
        if(aux == null){
            System.out.println("No se encontro el numero " + dato);
        }else{
            System.out.println("Se encontro el numero " + aux.dato);
        }
    }
    
    public void insertarNodo(NodoArbol nuevo){
        raiz = nuevo;
    }
    
    public NodoArbol crearNodo(int dato, char letra){
        NodoArbol nuevo = new NodoArbol();
        nuevo.letra = letra;
        nuevo.dato = dato;
        nuevo.izq = null;
        nuevo.der = null;
        
        return nuevo;
    }
    
}
