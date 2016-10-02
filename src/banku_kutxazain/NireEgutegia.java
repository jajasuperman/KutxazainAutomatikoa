package banku_kutxazain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.freixas.jcalendar.JCalendar;

public class NireEgutegia extends JPanel implements ActionListener{
    
    private JComboBox hiriak, zinemak;
    private JCalendar egutegia;
    private JButton ordainduBotoia;
    
    public NireEgutegia(){
        this.setBackground(new Color(144, 202, 249));
        this.setLayout(new BorderLayout());        
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
        gehituKonponenteak();
    }
    
    private void gehituKonponenteak() {
        JPanel tituluPanela = new JPanel();
        JLabel titulua = new JLabel("SARRERA-TXARTELA EROSI:");
        tituluPanela.add(titulua);
        
        JPanel aukerakPanela = new JPanel();
        JLabel hiriaTestua = new JLabel("HIRIA: ");
        JLabel zinemaTestua = new JLabel("ZINEMA: ");
        hiriak = new JComboBox(KontuDatuBasea.instantzia().kontsultatuHiriak().toArray());
        hiriak.addActionListener(this);
        zinemak = new JComboBox();
        zinemak.addActionListener(this);
        aukerakPanela.add(hiriaTestua);
        aukerakPanela.add(hiriak);
        aukerakPanela.add(zinemaTestua);
        aukerakPanela.add(zinemak);
        
        JPanel goikoPanela = new JPanel(new BorderLayout());
        goikoPanela.add(tituluPanela, BorderLayout.NORTH);
        goikoPanela.add(aukerakPanela, BorderLayout.SOUTH);
        
        JPanel egutegiaPanel = new JPanel();
        egutegia = new JCalendar();
        egutegiaPanel.add(egutegia);
        
        JPanel ordainketaPanela = new JPanel();
        JLabel ordainketaTestua = new JLabel("AUKERATU KONTUA ETA ORDAINDU: ");
        ordainduBotoia = new JButton("Ordaindu");
        ordainketaPanela.add(ordainketaTestua);
        ordainketaPanela.add(ordainduBotoia);
        
        this.add(goikoPanela, BorderLayout.NORTH);
        this.add(egutegiaPanel, BorderLayout.CENTER);
        this.add(ordainketaPanela, BorderLayout.SOUTH);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == hiriak) {
            System.out.println("SHUUUUUUUUU");
            zinemak.setModel(new DefaultComboBoxModel(KontuDatuBasea.instantzia().kontsultatuZinemak((String)hiriak.getSelectedItem()).toArray()));
        }
    }
}
