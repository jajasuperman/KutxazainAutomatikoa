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
public class KontsultatuKontuaTest {
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
}