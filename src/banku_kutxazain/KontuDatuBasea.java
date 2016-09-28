package banku_kutxazain;

import java.io.*;
import java.sql.*;
import java.util.*;

public class KontuDatuBasea {

    private static KontuDatuBasea instantzia;
    private String urla;
    private String dba;
    private Connection konekzioa;
    private Statement sententzia;

    public KontuDatuBasea() {
        try {
            urla = System.getProperty("user.dir") + File.separator + "bankua.db";
            dba = "jdbc:sqlite://" + urla;
            Class.forName("org.sqlite.JDBC");
            konekzioa = DriverManager.getConnection(dba, "", "");
            sententzia = konekzioa.createStatement();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
            anException.printStackTrace();
        }
    }

    public void finalize() {
        try {
            sententzia.close();
            konekzioa.close();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (Exception anException) {
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
        sql = "INSERT into Transakzioa VALUES (" + transakzioZenbakiBat + ", " + kontuZenbakiBat + ", '" + dateFormat.format(dataBat) + "'," + kantitateBat + ", '" + motaBat + "')";
        System.out.println("SQL : " + sql);
        try {
            // Insert sql exekutatu sartutako erregistroak itzuliz
            count = sententzia.executeUpdate(sql);
            System.out.println("count : " + count);
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println(" SQL Exception : " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
            anException.printStackTrace();
        }
    }

    public static KontuDatuBasea instantzia() {
        if (instantzia == null) {
            instantzia = new KontuDatuBasea();
        }
        return instantzia;
    }

    public boolean kontsultatuBezeroa(int pId, int pPin) {
        // Deklarazioak
        String query = "Select * from Bezeroa where Id="+pId+"";
        ResultSet resultSet;    
        boolean login = false;
        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);          
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next()) {
                int pinZenbakia = Integer.parseInt(resultSet.getString(5));
                System.out.println("Bezeroa: IdZenbakia = " + pId + " | " + "pinZenbakia = " + pinZenbakia + "");
                if(pPin == pinZenbakia) {                    
                    login = true;
                }                   
            }
            resultSet.close();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
            anException.printStackTrace();
        }
        return login;
    }
    
    public ArrayList<Integer> kontsultatuKontuak(int pId){
        ArrayList<Integer> kontuak = new ArrayList<Integer>();
        String query = "Select * from Kont_Bez where BezeroId="+pId+"";
        ResultSet resultSet;  
        
        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);          
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next()) {
                int kontuZenbakia = Integer.parseInt(resultSet.getString(1));
                System.out.println("Kontua: Zenbakia = " + kontuZenbakia);
                kontuak.add(kontuZenbakia);
            }
            resultSet.close();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
            anException.printStackTrace();
        }
        
        return kontuak;
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
            while (resultSet.next()) {
                transakzioZenbakia = resultSet.getInt("Zenbat");
            }
            resultSet.close();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
            anException.printStackTrace();
        } finally {
            if (transakzioZenbakia == 0) {
                transakzioZenbakia = 30000;
            }
            return transakzioZenbakia;
        }
    }
}
