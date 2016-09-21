package banku_kutxazain;

public class Bezeroa
{
	private long telefonoZenbakia;
	private String izena;
public Bezeroa(String izenBat, long telefonoZenbakiBat)
{
	this.setIzena(izenBat);
	this.setTelefonoZenbakia(telefonoZenbakiBat);
}
public String getIzena()
{
	return izena;
}
public long getTelefonoZenbakia()
{
	return telefonoZenbakia;
}
public void setIzena(String izenBerria)
{
	this.izena = izenBerria;
}
public void setTelefonoZenbakia(long telefonoZenbakiBerria)
{
	this.telefonoZenbakia = telefonoZenbakiBerria;
}
public String toString()
{
	try
	{
		return ObjetuIkuskatzailea.toString(this);
	}
	catch (Exception exception)
	{
		return "errorea To String -n";
	}
}
}
