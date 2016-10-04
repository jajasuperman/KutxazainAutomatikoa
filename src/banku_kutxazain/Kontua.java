package banku_kutxazain;

import java.text.*;
import java.util.*;

public final class Kontua {

    private int kontuZenbakia;
    private double saldoZaharra;
    private ArrayList transakzioak;

    public Kontua(int kontuZenbakiBat, double saldoZaharra) {
        this.setKontuZenbakia(kontuZenbakiBat);
        this.setSaldoZaharra(saldoZaharra);
        this.setTransakzioak(new ArrayList());
    }

    public int getKontuZenbakia() {
        return this.kontuZenbakia;
    }

    public double getSaldoZaharra() {
        return this.saldoZaharra;
    }

    public ArrayList getTransakzioak() {
        return this.transakzioak;
    }

    public Gordailua eginGordailua(double kantitateBat) {
        // Deklarazioak
        Transakzioa transakzioa;
        int transakzioZenbakia, kontuZenbaki;
        double kantitatea;
        Date data;
        String mota;
        // Hasieraketa
        transakzioa = new Gordailua(kantitateBat);
        // Kontua-ri gehitu
        this.getTransakzioak().add(transakzioa);
        // SQL taulan sartu Transakzioa
        transakzioZenbakia = transakzioa.getTransakzioZenbakia();
        data = transakzioa.getData();
        kontuZenbaki = this.getKontuZenbakia();
        kantitatea = transakzioa.kantitatea();
        mota = new String(transakzioa.getClass().getName().substring(16, transakzioa.getClass().getName().length()));
        System.out.println("gordailua :" + transakzioa);
        KontuDatuBasea.instantzia().sartuTransakzioa(transakzioZenbakia, kontuZenbaki, data, kantitatea, mota);
        // Irteera
        return (Gordailua) transakzioa;
    }

    public Kontsulta eginKontsulta() {
        // Deklarazioak
        Transakzioa transakzioa;
        int transakzioZenbakia, kontuZenbaki;
        double kantitatea;
        Date data;
        String mota;
        // Hasieraketa
        transakzioa = new Kontsulta();
        // Kontua-ri gehitu
        this.getTransakzioak().add(transakzioa);
        // SQL taulan sartu Transakzioa
        transakzioZenbakia = transakzioa.getTransakzioZenbakia();
        data = transakzioa.getData();
        kontuZenbaki = this.getKontuZenbakia();
        kantitatea = 0.0;
        mota = new String(transakzioa.getClass().getName().substring(16, transakzioa.getClass().getName().length()));
        System.out.println("kontsulta :" + transakzioa);
        KontuDatuBasea.instantzia().sartuTransakzioa(transakzioZenbakia, kontuZenbaki, data, kantitatea, mota);
        // Irteera
        return (Kontsulta) transakzioa;
    }

    public DiruAteratze ateraDirua(double kantitateBat) {
        // Deklarazioak
        Transakzioa transakzioa;
        int transakzioZenbakia, kontuZenbaki;
        double kantitatea;
        Date data;
        String mota;
        // Hasieraketa
        transakzioa = new DiruAteratze(kantitateBat);
        // Kontua-ri gehitu
        this.getTransakzioak().add(transakzioa);
        // SQL taulan sartu Transakzioa
        transakzioZenbakia = transakzioa.getTransakzioZenbakia();
        data = transakzioa.getData();
        kontuZenbaki = this.getKontuZenbakia();
        kantitatea = transakzioa.kantitatea();
        mota = new String(transakzioa.getClass().getName().substring(16, transakzioa.getClass().getName().length()));
        System.out.println("diruateratzea:" + transakzioa);
        KontuDatuBasea.instantzia().sartuTransakzioa(transakzioZenbakia, kontuZenbaki, data, kantitatea, mota);
        // Irteera
        return (DiruAteratze) transakzioa;
    }

    public double saldoBerria() {
        // Deklarazioak
        double saldoBerria;
        Iterator iterator;
        Transakzioa transakzioa;
        // Hasieraketa
        saldoBerria = this.getSaldoZaharra();
        iterator = this.transakzioak.iterator();
        // Prozesua
        while (iterator.hasNext()) {
            transakzioa = (Transakzioa) iterator.next();
            saldoBerria += transakzioa.kantitatea();
        }
        // Irteera
        return saldoBerria;
    }

    public void setKontuZenbakia(int kontuZenbakiBerria) {
        this.kontuZenbakia = kontuZenbakiBerria;
    }

    public void setSaldoZaharra(double saldoZaharBerria) {
        this.saldoZaharra = saldoZaharBerria;
    }

    public void setTransakzioak(ArrayList transakzioBerria) {
        this.transakzioak = transakzioBerria;
    }

    @Override
    public String toString() {
        // Delarations
        Iterator iterator;
        Transakzioa transakzioa;
        String string;
        NumberFormat numberFormat;
        // Hasieraketa
        numberFormat = NumberFormat.getCurrencyInstance();
        // Prozesua
        string = "(Kontua: "
                + "kontuZenbakia = " + this.getKontuZenbakia() + " |  "
                + "saldoZaharra = " + numberFormat.format(this.getSaldoZaharra()) + " |  "
                + "saldoBerria = " + numberFormat.format(this.saldoBerria()) + " |  ";
        iterator = transakzioak.iterator();
        while (iterator.hasNext()) {
            transakzioa = (Transakzioa) iterator.next();
            string += "\n" + transakzioa.toString();
        }
        string += ")";
        // Irteera
        return string;
    }
}
