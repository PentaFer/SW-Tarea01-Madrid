package com.example.demo.socket.tarea;

import java.io.*;
import java.net.*;

public class Server {
    private static final int PUERTO = 13;
    private static final String RUTA_ARCHIVO = "D:/server/prueba.txt"; // Cambia esto al archivo que deseas enviar

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("El servidor está esperando conexiones...");

            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("Cliente conectado");

                // Enviar archivo al cliente
                enviarArchivo(socketCliente);

                socketCliente.close();
                System.out.println("Cliente desconectado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void enviarArchivo(Socket socketCliente) throws IOException {
        File archivoAEnviar = new File(RUTA_ARCHIVO);
        FileInputStream fis = new FileInputStream(archivoAEnviar);
        OutputStream os = socketCliente.getOutputStream();

        byte[] buffer = new byte[1024];
        int bytesLeidos;
        while ((bytesLeidos = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytesLeidos);
        }

        os.close();
        fis.close();
        System.out.println("Archivo enviado con éxito");
    }
}
