package banku_kutxazain;

import java.util.HashMap;

public class Bezeroa {

    private long telefonoZenbakia;
    private String izena;
    private int id;
    private int pin;
    private HashMap kontuak;

    public Bezeroa(String izenBat, long telefonoZenbakiBat) {
        this.setIzena(izenBat);
        this.setTelefonoZenbakia(telefonoZenbakiBat);
    }

    public Bezeroa(int id, int pin, HashMap pKontuak) {
        this.setId(id);
        this.setPin(pin);
        this.setKontuak(pKontuak);
    }

    public HashMap getKontuak() {
        return kontuak;
    }

    public int getPin() {
        return pin;
    }

    public int getId() {
        return id;
    }

    public String getIzena() {
        return izena;
    }

    public long getTelefonoZenbakia() {
        return telefonoZenbakia;
    }

    public void setKontuak(HashMap pKontuak) {
        this.kontuak = pKontuak;
    }

    public void setPin(int pPin) {
        this.pin = pPin;
    }

    public void setId(int pId) {
        this.id = pId;
    }

    public void setIzena(String izenBerria) {
        this.izena = izenBerria;
    }

    public void setTelefonoZenbakia(long telefonoZenbakiBerria) {
        this.telefonoZenbakia = telefonoZenbakiBerria;
    }

    public String toString() {
        try {
            return ObjetuIkuskatzailea.toString(this);
        } catch (Exception exception) {
            return "errorea To String -n";
        }
    }
}
