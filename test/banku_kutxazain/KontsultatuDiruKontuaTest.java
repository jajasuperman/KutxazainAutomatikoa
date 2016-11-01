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
public class KontsultatuDiruKontuaTest {
    private Kontua k1,k2;
    
    @Before
    public void setUp() {
        k1 = new Kontua(200,200.00);
        k2 = new Kontua(600,3016.00);
    }
    
    @After
    public void tearDown() {
        k1 = null;
        k2 = null;
    }

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
}