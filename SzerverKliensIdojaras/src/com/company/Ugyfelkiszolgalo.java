package com.company;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Ugyfelkiszolgalo implements Runnable{
    private HashMap<String, Idojaras> idojarasok;
    Socket kapcsolat;

    public Ugyfelkiszolgalo(Socket kapcsolat) {
        idojarasok = new HashMap<>();
        beolvas();
        this.kapcsolat = kapcsolat;
    }

    @Override
    public void run() {
        try {
            DataInputStream klienstol = new DataInputStream(kapcsolat.getInputStream());
            DataOutputStream kliensnek = new DataOutputStream(kapcsolat.getOutputStream());

            int menu;
            do {
                menu = klienstol.readInt();
                switch (menu) {
                    default:
                        kliensnek.writeUTF("Nem található ilyen opció");
                        break;
                    case 0:
                        kliensnek.writeUTF("Kilépés...");
                        break;
                    case 1:
                        String s1= "";
                        for (Map.Entry<String, Idojaras> entry : idojarasok.entrySet()) {
                            s1 += entry.getKey() + entry.getValue() + System.lineSeparator();
                        }
                        kliensnek.writeUTF(s1);
                        break;
                    case 2:
                        String s2 = "Nincs ilyen megye";
                        String megye = klienstol.readUTF();
                        if (idojarasok.containsKey(megye))
                            s2 = idojarasok.get(megye).toString();
                        kliensnek.writeUTF(s2);
                        break;
                    case 3:
                        double atlag = 0;
                        for (Map.Entry<String, Idojaras> entry : idojarasok.entrySet()) {
                            double min = entry.getValue().getMaiElorejelzes().getMin();
                            double max = entry.getValue().getMaiElorejelzes().getMax();
                            atlag += (min + max) / 2;
                        }
                        kliensnek.writeUTF((atlag / idojarasok.entrySet().size()) + "");
                        break;
                    case 4:
                        double atlag2 = 0;
                        for (Map.Entry<String, Idojaras> entry : idojarasok.entrySet()) {
                            double min = entry.getValue().getHolnapiElorejelzes().getMin();
                            double max = entry.getValue().getHolnapiElorejelzes().getMax();
                            atlag2 += (min + max) / 2;
                        }
                        kliensnek.writeUTF((atlag2 / idojarasok.entrySet().size()) + "");
                        break;
                    case 5:
                        HashMap<String, Integer> atlagIdojaras = new HashMap<>();

                        for (Map.Entry<String, Idojaras> entry : idojarasok.entrySet()) {
                            String szoveges = entry.getValue().getMaiElorejelzes().getSzovegesEloreJelzes();
                            if(atlagIdojaras.containsKey(szoveges)){
                                atlagIdojaras.put(szoveges, atlagIdojaras.get(szoveges) + 1);
                            }
                            else{
                                atlagIdojaras.put(szoveges, 1);
                            }
                        }
                        int max = 0;
                        String szoveges = null;
                        for (Map.Entry<String, Integer> entry : atlagIdojaras.entrySet()) {
                            if(szoveges == null) {
                                szoveges = entry.getKey();
                                max = entry.getValue();
                            }
                            else{
                                if(entry.getValue() > max) {
                                    szoveges = entry.getKey();
                                    max = entry.getValue();
                                }
                            }
                        }
                        kliensnek.writeUTF(szoveges);
                        break;
                    case 6:
                        HashMap<String, Integer> atlagIdojaras2 = new HashMap<>();

                        for (Map.Entry<String, Idojaras> entry : idojarasok.entrySet()) {
                            String szoveges2 = entry.getValue().getHolnapiElorejelzes().getSzovegesEloreJelzes();
                            if(atlagIdojaras2.containsKey(szoveges2)){
                                atlagIdojaras2.put(szoveges2, atlagIdojaras2.get(szoveges2) + 1);
                            }
                            else{
                                atlagIdojaras2.put(szoveges2, 1);
                            }
                        }
                        int max2 = 0;
                        String szoveges2 = null;
                        for (Map.Entry<String, Integer> entry : atlagIdojaras2.entrySet()) {
                            if(szoveges2 == null) {
                                szoveges2 = entry.getKey();
                                max2 = entry.getValue();
                            }
                            else{
                                if(entry.getValue() > max2) {
                                    szoveges2 = entry.getKey();
                                    max2 = entry.getValue();
                                }
                            }
                        }
                        kliensnek.writeUTF(szoveges2);
                        break;
                }
                kliensnek.flush();
            } while (menu != 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void beolvas() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("SzerverKliensIdojaras/weather.txt"));
            br.readLine();
            String sor = br.readLine();
            while(sor != null) {
                Idojaras idojaras = new Idojaras(sor);
                String megye = idojaras.getMegye();
                idojarasok.put(megye, idojaras);
                sor = br.readLine();
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
