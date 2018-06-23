package view;


import controller.BtnZatvoriController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Baza;
import model.Film;

public class Page2 extends Stage{
	
	Baza baza;
	Page1 page1;
	Film film;
	
	public Page2() {
		baza = Baza.getInstance();
		film = Page1.getInstance().getTvFilmovi().getSelectionModel().getSelectedItem();
		VBox mainLayout = new VBox(10);
		
		//children
		
		mainLayout.getChildren().addAll(new Label("Naslov: " + film.getNaslov()),
										new Label("Reziser: " + film.getReziser()),
										new Label("Glumci: " + film.getGlumci()),
										new Label("Godina: " + film.getGodinaIzdavanja()),
										new Label("Statistika ocena: "));
		
		for(int i = 5; i <= 10; i++) {
			mainLayout.getChildren().add(new Label("Ocena " + i + " - " + baza.getProcenatOcene(i, film) + "%"));
		}
		
		Button btnZatvori = new Button("Zatvori");
			btnZatvori.setOnAction(new BtnZatvoriController(this));
		HBox hButtonZatvori= new HBox();
			hButtonZatvori.getChildren().add(btnZatvori);
		mainLayout.getChildren().add(hButtonZatvori);
		
		//properties
		hButtonZatvori.setAlignment(Pos.BOTTOM_RIGHT);
		mainLayout.setAlignment(Pos.CENTER_LEFT);
		
		Scene sc = new Scene(mainLayout);
		setWidth(400);
		setTitle("OOP - non SINGLETON page2");
		setScene(sc);
		show();
	
	}
	
}
