package adressbook;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AddressMain extends Application implements AddressSettings {

	private Contact ActiveRowData;
	private int selectedDataIndex = 0;
	private Contact tempContact;

	public void start(Stage ps) throws Exception {

		BorderPane gp = new BorderPane();
		gp.setPadding(new Insets(10, 5, 5, 5));

		gp.setMaxWidth(800);
		gp.setMaxHeight(500);
		gp.setMinSize(400, 400);

		/*
		 * The application has 4 different panels: Search panel on the Top. Search panel
		 * has one TextField for Incremental Search. Master Panel on the left. Master
		 * Panel has some TextEdit for entering/editing data. Table Panel on the Center
		 * TablePanel has one Table for showing data. Button Panel on the Bottom
		 * ButtonPanel has couple Buttons for actions Add, Delete, Edit, etc
		 */

		SearchPane sp = new SearchPane(SEARCHLABEL);
		MasterPane mp = new MasterPane();
		TablePane tp = new TablePane();
		ButtonPane bp = new ButtonPane();

		gp.setTop(sp);
		gp.setLeft(mp);
		gp.setCenter(tp);
		gp.setBottom(bp);

		// On row change
		tp.getTable().setRowFactory(tv -> {
			TableRow<Contact> row = new TableRow<>();
//			tp.getTable().setOnKeyPressed(e -> {
//
//				ObservableList<Contact> selectedCells = tp.getTable().getSelectionModel().getSelectedCells();
//				
//				
//				ActiveRowData = (Contact)selectedCells;
//				System.out.println(ActiveRowData.toString());
//
//				
//				if (!row.isEmpty()) {
//					if (selectedCells.size() != 1) {
//						ActiveRowData = (Contact)selectedCells;
//						selectedDataIndex = tp.getData().indexOf(ActiveRowData);
//						tempContact = ActiveRowData;
//
//						System.out.println(ActiveRowData.toString());
//					}
//				}
//
//			});

			row.setOnMouseClicked(event -> {

				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {

					ActiveRowData = row.getItem();
					selectedDataIndex = tp.getData().indexOf(ActiveRowData);
					tempContact = ActiveRowData;

					bp.ButtonsEditRecordState();
					mp.TextFieldNewEditRecordState();
					mp.contactToTextField(ActiveRowData);
				}
			});
			return row;
		});

		// New Button Click
		bp.getBt()[0].setOnAction(e -> {

			sp.clearTextFields();
			tp.setTableDataSource(tp.getData());
			bp.ButtonsNewRecordState();
			mp.TextFieldNewEditRecordState();
		});

		// Add Button Click
		bp.getBt()[1].setOnAction(e -> {
			if (!mp.isActiveWarning()) {
				Contact newContact = new Contact(mp.getTx()[0].getText(), mp.getTx()[1].getText(),
						mp.getTx()[2].getText(), mp.getTx()[3].getText(), mp.getTx()[4].getText(),
						mp.getTx()[5].getText());

				bp.ButtonsNormalState();
				tp.addContactToData(tp.getData(), newContact);
				mp.TextFieldNormalState();

			} else {
				Alert a = new Alert(AlertType.WARNING, ALERTWARNINGS[1]);
				a.showAndWait();
			}
		});

		// Cancel Button Click
		bp.getBt()[2].setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle(ALERTWARNINGS[1]);
			a.setHeaderText(ALERTWARNINGS[9]);
			a.setContentText(ALERTWARNINGS[10]);

			Optional<?> result = a.showAndWait();
			if (result.get() == ButtonType.OK) {
				sp.clearTextFields();
				tp.setTableDataSource(tp.getData());
				mp.TextFieldNormalState();
				bp.ButtonsNormalState();
			}

		});

		// Replace Button Click
		bp.getBt()[3].setOnAction(e -> {

			if (!mp.isActiveWarning()) {
				Alert a = new Alert(AlertType.CONFIRMATION);
				a.setTitle(ALERTWARNINGS[1]);
				a.setHeaderText(ALERTWARNINGS[5]);
				a.setContentText(ALERTWARNINGS[6]);

				Optional<?> result = a.showAndWait();
				if (result.get() == ButtonType.OK) {

					Contact updatedContact = new Contact(mp.getTx()[0].getText(), mp.getTx()[1].getText(),
							mp.getTx()[2].getText(), mp.getTx()[3].getText(), mp.getTx()[4].getText(),
							mp.getTx()[5].getText());

					tp.updateContactToData(tp.getData(), selectedDataIndex, updatedContact);
					if (tp.getTempData().indexOf(tempContact) >= 0)
						tp.updateContactToData(tp.getTempData(), tp.getTempData().indexOf(tempContact), updatedContact);

					bp.ButtonsNormalState();
					mp.TextFieldNormalState();
				}
			} else {
				Alert a = new Alert(AlertType.WARNING, ALERTWARNINGS[1]);
				a.showAndWait();
			}
		});

		// Delete Button Click
		bp.getBt()[4].setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle(ALERTWARNINGS[2]);
			a.setHeaderText(ALERTWARNINGS[3]);
			a.setContentText(ALERTWARNINGS[4]);

			Optional<?> result = a.showAndWait();
			if (result.get() == ButtonType.OK) {

				Contact selectedItem = (Contact) tp.getTable().getSelectionModel().getSelectedItem();
				tp.removeSelectedItemFromData(tp.getData(),selectedItem);
				bp.ButtonsNormalState();
				mp.TextFieldNormalState();
			}

		});

		// Clear file Button
		bp.getBt()[5].setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle(ALERTWARNINGS[1]);
			a.setHeaderText(ALERTWARNINGS[7]);
			a.setContentText(ALERTWARNINGS[8]);

			Optional<?> result = a.showAndWait();
			if (result.get() == ButtonType.OK) {
				mp.TextFieldNormalState();
				sp.clearTextFields();
				tp.clearData(tp.getData());
				
			}
		});

		// Get information from file
		bp.getBt()[6].setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle(ALERTWARNINGS[1]);
			a.setHeaderText(ALERTWARNINGS[11]);
			a.setContentText(ALERTWARNINGS[12]);

			Optional<?> result = a.showAndWait();
			if (result.get() == ButtonType.OK) {
				sp.clearTextFields();
				try {
					tp.getItemsFromFile(FILEPATHNAME);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Backup Data
		bp.getBt()[7].setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle(ALERTWARNINGS[1]);
			a.setHeaderText(ALERTWARNINGS[13]);
			a.setContentText(ALERTWARNINGS[14]);
			
			boolean check = true;

			Optional<?> result = a.showAndWait();
			if (result.get() == ButtonType.OK) {
				try {
					tp.setItemsToFile(BACKUPFILEPATHNAME);
				} catch (IOException e1) {
					e1.printStackTrace();
					check=false;
				}
				
				if (check) 
				{
					Alert alert = new Alert(AlertType.INFORMATION, ALERTWARNINGS[18]);
					alert.showAndWait();
				}	

			}

		});

		// Revert Data
		bp.getBt()[8].setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle(ALERTWARNINGS[1]);
			a.setHeaderText(ALERTWARNINGS[15]);
			a.setContentText(ALERTWARNINGS[16]);
			boolean check = true;

			Optional<?> result = a.showAndWait();
			if (result.get() == ButtonType.OK) {
				mp.TextFieldNormalState();
				sp.clearTextFields();
				
				try {
					tp.getItemsFromFile(BACKUPFILEPATHNAME);
				} catch (IOException e1) {
					Alert alert = new Alert(AlertType.ERROR, BACKUPFILEPATHNAME +" "+ ALERTWARNINGS[17]);
					alert.showAndWait();
					e1.printStackTrace();
					check = false;
				}

				if (check) {
					try {
						tp.setItemsToFile(FILEPATHNAME);
					} catch (IOException e1) {
						e1.printStackTrace();
						check = false;
					}
					
					if (check) 
					{
						Alert alert = new Alert(AlertType.INFORMATION, ALERTWARNINGS[19]);
						alert.showAndWait();
					}	
					
				}
			}

		});

		// Incremental Search
		sp.getsTx().setOnKeyTyped(e -> {
			if (!sp.getsTx().getText().isEmpty()) {
				mp.TextFieldNormalState();
				bp.ButtonsNormalState();
				tp.clearData(tp.getTempData());

				Iterator<Contact> i = tp.getData().iterator();
				String dataLine;

				while (i.hasNext()) {
					tempContact = (Contact) i.next();
					dataLine = tempContact.toString();

					if (dataLine.contains(sp.getsTx().getText())) {
						tp.addContactToData(tp.getTempData(), tempContact);

					}
					tp.setTableDataSource(tp.getTempData());
				}
			} else
			{
				tp.setTableDataSource(tp.getData());
				mp.TextFieldNormalState();
				
			}	

		});

		Scene scene = new Scene(gp);
		ps.setTitle(TITLE); // Set the stage title
		ps.setScene(scene); // Place the scene in the stage
		ps.sizeToScene();
		ps.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}

}
