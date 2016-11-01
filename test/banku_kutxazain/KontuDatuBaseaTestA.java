package banku_kutxazain;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import banku_kutxazain.Kontua;
import banku_kutxazain.KontuDatuBasea;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import sun.security.krb5.internal.KDCOptions;

/*
 * @author ADN
 */

public class KontuDatuBaseaTestA {
    
    KontuDatuBasea datuBasea;
    ArrayList<Integer> ai1,ai2;
    ArrayList<String> as1,as2,as3,as4,as5,as6;
    Integer i0,i1;
    String s0,s1,s2,s3,s4;
    
    @Before
    public void setUp() {
        datuBasea = KontuDatuBasea.instantzia();

        i0=400;
        i1=300;
        
        s0="Coliseo Max Ocio";
        s1="Yelmo Cines";
        s2="Bilbondo";
        
        s3="18:00";
        s4="20:00";
        
    }
    
    @After
    public void tearDown() {
        
        i0=null;
        i1=null;
        
        s0=null;
        s1=null;
        s2=null;
        
        s3=null;
        s4=null;
        
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
    
    @Test
    public void testKontsultatuOrduak() {
  
    as1= datuBasea.kontsultatuOrduak("Barakaldo", "Coliseo Max Ocio", "Matrix", "2016-10-05");
    assertEquals(as1.size(), 2);
    assertEquals(as1.get(0),s3);
    assertEquals(as1.get(1),s4);
    
    as2 = datuBasea.kontsultatuOrduak("Barakaldo", "Coliseo Max Ocio", "Matrix", "2016-10-06");
    assertEquals(as2.size(), 0);
    
    as3 = datuBasea.kontsultatuOrduak("Barakaldo", "Coliseo Max Ocio", "WALL·E", "2016-10-05");
    assertEquals(as3.size(), 1);
    assertEquals(as3.get(0),s3);
    
    as4 = datuBasea.kontsultatuOrduak("Barakaldo", "Coliseo Max Ocio", "Inception", "2016-10-05");
    assertEquals(as4.size(), 0);
    
    as5 = datuBasea.kontsultatuOrduak("Barakaldo", "Capitol", "Inception", "2016-10-05");
    assertEquals(as5.size(), 0);
    
    as6 = datuBasea.kontsultatuOrduak("Donostia", "Capitol", "Inception", "2016-10-05");
    assertEquals(as6.size(), 0);

    }

}