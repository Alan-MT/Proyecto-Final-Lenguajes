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
public class Errores {
    
    private String cadena;
    private String Descripcion;
    private int fila, columna;

    public Errores(String cadena) {
        this.setCadena(cadena);
    }

    public Errores(String cadena, int fila, int columna) {
        this.setCadena(cadena);
        this.setFila(fila);
        this.setColumna(columna);
        setDescripcion(Descripcion);
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
    
}
