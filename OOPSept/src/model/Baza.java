package model;

import java.io.BufferedReader;
import java.io.FileReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Baza {
	
	private static Baza instance = null;
	
	private ObservableList<Film> filmovi;

	
	public Baza() {
			filmovi = FXCollections.observableArrayList();
			ucitajIzBaze();
	}
	
	
	private void ucitajIzBaze() {
		try {
			FileReader load = new FileReader("filmovi.txt");
			BufferedReader baferLine = new BufferedReader(load);
			String linija;
			
				while((linija = baferLine.readLine()) != null) {
					String parseMain[] = linija.split(";");
					String parseReziser[] = parseMain[2].split(" ");
					String parseGlumci[] = parseMain[3].split(",");
					String parseOcene[] = parseMain[4].split(",");
					
					Reziser reziser = new Reziser(parseReziser[0], parseReziser[1]);
					Film film = new Film(parseMain[0], Integer.parseInt(parseMain[1]), reziser);
					
					for (int i = 0; i < parseGlumci.length; i++) {
						String parseGlumac[] = parseGlumci[i].split(" ");
						Glumac g = new Glumac(parseGlumac[0], parseGlumac[1]);
						film.getGlumci().add(g);
					}
					
					for (int i = 0; i < parseOcene.length; i++) {
						film.getOcene().add(Integer.parseInt(parseOcene[i]));
					}
					film.izracunajProsecnuOcenu();
					filmovi.add(film);
				}
			baferLine.close();
			load.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getProcenatOcene(int ocena, Film film) {
		int procenatIstihOcena = 0;
		for (Integer ocenaArr : film.getOcene()) {
			if(ocena == ocenaArr) {
				procenatIstihOcena++;
			}
		}
		
		procenatIstihOcena = (int) (((double)procenatIstihOcena/(double)film.getOcene().size())*100);
		
		return procenatIstihOcena;
	}
	
	public ObservableList<Film> filterFilmova(String ime, String prezime, String godinaOd, String godinaDo, String naslov, boolean tacanNaslov) {
		ObservableList<Film> listaFilmova = FXCollections.observableArrayList();
		for (Film film : getFilmovi()) {
			boolean boolFilmova = true;
			
			if(ime != null && !(ime.equals("")) && boolFilmova) {
				for (Glumac glumac : film.getGlumci()) {
					if(glumac.getIme().toLowerCase().equals(ime.toLowerCase())) {
						boolFilmova = true;
						break;
					}else {
						boolFilmova = false;
					}
				}
				if(boolFilmova == false) {
					boolFilmova = film.getReziser().getIme().toLowerCase().contains(ime.toLowerCase());
				}
			}
			
			if(prezime != null && !(prezime.equals("")) && boolFilmova) {
				for (Glumac glumac : film.getGlumci()) {
					if(glumac.getPrezime().toLowerCase().equals(prezime.toLowerCase())) {
						boolFilmova = true;
						break;
					}else {
						boolFilmova = false;
					}
				}
				if(boolFilmova == false) {
					boolFilmova = film.getReziser().getPrezime().toLowerCase().equals(prezime.toLowerCase());
				}
			}
			
			if(godinaOd != null && !godinaOd.equals("") && boolFilmova) {
				boolFilmova = film.getGodinaIzdavanja() >= Integer.parseInt(godinaOd);

			}
			
			if(godinaDo != null && !godinaDo.equals("") && boolFilmova) {
				boolFilmova = film.getGodinaIzdavanja() <= Integer.parseInt(godinaDo);
			}
			
			if(naslov != null && !naslov.equals("") && boolFilmova) {
				if(tacanNaslov) {
					boolFilmova = naslov.toLowerCase().equals(film.getNaslov().toLowerCase());
				}else {
					boolFilmova = film.getNaslov().toLowerCase().contains(naslov.toLowerCase());
				}
			}
			
			if(boolFilmova) listaFilmova.add(film);
			
		}
		
		
		return listaFilmova;
		
	}

	
//	private void ispisiImenaFilmova() {
//	for (Film film : filmovi) {
//		System.out.println(film.getNaslov());
//	}
//}
	
	public ObservableList<Film> getFilmovi() {
		return filmovi;
	}


	public static Baza getInstance() {
		if(instance == null) instance = new Baza();
		return instance;
	}

}
