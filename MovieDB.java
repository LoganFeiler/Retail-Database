import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * A class that consists of the database operations to insert and update the Movie information.
 * @author mmuppa
 *@author Michael W Munson
 *@author Logan Feiler
 */

public class MovieDB {
	private static String userName = "mwmunson";
	private static String password = "bezhyhy";
	private static String serverName = "cssgate.insttech.washington.edu";
	private static Connection conn;
	private List<Item> listItem;
	private List<Associate> listAssociate;
	private List<Transaction> listTransaction;

	/**
	 * Creates a sql connection to MySQL using the properties for
	 * userid, password and server information.
	 * @throws SQLException
	 */
	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
				+ serverName + "/", connectionProps);

		System.out.println("Connected to database");
	}
	
	/**
	 * Returns a list of item objects from the database.
	 * @return list of items
	 * @throws SQLException
	 */
	public List<Item> getItem() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select SKU, Description, Department, Price, Quantity, SaleDiscount "
				+ "from mwmunson.ITEM ";

		listItem = new ArrayList<Item>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				long sku = rs.getLong("SKU");
				String description = rs.getString("Description");
				String department = rs.getString("Department");
				double price = rs.getDouble("Price");
				int quantity = rs.getInt("Quantity");
				int saleDiscount = rs.getInt("SaleDiscount");
				Item item = new Item(sku, description, department, price, quantity, saleDiscount);
				listItem.add(item);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return listItem;
	}

	/**
	 * Finds the item with the matching SKU.
	 * 
	 * @param sku
	 * @return item with matching SKU as the SKU passed in
	 * @throws IllegalArgumentException if SKU is not a valid SKU number
	 */
	public Item getItem(long sku) throws IllegalArgumentException {
		try {
			listItem = getItem();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Item item : listItem) {
			if (item.getSKU() == sku) {
				return item;
			}
		}
		throw new IllegalArgumentException("Please supply a valid SKU number.");
	}
	
	/**
	 * Filters the item list to find items with the given description in the description.
	 * Returns a list with the item objects that match the description provided.
	 * 
	 * @param description
	 * @return list of items that match the description.
	 */
	public List<Item> getItem(String description) {
		List<Item> filterList = new ArrayList<Item>();
		try {
			listItem = getItem();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Item item : listItem) {
			if (item.getDescription().toLowerCase().contains(description.toLowerCase())) {
				filterList.add(item);
			}
		}
		return filterList;
	}
	
	/**
	 * Filters the item list to find items with the given description in the description.
	 * Returns a list with the item objects that match the description provided.
	 * 
	 * @param department
	 * @return list of items that match the description.
	 */
	public List<Item> getItemByDepartment(String department) {
		List<Item> filterList = new ArrayList<Item>();
		try {
			listItem = getItem();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Item item : listItem) {
			if (item.getDepartment().toLowerCase().contains(department.toLowerCase())) {
				filterList.add(item);
			}
		}
		return filterList;
	}

	/**
	 * Adds a new item to the table.
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		String sql = "insert into mwmunson.ITEM values (?, ?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, item.getSKU());
			preparedStatement.setString(2, item.getDescription());
			preparedStatement.setString(3, item.getDepartment());
			preparedStatement.setDouble(4, item.getPrice());
			preparedStatement.setInt(5, item.getQuantity());
			preparedStatement.setInt(6, item.getSaleDiscount());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
	/**
	 * Removes a  item from the table.
	 * 
	 * @param item
	 */
	public void removeItem(Item item) {
		String sql = "DELETE FROM mwmunson.ITEM WHERE SKU=? ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, item.getSKU());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}

	/**
	 * Modifies the movie information corresponding to the index in the list.
	 * @param row index of the element in the list
	 * @param columnName attribute to modify
	 * @param data value to supply
	 */
	public void updateItem(int row, String columnName, Object data) {
		
		Item item = listItem.get(row);
		long sku = item.getSKU();
		String sql = "update mwmunson.ITEM set " + columnName + " = ?  where SKU= ? ";
		System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			if (data instanceof String)
				preparedStatement.setString(1, (String) data);
			else if (data instanceof Integer)
				preparedStatement.setInt(1, (Integer) data);
			else if (data instanceof Long)
				preparedStatement.setLong(1, (Long) data);
			else if (data instanceof Double)
				preparedStatement.setDouble(1, (Double) data);
			preparedStatement.setLong(2, sku);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * Returns a list of Transaction objects from the database.
	 * @return list of transactions
	 * @throws SQLException
	 */
	public List<Transaction> getTransaction() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select TransactionNumber, TransactionType, TotalPrice, TransactionTime, "
				+ "TransactionDate, Tax, PaymentType, CardOrCheckNumber, EmployeeNumber "
				+ "from mwmunson.TRANSACTIONS ";

		listTransaction = new ArrayList<Transaction>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				long transactionNumber = rs.getLong("TransactionNumber");
				String transactionType = rs.getString("TransactionType");
				double totalPrice = rs.getDouble("TotalPrice");
				String transactionTime = rs.getString("TransactionTime");
				String transactionDate = rs.getString("TransactionDate");
				double tax = rs.getDouble("Tax");
				String paymentType = rs.getString("PaymentType");
				long cardOrCheckNumber = rs.getLong("CardOrCheckNumber");
				int employeeNumber = rs.getInt("EmployeeNumber");
				Transaction transaction = new Transaction(transactionNumber, transactionType, totalPrice, 
						transactionTime, transactionDate, tax, paymentType, cardOrCheckNumber, employeeNumber);
				listTransaction.add(transaction);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return listTransaction;
	}
	
	/**
	 * Finds the transaction with the matching trans number.
	 * 
	 * @param tran number
	 * @return transaction with matching trans number
	 * @throws IllegalArgumentException if trans number is not a valid trans number
	 */
	public Transaction getTransactionByNumber(long transactionNumber) throws IllegalArgumentException {
		try {
			listTransaction = getTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Transaction transaction : listTransaction) {
			if (transaction.getTransactionNumber() == transactionNumber) {
				return transaction;
			}
		}
		throw new IllegalArgumentException("Please supply a valid Transaction number.");
	}
	
	/**
	 * Finds the transaction with the matching credit/debit card or check number.
	 * 
	 * @param cardOrCheckNumber
	 * @return List<Transaction> of transactions with matching card or check number
	 */
	public List<Transaction> getTransaction(long cardOrCheckNumber) {
		List<Transaction> filterList = new ArrayList<Transaction>();
		try {
			listTransaction = getTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Transaction transaction : listTransaction) {
			if (transaction.getCardOrCheckNumber() == cardOrCheckNumber) {
				filterList.add(transaction);
			}
		}
		return filterList;
	}
	
	/**
	 * Finds the transaction with the matching time and date.
	 * 
	 * @param time
	 * @param date
	 * @return transaction
	 * @throws IllegalArgumentException if time 
	 */
	public Transaction getTransactionByTimeAndDate(String time, String date) throws IllegalArgumentException {
		try {
			listTransaction = getTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Transaction transaction : listTransaction) {
			if (transaction.getTransactionTime().compareTo(time) == 0 &&
					transaction.getTransactionDate().compareTo(date) == 0) {
				return transaction;
			}
		}
		throw new IllegalArgumentException("Please supply a valid time and date.");
	}
	
	/**
	 * Adds a new transaction to the table.
	 * @param transaction 
	 */
	public void addTransaction(Transaction transaction) {
		String sql = "insert into mwmunson.TRANSACTIONS values (?, ?, ?, ?, ?, ?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, transaction.getTransactionNumber());
			preparedStatement.setString(2, transaction.getTransactionType());
			preparedStatement.setDouble(3, transaction.getTotalPrice());
			preparedStatement.setString(4, transaction.getTransactionTime());
			preparedStatement.setString(5, transaction.getTransactionDate());
			preparedStatement.setDouble(6, transaction.getTax());
			preparedStatement.setString(7, transaction.getPaymentType());
			preparedStatement.setLong(8, transaction.getCardOrCheckNumber());
			preparedStatement.setInt(9, transaction.getEmployeeNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
	/**
	 * Removes a  transaction from the table.
	 * 
	 * @param item
	 */
	public void removeTransaction(Transaction transaction) {
		String sql = "DELETE FROM mwmunson.TRANSACTIONS WHERE TransactionNumber=? ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setLong(1, transaction.getTransactionNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
	/**
	 * Modifies the Transaction information corresponding to the index in the list.
	 * @param row index of the element in the list
	 * @param columnName attribute to modify
	 * @param data value to supply
	 */
	public void updateTransaction(int row, String columnName, Object data) {
		
		Transaction transaction = listTransaction.get(row);
		Long TransactionNumber = transaction.getTransactionNumber();
		String sql = "update mwmunson.TRANSACTIONS set " + columnName + " = ?  where TransactionNumber= ?";
		System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			if (data instanceof String)
				preparedStatement.setString(1, (String) data);
			else if (data instanceof Integer)
				preparedStatement.setInt(1, (Integer) data);
			else if (data instanceof Long)
				preparedStatement.setLong(1, (Long) data);
			else if (data instanceof Double)
				preparedStatement.setDouble(1, (Double) data);
			preparedStatement.setLong(2, TransactionNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * Returns a list of associate objects from the database.
	 * @return list of associates
	 * @throws SQLException
	 */
	public List<Associate> getAssociate() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = "select EmployeeNumber, FirstName, LastName, ManagerStatus "
				+ "from mwmunson.ASSOCIATE ";

		listAssociate = new ArrayList<Associate>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int employeeNumber = rs.getInt("EmployeeNumber");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				Boolean managerStatus = rs.getBoolean("ManagerStatus");
				Associate associate = new Associate(employeeNumber, firstName, lastName, managerStatus);
				listAssociate.add(associate);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return listAssociate;
	}
	
	/**
	 * Returns the associate object cooresponding with the employee number.
	 *  
	 * @param employeeNumber
	 * @return Associate
	 * @throws IllegalArgumentException
	 */
	public Associate getAssociate(int employeeNumber) throws IllegalArgumentException {
		try {
			listAssociate = getAssociate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Associate associate : listAssociate) {
			if (associate.getEmployeeNumber() == employeeNumber) {
				return associate;
			}
		}
		throw new IllegalArgumentException("Please supply a valid employee number.");
	}
	
	/**
	 * Adds a new associate to the table.
	 * @param associate 
	 */
	public void addAssociate(Associate associate) {
		String sql = "insert into mwmunson.ASSOCIATE values (?, ?, ?, ?); ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, associate.getEmployeeNumber());
			preparedStatement.setString(2, associate.getFirstName());
			preparedStatement.setString(3, associate.getLastName());
			preparedStatement.setBoolean(4, associate.getManagerStatus());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
	/**
	 * Removes the associate from the database.
	 * 
	 * @param associate
	 */
	public void removeAssociate(Associate associate) {
		String sql = "DELETE FROM mwmunson.ASSOCIATE WHERE EmployeeNumber=? ";

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, associate.getEmployeeNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Modifies the movie information corresponding to the index in the list.
	 * @param row index of the element in the list
	 * @param columnName attribute to modify
	 * @param data value to supply
	 */
	public void updateAssociate(int row, String columnName, Object data) {
		
		Associate associate = listAssociate.get(row);
		int EmployeeNumber = associate.getEmployeeNumber();
		String sql = "update mwmunson.ASSOCIATE set " + columnName + " = ?  where EmployeeNumber= ?  ";
		System.out.println(sql);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			if (data instanceof String)
				preparedStatement.setString(1, (String) data);
			else if (data instanceof Integer)
				preparedStatement.setInt(1, (Integer) data);
			else if (data instanceof Boolean)
				preparedStatement.setBoolean(1, (Boolean) data);
			preparedStatement.setInt(2, EmployeeNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
		
	}
}
