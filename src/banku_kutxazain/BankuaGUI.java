package banku_kutxazain;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;

class BankuaGUI extends JFrame {
	
	private Container contentPane;
	private NirePanela nirePanela;

	public BankuaGUI(String izenburua) {
		setTitle(izenburua);
		setSize(425, 350);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent anEvent) {
				// Irteeratik kontu nagusi guztia ikuskapenerako
				System.out.println(nirePanela.getKutxazain().toString());
				System.exit(0);
			}
		});
		nirePanela = new NirePanela();
		contentPane = getContentPane();
		contentPane.add(nirePanela);
	}
	public static void main(String[] args) {
		new BankuaGUI("Dirua Barra-barra").show();
	}

	public BankuaGUI() {
		try {
		  jbInit();
		}
		catch(Exception e) {
		  e.printStackTrace();
		}
	}

	private void jbInit() throws Exception  {
		this.setSize(new Dimension(454, 300));
	}
}
