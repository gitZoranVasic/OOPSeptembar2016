package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.Page1;
import view.Page2;

public class BtnViseDetaljaController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		if(Page1.getInstance().getTvFilmovi().getSelectionModel().getSelectedItem() != null) {
			Page2 page2 = new Page2();
		}
	}

}
