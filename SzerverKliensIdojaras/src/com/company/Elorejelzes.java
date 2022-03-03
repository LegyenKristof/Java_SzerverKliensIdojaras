package com.company;

public class Elorejelzes {
    private String szovegesEloreJelzes;
    private int min;
    private int max;

    public Elorejelzes(String elo, String minmax) {
        szovegesEloreJelzes = elo.trim();
        String[] adatok = minmax.split("/");
        min = Integer.parseInt(adatok[0].trim());
        max = Integer.parseInt(adatok[1].trim());
    }

    public String getSzovegesEloreJelzes() {
        return szovegesEloreJelzes;
    }

    public void setSzovegesEloreJelzes(String szovegesEloreJelzes) {
        this.szovegesEloreJelzes = szovegesEloreJelzes;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return String.format("%s, Min: %d, Max: %d", szovegesEloreJelzes, min, max);
    }
}
