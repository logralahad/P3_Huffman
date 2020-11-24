/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author logra
 */
public class Cola {
    
    NodoQ inicio = null;
    NodoQ fin = null;
    
    public NodoQ crearNodo(NodoArbol dato){
        NodoQ nuevo = new NodoQ();
        nuevo.anterior = null;
        nuevo.siguiente = null;
        nuevo.dato = dato;
        return nuevo;
    }
    

    public void push(NodoArbol dato){
        NodoQ nuevo = crearNodo(dato);
        if(isEmpty()){
            inicio = nuevo;
            fin = nuevo;
        }
        else{
            nuevo.anterior = fin;
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }
    

    public NodoArbol pop(){
        NodoArbol resultado = inicio.dato;
        if(inicio == fin){
            inicio = null;
            fin = null;
        }else{
            inicio = inicio.siguiente;
            inicio.anterior = null;
        }
        
        return resultado;
    }
    
    public boolean isEmpty(){
        return (inicio == null) && (fin == null);
    }

}