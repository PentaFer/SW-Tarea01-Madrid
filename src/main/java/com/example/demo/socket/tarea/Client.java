package com.example.demo.socket.tarea;

import java.io.*;
import java.net.*;

public class Client{
    private static final String IP_SERVIDOR = "localhost"; // Cambia esto a la IP del servidor
    private static final int PUERTO_SERVIDOR = 13;
    private static final String RUTA_ARCHIVO_RECIBIDO = "D:/server/prueba-2.txt"; // Nombre del archivo recibido

    public static void main(String[] args) {
        try {
            Socket socketServidor = new Socket(IP_SERVIDOR, PUERTO_SERVIDOR);
            System.out.println("Conectado al servidor");

            // Recibir archivo del servidor
            recibirArchivo(socketServidor);

            socketServidor.close();
            System.out.println("Desconectado del servidor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recibirArchivo(Socket socketServidor) throws IOException {
        InputStream is = socketServidor.getInputStream();
        FileOutputStream fos = new FileOutputStream(RUTA_ARCHIVO_RECIBIDO);

        byte[] buffer = new byte[1024];
        int bytesLeidos;
        while ((bytesLeidos = is.read(buffer)) != -1) {
            fos.write(buffer, 0, bytesLeidos);
        }

        fos.close();
        System.out.println("Archivo recibido y guardado como " + RUTA_ARCHIVO_RECIBIDO);
    }
}
