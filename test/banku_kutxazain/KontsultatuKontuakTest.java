package banku_kutxazain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import sun.security.krb5.internal.KDCOptions;

/*
 * @author ADN
 */

public class KontsultatuKontuakTest {
    
    KontuDatuBasea datuBasea;
    ArrayList<Integer> ai1,ai2;
    Integer i0,i1;
    
    @Before
    public void setUp() {
        datuBasea = KontuDatuBasea.instantzia();

        i0=400;
        i1=300;
      
    }
    
    @After
    public void tearDown() {
        
        i0=null;
        i1=null;
      
    }

    @Test
    public void testKontsultatuKontuak() {

    ai1 = datuBasea.kontsultatuKontuak(123456);
    assertEquals(ai1.size(), 2);
    assertEquals(ai1.get(0),i0);
    assertEquals(ai1.get(1),i1);
    
    ai2 = datuBasea.kontsultatuKontuak(123);
    assertEquals(ai2.size(), 0);
    
    }

}