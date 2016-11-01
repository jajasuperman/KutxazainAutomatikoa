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

/**
 *
 * @author Eka
 */
public class KontuDatuBaseaTestE {
    private ArrayList<String> a1;
    private Kontua k1,k2;
    private String s1,s2,s3,s4;
    
    @Before
    public void setUp() {
        a1 = new ArrayList<>();
        k1 = new Kontua(200,200.00);
        k2 = new Kontua(600,3016.00);
        s1 = "Barakaldo";
        s2 = "Bilbao";
        s3 = "Yelmo Cines";
        s4 = "Mikeldi Zinema";
    }
    
    @After
    public void tearDown() {
        a1.clear();
        a1 = null;
        k1 = null;
        k2 = null;
        s1 = null;
        s2 = null;
        s3 = null;
        s4 = null;
    }

    /**
     * Test of finalize method, of class KontuDatuBasea.
     */

    @Test
    public void testKontsultatuKontua() {
    
    Kontua k4 = KontuDatuBasea.instantzia().kontsultatuKontua(200);//con una cuenta que esta
    assertNotNull(k4);
    assertEquals(k4.getKontuZenbakia(), k1.getKontuZenbakia());
    assertFalse(k4.getKontuZenbakia()==k2.getKontuZenbakia());
    Kontua k5 = KontuDatuBasea.instantzia().kontsultatuKontua(600);//con una cuenta que no existe.
    assertNull(k5);
    
    }

    /**
     * Test of kontsultatuDiruKontua method, of class KontuDatuBasea.
     */
    @Test
    public void testKontsultatuDiruKontua() {
        
        double d1 = KontuDatuBasea.instantzia().kontsultatuDiruKontua(k1.getKontuZenbakia()); //existe la ide por lo que devuelve algo
        assertFalse(d1==0.00);
        double d2 = k1.getSaldoZaharra();
        assertEquals(d1,d2,0);
        d2 = 300.00;
        assertFalse(d1==d2);
        
        d1 = KontuDatuBasea.instantzia().kontsultatuDiruKontua(k2.getKontuZenbakia());//esta cuenta no existe porlo que devuelve 0
        assertEquals(d1,0.00,0);
    }
    /**
     * Test of kontsultatuHiriak method, of class KontuDatuBasea.
     */
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

    /**
     * Test of kontsultatuOrduak method, of class KontuDatuBasea.
     */
}