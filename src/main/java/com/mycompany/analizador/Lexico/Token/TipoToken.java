package com.mycompany.analizador.Lexico.Token;

public enum TipoToken {

    CERO("num", 6),
    NUMERO("num", 5),
    IDENTIFICADOR("id", 4),
    LITERAL("lit", 14),
    COMENTARIO("comnt", 15),
    SIGNO_MAS("+", 8),
    SIGNO_MENOS("-", 7),
    SIGNO_POR("*", 9),
    SIGNO_IGUAL("=", 10),
    PARENTESIS_ABRIR("(", 11),
    PARENTESIS_CERRAR(")", 12),
    FIN_PILA("$",20);

     private int aceptacion;
    private String valor;

    private TipoToken(String simbolo, int aceptacion) {
        this.valor = simbolo;
        this.aceptacion = aceptacion;
    }

    public String getValor() {
        return valor;
    }

    public int getAceptacion() {
        return aceptacion;
    }

    @Override
    public String toString() {
        return valor;
    }

}