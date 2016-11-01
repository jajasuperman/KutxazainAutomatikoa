    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banku_kutxazain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import sun.security.krb5.internal.KDCOptions;

/**
 *
 * @author Eka
 */
public class KontsultatuPelikulakTest {
    private ArrayList<String> a1;
    private String s1,s2,s3,s4;
    
    @Before
    public void setUp() {
        a1 = new ArrayList<>();
        s1 = "Barakaldo";
        s2 = "Bilbao";
        s3 = "Yelmo Cines";
        s4 = "Mikeldi Zinema";
    }
    
    @After
    public void tearDown() {
        a1.clear();
        a1 = null;
        s1 = null;
        s2 = null;
        s3 = null;
        s4 = null;
    }



    @Test
    public void testKontsultatuPelikulak() {
            a1 = KontuDatuBasea.instantzia().kontsultatuPelikulak(s1, s4);
            assertTrue(a1.isEmpty());
            a1 = KontuDatuBasea.instantzia().kontsultatuPelikulak(s1, s3);
            assertEquals(a1.size(), 5);                     //ordena zinemen araberakoa da (alfabetikoki ordenatuta daude)
            assertEquals(a1.get(0), "Blade Runner");
            assertEquals(a1.get(1), "Interstellar");
            assertEquals(a1.get(2), "WALL·E");
            assertEquals(a1.get(3), "E.T. estralurtarra");
            assertEquals(a1.get(4), "Distrito 9");

    }
}