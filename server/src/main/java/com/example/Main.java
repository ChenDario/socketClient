package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        //Main col minimo necessario
        System.out.println("Server avviato!");
        ServerSocket server = new ServerSocket(3000); // Ho creato un server con la porta 3000
        do {
            Socket s = server.accept(); // Socket che vuole entrare nel server
            System.out.println("Il client si è connesso");
            MioThread t = new MioThread(s);
            t.start();
        } while (true);

        //Chiudo tutto
        //s.close();
        //server.close();
    }
}