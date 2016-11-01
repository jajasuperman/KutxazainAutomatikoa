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

public class KontsultatuZinemakTest {
    
    KontuDatuBasea datuBasea;
    ArrayList<String> as1,as2,as3;
    String s0,s1,s2;
    
    @Before
    public void setUp() {
        datuBasea = KontuDatuBasea.instantzia();

        s0="Coliseo Max Ocio";
        s1="Yelmo Cines";
        s2="Bilbondo";
 
    }
    
    @After
    public void tearDown() {
        
        s0=null;
        s1=null;
        s2=null;
        
    }

    @Test
    public void testKontsultatuZinemak() {

    as1= datuBasea.kontsultatuZinemak("Barakaldo");
    assertEquals(as1.size(), 2);
    assertEquals(as1.get(0),s0);
    assertEquals(as1.get(1),s1);
    
    as2 = datuBasea.kontsultatuZinemak("Basauri");
    assertEquals(as2.size(), 1);
    assertEquals(as2.get(0),s2);
    
    as3 = datuBasea.kontsultatuZinemak("Donostia");
    assertEquals(as3.size(), 0);
    
    }

}