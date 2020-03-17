package adressbook;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablePane extends ScrollPane implements AddressSettings 
{
	private TableView<Contact> table = new TableView<Contact>();
	private TableColumn [] Col= new TableColumn [FIELDSCOUNT];
	private ObservableList<Contact> data = FXCollections.observableArrayList();
	private ObservableList<Contact> tempData = FXCollections.observableArrayList();

	public TablePane() throws IOException
	{
		super();
		table.setEditable(true);
  	    getItemsFromFile(FILEPATHNAME);
  	    setContent(table);
  	    fitToHeightProperty().set(true);
  	    fitToWidthProperty().set(true);
		
		for (int i = 0; i<FIELDSCOUNT; i++)
		{
			Col[i] = new TableColumn(FIELDS[i]); 
			Col[i].setMinWidth(100);
			Col[i].setCellValueFactory(new PropertyValueFactory<Contact, String>(FIELDS[i]));
            table.getColumns().add(Col[i]);
		}
		setTableDataSource(data);
	}
	
	public void getItemsFromFile(String path) throws IOException
	{
	    this.data.clear();	
		try {
			File f = new File(path);
			Scanner in = new Scanner(f);
			while(in.hasNextLine()) 
				addContactToData(data, new Contact(in.nextLine()));
	
			in.close();
		}
		catch(Exception e) {
		}
		
		setTableDataSource(data);
	}

	public void setItemsToFile(String path) throws IOException
	{
		File mFile = new File(path);
		if(mFile.exists()) mFile.delete();
	    // recreate the target file
		if (!mFile.exists()) mFile.createNewFile();

		// Create objects to read from sourceFile and write to targetFile
		PrintWriter output = new PrintWriter(mFile);

		// replace text and write result to targetFile
	      Iterator<Contact> i = data.iterator();
	      while (i.hasNext()) {
	         output.println(i.next().toString());
	      }
		output.close();
	}
	public TableView<Contact> getTable() {
		return table;
	}

	public void setTable(TableView<Contact> table) {
		this.table = table;
	}

	public ObservableList<Contact> getData() {
		return data;
	}

	public void setData(ObservableList<Contact> data) {
		this.data = data;
	}
	public ObservableList<Contact> getTempData() {
		return tempData;
	}

	public void setTempData(ObservableList<Contact> tempData) {
		this.tempData = tempData;
	}
	
	public void setTableDataSource(ObservableList<Contact> data)
	{
		this.table.setItems(data);
	}
	public void addContactToData(ObservableList<Contact> data, Contact contact)
	{
		data.add(contact);
		try 
		{
			setItemsToFile(FILEPATHNAME);
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}
	
	public void updateContactToData(ObservableList<Contact> data, int index, Contact contact)
	{
		data.set(index, contact);
		try 
		{
			setItemsToFile(FILEPATHNAME);
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}

	public void removeSelectedItemFromData(ObservableList<Contact> data, Contact contact)
	{
		
		data.remove(table.getSelectionModel().getSelectedItem());
		table.getItems().remove(contact);
		
		try 
		{
			setItemsToFile(FILEPATHNAME);
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}
	public void clearData(ObservableList<Contact> data)
	{
		data.clear();
		try 
		{
			setItemsToFile(FILEPATHNAME);
		} catch (IOException e1) {
			e1.printStackTrace();
		}		

		setTableDataSource(getData());
	}
}