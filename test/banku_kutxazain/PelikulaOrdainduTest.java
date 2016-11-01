package banku_kutxazain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import banku_kutxazain.KontuDatuBasea;
import banku_kutxazain.Kontua;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eneko
 */
public class PelikulaOrdainduTest {  
    
    KontuDatuBasea datuBasea;
    
    @Before
    public void setUp() {
        datuBasea = KontuDatuBasea.instantzia();
    }
    
    @Test
    public void pelikulaOrdaindu() {
        // Hasiera kontuak 100 izango ditu
        assertTrue(datuBasea.pelikulaOrdaindu(100, 10));
        assertFalse(datuBasea.pelikulaOrdaindu(100, 100));
        // Ezin izango ditu 100 atera, 90 dituelako
    }
}
