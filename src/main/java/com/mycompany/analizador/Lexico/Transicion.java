
package com.mycompany.analizador.Lexico;

/**
 *
 * @author alanm
 */
public class Transicion {

    public int nuevoEstado(char ch, int estadoActual) {

        int SiguienteEstado = -1;

        if ((ch == ' ') | (ch == '\n')) {
            SiguienteEstado = 0;

        } else {
            switch (estadoActual) {

                case 0:

                    if (Character.isLetter(ch) || (Character.isUpperCase(ch))) { // S0 -> letra  -> S1 
                        SiguienteEstado = 1;
                    } else if (Character.isDigit(ch)) { // S0 -> dígito -> S2 
                        SiguienteEstado = 2;
                    } else if ((ch == '.') || (ch == ',') || (ch == ':') || (ch == ';')) { // S0 -> ( . | , | ; | : ) -> S5 
                        SiguienteEstado = 5;
                    } else if ((ch == '+') || (ch == '-') || (ch == '*') || (ch == '/') || (ch == '%')) { // S0 -> ( + | - | / | * | % ) -> S6 
                        SiguienteEstado = 6;
                    } else if ((ch == '(') || (ch == ')') || (ch == '[') || (ch == ']') || (ch == '{') || (ch == '}')) { // S0 -> ( '(' | ')' | '[' | ']' | '{' | '}' ) -> S7 
                        SiguienteEstado = 7;
                    } else { // No pertenece al alfabeto
                        SiguienteEstado = -5;
                    }
                    break;

                case 1:
                    if (Character.isLetterOrDigit(ch) || (Character.isUpperCase(ch))) { // S1 -> ( letra | dígito ) -> S1
                        SiguienteEstado = 1;
                        /*} else {
                        SiguienteEstado = 0;*/
                    }
                    break;

                case 2:
                    if (Character.isDigit(ch)) { // S2 -> dígito -> S2
                        SiguienteEstado = 2;
                    } else if (ch == '.') { // S2 ->  '.'   -> S3
                        SiguienteEstado = 3;
                        /*} else if (!Character.isLetter(ch)) {
                        SiguienteEstado = 0;*/
                    }
                    break;

                case 3:
                    if (Character.isDigit(ch)) { // S3 -> dígito -> S4
                        SiguienteEstado = 4;
                        /*} else if (Character.isLetter(ch) || (ch == '(') || (ch == ')') || (ch == '[') || (ch == ']') || (ch == '{') || (ch == '}')) {
                        SiguienteEstado = 0;*/
                    }
                    break;

                case 4:
                    if (Character.isDigit(ch)) { // S4 -> dígito -> S4
                        SiguienteEstado = 4;
                        /*} else if (!(Character.isLetter(ch) || ch == '.')) {
                        SiguienteEstado = 0;*/
                    }
                    break;
                case (5 | 6 | 7):
                    SiguienteEstado = 0;
                    break;
                /*case 5:
                    if (Character.isLetterOrDigit(ch)) { // S1 -> ( letra | dígito ) -> S1
                        SiguienteEstado = 0;
                    }
                    break;
                case 6:
                    if (Character.isLetterOrDigit(ch) || (ch == '(') || (ch == ')') || (ch == '[') || (ch == ']') || (ch == '{') || (ch == '}')) { // S1 -> ( letra | dígito ) -> S1
                        SiguienteEstado = 0;
                    }
                    break;
                case 7:
                    SiguienteEstado = 0;
                    break;*/
            }

        }
        return SiguienteEstado;

    }

}
