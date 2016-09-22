package banku_kutxazain;

// Class izena: 	banku_kutxazain.NirePanela.java
// Function:	Arkitekturaren aurkezpen maila

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.awt.Dimension;

public class NirePanela extends JPanel implements ActionListener {

	// Kontrolak
	private JPasswordField testuEremuaPinZenbakia;
	private JButton botoiaSartuTxartela, botoiaGordailua, botoiaDiruAteratzea, botoiaKontsulta, botoiaBukatu;
	private JTextField testuEremuaKontuZenbakia, testuEremuaSaldoa, testuEremuaGordailua, testuEremuaDiruAteratzea, testuEremuaSaldoBerria ;
	private JLabel etiketaIzena, etiketaKontuZenbakia, etiketaPinZenbakia, etiketaSaldoa, etiketaGordailua, etiketaDiruAteratzea, etiketaSaldoBerria;
	// Modeloak
	private Kutxazaina kutxazaina;
	private Kontua kontua;
	// Utilitatea
	private NumberFormat numberFormat;

	public NirePanela() {
		// Modeloa hasieratu
		kutxazaina = new Kutxazaina();
		// Utilitatea hasieratu
		numberFormat = NumberFormat.getCurrencyInstance();
		// Leihoak doitu
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setBackground(Color.lightGray);
		this.setLayout(new GridLayout(6, 3, 25, 25));
		this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		// Kontrolak hasieratu
		gehituKontrolak();
	    try {
	      jbInit();
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }

	}
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == botoiaSartuTxartela) {
			// Deklarazioak
			int kontuZenbakia, pinZenbakia;
			// Input
			kontuZenbakia = Integer.parseInt(testuEremuaKontuZenbakia.getText());
			pinZenbakia = Integer.parseInt(new String((testuEremuaPinZenbakia.getPassword())));
			// Modeloa sartu 
			kontua = kutxazaina.sartuTxartela(kontuZenbakia, pinZenbakia);
			// Erroreak egiaztatu
			if (kontua != null) {
				// Botoiak eta testu eremuak gaitu
				botoiaSartuTxartela.setEnabled(false);
				botoiaKontsulta.setEnabled(true);
				botoiaGordailua.setEnabled(true);
				botoiaDiruAteratzea.setEnabled(true);
				botoiaBukatu.setEnabled(false);
				testuEremuaKontuZenbakia.setEditable(false);
				testuEremuaPinZenbakia.setEditable(false);
				testuEremuaGordailua.setEditable(true);
				testuEremuaDiruAteratzea.setEditable(true);
			}
			else {
				JOptionPane.showMessageDialog(this, "Message", "Title", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (event.getSource() == botoiaKontsulta) {
			// Modeloa sartu 
			kutxazaina.sartuKontsulta();
			// Irteera
			testuEremuaSaldoa.setText(numberFormat.format(kontua.saldoBerria()));
			// Botoiak eta testu eremuak gaitu
			botoiaSartuTxartela.setEnabled(false);
			botoiaKontsulta.setEnabled(true);
			botoiaGordailua.setEnabled(true);
			botoiaDiruAteratzea.setEnabled(true);
			botoiaBukatu.setEnabled(true);
			testuEremuaKontuZenbakia.setEditable(false);
			testuEremuaPinZenbakia.setEditable(false);
			testuEremuaGordailua.setEditable(true);
			testuEremuaDiruAteratzea.setEditable(true);
		}
		if (event.getSource() == botoiaGordailua) {
			// Deklarazioak
			double gordailuKantitatea, saldoBerria;
			// Input
	    	String sartutakoKantitatea = testuEremuaGordailua.getText();
	    if (sartutakoKantitatea.indexOf("Pts") != -1)
	      sartutakoKantitatea = sartutakoKantitatea.substring(0, sartutakoKantitatea.length()-3);
			gordailuKantitatea = Double.valueOf(sartutakoKantitatea).doubleValue();
			// Modeloa sartu 
			kutxazaina.sartuGordailua(gordailuKantitatea);
			// Irteera
			testuEremuaGordailua.setText(numberFormat.format(gordailuKantitatea));
			testuEremuaSaldoBerria .setText(numberFormat.format(kontua.saldoBerria()));
			// Botoiak eta testu eremuak gaitu
			botoiaSartuTxartela.setEnabled(false);
			botoiaKontsulta.setEnabled(true);
			botoiaGordailua.setEnabled(true);
			botoiaDiruAteratzea.setEnabled(true);
			botoiaBukatu.setEnabled(true);
			testuEremuaKontuZenbakia.setEditable(false);
			testuEremuaPinZenbakia.setEditable(false);
			testuEremuaGordailua.setEditable(true);
			testuEremuaDiruAteratzea.setEditable(true);
		}
		if (event.getSource() == botoiaDiruAteratzea)
		{
			// Deklarazioak
			double diruAteratzeKantitatea, saldoBerria;
			// Input
	    String sartutakoKantitatea = testuEremuaDiruAteratzea.getText();
	    if (sartutakoKantitatea.indexOf("Pts") != -1)
	      sartutakoKantitatea = sartutakoKantitatea.substring(0, sartutakoKantitatea.length()-3);
			diruAteratzeKantitatea = Double.valueOf(sartutakoKantitatea).doubleValue();
			// Modeloa sartu 
			kutxazaina.sartuDirua(diruAteratzeKantitatea);
			// Irteera
			saldoBerria = kontua.saldoBerria();
			testuEremuaDiruAteratzea.setText(numberFormat.format(diruAteratzeKantitatea));
			testuEremuaSaldoBerria .setText(numberFormat.format(saldoBerria));
			// Botoiak eta testu eremuak gaitu
			botoiaSartuTxartela.setEnabled(false);
			botoiaKontsulta.setEnabled(true);
			botoiaGordailua.setEnabled(true);
			botoiaDiruAteratzea.setEnabled(true);
			botoiaBukatu.setEnabled(true);
			testuEremuaKontuZenbakia.setEditable(false);
			testuEremuaPinZenbakia.setEditable(false);
			testuEremuaGordailua.setEditable(true);
			testuEremuaDiruAteratzea.setEditable(true);
		}
		if (event.getSource() == botoiaBukatu) {
			// Modeloa sartu 
			kutxazaina.sartuBukaera();
			// Eremuak ezabatu
			testuEremuaKontuZenbakia.setText("");
			testuEremuaPinZenbakia.setText("");
			testuEremuaSaldoa.setText("");
			testuEremuaGordailua.setText("");
			testuEremuaDiruAteratzea.setText("");
			testuEremuaSaldoBerria .setText("");
			// Botoiak eta testu eremuak gaitu
			botoiaSartuTxartela.setEnabled(true);
			botoiaGordailua.setEnabled(false);
			botoiaDiruAteratzea.setEnabled(false);
			botoiaBukatu.setEnabled(false);
			botoiaKontsulta.setEnabled(false);
			testuEremuaKontuZenbakia.setEditable(true);
			testuEremuaPinZenbakia.setEditable(true);
			testuEremuaGordailua.setEditable(false);
			testuEremuaDiruAteratzea.setEditable(false);
		}
	}

	private void gehituKontrolak() {
		// private metodo lagungarria clutter-a paneleko eraiketzailetik kentzeko
		// Grid (row=1, col=1)
		etiketaKontuZenbakia = new JLabel("Kontu Zenbakia");
		this.add(etiketaKontuZenbakia);
		// Grid (row=1, col=2)
		testuEremuaKontuZenbakia = new JTextField();
		this.add(testuEremuaKontuZenbakia);
		// Grid (row=1, col=3)
		this.add(new JLabel("                 "));
		// Grid (row=2, col=1)
		etiketaPinZenbakia = new JLabel("PIN Zenbakia");
		this.add(etiketaPinZenbakia);
		// Grid (row=2, col=2)
		testuEremuaPinZenbakia = new JPasswordField();
		this.add(testuEremuaPinZenbakia);
		// Grid (row=2, col=3)
		botoiaSartuTxartela = new JButton("Sartu Txartela");
		this.add(botoiaSartuTxartela);
		botoiaSartuTxartela.addActionListener(this);
		// Grid (row=3, col=1)
		etiketaSaldoa = new JLabel("Saldoa");
		this.add(etiketaSaldoa);
		// Grid (row=3, col=2)
		testuEremuaSaldoa = new JTextField();
		this.add(testuEremuaSaldoa);
		testuEremuaSaldoa.setEditable(false);
		// Grid (row=3, col=3) 
		botoiaKontsulta = new JButton("Kontsulta");
		this.add(botoiaKontsulta);
		botoiaKontsulta.addActionListener(this);
		// Grid (row=4, col=1)
		etiketaGordailua = new JLabel("Gordailua");
		this.add(etiketaGordailua);
		// Grid (row=4, col=2)
		testuEremuaGordailua = new JTextField();
		this.add(testuEremuaGordailua);
		// Grid (row=4, col=3)
		botoiaGordailua = new JButton("Gordailua");
		this.add(botoiaGordailua);
		botoiaGordailua.addActionListener(this);
		// Grid (row=5, col=1)
		etiketaDiruAteratzea = new JLabel("DiruAteratzea");
		this.add(etiketaDiruAteratzea);
		// Grid (row=5, col=2)
		testuEremuaDiruAteratzea = new JTextField();
		this.add(testuEremuaDiruAteratzea);
		// Grid (row=5, col=3)
		botoiaDiruAteratzea = new JButton("DiruAteratzea");
		this.add(botoiaDiruAteratzea);
		botoiaDiruAteratzea.addActionListener(this);
		// Grid (row=6, col=1)
		etiketaSaldoBerria = new JLabel("Saldo berria");
		this.add(etiketaSaldoBerria);
		// Grid (row=6, col=2)
		testuEremuaSaldoBerria  = new JTextField();
		this.add(testuEremuaSaldoBerria );
		testuEremuaSaldoBerria .setEditable(false);
		// Grid (row=6, col=3)
		botoiaBukatu = new JButton("Bukatu");
		this.add(botoiaBukatu);
		botoiaBukatu.addActionListener(this);
		// Botoiak eta testu eremuak ezgaitu
		botoiaGordailua.setEnabled(false);
		botoiaDiruAteratzea.setEnabled(false);
		botoiaBukatu.setEnabled(false);
		botoiaKontsulta.setEnabled(false);
		testuEremuaGordailua.setEditable(false);
		testuEremuaDiruAteratzea.setEditable(false);
	}
	public Kutxazaina getKutxazain() {
		return kutxazaina;
	}
	public void setKutxazain(Kutxazaina kutxazainBerria) {
		kutxazaina = kutxazainBerria;
	}

	private void jbInit() throws Exception {
		testuEremuaGordailua.setSize(new Dimension(486, 300));
	}
}
