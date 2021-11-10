/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.analizador.Lexico.Token;

/**
 *
 * @author alanm
 */
public enum Reservada {
    
    ESCRIBIR,
    REPETIR,
    INICIAR,
    SI,
    VERDADERO,
    FALSO,
    ENTONCES,
    FIN;

    @Override
    public String toString() {
        return name();
    }
}
