package model;

public class Reziser {
	
	private String ime;
	private String prezime;
	
	public Reziser(String ime, String prezime) {
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
		return ime + " " + prezime;
	}
	
}
