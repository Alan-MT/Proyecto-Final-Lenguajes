package com.mycompany.analizador.Lexico;

import com.mycompany.analizador.Lexico.Token.*;
import java.util.ArrayList;

/**
 *
 * @author alanm
 */
public class Analizador {

//    private Transicion trans;
//
//    public Analizador() {
//        this.trans = new Transicion();
//    }

    static String transiciones;

    public void analizar(String texto, ArrayList<Errores> listaErrores, ArrayList<Token> listaTokens) {
        
        listaTokens.clear();
        listaErrores.clear();
        transiciones = ("\n  TRANSICIONES EN EL AUTOMATA:\n  ---------------------------\n\n");    
        String cadena = "";
        int estadoActual = 0, numSimbolo=0;
        int fila = 1;
       int columna = 1;

        for (int i = 0; i < texto.length(); i++) {   
            
            char chr = texto.charAt(i);
            //numSimbolo = MatrizLexico.getNumSimbolo(chr);
            
            if ((texto.charAt(i) == ' ') || (texto.charAt(i) == '\n') || (texto.charAt(i) == '\t')) { 

                if ((Character.isWhitespace(chr) && ( estadoActual==1 || estadoActual == 3 || estadoActual == 13)
                    || (chr=='\n' && (estadoActual==3))) ) {
                    
                    this.addTransicion("  S" + estadoActual + "  ->");
                    //System.out.println("  S" + estadoActual + "  ->");
                    estadoActual = MatrizLexico.nuevoEstado(chr, estadoActual);
                    this.addTransicion("  " + chr + "  ->  S" + estadoActual + "\n");
                    //System.out.println("  " + chr + "  ->  S" + estadoActual + "\n");
                    cadena += chr;

                    if (estadoActual < 0) {
                        this.agregarError(cadena, estadoActual, fila, columna, listaErrores);                    
                        estadoActual = 0;
                        cadena = "";
                    }


                } else {

                    if (!cadena.isEmpty()) {
                        this.agregarToken(cadena, estadoActual, fila, columna, listaTokens);
                        estadoActual = 0;
                        cadena = "";
                    }
                }


                
            } else {
                
                this.addTransicion("  S" + estadoActual + "  ->");
                //System.out.println("  S" + estadoActual + "  ->");
                estadoActual = MatrizLexico.nuevoEstado(chr, estadoActual);
                this.addTransicion("  " + chr + "  ->  S" + estadoActual + "\n");
                //System.out.println("  " + chr + "  ->  S" + estadoActual + "\n");
                cadena += chr;
                
                if (estadoActual < 0) {
                    this.agregarError(cadena, estadoActual, fila, columna, listaErrores);                    
                    estadoActual = 0;
                    cadena = "";
                }
                
            }
            
           switch (texto.charAt(i)) {
                case ' ':
                    break;
                case '\n':
                    fila++;
                    columna = 0;
                    break;
            }
            columna++;
        }

    }
    
    private void agregarError(String cadena, int estadoActual, int fila, int columna, ArrayList<Errores> listaErrores) {
        Errores erl = new Errores(cadena, fila, columna);
        if (estadoActual==-1) {
            erl.setDescripcion("Error léxico");
        } else if (estadoActual==-5) {
            erl.setDescripcion("Símbolo no admitido");
        }
        listaErrores.add(erl);
    }
    
    private void agregarToken(String cadena, int estadoActual, int fila, int columna, ArrayList<Token> listaTokens) {

        Enum enumToken = null;
        for (TipoToken tk : TipoToken.values()) {
            if (tk.getAceptacion() == estadoActual) {
                enumToken = tk;    
            }
        }

        if (estadoActual==4) {
            for (Reservada tk : Reservada.values()) {
                if (tk.toString().equals(cadena)) {
                    enumToken = tk;
                }
            }
        }

        int columnaToken = columna - cadena.length();
        Token nuevoToken = new Token(cadena, enumToken, fila, columnaToken);
        listaTokens.add(nuevoToken);
        this.addTransicion("  Token: " + nuevoToken.getTipoToken().name() + ",   Lexema: " + nuevoToken.getLexema() + "\n\n");

    }
       
    public void addTransicion(String transiciones) {       
        this.transiciones = this.transiciones + transiciones;
    }
    
    public String getTransiciones() {
        return transiciones;
    }

}
