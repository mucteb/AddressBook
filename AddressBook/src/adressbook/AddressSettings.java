package adressbook;

/*This interface for supporting Language
 * all Labels, errors, settings can define here
*/

public interface AddressSettings {
	
	public final String TITLE ="Address Book";

	public final String FILEPATHNAME="c:\\temp\\mynewfile.txt";
	public final String BACKUPFILEPATHNAME="c:\\temp\\mynewfile.bac";
	public final String SEARCHLABEL="Incremental Search: ";
	
	public final String [] BUTTONS = {"_New"
			                         ,"_Add"
			                         , "_Cancel"
			                         , "_Update"
			                         ,"_Delete"
			                         , "Clear _File"
			                         , "_Refresh"
			                         ,"_Backup"
			                         ,"R_evert"
			                         };
	public final int BUTTONCOUNT=BUTTONS.length;
	
	public final String [] ALERTWARNINGS = {
			                                 ""  //0
			                                 ,"Please enter valid values" //1
			                                 ,"Confirmation Dialog" //2

			                                 ,"DELETING THE RECORD!" //3
			                                 ,"Do you wan to delete this record?" //4
			                                 
			                                 ,"UPDATING THE RECORD!" //5
			                                 ,"Do you want to update this record?" //6

			                                 ,"EMPTY THE RECORD!" //7
			                                 ,"Do you want to clean the table?"  //8
			                                 
			                                 ,"LOST DATA!" //9
			                                 ,"Do you want to cancel all changes?" //10
			                                 
		                                     ,"GETTING INFROMATION FROM "+FILEPATHNAME //11
			                                 ,"Do you want to cancel all changes?" //12

		                                     ,"BACKUP CONTACTS "+BACKUPFILEPATHNAME //13
			                                 ,"Do you want to backup contacts?" //14

			                                 ,"REVERT CONTACTS FROM "+BACKUPFILEPATHNAME //15
			                                 ,"Do you want to revert contacts?" //16
			                                 
			                                 ,"File Not Found" //17
			                                 ,"Backup Completed" //18
			                                 ,"Revert Completed" //19
			                                 
			                                 
	};
	
	public final String [] FIELDWARNINGS= {
			                           ""   
			                          ,"* This field is mandatory"
			                          ,"* Please enter a valid Phone number (123) 123-1212"
			                          ,"* Please enter a valid email address"
	                                 };
	
	
	public final String [] FIELDS= {"FirstName", 
			                        "LastName", 
			                        "Phone1", 
			                        "Phone2", 
			                        "Email", 
			                        "Notes"
//			                        ,"Address1",
//			                        "Address2",
//			                        "Address3",
//			                        "Address4",
//			                        "Address5",
//			                        "Address6",
//			                        "Address7",
//			                        "Address8",
//			                        "Address9",
//			                        "Address10"
	};
	public final int FIELDSCOUNT = FIELDS.length;
}
