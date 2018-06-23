package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Baza;
import model.Film;
import model.Reziser;

public class Page1 extends Stage{
	
	private static Page1 instance = null;
	
	Baza baza = Baza.getInstance();
	
	TextField tfIme;
	TextField tfPrezime;
	TextField tfGodinaOd;
	TextField tfGodinaDo;
	TextField tfNaslov;
	TextField tfNovaOcena;
	
	CheckBox  chTacanNaslov;
	
	Button	  btnFiltriraj;
	Button	  btnViseDetalja;
	Button	  btnOceni;
	
	TableView<Film> tvFilmovi;
	
	public Page1() {
		
		setMinWidth(600);
		setMinHeight(600);
		setTitle("OOP - Septembar - Grupa 1");
		
		tfIme = new TextField();
		tfPrezime = new TextField();
		tfGodinaOd = new TextField();
		tfGodinaDo = new TextField();
		tfNaslov = new TextField();
		tfNovaOcena = new TextField();
		
		chTacanNaslov = new CheckBox("Tacan naziv");
			chTacanNaslov.setDisable(false);
		btnFiltriraj = new Button("Filtriraj");
			btnFiltriraj.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					Page1 page = Page1.getInstance();
					page.getTvFilmovi().setItems(Baza.getInstance().filterFilmova(page.getTfIme(),
																				  page.getTfPrezime(),
																				  page.getTfGodinaOd(),
																				  page.getTfGodinaDo(),
																				  page.getTfNaslov(),
																				  page.getChTacanNaslov()));
					
				}
				
			});
			
		
		btnViseDetalja = new Button("Vise detalja...");
			btnViseDetalja.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					if(Page1.getInstance().getTvFilmovi().getSelectionModel().getSelectedItem() != null) 
					Page2.getInstance(Page1.getInstance(), Baza.getInstance()).show();
				}
			});
				
		btnOceni = new Button("Oceni");
			btnOceni.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					Page1 page = Page1.getInstance();
					Baza baza = Baza.getInstance();
					
					for (Film film : baza.getFilmovi()) {
						if(page.getTvFilmovi().getSelectionModel().getSelectedItem().equals(film)) {
							film.dodajOcenu(Integer.parseInt(page.getTfNovaOcena().getText()));
							page.getTfNovaOcena().clear();
							page.getTvFilmovi().refresh();
						}
					}
				}
			});
		
		
		
		//GRIDPANE
		GridPane gpLayout = new GridPane();
			//column 0
			gpLayout.add(new Label("Ime"), 0, 0);
			gpLayout.add(new Label("Prezime"), 0, 1);
			gpLayout.add(new Label("Godina opseg"), 0, 2);
			gpLayout.add(new Label("Naslov"), 0, 3);
			//column 1
			gpLayout.add(tfIme, 1, 0);
			gpLayout.add(tfPrezime, 1, 1);
			gpLayout.add(tfGodinaOd, 1, 2);		
			gpLayout.add(tfNaslov, 1, 3);		
			gpLayout.add(btnFiltriraj, 1, 4);	
			//column 2
			gpLayout.add(tfGodinaDo, 2, 2);
			gpLayout.add(chTacanNaslov, 2, 3);
			//property
			gpLayout.setVgap(20);
			gpLayout.setHgap(20);
			gpLayout.setAlignment(Pos.CENTER);
			
		//TABLEVIEW
			tvFilmovi = new TableView<>();
			//property
			tvFilmovi.setMaxWidth(550);
			
					//TABLE COLUMNS
					TableColumn<Film, String> tcNaslov = new TableColumn<>("Naslov");
						tcNaslov.setMinWidth(tvFilmovi.getMaxWidth()/4);
						tcNaslov.setCellValueFactory(new PropertyValueFactory<>("naslov"));
					TableColumn<Film, Integer> tcGodina = new TableColumn<>("Godina");
						tcGodina.setMinWidth(tvFilmovi.getMaxWidth()/4);
						tcGodina.setCellValueFactory(new PropertyValueFactory<>("godinaIzdavanja"));
					TableColumn<Film, Reziser> tcReziser = new TableColumn<>("Reziser");
						tcReziser.setMinWidth(tvFilmovi.getMaxWidth()/4);
						tcReziser.setCellValueFactory(new PropertyValueFactory<>("reziser"));
					TableColumn<Film, Double> tcProsecnaOcena = new TableColumn<>("Prosecna Ocena");
						tcProsecnaOcena.setMinWidth(tvFilmovi.getMaxWidth()/4);
						tcProsecnaOcena.setCellValueFactory(new PropertyValueFactory<>("prosecnaOcena"));
			tvFilmovi.getColumns().addAll(tcNaslov,
										  tcGodina,
										  tcReziser,
										  tcProsecnaOcena);
			tvFilmovi.setItems(baza.getFilmovi());
			
		
			//HBOX LAYOUT
			HBox hBoxLayout = new HBox(20);
				hBoxLayout.getChildren().addAll(new Label("Nova ocena: "),
												tfNovaOcena,
												btnOceni);
				//property
				hBoxLayout.setAlignment(Pos.CENTER);
			//mainLayout add
			VBox mainLayout = new VBox(20);
			mainLayout.getChildren().addAll(gpLayout,
											tvFilmovi,
											btnViseDetalja,
											hBoxLayout);
			
			//property
			mainLayout.setAlignment(Pos.CENTER);
			mainLayout.setPadding(new Insets(30));
			
		Scene sc = new Scene(mainLayout);
		setScene(sc);	
		show();
		
	}
	
	public TextField getTfNovaOcena() {
		return tfNovaOcena;
	}
	
	public Button getBtnViseDetalja() {
		return btnViseDetalja;
	}
	
	public Button getBtnOceni() {
		return btnOceni;
	}
	
	public static Page1 getInstance() {
		if(instance == null) instance = new Page1();
		return instance;
	}
	
	public String getTfIme() {
		return tfIme.getText();
	}
	
	public String getTfPrezime() {
		return tfPrezime.getText();
	}
	
	public String getTfGodinaOd() {
		return tfGodinaOd.getText();
	}
	
	public String getTfGodinaDo() {
		return tfGodinaDo.getText();
	}
	
	public String getTfNaslov() {
		return tfNaslov.getText();
	}
	
	public boolean getChTacanNaslov() {
		return chTacanNaslov.isSelected();
	}
	
	public Button getBtnFiltriraj() {
		return btnFiltriraj;
	}
	
	public TableView<Film> getTvFilmovi() {
		return tvFilmovi;
	}
	
	public void showThis() {
		show();
	}
 	
}
