package banku_kutxazain;

// Class izena: 	banku_kutxazain.Kutxazaina.java
// Function:	Bigarren mailaren kontrolatzailea edo Facade
import java.text.*;

public class Kutxazaina {

    private KontuNagusia kontuNagusia;
    private Bezeroa bezero;
    private Kontua kontua;
    private double kutxazainSaldoa;

    public Kutxazaina() {
        this.setKontuNagusia(new KontuNagusia());
        this.setKutxazainSaldoa(2500.00);
    }

    public boolean bezeroaLogin(int idZenbakia, int pinZenbakiBat) {
        return kontuNagusia.aurkituBezeroa(idZenbakia, pinZenbakiBat);
    }

    public Gordailua sartuGordailua(double kantitateBat) {
        Transakzioa transakzioa;
        transakzioa = this.getKontua().eginGordailua(kantitateBat);
        //  System.out.println("gordailuaa:" + transakzioa);
        return (Gordailua) transakzioa;
    }

    public void sartuBukaera() {
        this.setKontua(null);
    }

    public Kontsulta sartuKontsulta() {
        Transakzioa transakzioa;
        transakzioa = this.getKontua().eginKontsulta();
        //  System.out.println("kontsulta:" + transakzioa);
        return (Kontsulta) transakzioa;
    }

    public DiruAteratze sartuDirua(double kantitateBat) {
        Transakzioa transakzioa;
        double kutxazainSaldoa;
        kutxazainSaldoa = this.getKutxazainSaldoa();
        kutxazainSaldoa -= kantitateBat;
        this.setKutxazainSaldoa(kutxazainSaldoa);
        transakzioa = this.getKontua().ateraDirua(kantitateBat);
        //  System.out.println("DiruAteratze is:" + transakzioa);
        return (DiruAteratze) transakzioa;
    }

    public Kontua getKontua() {
        return this.kontua;
    }

    public KontuNagusia getKontuNagusia() {
        return this.kontuNagusia;
    }

    public double getKutxazainSaldoa() {
        return this.kutxazainSaldoa;
    }

    public void setKontua(Kontua kontuBerria) {
        this.kontua = kontuBerria;
    }

    public void setKontuNagusia(KontuNagusia kontuNagusiBerria) {
        this.kontuNagusia = kontuNagusiBerria;
    }

    public void setKutxazainSaldoa(double kutxazainSaldoBerria) {
        this.kutxazainSaldoa = kutxazainSaldoBerria;
    }

    public String toString() {
        //  Decarations
        NumberFormat numberFormat;
        // Formatuak hasieratu
        numberFormat = NumberFormat.getCurrencyInstance();
        // Irteera
        return "(Kutxazaina = "
                + "kutxazainSaldoa = " + numberFormat.format(this.getKutxazainSaldoa()) + " | "
                + "kontuNagusia = " + this.getKontuNagusia() + ")";
    }
}
