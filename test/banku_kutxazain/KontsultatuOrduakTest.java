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

public class KontsultatuOrduakTest {
    
    KontuDatuBasea datuBasea;
    ArrayList<String> as1,as2,as3,as4,as5,as6;
    String s0,s1;
    
    @Before
    public void setUp() {
        datuBasea = KontuDatuBasea.instantzia();

        s0="18:00";
        s1="20:00";
        
    }
    
    @After
    public void tearDown() {
        
        s0=null;
        s1=null;
        
    }

    @Test
    public void testKontsultatuOrduak() {
  
    as1= datuBasea.kontsultatuOrduak("Barakaldo", "Coliseo Max Ocio", "Matrix", "2016-10-05");
    assertEquals(as1.size(), 2);
    assertEquals(as1.get(0),s0);
    assertEquals(as1.get(1),s1);
    
    as2 = datuBasea.kontsultatuOrduak("Barakaldo", "Coliseo Max Ocio", "Matrix", "2016-10-06");
    assertEquals(as2.size(), 0);
    
    as3 = datuBasea.kontsultatuOrduak("Barakaldo", "Coliseo Max Ocio", "WALL·E", "2016-10-05");
    assertEquals(as3.size(), 1);
    assertEquals(as3.get(0),s0);
    
    as4 = datuBasea.kontsultatuOrduak("Barakaldo", "Coliseo Max Ocio", "Inception", "2016-10-05");
    assertEquals(as4.size(), 0);
    
    as5 = datuBasea.kontsultatuOrduak("Barakaldo", "Capitol", "Inception", "2016-10-05");
    assertEquals(as5.size(), 0);
    
    as6 = datuBasea.kontsultatuOrduak("Donostia", "Capitol", "Inception", "2016-10-05");
    assertEquals(as6.size(), 0);

    }

}