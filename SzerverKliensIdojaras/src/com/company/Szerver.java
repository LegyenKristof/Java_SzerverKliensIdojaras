package com.company;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Szerver {
    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();
        System.out.println("A szerver indul");
        try {
            ServerSocket socket = new ServerSocket(8080);

            while (true) {
                Socket kapcsolat = socket.accept();
                InetAddress kliens = kapcsolat.getInetAddress();
                System.out.println("Kliens címe: " + kliens.getHostAddress());
                Ugyfelkiszolgalo uk = new Ugyfelkiszolgalo(kapcsolat);
                exe.submit(uk);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
