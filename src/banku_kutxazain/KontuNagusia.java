package banku_kutxazain;

import java.util.*;
public class KontuNagusia {

	private HashMap kontuak;

	public KontuNagusia() throws ClassNotFoundException {
		kontuak = KontuDatuBasea.instantzia().irakurriKontuak();
	}
	
	public Kontua aurkituKontua(int kontuZenbakiBat, int pinZenbakiBat) {
		Kontua kontua;
		kontua = (Kontua) kontuak.get(new Integer(kontuZenbakiBat));
		if (kontua.getPinZenbakia() == pinZenbakiBat) {
			return kontua;
		}
		else
			return null;
	}

	public HashMap getKontuak() {
		return kontuak;
	}

	public void setKontuak(HashMap kontuBerriak) {
		this.kontuak = kontuBerriak;
	}

	public String toString() {
		Iterator iterator;
		Kontua kontua;
		String string;
		string = "(KontuNagusia: " + "kontuak = ";
		iterator = kontuak.values().iterator();
		while (iterator.hasNext()) {
			kontua = (Kontua) iterator.next();
			string += "\n" + kontua.toString();
		}
		string += ")";
		return string;
	}
}
