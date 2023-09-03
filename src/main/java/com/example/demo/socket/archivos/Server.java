package com.example.demo.socket.archivos;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private final Integer PORT = 13;
	
	public Server() {
	
		try {
			Socket clientSocket;
			
			ServerSocket serverSocket = new ServerSocket(PORT);
			while(true) {
				System.out.println("------------- 1 Iniciando ServerSocket ---------------");
				System.out.println("------------- 2 A la espera de  clientSocket ---------");
				clientSocket = serverSocket.accept();
				System.out.println("------------- 3 Llegada de un clientSocket -----------");
				
				File fileDestino = new File("D:/server/angular-madrid-2.png");
				FileOutputStream fos = new FileOutputStream(fileDestino);
				DataInputStream entrada = new DataInputStream(clientSocket.getInputStream());
				
				int byteLeidos;
				while ( (byteLeidos = entrada.read())!= -1) {
					fos.write(byteLeidos);
				}
				fos.close();
				
				clientSocket.close();
				System.out.println("------------- 4 Finaliza atención al clientSocket ----");
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
	
}