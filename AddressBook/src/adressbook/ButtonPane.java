package adressbook;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class ButtonPane extends GridPane implements AddressSettings {
  private Button[] bt;

  

public ButtonPane()
  {
	  bt= new Button[BUTTONCOUNT];
      
	  for(int i=0; i<BUTTONCOUNT; i++)
	  {  
	     bt[i]=new Button();
	     add(bt[i], i, 0);
	     bt[i].setMaxWidth(Double.MAX_VALUE);
	     bt[i].setText(BUTTONS[i]);
	  }
	  
	    ButtonsNormalState();
	    
		ColumnConstraints bc[] = new ColumnConstraints[BUTTONCOUNT];

		for (ColumnConstraints cc : bc) {
			cc = new ColumnConstraints();
			cc.setPercentWidth(100/BUTTONCOUNT+1);
			getColumnConstraints().add(cc);
		}
	  
  }
  
  public void ButtonsNewRecordState()
  {
      for (int i=0; i<BUTTONCOUNT; i++ )
    	  bt[i].setDisable(true);
    
          bt[1].setDisable(false);
          bt[2].setDisable(false);
      
	  
  }
	
  public void ButtonsNormalState()
  {
      for (int i=0; i<BUTTONCOUNT; i++ )
    	  bt[i].setDisable(false);
    
          bt[1].setDisable(true);
          bt[2].setDisable(true);
          bt[3].setDisable(true);
          bt[4].setDisable(true);
  }

  public void ButtonsEditRecordState()
  {
      for (int i=0; i<BUTTONCOUNT; i++ )
    	  bt[i].setDisable(false);
    
          bt[1].setDisable(true);
          bt[2].setDisable(false);
          bt[3].setDisable(false);
          bt[4].setDisable(false);
  }

  public Button[] getBt() {
	return bt;
}

public void setBt(Button[] bt) {
	this.bt = bt;
}
  
  
  
  
  
	
	
}
