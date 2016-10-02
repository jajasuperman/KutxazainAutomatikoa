package banku_kutxazain;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

class BankuaGUI extends JFrame {

    private Container contentPane;

    public BankuaGUI(String izenburua) throws FontFormatException, IOException {
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File(System.getProperty("user.dir") + File.separator + "Roboto-Regular.ttf"));
        font = font.deriveFont(Font.PLAIN, 15);
        setUIFont(new javax.swing.plaf.FontUIResource(font));
        setTitle(izenburua);
        setSize(425, 350);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent anEvent) {
                // Irteeratik kontu nagusi guztia ikuskapenerako
                //System.out.println(nirePanela.getKutxazain().toString());
                System.exit(0);
            }
        });

        contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new NirePanela(), BorderLayout.WEST);
        contentPane.add(new NireEgutegia(), BorderLayout.EAST);

        pack();
    }

    public static void main(String[] args) throws FontFormatException, IOException {
        new BankuaGUI("Dirua Barra-barra").show();
    }

    public BankuaGUI() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(454, 300));
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}
