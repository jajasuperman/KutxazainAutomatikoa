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
        String query = "Select * from Bezeroa where Id=" + pId + "";
        ResultSet resultSet;
        boolean login = false;
        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next()) {
                int pinZenbakia = Integer.parseInt(resultSet.getString(5));
                System.out.println("Bezeroa: IdZenbakia = " + pId + " | " + "pinZenbakia = " + pinZenbakia + "");
                if (pPin == pinZenbakia) {
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
        }
        return login;
    }

    public ArrayList<Integer> kontsultatuKontuak(int pId) {
        ArrayList<Integer> kontuak = new ArrayList<>();
        String query = "Select * from Kont_Bez where BezeroId=" + pId + "";
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
        }

        return kontuak;
    }

    public Kontua kontsultatuKontua(int pId) {
        Kontua kontua = null;
        String query = "Select * from Kontua where Kontu_Zenbakia=" + pId + "";
        ResultSet resultSet;

        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next()) {
                double saldoZaharra = Double.parseDouble(resultSet.getString(2));
                kontua = new Kontua(pId, saldoZaharra);
            }
            resultSet.close();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
        }

        return kontua;
    }

    public ArrayList<String> kontsultatuHiriak() {
        ArrayList<String> hiriak = new ArrayList<>();
        String query = "Select * from Hiria";
        ResultSet resultSet;

        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next()) {
                String hiriIzena = resultSet.getString(1);
                hiriak.add(hiriIzena);
            }
            resultSet.close();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
        }

        return hiriak;
    }

    public ArrayList<String> kontsultatuZinemak(String pHiria) {
        ArrayList<String> zinemak = new ArrayList<>();
        String query = "Select * from Zinema where HiriaIzena='" + pHiria + "'";
        ResultSet resultSet;

        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next()) {
                String zinemaIzena = resultSet.getString(1);
                zinemak.add(zinemaIzena);
            }
            resultSet.close();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
        }

        return zinemak;
    }

    public ArrayList<String> kontsultatuPelikulak(String pHiria, String pZinema) {
        ArrayList<String> pelikulak = new ArrayList<>();
        String query = "Select * from Pelikula where HiriaIzena='" + pHiria + "' and ZinemaIzena='" + pZinema + "'";
        ResultSet resultSet;

        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next()) {
                String pelikulaIzena = resultSet.getString(1);
                pelikulak.add(pelikulaIzena);
            }
            resultSet.close();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
        }

        return pelikulak;
    }
    
    public ArrayList<String> kontsultatuOrduak(String pHiria, String pZinema, String pPelikula, String pEguna) {
        ArrayList<String> pelikulak = new ArrayList<>();
        String query = "Select Ordua from Egunak where HiriaIzena='" + pHiria + "' and ZinemaIzena='" + pZinema + "' and PelikulaIzena='" + pPelikula + "' and Eguna='" + pEguna + "'";
        ResultSet resultSet;

        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next()) {
                String pelikulaIzena = resultSet.getString(1);
                pelikulak.add(pelikulaIzena);
            }
            resultSet.close();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
        }

        return pelikulak;
    }
    
    public boolean pelikulaOrdaindu(int pKontua, double pPrezioa) {
        
        String query = "Select Saldo_Zaharra from Kontua where Kontu_Zenbakia='" + pKontua + "'";
        ResultSet resultSet;
        
        boolean ordainduDa = false;
        double dirua = 0;

        try {
            // SQL exekutatu
            resultSet = sententzia.executeQuery(query);
            //	Resultset-eko errenkada eta zutabe guztiak kapturatu
            while (resultSet.next()) {
                dirua = resultSet.getDouble(1);
            }
            if(pPrezioa <= dirua) {
                String query1 = "Update Kontua Set Saldo_Zaharra="+(dirua-pPrezioa)+" Where Kontu_Zenbakia='" + pKontua + "'";
                sententzia.executeUpdate(query1);
                ordainduDa = true;
            }           
            
            resultSet.close();
        } catch (SQLException anException) {
            while (anException != null) {
                System.out.println("SQL Exception:  " + anException.getMessage());
                anException = anException.getNextException();
            }
        } catch (java.lang.Exception anException) {
        }

        return ordainduDa;
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
        } finally {
            if (transakzioZenbakia == 0) {
                transakzioZenbakia = 30000;
            }
            return transakzioZenbakia;
        }
    }
}
