package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread{
    private Socket socketClient;

    public MioThread(Socket s){
        this.socketClient = s;
    }

    public void run(){
        try {
            //Tutta sta roba sotto dentro al thread
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());
            
            String stringRicevuta;
            String operazione;
            String stringaTrasformata;
            
            do {
                stringRicevuta = in.readLine(); //Leggere ciò che ci manda il client
                System.out.println("La stringa ricevuta: " + stringRicevuta); //Leggiamo il messaggio del client

                if(stringRicevuta.equalsIgnoreCase("exit")){
                    stringaTrasformata = "!";;
                } else {
                    operazione = in.readLine();
                    System.out.println("Operazione: " + operazione);
            
                    switch(operazione){
                        case "a": 
                        stringaTrasformata = stringRicevuta.toUpperCase(); //Metto in UpperCase
                            break;

                        case "b":
                        stringaTrasformata = stringRicevuta.toLowerCase(); //Metto in LowerCase
                            break;

                        case "c":
                        stringaTrasformata = new StringBuilder(stringRicevuta).reverse().toString(); //Stringa ribaltata
                            break;

                        case "d":
                        stringaTrasformata = stringRicevuta.length() + ""; //Cont dei caratteri presenti nella stringa
                            break;

                        default:
                        stringaTrasformata = "!!!";
                            break;
                    }
                }
                    
                out.writeBytes(stringaTrasformata + "\n"); //Lo mando al client
            
            } while (!stringRicevuta.equalsIgnoreCase("exit"));

            this.socketClient.close();

        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\n Collegamento interrotto");
    }
}
