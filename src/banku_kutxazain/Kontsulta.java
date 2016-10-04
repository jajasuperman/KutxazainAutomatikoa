package banku_kutxazain;

import java.util.Date;

public class Kontsulta extends Transakzioa {

    public Kontsulta() {
        //  Deklarazioak
        int temp;
        //  Kalkulatu transakzio zenbakia	
        temp = KontuDatuBasea.instantzia().irakurriHasierakoTransakzioZenbakia();
        temp++;
        Kontsulta.setAzkenTransakzioarenZenbakia(temp);
        //  Idatzi
        this.setTransakzioZenbakia(temp);
        this.setData(new Date());
    }

    @Override
    public double kantitatea() {
        return 0.0;
    }

    @Override
    public String toString() {
        try {
            return ObjetuIkuskatzailea.toString(this);
        } catch (Exception exception) {
            return "errorea To String -n";
        }
    }
}
