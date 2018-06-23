package view;


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
	
	private static Page2 instance = null; 
	public Page2(Page1 page, Baza baza) {
		//setHeight(300);
		//setWidth(350);
		Button btnZatvori = new Button("Zatvori");
			btnZatvori.setOnAction(e-> {
				close();
				page.showThis();
			});
		VBox mainLayout = new VBox(20);
		Film film = page.getTvFilmovi().getSelectionModel().getSelectedItem();
		mainLayout.getChildren().addAll(new Label("Naslov: " + film.getNaslov()),
										new Label("Reziser: " + film.getReziser().toString()),
										new Label("Glumci: " + film.getGlumci()),
										new Label("Godina: " + film.getGodinaIzdavanja()),
										new Label("Statistika ocena: "));
		VBox oceneLayout = new VBox();
			//da ne bih imao 30 linija istog koda!
			for (int i = 5; i <= 10; i++) {
			oceneLayout.getChildren().add(new Label("Ocena " + i + " - " + baza.getProcenatOcene(i, film) + "%"));
			}
			
			mainLayout.getChildren().add(oceneLayout);
			
			HBox hBoxBtnZatvori = new HBox(5);
			hBoxBtnZatvori.getChildren().add(btnZatvori);
				hBoxBtnZatvori.setAlignment(Pos.BOTTOM_RIGHT);
			mainLayout.getChildren().add(hBoxBtnZatvori);
			
			//property
			mainLayout.setAlignment(Pos.TOP_LEFT);
		
		Scene sc = new Scene(mainLayout);
		setWidth(500);
		setTitle("OOP - SEPT - G1");
		setScene(sc);
		showAndWait();
	}
	
	public static Page2 getInstance(Page1 page, Baza baza) {
		if(instance == null) instance = new Page2(page, baza);
		return instance;
	}
	
	
	
}
