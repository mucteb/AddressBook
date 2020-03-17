package adressbook;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class SearchPane extends BorderPane implements AddressSettings {
    private TextField sTx;
	private Label sLb; 
	
	public SearchPane(String title) {
 		   super();
		  
 		   sLb = new Label(title);
		   sLb.setStyle("-fx-translate-y:-14;-fx-text-fill:black; -fx-background-color:lightgrey");
		   sTx = new TextField();
	   	   sTx.setMaxWidth(Double.MAX_VALUE);
		   
		   setAlignment(sLb, Pos.TOP_LEFT);
		   setTop(sLb);
 		   setCenter(sTx);

 		   setPadding(new Insets(4, 12.5, 13.5, 14.5));
		   setStyle("-fx-font-size: 14; -fx-border-color:lightgrey;-fx-border-style:solid;-fx-border-width:3;");
	}
	
    public TextField getsTx() {
		return sTx;
	}

	public void setsTx(TextField sTx) {
		this.sTx = sTx;
	}
	
	public void clearTextFields()
	{
		sTx.setText("");
	}
	
	


}
