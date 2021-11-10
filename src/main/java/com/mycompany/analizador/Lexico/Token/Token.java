package com.mycompany.analizador.Lexico.Token;

/**
 *
 * @author alanm
 */
public class Token {


    private String lexema;
    private Enum tipoToken;
    private int fila, columna;

    public Token(String lexema, Enum tipoToken) {
        this.setLexema(lexema);
        this.tipoToken = tipoToken;
    }

    public Token(String lexema, Enum tipoToken, int fila, int columna) {
        this.setLexema(lexema);
        this.setTipoToken(tipoToken);
        this.setFila(fila);
        this.setColumna(columna);
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public Enum getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(Enum tipoToken) {
        this.tipoToken = tipoToken;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
}
