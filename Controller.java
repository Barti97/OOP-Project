package FinalProject;

import java.sql.Connection;
import java.util.ArrayList;


public class Controller{

	SQLQuery sql = new SQLQuery();
	private Connection con;

	public Controller(Connection con)
	{
		this.con = con;
	}

	public void createPhone(Phone ph) // Add Phone
	{
		sql.insertPhone(con, ph);
	}

	public void createTV(TV tv) // Add TV
	{
		sql.insertTV(con, tv);
	}

	public ArrayList<Product> displayProducts() // show Products on sale
	{
		ArrayList<Phone> phones = sql.findPhones(con);	

		ArrayList<TV> tvs = sql.findTVs(con);

		ArrayList<Product> result = new ArrayList<Product>();
		result.addAll(phones);
		result.addAll(tvs);

		return result;

	}

	public Product findProductByID(String id) // find Product using it's ID
	{
		return sql.searchByID(con, id);
	}
	
	public ArrayList<Customer> findCustomers() // show Customers
	{
		return sql.findCustomers(con);
	}
	
	public ArrayList<Order> findOrders() // show Orders
	{
		return sql.findOrders(con);
	}
	
	public void createOrder(Order o) // make a new Order	
	{
		sql.insertOrder(con, o);
	}
	
	public void insertCustomer(Customer c)  // create a new Customer
	{
		sql.insertCustomer(con, c);
	}
	
}

