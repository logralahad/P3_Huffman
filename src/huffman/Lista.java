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
public class Lista {
    
    NodoL inicio = null;
    
    public void insertar(int dato, char letra){
        NodoL nuevo = nuevoNodo(dato, letra);
        if(isEmpty()){
            inicio = nuevo;
        }else if(inicio.arbol.raiz.dato > nuevo.arbol.raiz.dato){
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }else{
            NodoL ant = null;
            NodoL aux = inicio;
            
            while(aux != null && aux.arbol.raiz.dato <= nuevo.arbol.raiz.dato){
                ant = aux;
                aux = aux.siguiente;
            }
            
            ant.siguiente = nuevo;
            nuevo.siguiente = aux;            
        }
    }
    
    public void insertarArbol(Arbol groot){
        NodoL nuevo = nuevoArbol(groot);
        
        if(isEmpty()){
            inicio = nuevo;
        }else if(inicio.arbol.raiz.dato > nuevo.arbol.raiz.dato){
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }else{
            NodoL ant = null;
            NodoL aux = inicio;
            
            while(aux != null && aux.arbol.raiz.dato <= nuevo.arbol.raiz.dato){
                ant = aux;
                aux = aux.siguiente;
            }
            
            ant.siguiente = nuevo;
            nuevo.siguiente = aux;            
        }
    }
    
    public Arbol combinar(Arbol letra1, Arbol letra2){
        if(letra1.raiz == null || letra2.raiz == null){
            return letra1;
        }
        Arbol combinado = new Arbol();
        NodoArbol raiz_combinada = new NodoArbol();
        NodoArbol izq = letra1.raiz;
        NodoArbol der = letra2.raiz;
        
        raiz_combinada.letra = '^';
        raiz_combinada.dato = izq.dato + der.dato;
        raiz_combinada.izq = izq;
        raiz_combinada.der = der;
        
        combinado.insertarNodo(raiz_combinada);
        return combinado;
    }
    
    public void codigoArbol(){
        if(inicio != null){
            NodoL aux1 = inicio;
            NodoL aux2 = inicio.siguiente;
            
            while(aux2 != null){
                Arbol groot = combinar(aux1.arbol, aux2.arbol);
                this.insertarArbol(groot);
                inicio = inicio.siguiente.siguiente;
                aux1 = inicio;
                aux2 = inicio.siguiente;
            }
        }
    }
    
    public void recorrer(){
        NodoL aux = inicio;
        while( aux != null ){
            NodoArbol raiz = aux.arbol.raiz;
            System.out.print(raiz.letra + ": " + raiz.dato + " ");
            aux = aux.siguiente;
        }
    }
    
    public boolean isEmpty(){
        return inicio == null;
    }
    
    public NodoL nuevoNodo(int dato, char letra){
        Arbol groot = new Arbol();
        groot.insertar(dato, letra);
        
        NodoL nuevo = new NodoL();
        nuevo.arbol = groot;
        nuevo.siguiente = null;
        return nuevo;
    }
    
    public NodoL nuevoArbol(Arbol groot){       
        NodoL nuevo = new NodoL();
        nuevo.arbol = groot;
        nuevo.siguiente = null;
        return nuevo;
    }
    
}
