package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Kliens {
    public static void main(String[] args) {
        try {
            Socket kapcsolat = new Socket("localhost", 8080);
            DataInputStream szervertol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream szervernek = new DataOutputStream(kapcsolat.getOutputStream());

            Scanner sc = new Scanner(System.in);
            int menu;
            do {
                System.out.println("Válasszon az alábbi lehetőségek közül: ");
                System.out.println("0: Kilépés");
                System.out.println("1: Minden előrejelzés kiírása");
                System.out.println("2: Adott megye előrejelzése");
                System.out.println("3: Mai átlaghőmérséklet");
                System.out.println("4: Holnapi átlaghőmérséklet");
                System.out.println("5: Mai átlag időjárás");
                System.out.println("6: Holnapi átlag időjárás");
                menu = sc.nextInt();
                szervernek.writeInt(menu);
                switch (menu) {
                    case 2:
                        System.out.print("Adjon meg egy megyét: ");
                        String megye = sc.next();
                        szervernek.writeUTF(megye);
                        break;
                }
                szervernek.flush();
                System.out.println(szervertol.readUTF() + System.lineSeparator());
            } while (menu != 0);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
