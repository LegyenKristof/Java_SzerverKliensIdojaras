package com.company;

public class Idojaras {
    private String megye;
    private Elorejelzes maiElorejelzes;
    private Elorejelzes holnapiElorejelzes;

    public Idojaras(String sor) {
        String[] adatok = sor.split("\\t+", -1);
        megye = adatok[0].trim();
        maiElorejelzes = new Elorejelzes(adatok[1], adatok[2]);
        holnapiElorejelzes = new Elorejelzes(adatok[3], adatok[4]);
    }

    public String getMegye() {
        return megye;
    }

    public void setMegye(String megye) {
        this.megye = megye;
    }

    public Elorejelzes getMaiElorejelzes() {
        return maiElorejelzes;
    }

    public void setMaiElorejelzes(Elorejelzes maiElorejelzes) {
        this.maiElorejelzes = maiElorejelzes;
    }

    public Elorejelzes getHolnapiElorejelzes() {
        return holnapiElorejelzes;
    }

    public void setHolnapiElorejelzes(Elorejelzes holnapiElorejelzes) {
        this.holnapiElorejelzes = holnapiElorejelzes;
    }

    @Override
    public String toString() {
        return String.format("%s, Mai előrejelzés: %s, Holnapi előrejelzés: %s", megye, maiElorejelzes, holnapiElorejelzes);
    }
}
