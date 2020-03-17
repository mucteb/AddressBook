package adressbook;

public class Contact implements AddressSettings, Comparable<Object>{
	
	private String FirstName;
    private String LastName;
    private String Phone1;
    private String Phone2;
    private String Notes;
    private String Email;
	
	public Contact(String row) {
	
		String[] fields = row.split(",\\s*");
		setFirstName(fields[0]);
		setLastName(fields[1]);
		setPhone1(fields[2]);
		setPhone2(fields[3]);
		setEmail(fields[4]);
		setNotes(fields[5]);

	
	}
	

    public Contact(String firstName, String lastName, String phone1, String phone2, String email, String notes) {

		FirstName = firstName;
		LastName = lastName;
		Phone1 = phone1;
		Phone2 = phone2;
		Email = email;
		Notes = notes;

	}




	public String getFirstName() {
		return FirstName;
	}


	public void setFirstName(String firstName) {
		FirstName = firstName;
	}


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public String getPhone1() {
		return Phone1;
	}


	public void setPhone1(String phone1) {
		Phone1 = phone1;
	}


	public String getPhone2() {
		return Phone2;
	}


	public void setPhone2(String phone2) {
		Phone2 = phone2;
	}


	public String getNotes() {
		return Notes;
	}


	public void setNotes(String notes) {
		Notes = notes;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return FirstName+","+LastName+","+Phone1+","+Phone2+","+Email+","+Notes;
	}


	@Override
	public int compareTo(Object o) {
		if (this.equals(o))
			return 1;
		else
  		    return 0;
	}
	
}
