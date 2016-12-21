/**
 * Keeps track of the items in inventory.
 * 
 * @author Logan Feiler
 */
public class Item {
	private long SKU;
	private String description;
	private String department;
	private double price;
	private int quantity;
	private int saleDiscount;
	
	/**
	 * Constructor: Initialize the item parameters.
	 * 
	 * @param SKU (Primary Key) Identifying number
	 * @param description Short description of the item
	 * @param department Department the item belongs in 
	 * @param price Price of the item
	 * @param quantity Quantity of the item in stock
	 * @param saleDiscount The percentage the item is on sale for
	 * @throws IllegalArgumentException if description or department are null, empty,
	 * or > 15 characters long or if SKU, price, quantity, or saleDiscount are <= 0.
	 */
	public Item(long SKU, String description, String department, double price,
			int quantity, int saleDiscount)
	{
		setSKU(SKU);
		setDescription(description);
		setDepartment(department);
		setPrice(price);
		setQuantity(quantity);
		setSaleDiscount(saleDiscount);
	}
	
	@Override
	public String toString()
	{
		return "Item [SKU=" + SKU + ", Description=" + description + ", Department="
				+ department + ", Price=" + price + ", Quantity=" + quantity +
				", Sale Discount=" + saleDiscount + "]";
	}

	/**
	 * Returns the SKU of the item.
	 * 
	 * @return SKU
	 */
	public long getSKU()
	{
		return SKU;
	}
	
	/**
	 * Modifies the SKU of the item.
	 * 
	 * @param SKU
	 * @throws IllegalArgumentException if SKU is <= 0.
	 */
	private void setSKU(long SKU)
	{
		if(SKU <= 0 )
			throw new IllegalArgumentException("Please supply a valid SKU.");

		this.SKU = SKU;
	}
	
	/**
	 * Returns a short description of the item.
	 * 
	 * @return description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Sets the description for the item.
	 * 
	 * @param description
	 * @throws IllegalArgumentException if the description is NULL or empty or
	 * length is greater than 15 characters. 
	 */
	public void setDescription(String description)
	{
		if(description == null || description.length() == 0 || description.length() > 15)
			throw new IllegalArgumentException("Please supply a description of appropriate length.");

		this.description = description;
	}
	
	/**
	 * Returns which department the item belongs in.
	 * 
	 * @return department
	 */
	public String getDepartment()
	{
		return department;
	}
	
	/**
	 * Modifies the department to which the item belongs.
	 * 
	 * @param department
	 * @throws IllegalArgumentException if the department is NULL or empty or
	 * length is greater than 15 characters.
	 */
	public void setDepartment(String department)
	{
		if(department == null || department.length() == 0 || department.length() > 15)
			throw new IllegalArgumentException("Please specify a valid department.");
		
		this.department = department;
	}
	
	/**
	 * Returns the price of the item.
	 * 
	 * @return price
	 */
	public double getPrice()
	{
		return price;
	}
	
	/**
	 * Sets the price of the item.
	 * 
	 * @param price
	 * @throws IllegalArgumentException if price is <= 0.
	 */
	public void setPrice(double price)
	{
		if (price <= 0)
			throw new IllegalArgumentException("Please supply a valid price.");
		
		this.price = price;
	}
	
	/**
	 * Returns the quantity of the item in stock.
	 * 
	 * @return quantity
	 */
	public int getQuantity()
	{
		return quantity;
	}
	
	/**
	 * Sets the quantity of the item in stock.
	 *  
	 * @param quantity
	 * @throws IllegalArgumentException if quantity is < 0.
	 */
	public void setQuantity(int quantity)
	{
		if(quantity < 0)
			throw new IllegalArgumentException("Please supply a valid quantity.");
		
		this.quantity = quantity;
	}
	
	/**
	 * Decrements the quantity of the item by 1 (useful for when an item is bought).
	 */
	public void decrementQuantity()
	{
		if(quantity == 0)
			throw new IllegalArgumentException("This item is already out of stock.");
		
		quantity--;
	}
	
	/**
	 * Increments the quantity of the item by 1 (useful for when an item is returned).
	 */
	public void incrementQuantity()
	{
		quantity++;
	}
	
	/**
	 * Returns the percentage that the item is on sale for.
	 * 
	 * @return saleDiscount
	 */
	public int getSaleDiscount()
	{
		return saleDiscount;
	}
	
	/**
	 * Sets the percentage that the item is on sale for.
	 * 
	 * @param saleDiscount
	 */
	public void setSaleDiscount(int saleDiscount)
	{
		if(saleDiscount < 0)
			throw new IllegalArgumentException("Please supply a valid sale discount.");
		
		this.saleDiscount = saleDiscount;
	}
}
