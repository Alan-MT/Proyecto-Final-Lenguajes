/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.analizador.Manejadores;

import com.mycompany.analizador.frontend.Principal;
import com.mycompany.analizador.Archivos.ArchivoTexto;
import com.mycompany.analizador.Lexico.Analizador;
import com.mycompany.analizador.Lexico.Token.Errores;
import com.mycompany.analizador.Lexico.Token.Token;
import com.mycompany.analizador.Sintactico.Sintax;
import com.mycompany.analizador.frontend.ReporteTokens;
import com.mycompany.analizador.frontend.Salida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alanm
 */
public class Manejadorbotones {

    private JFileChooser Seleccionar = new JFileChooser();
    private File archivo;
    private Principal princi;
    private ArchivoTexto lector;
    private JFileChooser fileChosser = new JFileChooser();
    private ArrayList<Token> listaTokens;
    private ArrayList<Errores> listaErrores;
    private Analizador analizador;
    private ReporteTokens report;
    private Salida salida;

    public Manejadorbotones() {
        this.princi = new Principal();
        this.lector = new ArchivoTexto();
        this.princi.setVisible(true);
        this.listaTokens = new ArrayList<>();
        this.listaErrores = new ArrayList<>();
        this.analizador = new Analizador();
        this.report = new ReporteTokens();
        this.salida = new Salida();
        this.princi.getTextAreaSin().setVisible(false);
        this.princi.getAnalizarSintactico().setVisible(false);
        
        this.princi.getAbrir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
                fileChosser.setFileFilter(filtro);
                int seleccion = fileChosser.showOpenDialog(princi.getAbrir());
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    archivo = fileChosser.getSelectedFile();
                    try {
                        lector.leerFichero(archivo, princi.getjTextArea1());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al leer el archivo");
                    }
                }
            }
        });

        this.princi.getGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if (archivo != null) {
                        lector.GuardarArchivos(archivo, princi.getjTextArea1().getText());
                    } else {
                        if (Seleccionar.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION) {
                            archivo = new File(Seleccionar.getSelectedFile() + ".txt");
                            lector.GuardarArchivos(archivo, princi.getjTextArea1().getText());
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        );

        this.princi.getNuevo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (archivo == null || princi.getjTextArea1().getText() == null) {
                    princi.getjTextArea1().setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Debes de guardar lo anterior");
                }
            }
        });
        
                this.princi.getAnalizardor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                if (princi.getjTextArea1().getText().length() != 0) {
                    analizador.analizar(princi.getjTextArea1().getText(), listaErrores, listaTokens);
                    mostrarErrores();
                    if (listaErrores == null || listaErrores.size() == 0) {
                        report.setVisible(true);
                        reporteTokens();

                            princi.getTextAreaSin().setVisible(true);
                            princi.getTextAreaSin().setText(princi.getjTextArea1().getText());
                            princi.getjTextArea1().setEditable(false);
                            princi.getAnalizarSintactico().setVisible(true);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Recuerda que debes subir un archivos para iniciar");
                }

            }
        });
                this.princi.getAnalizarSintactico().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                Sintax sin = new Sintax();
                String doc = "";
                sin.analizar(princi.getTextAreaSin().getText(), listaTokens, doc);
               
                salida.setVisible(true);
                salida.getjTextSalida().setText(doc);
            }
                });
                
    }


    public void mostrarErrores() {
        DefaultTableModel modelo = (DefaultTableModel) princi.getjTable1().getModel();
        modelo.setRowCount(0);
        for (Errores j : listaErrores) {
            Object[] row = new Object[4];
            row[0] = j.getCadena();
            row[1] = j.getDescripcion();
            row[2] = j.getColumna();
            row[3] = j.getFila();
            modelo.addRow(row);
        }
    }
    
    public void reporteTokens() {
        DefaultTableModel modelo = (DefaultTableModel) report.getjTable1().getModel();
        modelo.setRowCount(0);
        for (Token T : listaTokens) {
            Object[] row = new Object[5];
            row[0] = T.getTipoToken();
            row[1] = T.getLexema();
            row[2] = T.getFila();
            row[3] = T.getColumna();
            modelo.addRow(row);
        }
    }
    


}
