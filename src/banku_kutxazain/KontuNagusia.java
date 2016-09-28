package banku_kutxazain;

import java.util.*;

public class KontuNagusia {

    //private HashMap bezeroak;

    public KontuNagusia() {
        //bezeroak = KontuDatuBasea.instantzia().irakurriBezeroak();
    }

    public boolean aurkituBezeroa(int idZenbakia, int pinZenbakiBat) {
        return KontuDatuBasea.instantzia().kontsultatuBezeroa(idZenbakia, pinZenbakiBat);
    }

    /*public String toString() {
        Iterator iterator;
        Bezeroa bezero;
        String string;
        string = "(KontuNagusia: " + "kontuak = ";
        iterator = bezeroak.values().iterator();
        while (iterator.hasNext()) {
            bezero = (Bezeroa) iterator.next();
            string += "\n" + bezero.toString();
        }
        string += ")";
        return string;
    }*/
}
