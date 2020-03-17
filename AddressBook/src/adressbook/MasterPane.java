package adressbook;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class MasterPane extends ScrollPane implements AddressSettings{
    private TextField[] tx;
	private Label[] lb;
	private Text[] wlb;
	private boolean ActiveWarning;
	
    public boolean isActiveWarning() {
		return ActiveWarning;
	}

	private void setActiveWarning(boolean activeWarning) {
		ActiveWarning = activeWarning;
	}

	public MasterPane()
	{ 
    	ActiveWarning=false;
    	GridPane gp = new GridPane();
    	
    	setContent(gp);
  	    fitToHeightProperty().set(true);
    	
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		gp.setHgap(5);
		gp.setVgap(5);
		
		tx = new TextField[FIELDSCOUNT];
		lb = new Label[FIELDSCOUNT];
		wlb = new Text[FIELDSCOUNT];
		
		for (int i=0; i<FIELDSCOUNT; i++)
		{
			lb[i]=new Label(FIELDS[i]);
		    tx[i] = new TextField();
	  		tx[i].setOnKeyTyped(e->formValidate());

		    wlb[i]=new Text();
  		    wlb[i].setWrappingWidth(230);
  		    wlb[i].setFill(Color.RED);
  		    wlb[i].setStyle("-fx-font-size: 9");
  	        
  		    gp.add(lb[i], 0, i*2);
  		    gp.add(tx[i], 1, i*2);
  	        gp.add(wlb[i], 0, i*2+1);
  		      GridPane.setColumnSpan(wlb[i], 2);
		}

		wlb[0].setText(FIELDWARNINGS[1]);
		wlb[1].setText(FIELDWARNINGS[1]);
		wlb[2].setText(FIELDWARNINGS[2]);
		wlb[3].setText(FIELDWARNINGS[2]);
		wlb[4].setText(FIELDWARNINGS[3]);

		TextFieldNormalState();
	}	
    
    public void ShowTextWarnings(boolean show)
    {
		for (int i=0; i<FIELDSCOUNT; i++)
		   wlb[i].setVisible(show);
    }
    
	
	public void TextFieldNewEditRecordState()
	{
	    ShowTextWarnings(true);
		clearTextFields();
		
		for (int i = 0; i<FIELDSCOUNT; i++ )
			  tx[i].setDisable(false);
	    
	    tx[0].requestFocus();

	    formValidate();
	}
	public void TextFieldNormalState()
	{
	    clearTextFields();

		for (int i = 0; i<FIELDSCOUNT; i++ )
			  tx[i].setDisable(true);

		ShowTextWarnings(false);
	}
	
    public TextField[] getTx() {
		return tx;
	}

	public void setTx(TextField[] tx) {
		this.tx = tx;
	}
	
	public void formValidate()
	{
		setActiveWarning(false);
	    for (int i = 0; i<FIELDSCOUNT-1; i++ )
	    {	
   		   if (tx[i].getText().isEmpty())
   		   {
			  wlb[i].setText(FIELDWARNINGS[1]);
			  setActiveWarning(true);
   		   } 	  
   		   else
   			  wlb[i].setText(FIELDWARNINGS[0]);
	    }  
			
	    if (!tx[2].getText().matches("^\\([0-9]{3}\\)\\s?[0-9]{3}-[0-9]{4}$"))
	    {	
	    	wlb[2].setText(FIELDWARNINGS[2]);
			setActiveWarning(true);
	    }	
	    
	    if (!tx[3].getText().matches("^\\([0-9]{3}\\)\\s?[0-9]{3}-[0-9]{4}$"))
	    {	
	    	wlb[3].setText(FIELDWARNINGS[2]);
			setActiveWarning(true);
	    }	
	    
	    String email=tx[4].getText();
	    if (!email.contains("@") || !email.contains("."))
	    {	
	    	wlb[4].setText(FIELDWARNINGS[3]);
			setActiveWarning(true);
	    }	
	}
	
	public void clearTextFields()
	{
		for (int i = 0; i < FIELDSCOUNT; i++)
			tx[i].setText("");
	}
	
	public void contactToTextField(Contact contact)
	{
		getTx()[0].setText(contact.getFirstName());
		getTx()[1].setText(contact.getLastName());
		getTx()[2].setText(contact.getPhone1());
		getTx()[3].setText(contact.getPhone2());
		getTx()[4].setText(contact.getEmail());
		getTx()[5].setText(contact.getNotes());
		
		formValidate();
	}
	
	
	

}
