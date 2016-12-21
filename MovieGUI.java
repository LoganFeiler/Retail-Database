import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * A user interface to view the inventory associates and transactions.
 *  add a new item , transaction and to update an existing item and transaction.
 * The list is a table with all the inventory information in it. The TableModelListener listens to
 * any changes to the cells to modify the values for each item or transaction.
 * @author mmuppa
 * @author Michael Munson
 * @author Logan Feiler
 *
 */
public class MovieGUI extends JFrame implements ActionListener, TableModelListener
{
	
	private JButton btnList, btnAddAssociateAdd, btnListTran, btnAssociateList, btnSearch, btnTranSearch, 
					btnAdd, btnRemove, btnAddTran, btnRemoveTran, btnRemoveTransaction, btnAddAssociate, 
					btnRemoveAssociate, btnRemoveAssociateRemove, btnRemoveItem, btnAddTransaction,
					btnItemSearchSKU, btnItemSearchDep, btnTransactionSearch,
					btnTransactionCardOrCheckSearch, btnTransactionTimeAndDateSearch;
	private JPanel pnlButtons, pnlButtons2, pnlButtons3, pnlContent, btnPanel;
	private MovieDB db;
	private List<Item> list;
	private List<Associate> listAssociate;
	private List<Transaction> listTransaction;
	private String[] columnNames = {"SKU",
			"Description",
			"Department",
            "Price",
            "Quantity",            
            "Sale Discount"};
	private String[] columnNames2 = {"Transaction Number",
			"Type",
			"Total Price",
            "Time",
            "Date",            
            "Tax",
            "Payment Type",
            "Employee Number"};
	private String[] columnNames3 = {"Employee Number",
			"First Name",
			"Last Name",
            "Manager Status"};
	
	private Object[][] data;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel pnlSearch, pnlSearchTran, pnlRemoveItem, pnlRemoveTransaction, pnlRemoveAssociate;
	private JLabel lblTitle;;
	private JTextField txfItemRemove, txfTransactionRemove, txfAssociateRemove, txfItemSearch,
		txfTransactionSearch, txfTransactionSearch2;
	private JButton btnItemSearch;
	
	private JPanel pnlAdd, pnlAddTran, pnlAddAssociate;
	private JLabel[] txfLabel = new JLabel[6];
	private JTextField[] txfField = new JTextField[6];
	private JLabel[] txfLabelTran = new JLabel[10];
	private JTextField[] txfFieldTran = new JTextField[10];
	private JLabel[] txfLabelAssociate = new JLabel[4];
	private JTextField[] txfFieldAssociate = new JTextField[4];
	private JButton btnAddItem;
	
	
	/**
	 * Creates the frame and components and launches the GUI.
	 */
	public MovieGUI() {
		super("Ma and Pa SuperStore");
		
		db = new MovieDB();
		try
		{
			list = db.getItem();
			
			data = new Object[list.size()][columnNames.length];
			for (int i=0; i<list.size(); i++) {
				data[i][0] = list.get(i).getSKU();
				data[i][1] = list.get(i).getDescription();
				data[i][2] = list.get(i).getDepartment();
				data[i][3] = list.get(i).getPrice();
				data[i][4] = list.get(i).getQuantity();
				data[i][5] = list.get(i).getSaleDiscount();
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		createComponents();
		setVisible(true);
		setSize(1000, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    
	/**
	 * Creates panels for Movie list, search, add and adds the corresponding 
	 * components to each panel.
	 */
	private void createComponents()
	{
		pnlButtons = new JPanel();
		pnlButtons2 = new JPanel();
		pnlButtons3 = new JPanel();
		btnPanel = new JPanel(new BorderLayout());
		
		btnList = new JButton("Inventory ");
		btnList.addActionListener(this);
		
		btnAssociateList = new JButton("Associates ");
		btnAssociateList.addActionListener(this);
		
		pnlButtons = new JPanel();
		btnListTran = new JButton("Transaction History ");
		btnListTran.addActionListener(this);
		
		btnSearch = new JButton("Item Search");
		btnSearch.addActionListener(this);
		
		btnTranSearch = new JButton("Transaction Search");
		btnTranSearch.addActionListener(this);
		
		btnAdd = new JButton("Add Item");
		btnAdd.addActionListener(this);
		
		btnRemove = new JButton("Remove Item");
		btnRemove.addActionListener(this);
		
		btnAddTran = new JButton("Add Transaction");
		btnAddTran.addActionListener(this);
		
		btnRemoveTran = new JButton("Remove Transaction");
		btnRemoveTran.addActionListener(this);
		
		btnAddAssociate = new JButton("Add Associate");
		btnAddAssociate.addActionListener(this);
		
		btnRemoveAssociate = new JButton("Remove Associate");
		btnRemoveAssociate.addActionListener(this);
		
		pnlButtons.add(btnList);
		pnlButtons2.add(btnListTran);
		pnlButtons.add(btnSearch);
		pnlButtons2.add(btnTranSearch);
		pnlButtons.add(btnAdd);
		pnlButtons.add(btnRemove);
		pnlButtons2.add(btnAddTran);
		pnlButtons2.add(btnRemoveTran);
		pnlButtons3.add(btnAssociateList);
		pnlButtons3.add(btnAddAssociate);
		pnlButtons3.add(btnRemoveAssociate);
		
		btnPanel.add(pnlButtons, BorderLayout.NORTH);
		btnPanel.add(pnlButtons2, BorderLayout.CENTER);
		btnPanel.add(pnlButtons3, BorderLayout.SOUTH);
		add(btnPanel, BorderLayout.NORTH);
		
		//List Panel
		pnlContent = new JPanel();
		table = new JTable(data, columnNames);
		scrollPane = new JScrollPane(table);
		pnlContent.add(scrollPane);
		table.getModel().addTableModelListener(this);
		
		//Search Item Panel
		pnlSearch = new JPanel();
		lblTitle = new JLabel("Enter Data: ");
		txfItemSearch = new JTextField(25);
		btnItemSearch = new JButton("Search by Description");
		btnItemSearch.addActionListener(this);
		
		btnItemSearchDep = new JButton("Search by Department");
		btnItemSearchDep.addActionListener(this);
		
		btnItemSearchSKU = new JButton("Search by SKU");
		btnItemSearchSKU.addActionListener(this);
		
		pnlSearch.add(lblTitle);
		pnlSearch.add(txfItemSearch);
		pnlSearch.add(btnItemSearch);
		pnlSearch.add(btnItemSearchSKU);
		pnlSearch.add(btnItemSearchDep);
		
		//Search Tran Panel
		pnlSearchTran = new JPanel();
		txfTransactionSearch = new JTextField(25);
		txfTransactionSearch2 = new JTextField(25);
		btnTransactionSearch = new JButton("Search by Transaction Number");
		btnTransactionSearch.addActionListener(this);
		btnTransactionCardOrCheckSearch = new JButton("Search by Card or Check Number");
		btnTransactionCardOrCheckSearch.addActionListener(this);
		btnTransactionTimeAndDateSearch = new JButton("Search by Time and Date");
		btnTransactionTimeAndDateSearch.addActionListener(this);
		pnlSearchTran.add(lblTitle);
		pnlSearchTran.add(txfTransactionSearch);
		pnlSearchTran.add(txfTransactionSearch2);
		pnlSearchTran.add(btnTransactionSearch);
		pnlSearchTran.add(btnTransactionCardOrCheckSearch);
		pnlSearchTran.add(btnTransactionTimeAndDateSearch);
		
		//Remove Item Panel
		pnlRemoveItem = new JPanel();
		lblTitle = new JLabel("Enter SKU: ");
		txfItemRemove = new JTextField(25);
		btnRemoveItem = new JButton("Delete Item");
		btnRemoveItem.addActionListener(this);
		pnlRemoveItem.add(lblTitle);
		pnlRemoveItem.add(txfItemRemove);
		pnlRemoveItem.add(btnRemoveItem);
		
		//Remove Transaction Panel
		pnlRemoveTransaction = new JPanel();
		lblTitle = new JLabel("Enter Transaction Number: ");
		txfTransactionRemove = new JTextField(25);
		btnRemoveTransaction = new JButton("Delete Transaction");
		btnRemoveTransaction.addActionListener(this);
		pnlRemoveTransaction.add(lblTitle);
		pnlRemoveTransaction.add(txfTransactionRemove);
		pnlRemoveTransaction.add(btnRemoveTransaction);
		
		//Remove Associate Panel
		pnlRemoveAssociate = new JPanel();
		lblTitle = new JLabel("Enter Employee Number: ");
		txfAssociateRemove = new JTextField(25);
		btnRemoveAssociateRemove = new JButton("Delete Associate");
		btnRemoveAssociateRemove.addActionListener(this);
		pnlRemoveAssociate.add(lblTitle);
		pnlRemoveAssociate.add(txfAssociateRemove);
		pnlRemoveAssociate.add(btnRemoveAssociateRemove);
				
		//Add Panel
		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(6, 0));
		String labelNames[] = {"Enter SKU: ", "Enter Description: ", "Enter Department: ", 
				"Enter Price: ", "Enter Quantity: ", "Enter Sale Discount:"};
		for (int i=0; i<labelNames.length; i++) {
			JPanel panel = new JPanel();
			txfLabel[i] = new JLabel(labelNames[i]);
			txfField[i] = new JTextField(25);
			panel.add(txfLabel[i]);
			panel.add(txfField[i]);
			pnlAdd.add(panel);
		}
		JPanel panel = new JPanel();
		btnAddItem = new JButton("Add");
		btnAddItem.addActionListener(this);
		panel.add(btnAddItem);
		pnlAdd.add(panel);
		
		//Add TranPanel
		pnlAddTran = new JPanel();
		pnlAddTran.setLayout(new GridLayout(6, 0));
		String labelNamesTran[] = {"Enter Transaction Number: ", "Enter Transaction Type: ",
				"Enter Total Price: ", "Enter Transaction Time: ", "Enter Transaction Date: ",
				"Enter Tax:", "Enter Payment Type: ", "Enter Credit/Debit Card or Check Number: ",
				"Enter Employee Number: "};
		for (int i=0; i<labelNamesTran.length; i++) {
			JPanel panelTran = new JPanel();
			txfLabelTran[i] = new JLabel(labelNamesTran[i]);
			txfFieldTran[i] = new JTextField(25);
			panelTran.add(txfLabelTran[i]);
			panelTran.add(txfFieldTran[i]);
			pnlAddTran.add(panelTran);
		}
		JPanel panelTran = new JPanel();
		btnAddTransaction = new JButton("Add");
		btnAddTransaction.addActionListener(this);
		panelTran.add(btnAddTransaction);
		pnlAddTran.add(panelTran);
		
		//Add Associate Panel
		pnlAddAssociate = new JPanel();
		pnlAddAssociate.setLayout(new GridLayout(6, 0));
		String labelNamesAssociate[] = {"Enter Associate Employee Number:", "Enter Associate First Name: ", "Enter Associate Last Name: ",
				"Enter Associate Management Statues (True/False): "};
		for (int i=0; i<labelNamesAssociate.length; i++) {
			JPanel panelAssociate = new JPanel();
			txfLabelAssociate[i] = new JLabel(labelNamesAssociate[i]);
			txfFieldAssociate[i] = new JTextField(25);
			panelAssociate.add(txfLabelAssociate[i]);
			panelAssociate.add(txfFieldAssociate[i]);
			pnlAddAssociate.add(panelAssociate);
		}
		JPanel panelAssociate = new JPanel();
		btnAddAssociateAdd = new JButton("Add");
		btnAddAssociateAdd.addActionListener(this);
		panelAssociate.add(btnAddAssociateAdd);
		pnlAddAssociate.add(panelAssociate);
		
		add(pnlContent, BorderLayout.CENTER);
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		new MovieGUI();

	}

	/**
	 * Event handling to change the panels when different tabs are clicked,
	 * add and search buttons are clicked on the corresponding add and search panels.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnList) {
			try {
				list = db.getItem();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			data = new Object[list.size()][columnNames.length];
			for (int i=0; i<list.size(); i++) {
				data[i][0] = list.get(i).getSKU();
				data[i][1] = list.get(i).getDescription();
				data[i][2] = list.get(i).getDepartment();
				data[i][3] = list.get(i).getPrice();
				data[i][4] = list.get(i).getQuantity();
				data[i][5] = list.get(i).getSaleDiscount();
			}
			
			pnlContent.removeAll();
			table = new JTable(data, columnNames);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();
			
		}else if (e.getSource() == btnAssociateList) {
			try {
				listAssociate = db.getAssociate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			data = new Object[listAssociate.size()][columnNames3.length];
			for (int i=0; i<listAssociate.size(); i++) {
				data[i][0] = listAssociate.get(i).getEmployeeNumber();
				data[i][1] = listAssociate.get(i).getFirstName();
				data[i][2] = listAssociate.get(i).getLastName();
				data[i][3] = listAssociate.get(i).getManagerStatus();
			}
			
			pnlContent.removeAll();
			table = new JTable(data, columnNames3);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnListTran) {
			try {
				listTransaction = db.getTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			data = new Object[listTransaction.size()][columnNames2.length];
			for (int i=0; i<listTransaction.size(); i++) {
				data[i][0] = listTransaction.get(i).getTransactionNumber();
			    data[i][1] = listTransaction.get(i).getTransactionType();
			    data[i][2] = listTransaction.get(i).getTotalPrice();
			    data[i][3] = listTransaction.get(i).getTransactionTime();
			    data[i][4] = listTransaction.get(i).getTransactionDate();
			    data[i][5] = listTransaction.get(i).getTax();
			    data[i][6] = listTransaction.get(i).getPaymentType();
			    data[i][7] = listTransaction.get(i).getEmployeeNumber();
			}
			
			pnlContent.removeAll();
			table = new JTable(data, columnNames2);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();
			
		}else if (e.getSource() == btnSearch) {
			pnlContent.removeAll();
			pnlContent.add(pnlSearch);
			pnlContent.revalidate();
			this.repaint();
		}else if (e.getSource() == btnTranSearch) {
			pnlContent.removeAll();
			pnlContent.add(pnlSearchTran);
			pnlContent.revalidate();
			this.repaint();
		} else if (e.getSource() == btnRemove) {
			pnlContent.removeAll();
			pnlContent.add(pnlRemoveItem);
			pnlContent.revalidate();
			this.repaint();
		}else if (e.getSource() == btnRemoveTran) {
			pnlContent.removeAll();
			pnlContent.add(pnlRemoveTransaction);
			pnlContent.revalidate();
			this.repaint();
		}else if (e.getSource() == btnRemoveAssociate) {
			pnlContent.removeAll();
			pnlContent.add(pnlRemoveAssociate);
			pnlContent.revalidate();
			this.repaint();
		}else if (e.getSource() == btnAdd) {
			pnlContent.removeAll();
			pnlContent.add(pnlAdd);
			pnlContent.revalidate();
			this.repaint();
			
		} else if (e.getSource() == btnAddTran) {
			pnlContent.removeAll();
			pnlContent.add(pnlAddTran);
			pnlContent.revalidate();
			this.repaint();
			
		}else if (e.getSource() == btnAddAssociate) {
			pnlContent.removeAll();
			pnlContent.add(pnlAddAssociate);
			pnlContent.revalidate();
			this.repaint();
			
		}else if(e.getSource() == btnRemoveItem){
			String myInput = txfItemRemove.getText();
			Long mySKU = Long.parseLong(myInput);
			Item myItem = db.getItem(mySKU);
			db.removeItem(myItem);	
			JOptionPane.showMessageDialog(null, "Item Removed Successfully!");
			txfItemRemove.setText("");
		}else if(e.getSource() == btnRemoveTransaction){
			String myInput = txfTransactionRemove.getText();
			Long myTransactionNumber = Long.parseLong(myInput);
			Transaction myTransaction = db.getTransactionByNumber(myTransactionNumber);
			db.removeTransaction(myTransaction);
			JOptionPane.showMessageDialog(null, "Transaction Removed Successfully!");
			txfTransactionRemove.setText("");
		}else if(e.getSource() == btnRemoveAssociateRemove){
			String myInput = txfAssociateRemove.getText();
			int myEmployeeNumber = Integer.parseInt(myInput);
			Associate myAssociate = db.getAssociate(myEmployeeNumber);
			db.removeAssociate(myAssociate);	
			JOptionPane.showMessageDialog(null, "Associate Removed Successfully!");
			txfAssociateRemove.setText("");
		}else if (e.getSource() == btnItemSearch) {
			String title = txfItemSearch.getText();
			if (title.length() > 0) {
				list = db.getItem(title);
				data = new Object[list.size()][columnNames.length];
				for (int i=0; i<list.size(); i++) {
					data[i][0] = list.get(i).getSKU();
					data[i][1] = list.get(i).getDescription();
					data[i][2] = list.get(i).getDepartment();
					data[i][3] = list.get(i).getPrice();
					data[i][4] = list.get(i).getQuantity();
					data[i][5] = list.get(i).getSaleDiscount();
				}
				
				pnlContent.removeAll();
				table = new JTable(data, columnNames);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();
			}
		} else if (e.getSource() == btnItemSearchDep) {
			String title = txfItemSearch.getText();
			if (title.length() > 0) {
				list = db.getItemByDepartment(title);
				data = new Object[list.size()][columnNames.length];
				for (int i=0; i<list.size(); i++) {
					data[i][0] = list.get(i).getSKU();
					data[i][1] = list.get(i).getDescription();
					data[i][2] = list.get(i).getDepartment();
					data[i][3] = list.get(i).getPrice();
					data[i][4] = list.get(i).getQuantity();
					data[i][5] = list.get(i).getSaleDiscount();
				}
				
				pnlContent.removeAll();
				table = new JTable(data, columnNames);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();
			}
		}else if (e.getSource() == btnItemSearchSKU) {
			Long title = Long.parseLong(txfItemSearch.getText());
			if (title > 0) {
				Item myItem = db.getItem(title);
				data = new Object[list.size()][columnNames.length];
				
				data[0][0] = myItem.getSKU();
				data[0][1] = myItem.getDescription();
				data[0][2] = myItem.getDepartment();
				data[0][3] = myItem.getPrice();
				data[0][4] = myItem.getQuantity();
				data[0][5] = myItem.getSaleDiscount();
								
				pnlContent.removeAll();
				table = new JTable(data, columnNames);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();
			}
		}else if (e.getSource() == btnTransactionSearch) {
			long transactionNumber = Integer.parseInt(txfTransactionSearch.getText());
			if (transactionNumber > 0) {
				Transaction myTransaction = db.getTransactionByNumber(transactionNumber);
				data = new Object[listTransaction.size()][columnNames2.length];
				
				data[0][0] = myTransaction.getTransactionNumber();
				data[0][1] = myTransaction.getTransactionType();
				data[0][2] = myTransaction.getTotalPrice();
				data[0][3] = myTransaction.getTransactionTime();
				data[0][4] = myTransaction.getTransactionDate();
				data[0][5] = myTransaction.getTax();
				data[0][6] = myTransaction.getPaymentType();
				data[0][7] = myTransaction.getEmployeeNumber();
								
				pnlContent.removeAll();
				table = new JTable(data, columnNames2);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();
			}
		}else if (e.getSource() == btnTransactionCardOrCheckSearch) {
			long cardOrCheckNumber = Integer.parseInt(txfTransactionSearch.getText());
			if (cardOrCheckNumber > 0) {
				listTransaction = db.getTransaction(cardOrCheckNumber);
				data = new Object[listTransaction.size()][columnNames2.length];
				for (int i=0; i<listTransaction.size(); i++) {
					data[i][0] = listTransaction.get(i).getTransactionNumber();
					data[i][1] = listTransaction.get(i).getTransactionType();
					data[i][2] = listTransaction.get(i).getTotalPrice();
					data[i][3] = listTransaction.get(i).getTransactionTime();
					data[i][4] = listTransaction.get(i).getTransactionDate();
					data[i][5] = listTransaction.get(i).getTax();
					data[i][6] = listTransaction.get(i).getPaymentType();
					data[i][7] = listTransaction.get(i).getEmployeeNumber();
				}
								
				pnlContent.removeAll();
				table = new JTable(data, columnNames2);
				table.getModel().addTableModelListener(this);
				scrollPane = new JScrollPane(table);
				pnlContent.add(scrollPane);
				pnlContent.revalidate();
				this.repaint();
			}
		}else if (e.getSource() == btnTransactionTimeAndDateSearch) {
			String transactionTime = txfTransactionSearch.getText();
			String transactionDate = txfTransactionSearch2.getText();
			Transaction myTransaction = db.getTransactionByTimeAndDate(transactionTime, transactionDate);
			data = new Object[listTransaction.size()][columnNames2.length];
			
			data[0][0] = myTransaction.getTransactionNumber();
			data[0][1] = myTransaction.getTransactionType();
			data[0][2] = myTransaction.getTotalPrice();
			data[0][3] = myTransaction.getTransactionTime();
			data[0][4] = myTransaction.getTransactionDate();
			data[0][5] = myTransaction.getTax();
			data[0][6] = myTransaction.getPaymentType();
			data[0][7] = myTransaction.getEmployeeNumber();
							
			pnlContent.removeAll();
			table = new JTable(data, columnNames2);
			table.getModel().addTableModelListener(this);
			scrollPane = new JScrollPane(table);
			pnlContent.add(scrollPane);
			pnlContent.revalidate();
			this.repaint();
		} else if (e.getSource() == btnAddItem) {
			Item item = new Item(Long.parseLong(txfField[0].getText()), txfField[1].getText()
					,txfField[2].getText(), Double.parseDouble(txfField[3].getText()), 
					Integer.parseInt(txfField[4].getText()), Integer.parseInt(txfField[5].getText()) );
			db.addItem(item);
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			for (int i=0; i<txfField.length; i++) {
				txfField[i].setText("");
			}
		}else if (e.getSource() == btnAddTransaction) {
			Transaction transaction = new Transaction(Long.parseLong(txfFieldTran[0].getText()), txfFieldTran[1].getText(),
					Double.parseDouble(txfFieldTran[2].getText()), txfFieldTran[3].getText(), txfFieldTran[4].getText(),
					Double.parseDouble(txfFieldTran[5].getText()),txfFieldTran[6].getText(), Long.parseLong(txfFieldTran[7].getText()),
					Integer.parseInt(txfFieldTran[8].getText()) );
			db.addTransaction(transaction);
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			for (int i=0; i<txfFieldTran.length; i++) {
				txfFieldTran[i].setText("");
			}
		}else if (e.getSource() == btnAddAssociateAdd) {
			Associate associate = new Associate(Integer.parseInt(txfFieldAssociate[0].getText()), txfFieldAssociate[1].getText(),
					txfFieldAssociate[2].getText(), Boolean.parseBoolean(txfFieldAssociate[3].getText()));
			db.addAssociate(associate);
			JOptionPane.showMessageDialog(null, "Added Successfully!");
			for (int i=0; i<txfFieldAssociate.length; i++) {
				txfFieldAssociate[i].setText("");
			}
		}
		
	}

	/**
	 * Event handling for any cell being changed in the table.
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        
        db.updateItem(row, columnName, data);
		
	}

}
