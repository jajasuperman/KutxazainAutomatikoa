package banku_kutxazain;

import java.io.*;
import java.sql.*;
import java.util.*;

public final class KontuDatuBasea {
    private static KontuDatuBasea instantzia;
    private String urla;
    private String dba;
    private Connection konekzioa;
    private Statement sententzia;
        
    public KontuDatuBasea() throws ClassNotFoundException{
        try{
            urla = System.getProperty("user.dir") + File.separator + "bankua.db";
            dba = "jdbc:sqlite://"+ urla ;
            Class.forName("org.sqlite.JDBC");
            konekzioa = DriverManager.getConnection(dba,"","");
            sententzia = konekzioa.createStatement();
            sortuTaulak();
	}
	catch (SQLException anException){
            while (anException != null){
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
	}
    }

    public void sortuTaulak() {
        try {
            String sortuTransakzioa = "CREATE TABLE IF NOT EXISTS 'Transakzioa'" 
                + "(    Transakzio_Zenbakia     INTEGER PRIMARY KEY,"
                + "     Kontu_Zenbakia          INTEGER,"
                + "     Data                    DATETIME,"
                + "     Kantitatea              DOUBLE,"
                + "     Mota                    TEXT)";

            String sortuKontuak = "CREATE TABLE IF NOT EXISTS 'Kontua'"
                + "(    Kontu_Zenbakia                  INTEGER PRIMARY KEY,"
                + "     Pin_Zenbakia                    INTEGER,"
                + "     Saldo_Zaharra                   DOUBLE,"
                + "     Bezeroaren_Izena                TEXT,"
                + "     Bezeroaren_Telefono_Zenbakia    TEXT)";
            Statement st = konekzioa.createStatement();
            st.execute(sortuTransakzioa);
            st.execute(sortuKontuak);
        }
        catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        }

    }
    @Override
    public void finalize() {
        try {
            sententzia.close();
            konekzioa.close();
        }
        catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        }
        catch (Exception anException) {
            anException.printStackTrace();
        }
    }
    public Connection getKonekzioa() {
        return konekzioa;
    }
    public Statement getSententzia() {
        return sententzia;
    }  
    public String getUrl() {
        return urla;
    }
    public void sartuTransakzioa(int transakzioZenbakiBat, int kontuZenbakiBat, java.util.Date dataBat, double kantitateBat, String motaBat) {
        // Deklarazioak
        String sql;
        int count;
        java.text.DateFormat dateFormat = java.text.DateFormat.getDateTimeInstance();
        // Hasieraketa
        sql = "INSERT into Transakzioa VALUES (" + transakzioZenbakiBat + ", " + kontuZenbakiBat + ", '"  + dateFormat.format(dataBat) + "'," + kantitateBat + ", '" + motaBat + "')";
        System.out.println("SQL : " + sql);
        try {
            // Insert sql exekutatu sartutako erregistroak itzuliz
            count = sententzia.executeUpdate(sql);
            System.out.println("count : " + count);
        }
        catch (SQLException anException) {
            while (anException != null) {
                System.out.println(" SQL Exception : " + anException.getMessage());
                anException = anException.getNextException();
            }
        }
        catch (java.lang.Exception anException) {
            anException.printStackTrace();
        }
    }
    public static KontuDatuBasea instantzia() throws ClassNotFoundException {
            if (instantzia == null) {
                instantzia = new KontuDatuBasea();
            }
            return instantzia;
    }
    public HashMap irakurriKontuak() {
        // Deklarazioak
        String query = "select * from Kontua";
        HashMap accounts;
        ResultSet resultSet;
        // Initilization
        accounts = new HashMap();
        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next()) {
                int kontuZenbakia = Integer.parseInt(resultSet.getString(1));
                int pinZenbakia = Integer.parseInt(resultSet.getString(2));
                double saldoZaharra = Double.valueOf(resultSet.getString(3)).doubleValue();
                String bezeroaIzena = resultSet.getString(4);
                long bezeroaTelefonoZenbakia = Long.parseLong(resultSet.getString(5));
                System.out.println("Kontua: kontuZenbakia = " + kontuZenbakia + " | " + "pinZenbakia = " + pinZenbakia + " | " + "saldoZaharra = " + saldoZaharra + " | " + "bezeroaIzena = " + bezeroaIzena + " | " + "bezeroaTelefonoZenbakia = " + bezeroaTelefonoZenbakia + " | ");
                Kontua kontua;
                Bezeroa bezeroa;
                bezeroa = new Bezeroa(bezeroaIzena, bezeroaTelefonoZenbakia);
                kontua = new Kontua(kontuZenbakia, saldoZaharra, pinZenbakia, bezeroa);
                accounts.put(new Integer(kontuZenbakia), kontua);
            }
            resultSet.close();
        }
        catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        }
        catch (java.lang.Exception anException) {
            anException.printStackTrace();
        }
        finally {
            return accounts;
        }
    }
    public void setKonekzioa(Connection konekzioBerria) {
            konekzioa = konekzioBerria;
    }
    public void setSententzia(Statement sententziBerria) {
            sententzia = sententziBerria;
    }
    public void setUrl(String urlBerria) {
            urla = urlBerria;
    }

    public int irakurriHasierakoTransakzioZenbakia() {
        // Deklarazioak
        String query = "select MAX(Transakzio_Zenbakia) as Zenbat from Transakzioa";
        int transakzioZenbakia = 30000;
        ResultSet resultSet;	
        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next())
            {     
                    transakzioZenbakia = resultSet.getInt("Zenbat");		
            }
            resultSet.close();
        }
        catch (SQLException anException) {
            while (anException != null)
            {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        }
        catch (java.lang.Exception anException) {
            anException.printStackTrace();
        }
        finally {
            if (transakzioZenbakia == 0) {
                transakzioZenbakia = 30000;
            }
            return transakzioZenbakia;
        }
    }
}
