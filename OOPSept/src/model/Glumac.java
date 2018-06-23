package model;

public class Glumac {
	String ime;
	String prezime;

	public Glumac(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
	}
	
	public String getIme() {
		return ime;
	}
	
	
	public String getPrezime() {
		return prezime;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ime + " " + prezime;
	}
	
}
