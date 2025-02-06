/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.serviciosapi.FTP;

/**
 *
 * @author alumno
 */
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTP;
import javax.swing.*;
import java.io.*;
import java.io.IOException;

public class GestorFTP {

    private final FTPClient ftpClient;
    private JTextArea areaDeArchivos;

    public GestorFTP(JTextArea areaDeArchivos) {
        this.areaDeArchivos = areaDeArchivos;
        this.ftpClient = new FTPClient();  // No debería haber error aquí
    }

    public void conectarAlServidorFTP(String servidor, String usuario, String contrasena) {
        try {
            ftpClient.connect(servidor);
            boolean inicioSesion = ftpClient.login(usuario, contrasena);
            if (inicioSesion) {
                areaDeArchivos.append("Conectado al servidor: " + servidor + "\n");
                ftpClient.enterLocalPassiveMode(); // Modo pasivo para transferencia de archivos
                listarArchivos();
            } else {
                areaDeArchivos.append("Error al conectar.\n");
            }
        } catch (IOException ex) {
            areaDeArchivos.append("Error al conectar al servidor: " + ex.getMessage() + "\n");
        }
    }

    private void listarArchivos() {
        try {
            String[] archivos = ftpClient.listNames();
            if (archivos != null) {
                areaDeArchivos.append("Archivos en el servidor:\n");
                for (String archivo : archivos) {
                    areaDeArchivos.append(archivo + "\n");
                }
            }
        } catch (IOException ex) {
            areaDeArchivos.append("Error al listar archivos: " + ex.getMessage() + "\n");
        }
    }

    public void subirArchivo() {
        JFileChooser selectorDeArchivos = new JFileChooser();
        if (selectorDeArchivos.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File archivo = selectorDeArchivos.getSelectedFile();
            try (InputStream flujoDeEntrada = new FileInputStream(archivo)) {
                boolean exito = ftpClient.storeFile(archivo.getName(), flujoDeEntrada);
                if (exito) {
                    areaDeArchivos.append("Archivo subido: " + archivo.getName() + "\n");
                } else {
                    areaDeArchivos.append("Error al subir el archivo: " + archivo.getName() + "\n");
                }
            } catch (IOException ex) {
                areaDeArchivos.append("Error al subir el archivo: " + ex.getMessage() + "\n");
            }
        }
    }

    public void descargarArchivo() {
        String nombreArchivo = JOptionPane.showInputDialog("Ingrese el nombre del archivo para descargar:");
        if (nombreArchivo != null && !nombreArchivo.isEmpty()) {
            try (OutputStream flujoDeSalida = new FileOutputStream(nombreArchivo)) {
                boolean exito = ftpClient.retrieveFile(nombreArchivo, flujoDeSalida);
                if (exito) {
                    areaDeArchivos.append("Archivo descargado: " + nombreArchivo + "\n");
                } else {
                    areaDeArchivos.append("Error al descargar el archivo: " + nombreArchivo + "\n");
                }
            } catch (IOException ex) {
                areaDeArchivos.append("Error al descargar el archivo: " + ex.getMessage() + "\n");
            }
        }
    }
}
