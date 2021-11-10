/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.analizador.Sintactico;

import com.mycompany.analizador.Lexico.Token.*;
import java.util.ArrayList;

/**
 *
 * @author alanm
 */
public class Sintax {

    TablasSimbolo tabla = new TablasSimbolo();
    Pila pila = new Pila("G");

    //String[] tokens;
    final String epsilon = "#";

     public void analizar (String texto, ArrayList<Token> listaTokens) {
        
        Token finPila = new Token("$", TipoToken.FIN_PILA);
        listaTokens.add(finPila);
        
        Token token = getToken(listaTokens);
        //String nuevoToken = getToken(), reduccion="";
        System.out.println("EL ARRAYLIST TIENE " + listaTokens.size() + " ELEMENTOS.");
        String nuevoToken = token.getTipoToken().toString();
        System.out.println(nuevoToken);
        String reduccion = "";
        do {
            // REPETIR 5 INICIAR ESCRIBIR "hola mundo" FIN 
            pila.printPila();
            
            if (nuevoToken.equals(pila.getUltimoNodo())) {
                reduccion = pila.pop();
                System.out.println("Reduce => " + reduccion);

                token = getToken(listaTokens);
                nuevoToken = token.getTipoToken().toString();
                System.out.println("\nNUEVO TOKEN: " + nuevoToken);
                
                
            } else if (epsilon.equals(pila.getUltimoNodo())) {
                reduccion = pila.pop();
                System.out.println("Reduce => " + reduccion);                
                
            } else {
                String notermin = pila.pop();
                pushProduccion(notermin, nuevoToken);
                
            }

        } while (!pila.getUltimoNodo().equals("$"));

        pila.printPila();
        System.out.println("\n - - - ANALISIS FINALIZADO - - -\n");
    }

    public void pushProduccion (String noTerminal, String terminal) {
        String produccion = " " + noTerminal + " -> ";
        String[] nuevosNodos = tabla.getProduccion(noTerminal, terminal);

        for (int i = 1; i <= nuevosNodos.length; i++) {
            pila.push(nuevosNodos[nuevosNodos.length-i]);
            produccion += nuevosNodos[i - 1] + " ";
        }
        System.out.println(produccion);
    }

    static int numToken=0;

    public Token getToken (ArrayList<Token> listaTokens) {

        if (numToken < listaTokens.size()) {
            //String tkn = tokens[numToken];
            Token tkn = listaTokens.get(numToken);
            numToken++;
            return tkn;
        } else {
            return new Token("token falso", Reservada.FIN);
        }

    }

}
