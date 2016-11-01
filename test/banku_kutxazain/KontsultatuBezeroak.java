package banku_kutxazain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import banku_kutxazain.KontuDatuBasea;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Eneko
 */
public class KontsultatuBezeroak {
    
    KontuDatuBasea datuBasea; 
    
    @Before
    public void setUp() {
        datuBasea = KontuDatuBasea.instantzia();
    }
    
    @Test
    public void testKontsultatuBezeroak() {
        /* 
         * Metodo honek boolear bat itzuliko du. Sartutako erabiltzailea
         * eta pasahitza datu-basean badaude, true bueltatuko du.
         * Bestela, false
        */
        
        /*
         * Datu-basean dauden datuak:
         * 123456 - 753
         * 123654 - 654
         * 123655 - 1234
         */
        
        assertTrue(datuBasea.kontsultatuBezeroa(123456, 753));
        assertFalse(datuBasea.kontsultatuBezeroa(123456, 75));
        
        assertTrue(datuBasea.kontsultatuBezeroa(123654, 654));
        assertFalse(datuBasea.kontsultatuBezeroa(123654, 753));
        
        assertTrue(datuBasea.kontsultatuBezeroa(123655, 1234));
        assertFalse(datuBasea.kontsultatuBezeroa(123654, 1234));
        
    }
}
