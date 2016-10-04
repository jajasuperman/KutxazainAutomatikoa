package banku_kutxazain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendar;

public class NireEgutegia extends JPanel implements ActionListener, DateListener {

    private JComboBox hiriak, zinemak, pelikulak, orduak;
    private JCalendar egutegia;
    private static JButton ordainduBotoia;
    private String hiria, zinema, pelikula;
    private JFrame framea;
    private SimpleDateFormat ft;
    private JLabel orduakTestua;

    public NireEgutegia(JFrame nireFrame) {
        this.setBackground(new Color(144, 202, 249));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.framea = nireFrame;
        
        ft = new SimpleDateFormat("yyyy-MM-dd");
        
        gehituKonponenteak();
    }

    private void gehituKonponenteak() {
        JPanel tituluPanela = new JPanel();
        JTextPane titulua = new JTextPane();
        titulua.setContentType("text/html");
        titulua.setText("<html><p style=\"text-align: center;\"><strong style=\"color: #5e9ca0;\">&nbsp;SARRERA-TXARTELA EROSI:</strong></p><br></html>");
        titulua.setEditable(false);
        titulua.setBackground(null);
        titulua.setBorder(null);
        tituluPanela.add(titulua);

        JPanel aukerakPanela = new JPanel(new GridLayout(1, 3));
        JPanel hiriaTestuaPanel = new JPanel();
        JLabel hiriaTestua = new JLabel("HIRIA: ");
        hiriaTestuaPanel.add(hiriaTestua);
        JPanel zinemaTestuaPanel = new JPanel();
        JLabel zinemaTestua = new JLabel("ZINEMA: ");
        zinemaTestuaPanel.add(zinemaTestua);
        JPanel pelikulaTestuaPanel = new JPanel();
        JLabel pelikulaTestua = new JLabel("PELIKULA: ");
        pelikulaTestuaPanel.add(pelikulaTestua);
        hiriak = new JComboBox(KontuDatuBasea.instantzia().kontsultatuHiriak().toArray());
        hiria = (String)hiriak.getSelectedItem();
        hiriak.addActionListener(this);
        zinemak = new JComboBox(KontuDatuBasea.instantzia().kontsultatuZinemak(hiria).toArray());
        zinema = (String)zinemak.getSelectedItem();
        zinemak.addActionListener(this);
        pelikulak = new JComboBox(KontuDatuBasea.instantzia().kontsultatuPelikulak(hiria, zinema).toArray());
        pelikula = (String)pelikulak.getSelectedItem();
        pelikulak.addActionListener(this);       

        JPanel hiriaKud = new JPanel(new GridLayout(2, 1));
        hiriaKud.add(hiriaTestuaPanel);
        hiriaKud.add(hiriak);
        JPanel zinemaKud = new JPanel(new GridLayout(2, 1));
        zinemaKud.add(zinemaTestuaPanel);
        zinemaKud.add(zinemak);
        JPanel pelikulaKud = new JPanel(new GridLayout(2, 1));
        pelikulaKud.add(pelikulaTestuaPanel);
        pelikulaKud.add(pelikulak);

        aukerakPanela.add(hiriaKud);
        aukerakPanela.add(zinemaKud);
        aukerakPanela.add(pelikulaKud);

        JPanel goikoPanela = new JPanel(new BorderLayout());
        goikoPanela.add(tituluPanela, BorderLayout.NORTH);
        goikoPanela.add(aukerakPanela, BorderLayout.CENTER);
        goikoPanela.add(new JPanel(), BorderLayout.SOUTH);

        JPanel egutegiaOrduaPanel = new JPanel(new BorderLayout());
        JPanel egutegiaPanel = new JPanel();
        egutegia = new JCalendar();
        egutegia.addDateListener(this);
        egutegiaPanel.add(egutegia);
        JPanel orduakPanela = new JPanel();
        orduakTestua = new JLabel("ORDUA:");
        orduak = new JComboBox();
        orduak.addActionListener(this);        
        orduakPanela.add(orduakTestua);
        orduakPanela.add(orduak);
        egutegiaOrduaPanel.add(egutegiaPanel, BorderLayout.NORTH);
        egutegiaOrduaPanel.add(orduakPanela, BorderLayout.SOUTH);

        JPanel ordainketaPanela = new JPanel();
        JLabel ordainketaTestua = new JLabel("AUKERATU KONTUA ETA ORDAINDU: ");
        ordainduBotoia = new JButton("Ordaindu");
        ordainduBotoia.addActionListener(this);
        ordainduBotoia.setEnabled(false);
        ordainketaPanela.add(ordainketaTestua);
        ordainketaPanela.add(ordainduBotoia);        

        this.add(goikoPanela, BorderLayout.NORTH);
        this.add(egutegiaOrduaPanel, BorderLayout.CENTER);
        this.add(ordainketaPanela, BorderLayout.SOUTH);
        
        begiratuEgunak();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hiriak) {
            hiria = (String) hiriak.getSelectedItem();
            zinemak.setModel(new DefaultComboBoxModel(KontuDatuBasea.instantzia().kontsultatuZinemak(hiria).toArray()));
            pelikulak.setModel(new DefaultComboBoxModel(KontuDatuBasea.instantzia().kontsultatuPelikulak(hiria, (String)zinemak.getSelectedItem()).toArray()));

            framea.pack();
        }
        if (e.getSource() == zinemak) {
            zinema = (String) zinemak.getSelectedItem();
            pelikulak.setModel(new DefaultComboBoxModel(KontuDatuBasea.instantzia().kontsultatuPelikulak(hiria, zinema).toArray()));

            framea.pack();
        }
        if (e.getSource() == pelikulak) {
            pelikula = (String) pelikulak.getSelectedItem();             
            begiratuEgunak();
        }
        
        if (e.getSource() == ordainduBotoia) {
            NirePanela.getKontua().ordaindu(6.4);            
        }
        
    }

    @Override
    public void dateChanged(DateEvent de) {
        begiratuEgunak();
    }
    
    private void begiratuEgunak() {
        String eguna = ft.format(egutegia.getDate());
        
        Object[] egunArray = KontuDatuBasea.instantzia().kontsultatuOrduak(hiria, zinema, pelikula, eguna).toArray();
        if(egunArray.length == 0){
            orduakTestua.setText("PELIKULA EZ DA EGUN HONETAN EMANGO");
            orduak.setVisible(false);
            ordainduBotoia.setEnabled(false);
        }
        else {
            orduakTestua.setText("ORDUA:");
            orduak.setVisible(true);
            orduak.setModel(new DefaultComboBoxModel(egunArray));
        }
        
        framea.pack();
    }
    
    public static void ordainduAktibatu(boolean pEgoera) {
        ordainduBotoia.setEnabled(pEgoera);
    }
}
