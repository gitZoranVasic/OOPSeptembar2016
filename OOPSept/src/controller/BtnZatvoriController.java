package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.Page2;

public class BtnZatvoriController implements EventHandler<ActionEvent> {

	Page2 page2;
	
	public BtnZatvoriController(Page2 page2) {
		this.page2 = page2;
	}
	
	@Override
	public void handle(ActionEvent event) {
		page2.close();
	}
	
	
}
