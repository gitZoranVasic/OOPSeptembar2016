package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Film {
	
	private String naslov;
	private int godinaIzdavanja;
	private Reziser reziser;
	private double prosecnaOcena;
	

	ObservableList<Glumac> glumci;
	ObservableList<Integer> ocene;
	
	public Film(String naslov, int godinaIzdavanja, Reziser reziser) {
		this.naslov = naslov;
		this.godinaIzdavanja = godinaIzdavanja;
		this.reziser = reziser;
		glumci = FXCollections.observableArrayList();
		ocene = FXCollections.observableArrayList();

}
	
	
	public Double izracunajProsecnuOcenu() {
		double zbirOcena = 0;
		for (Integer ocena : ocene) {
			zbirOcena += ocena;
		}
		this.prosecnaOcena = zbirOcena/(double)ocene.size();
		return prosecnaOcena;
	}
	
	public void dodajOcenu(Integer ocena) {
		this.ocene.add(ocena);
		izracunajProsecnuOcenu();
	}
	
	public String getNaslov() {
		return naslov;
	}

	public int getGodinaIzdavanja() {
		return godinaIzdavanja;
	}

	public Reziser getReziser() {
		return reziser;
	}

	public ObservableList<Glumac> getGlumci() {
		return glumci;
	}

	public ObservableList<Integer> getOcene() {
		return ocene;
	}
	
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return naslov;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof Film) {
			Film f = (Film)arg0;
			if(f.getNaslov().equals(this.getNaslov()) && f.getGodinaIzdavanja() == this.getGodinaIzdavanja()) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
}
