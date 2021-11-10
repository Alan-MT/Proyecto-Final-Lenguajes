/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.analizador.Sintactico;

/**
 *
 * @author alanm
 */
public class TablasSimbolo {

    public TablasSimbolo() {
    }

    private final String[] estructuras = { "G", "P", "P’", "R", "C", "B", "N", "N’", "X", "T", "U", "U’", "V" };

    private final String[] terminales = { "ESCRIBIR", "REPETIR", "SI", "VERDADERO", "FALSO", "(", ")", 
                                        "num", "id", "literal", "-", "+", "*", "=", "FIN", "$"} ;

    private final String[][] producciones = {
        /*  0 */ { "#" }, // epsilon
        /*  1 */ { "P", "G" },
        /*  2 */ { "R", "G" },
        /*  3 */ { "C", "G" },
        /*  4 */ { "N", "G" },
        /*  5 */ { "ESCRIBIR", "T", "FIN" },
        /*  6 */ { "P", "P’" },
        /*  7 */ { "REPETIR", "V", "INICIAR", "P’", "FIN" },
        /*  8 */ { "SI", "B", "ENTONCES", "P", "FIN" },
        /*  9 */ { "VERDADERO" },
        /* 10 */ { "FALSO" },
        /* 11 */ { "id", "N’" },
        /* 12 */ { "num", "U’" },
        /* 13 */ { "-", "V", "U’" },
        /* 14 */ { "(", "N", ")" },
        /* 15 */ { "=", "X", "FIN" },
        /* 16 */ { "U’" },
        /* 17 */ { "(", "X", ")", "U’" },
        /* 18 */ { "U", "U’" },
        /* 19 */ { "literal" },
        /* 20 */ { "U" },
        /* 21 */ { "-", "V" },
        /* 22 */ { "V" },
        /* 23 */ { "+", "X", "U’" },
        /* 24 */ { "*", "X", "U’" },
        /* 25 */ { "num" },
        /* 26 */ { "id" }
        
    };

    private int[][] tablaSimbolos = {
        //           --- 0 --- 1 --- 2 --- 3 --- 4 --- 5 --- 6 --- 7 --- 8 --- 9 -- 10 -- 11 -- 12 -- 13 -- 14 -- 15 -- 
        //             "ESC","REP"," SI","VRD","FAL"," ( "," ) ","num","ide","lit"," - "," + "," * "," = ","FIN"," $ "
        /*  0, G,  */ {  1,    2,    3,   -1,   -1,    4,   -1,    4,    4,   -1,    4,   -1,   -1,   -1,   -1,    0 },
        /*  1, P,  */ {  5,    0,    0,   -1,   -1,    0,   -1,    0,    0,   -1,    0,   -1,   -1,   -1,    0,    0 },
        /*  2, P’, */ {  6,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,    0,   -1 },
        /*  3, R,  */ { -1,    7,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1 },
        /*  4, C,  */ { -1,   -1,    8,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1 },
        /*  5, B,  */ { -1,   -1,   -1,    9,   10,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1 },
        /*  6, N,  */ { -1,   -1,   -1,   -1,   -1,   14,   -1,   12,   11,   -1,   13,   -1,   -1,   -1,   -1,   -1 },
        /*  7, N’, */ {  0,    0,    0,   -1,   -1,    0,    0,    0,    0,   -1,    0,   16,   16,   15,    0,    0 },
        /*  8, X,  */ { -1,   -1,   -1,   -1,   -1,   17,   -1,   18,   18,   -1,   18,   -1,   -1,   -1,   -1,   -1 },
        /*  9, T,  */ { -1,   -1,   -1,   -1,   -1,   -1,   -1,   20,   20,   19,   20,   -1,   -1,   -1,   -1,   -1 },
        /* 10, U,  */ { -1,   -1,   -1,   -1,   -1,   -1,   -1,   22,   22,   -1,   21,   -1,   -1,   -1,   -1,   -1 },
        /* 11, U’  */ {  0,    0,    0,   -1,   -1,    0,    0,    0,    0,   -1,    0,   23,   24,   -1,    0,    0 },
        /* 12, V,  */ { -1,   -1,   -1,   -1,   -1,   -1,   -1,   25,   26,   -1,   -1,   -1,   -1,   -1,   -1,   -1 }        
    };

    private int getNumCasilla (String valor, String[] casilla) {

        for (int i = 0; i < casilla.length; i++) {            
            if (valor.equals(casilla[i])) {
                return i;
            }
        }
        return -1;
    }

    public String[] getProduccion(String noTerminal, String terminal) {
        
        int numNoTermi = getNumCasilla(noTerminal, this.estructuras);
        int numTermi = getNumCasilla(terminal, this.terminales);
        
        int numProduccion = this.tablaSimbolos[numNoTermi][numTermi];

        if (numProduccion >= 0) {
            return this.producciones[numProduccion];
        } else {
            return null;
        }

    }


}
