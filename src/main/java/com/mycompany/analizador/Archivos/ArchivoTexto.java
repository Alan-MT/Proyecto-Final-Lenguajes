/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.analizador.Archivos;

import java.awt.Choice;
import java.awt.Component;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author alanm
 */
public class ArchivoTexto {

    public void leerFichero(File archivo, JTextArea text) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            //poner el operador de tokens para verificacion
            text.append("\n" + linea);
        }
        text.setEditable(false);
        fr.close();

    }

    public void GuardarArchivos(File archivo, String documento) {
        try {
                BufferedWriter save = new BufferedWriter(new FileWriter(archivo));
                save.write(documento);
                save.close();
                JOptionPane.showMessageDialog(null, "Guardado");
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR");
        }

    }
    

}
