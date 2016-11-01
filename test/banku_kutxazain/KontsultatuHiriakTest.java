package banku_kutxazain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import banku_kutxazain.KontuDatuBasea;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Eneko
 */
public class KontsultatuHiriakTest {
    
    KontuDatuBasea datuBasea; 
    
    @Before
    public void setUp() {
        datuBasea = KontuDatuBasea.instantzia();
    }
    
    @Test
    public void kontsultatuHiriak() {
        /*
         * Datu-basean, honako datuak daude
         * Barakaldo / Bilbao / Basauri / Gasteiz
         */
        
        ArrayList<String> hiriak = datuBasea.kontsultatuHiriak();
        assertTrue(hiriak.get(0).equals("Barakaldo"));
        assertFalse(hiriak.get(0).equals("Barakald"));
        
        assertTrue(hiriak.get(1).equals("Bilbao"));
        assertFalse(hiriak.get(1).equals("Barakaldo"));
        
        assertTrue(hiriak.get(2).equals("Basauri"));
        
        assertTrue(hiriak.get(3).equals("Gasteiz"));
        assertFalse(hiriak.get(3).equals("Bilbao"));
    }
}
