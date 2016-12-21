/**
 * Keeps track of the transactions for purposes of returns and sales tracking.
 * 
 * @author Logan Feiler
 *
 */
public class Transaction {
	private long transactionNumber;
	private String transactionType;
	private double totalPrice;
	private String transactionTime;
	private String transactionDate;
	private double tax;
	private String paymentType;
	private long cardOrCheckNumber;
	private int employeeNumber;
	
	/**
	 * Constructor: Initializes the transaction parameters.
	 * 
	 * @param transactionNumber (Primary Key) Identifying number
	 * @param transactionType 'S' for Sale or 'R' for Return
	 * @param totalPrice total price of the transaction
	 * @param transactionTime time of the transaction in String form
	 * @param transactionDate date of the transaction in String form
	 * @param tax percentage of sales tax
	 * @param paymentType Cash, Check, Credit, Debit
	 * @throws IllegalArgumentException if transactionNumber, price, tax, or employeeNumber are <= 0,
	 * transactionType is NULL or a character other than 'S' or R', or transactionTime,
	 * transactionDate, or paymentType are NULL, empty, or > 10 characters long
	 */
	public Transaction(long transactionNumber, String transactionType, double totalPrice,
			String transactionTime, String transactionDate, double tax, String paymentType,
			long cardOrCheckNumber, int employeeNumber)
	{
		setTransactionNumber(transactionNumber);
		setTransactionType(transactionType);
		setTotalPrice(totalPrice);
		setTransactionTime(transactionTime);
		setTransactionDate(transactionDate);
		setTax(tax);
		setPaymentType(paymentType);
		setCardOrCheckNumber(cardOrCheckNumber);
		setEmployeeNumber(employeeNumber);
	}
	
	/**
	 * Initializes transaction number.
	 * 
	 * @param transactionNumber
	 */
	public void setTransactionNumber(long transactionNumber)
	{
		if(transactionNumber <= 0)
			throw new IllegalArgumentException("Please supply a valid transaction number.");
		
		this.transactionNumber = transactionNumber;
	}
	
	/**
	 * Returns the transaction number.
	 * 
	 * @return transactionNumber
	 */
	public long getTransactionNumber()
	{
		return transactionNumber;
	}
	
	/**
	 * Initializes the type of the transaction. 'S' for Sale and 'R' for Return.
	 * Private function to prevent transaction type from being changed. 
	 * 
	 * @param transactionType
	 * @throws IllegalArgumentException if transactionType is NULL or a character other than 'S' or R'
	 */
	private void setTransactionType(String transactionType)
	{
		if(transactionType == "\u0000" || (transactionType == "S" || transactionType == "R"))
			throw new IllegalArgumentException("Please supply a valid transaction type.");
		
		this.transactionType = transactionType;
	}
	
	/**
	 * Returns the transaction type. 'S' for Sale and 'R' for Return.
	 * 
	 * @return transactionType
	 */
	public String getTransactionType()
	{
		return transactionType;
	}
	
	/**
	 * Initializes the price of the transaction.
	 * Private function to prevent total price from being changed.
	 * 
	 * @param totalPrice
	 * @throws IllegalArgumentException if totalPrice <= 0
	 */
	private void setTotalPrice(double totalPrice)
	{
		if(totalPrice <= 0)
			throw new IllegalArgumentException("Please supply a valid price for the transaction.");
		
		this.totalPrice = totalPrice;
	}

	/**
	 * Returns the price of the transaction.
	 * 
	 * @return totalPrice
	 */
	public double getTotalPrice()
	{
		return totalPrice;
	}
	
	/**
	 * Initializes the time of the transaction.
	 * Private function to prevent transaction time from being changed.
	 * 
	 * @param transactionTime
	 */
	private void setTransactionTime(String transactionTime)
	{
		if(transactionTime == null || transactionTime.length() == 0 || transactionTime.length() > 10)
			throw new IllegalArgumentException("Please supply a valid transaction time.");
		
		this.transactionTime = transactionTime;
	}
	
	/**
	 * Returns the time of the transaction.
	 * 
	 * @return transactionTime
	 */
	public String getTransactionTime()
	{
		return transactionTime;
	}
	
	/**
	 * Initializes the date of the transaction.
	 * Private function to prevent transaction date from being changed.
	 * 
	 * @param transactionDate
	 */
	private void setTransactionDate(String transactionDate)
	{
		if(transactionDate == null || transactionDate.length() == 0 || transactionDate.length() > 10)
			throw new IllegalArgumentException("Please supply a valid transaction date.");
		
		this.transactionDate = transactionDate;
	}
	
	/**
	 * Returns the date of the transaction.
	 * 
	 * @return transactionDate
	 */
	public String getTransactionDate()
	{
		return transactionDate;
	}
	
	/**
	 * Initializes the tax percentage.
	 * Private function to prevent tax from being changed.
	 * 
	 * @param tax
	 */
	private void setTax(double tax)
	{
		if(tax <= 0)
			throw new IllegalArgumentException("Please supply a valid tax percentage.");
		
		this.tax = tax;
	}
	
	/**
	 * Returns the tax percentage.
	 * 
	 * @return tax
	 */
	public double getTax()
	{
		return tax;
	}
	
	/**
	 * Initializes the type of payment used in the transaction.
	 * Private function to prevent payment type from being changed.
	 * 
	 * @param paymentType
	 */
	private void setPaymentType(String paymentType)
	{
		if(paymentType == null || paymentType.length() == 0 || paymentType.length() > 10)
			throw new IllegalArgumentException("Please supply a valid payment type.");
		
		this.paymentType = paymentType;
	}
	
	/**
	 * Returns the type of payment used in the transaction.
	 * 
	 * @return paymentType
	 */
	public String getPaymentType()
	{
		return paymentType;
	}
	
	/**
	 * Initializes the number of the card or check used in the transaction.
	 * Private function to prevent card or check number from being changed.
	 * 
	 * @param cardOrCheckNumber
	 */
	public void setCardOrCheckNumber(long cardOrCheckNumber)
	{
		this.cardOrCheckNumber = cardOrCheckNumber;
	}
	
	/**
	 * Returns the number of the card or check used in the transaction
	 * 
	 * @return cardOrCheckNumber
	 */
	public long getCardOrCheckNumber()
	{
		return cardOrCheckNumber;
	}
	
	/**
	 * Initialize the employee number for the transaction.
	 * Private function to prevent tax from being changed.
	 * 
	 * @param employeeNumber
	 */
	private void setEmployeeNumber(int employeeNumber)
	{
		if(employeeNumber <= 0)
			throw new IllegalArgumentException("Please supply a valid employee number.");
		
		this.employeeNumber = employeeNumber;
	}
	
	/**
	 * Returns the employee number associated with the transaction.
	 * 
	 * @return employeeNumber
	 */
	public int getEmployeeNumber()
	{
		return employeeNumber;
	}
}