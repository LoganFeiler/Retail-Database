/**
 * Represents a Associate with employee number, first name, last name, and manager status.
 * 
 * @author Michael Munson
 *
 */
public class Associate {
	private int employeeNumber;
	private String firstName;
	private String lastName;
	private Boolean managerStatus;
	
	
	/**
	 * Initialize the Associate parameters.
	 * @param employeeNumber
	 * @param firstName
	 * @param lastName
	 * @param managerStatus
	 * @throws IllegalArgumentException if firstName or lastName are null or empty,
	 * employeeNumber <= 0.
	 */
	public Associate(int employeeNumber, String firstName, String lastName, Boolean managerStatus) {
		setEmployeeNumber(employeeNumber);
		setFirstName(firstName);
		setLastName(lastName);
		setManagerStatus(managerStatus);
	}
	
	@Override
	public String toString()
	{
		return "Associate [Employee Number=" + employeeNumber + ", First Name=" + firstName + ", Last Name="
				+ lastName + ", Manager Status=" + managerStatus + "]";
	}

	/**
	 * Returns the employee number of the associate.
	 * @return employee number
	 */
	public int getEmployeeNumber()
	{
		return employeeNumber;
	}
	
	/**
	 * Modifies the employee number of the associate.
	 * @param employeeNumber
	 * @throws IllegalArgumentException if employeeNumber <= 0.
	 */
	public void setEmployeeNumber(int employeeNumber)
	{
		if (employeeNumber <= 0)
			throw new IllegalArgumentException("Please supply a valid employee number.");
		this.employeeNumber = employeeNumber;
	}
	
	/**
	 * Returns the first name of the associate.
	 * @return firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 * @param firstName
	 * @throws IllegalArgumentException if first name is null or empty. 
	 */
	public void setFirstName(String firstName)
	{
		if (firstName == null || firstName.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid first name.");
		this.firstName = firstName;
	}
	
	/**
	 * Returns the last name of the associate.
	 * @return lastName
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 * @param lastName
	 * @throws IllegalArgumentException if last name is null or empty. 
	 */
	public void setLastName(String lastName)
	{
		if (lastName == null || lastName.length() == 0 )
			throw new IllegalArgumentException("Please supply a valid last name.");
		this.lastName = lastName;
	}
	
	/**
	 * Returns the Manager Status of the associate.
	 * @return managerStatus
	 */
	public Boolean getManagerStatus()
	{
		return managerStatus;
	}
	
	/**
	 * Sets the manager Status of a associate .
	 * @param managerStatus
	 */
	public void setManagerStatus(Boolean managerStatus)
	{
		this.managerStatus = managerStatus;
	}	
}
