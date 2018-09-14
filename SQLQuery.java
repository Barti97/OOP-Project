package FinalProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLQuery {

	SQLQuery(){

	}

	public void insertPhone(Connection con, Phone ph)
	{
		try {

			Statement insertStmt = con.createStatement();

			String id = null;

			Statement stmt = con.createStatement();
			String idSelect = "select MAX(Phone_ID) from Phone";
			ResultSet rs = stmt.executeQuery(idSelect);

			while(rs.next()) 
			{ 
				id = rs.getString(1);
			}

			id = "p" + (Integer.parseInt(id.substring(1)) + 1);
			rs.close();
			stmt.close();

			String insertProduct = " insert into product values('" + id + "','" + ph.getName() + "','" + ph.getDescription() + "'," + ph.getPrice() + ");";
			insertStmt.executeUpdate(insertProduct);

			String insertPhone = " insert into phone values('" + id + "','" + ph.getMake() + "','" + ph.getModel() + "'," + ph.getStorage() + ");";
			insertStmt.executeUpdate(insertPhone);

			insertStmt.close();
		}

		catch (Exception io) 
		{
			System.out.println("error"+io);
			io.printStackTrace();
		};  

	}

	public void insertTV(Connection con, TV tv)
	{
		try {

			Statement insertStmt = con.createStatement();

			String id = null;

			Statement stmt = con.createStatement();
			String idSelect = "select MAX(TV_ID) from tv";
			ResultSet rs = stmt.executeQuery(idSelect);

			while(rs.next()) 
			{ 
				id = rs.getString(1);
			}

			id = "t" + (Integer.parseInt(id.substring(1)) + 1);
			rs.close();
			stmt.close();

			String insertProduct = " insert into product values('" + id + "','" + tv.getName() + "','" + tv.getDescription() + "'," + tv.getPrice() + ");";
			insertStmt.executeUpdate(insertProduct);

			String insertPhone = " insert into tv values('" + id + "','" + tv.getMake() + "','" + tv.getType() + "'," + tv.getScreenSize() + ","+tv.isCapable3D() +");";
			insertStmt.executeUpdate(insertPhone);

			insertStmt.close();
		}

		catch (Exception io) 
		{
			System.out.println("error"+io);
			io.printStackTrace();
		};
	}

	public void insertCustomer(Connection con, Customer c)
	{
		try {

			Statement insertStmt = con.createStatement();

			String id = null;

			Statement stmt = con.createStatement();
			String custSelect = "select MAX(Cust_ID) from Customer";
			ResultSet rs = stmt.executeQuery(custSelect);

			while(rs.next()) 
			{ 
				id = rs.getString(1);
			}

			id = "c" + (Integer.parseInt(id.substring(1)) + 1);
			rs.close();
			stmt.close();

			String insertCustomer = " insert into customer values('" + id + "','" + c.getName() + "','" + c.getAddress() + "');";
			insertStmt.executeUpdate(insertCustomer);

			insertStmt.close();
		}

		catch (Exception io) 
		{
			System.out.println("error"+io);
			io.printStackTrace();
		};  
	}
	
	public ArrayList<Phone> findPhones(Connection con) 
	{
		ArrayList<Phone> products = new ArrayList<Phone>();

		try {
			Statement stmt = con.createStatement();
			String productsSelect = "select * from product join Phone on Product_ID = Phone_ID;";
			ResultSet rs = stmt.executeQuery(productsSelect);

			while(rs.next()) 
			{ 
				Phone phone = new Phone(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(4));
				products.add(phone);
				System.out.println("\n-----------------------------");
				System.out.println("Name: " + phone.getName());
				System.out.println("Description: " + phone.getDescription());
				System.out.println("Make: " + phone.getMake());
				System.out.println("Model: " + phone.getModel());
				System.out.println("Storage: " + phone.getStorage());
				System.out.println("Price: €" + phone.getPrice());

			}
			rs.close();
			stmt.close();
		}

		catch (Exception io) 
		{
			System.out.println("error"+io);
		}

		return products;
	}

	public ArrayList<TV> findTVs(Connection con) 
	{
		ArrayList<TV> products = new ArrayList<TV>();

		try {
			Statement stmt = con.createStatement();
			String productsSelect = "select * from product join TV on Product_ID = TV_ID;";
			ResultSet rs = stmt.executeQuery(productsSelect);

			while(rs.next()) 
			{ 

				TV tv = new TV(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(6), rs.getString(7),rs.getInt(8),rs.getBoolean(9),rs.getInt(4));
				products.add(tv);
				System.out.println("\n-----------------------------");
				System.out.println("Name: " + tv.getName());
				System.out.println("Description: " + tv.getDescription());
				System.out.println("Make: " + tv.getMake());
				System.out.println("Type: " + tv.getType());
				System.out.println("Screen Size: " + tv.getScreenSize());
				System.out.println("3D Capable: " + tv.isCapable3D());
				System.out.println("Price: €" + tv.getPrice());
			}
			rs.close();
			stmt.close();
		}

		catch (Exception io) 
		{
			System.out.println("error"+io);
		}

		return products;
	}

	public Product searchByID(Connection con, String id)
	{
		Product product = null;
		if(id.startsWith("p"))
		{
			try {
				Statement stmt = con.createStatement();
				String productSelect = "select * from product join Phone on Product_ID = Phone_ID where Phone_ID = '" + id + "';";
				ResultSet rs = stmt.executeQuery(productSelect);

				while(rs.next()) 
				{ 
					product = new Phone(rs.getString(2),rs.getString(3), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(4));

					System.out.println("\n-----------------------------");
					System.out.println("ID: " + rs.getString(1));
					System.out.println("Name: " + product.getName());
					System.out.println("Description: " + product.getDescription());
					System.out.println("Make: " + ((Phone) product).getMake());
					System.out.println("Model: " + ((Phone) product).getModel());
					System.out.println("Storage: " + ((Phone) product).getStorage());
					System.out.println("Price: €" + product.getPrice());

				}
				rs.close();
				stmt.close();
			}

			catch (Exception io) 
			{
				System.out.println("error"+io);
			}
		}
		else if(id.startsWith("t"))
		{
			try {
				Statement stmt = con.createStatement();
				String productSelect = "select * from product join TV on Product_ID = TV_ID where TV_ID = '" + id + "';";
				ResultSet rs = stmt.executeQuery(productSelect);

				while(rs.next()) 
				{ 

					product = new TV(rs.getString(2),rs.getString(3),rs.getString(6), rs.getString(7),rs.getInt(8),rs.getBoolean(9),rs.getInt(4));

					System.out.println("\n-----------------------------");
					System.out.println("\nID: " + rs.getString(1));
					System.out.println("\nName: " + product.getName());
					System.out.println("\nDescription: " + product.getDescription());
					System.out.println("\nMake: " + ((TV) product).getMake());
					System.out.println("\nType: " + ((TV) product).getType());
					System.out.println("\nScreen Size: " + ((TV) product).getScreenSize());
					System.out.println("\n3D Capable: " + ((TV) product).isCapable3D());
					System.out.println("\nPrice: €" + product.getPrice());
				}
				rs.close();
				stmt.close();
			}

			catch (Exception io) 
			{
				System.out.println("error"+io);
			}
		}

		return product;
	}

	public ArrayList<Customer> findCustomers(Connection con) 
	{
		ArrayList<Customer> customers = new ArrayList<Customer>();

		try {
			Statement stmt = con.createStatement();
			String customersSelect = "select * from customer;";
			ResultSet rs = stmt.executeQuery(customersSelect);

			while(rs.next()) 
			{ 
				Customer cust = new Customer(rs.getString(1), rs.getString(2),rs.getString(3));
				customers.add(cust);
				System.out.println("\n-----------------------------");
				System.out.println("ID: " + rs.getString(1));
				System.out.println("Name: " + rs.getString(2));
				System.out.println("Address: " + rs.getString(3));

			}
			rs.close();
			stmt.close();
		}

		catch (Exception io) 
		{
			System.out.println("error"+io);
		}

		return customers;
	}
	
	/////////////////// FIND ORDERS ////////////////////////
	public ArrayList<Order> findOrders(Connection con)
	{
		ArrayList<Order> orders = new ArrayList<Order>();
		
		try
    	{
    		Statement stmt = con.createStatement();
    		String ordersSelect = "Select distinct Order_ID, Cust_ID from Orders;";
    		ResultSet rs = stmt.executeQuery(ordersSelect);
    		
    		System.out.println("FIND ORDERS");
    		
    		while(rs.next())
    		{
    			Order tmpO = new Order(rs.getString(1), findCustomer(con, rs.getString(2)));
    			ArrayList<OrderDetails> tmpOD = findOrderDetails(con, rs.getString(1));
    			for(int i = 0; i < tmpOD.size(); i++)
    			{
    				tmpO.add(tmpOD.get(i).getProduct(), tmpOD.get(i).getQuantity());
    				System.out.println(tmpOD);
    			}
    			orders.add(tmpO);
    		}
    		
    		rs.close();
    		stmt.close();
    	}
    	catch (Exception io)
    	{
    		System.out.println("Get Order Error: " + io);
    	}
		
		return orders;
	}
	
	public ArrayList<OrderDetails> findOrderDetails(Connection con,String id)
	{
		ArrayList<OrderDetails> details = new ArrayList<OrderDetails>();
		try
    	{
    		Statement stmt = con.createStatement();
    		String detailsSelect = "select od.Product_ID, od.quantity from Order_details od where od.Details_ID in (select Details_ID from Orders where Order_ID = '" + id + "');";
    		ResultSet rs = stmt.executeQuery(detailsSelect);
    		
    		while(rs.next())
    		{
    			if(rs.getString(1).startsWith("p"))
    			{
    				details.add(new OrderDetails(findPhone(con, rs.getString(1)), rs.getInt(2)));
    			}
    			else if(rs.getString(1).startsWith("t"))
    			{
    				details.add(new OrderDetails(findTV(con, rs.getString(1)), rs.getInt(2)));
    			}
    		}
    		
    		rs.close();
    		stmt.close();
    	}
    	catch (Exception io)
    	{
    		System.out.println("Find OD Error: " + io);
    	}
		return details;
	}
	
	public Phone findPhone(Connection con, String id)
	{
		Phone ph = null;
		try
    	{
    		Statement stmt = con.createStatement();
    		String phoneSelect = "Select pd.Product_ID, pd.Product_Name, pd.Product_Description, pd.Product_Price, p.Phone_Make, p.Phone_Model, p.Phone_Storage from Product pd "
    				+ "join Phone p on pd.Product_ID = p.Phone_ID where pd.Product_ID = '" + id + "';";
    		ResultSet rs = stmt.executeQuery(phoneSelect);
    		
    		while(rs.next())
    		{
    			ph = new Phone(rs.getString(1), rs.getString(2),rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(4));
    		}
    		
    		rs.close();
    		stmt.close();
    	}
    	catch (Exception io)
    	{
    		System.out.println("Find Phone Error: " + io);
    	}
		
		return ph;
	}
	
	public TV findTV(Connection con, String id)
	{
		TV tv = null;
		try
    	{
    		Statement stmt = con.createStatement();
    		String tvSelect = "Select pd.Product_ID, pd.Product_Name, pd.Product_Description, pd.Product_Price, t.TV_Make, t.TV_Type, t.Screen_Size, t.Capable_3D from Product pd "
    				+ "join TV t on pd.Product_ID = t.TV_ID where pd.Product_ID = '" + id + "';";
    		ResultSet rs = stmt.executeQuery(tvSelect);
    		
    		while(rs.next())
    		{
    			tv = new TV(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(5), rs.getString(6),rs.getInt(7),rs.getBoolean(8),rs.getInt(4));
    		}
    		
    		rs.close();
    		stmt.close();
    	}
    	catch (Exception io)
    	{
    		System.out.println("Find TV Error: " + io);
    	}
		
		return tv;
	}

	public Customer findCustomer(Connection con, String id)
	{
		Customer customer = null;
		try
    	{
    		Statement stmt = con.createStatement();
    		String customerSelect = "Select * from Customer where Cust_ID = '"+ id +"';";
    		ResultSet rs = stmt.executeQuery(customerSelect);
    		
    		while(rs.next())
    		{
    			customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3));
    		}
    		
    		rs.close();
    		stmt.close();
    	}
    	catch (Exception io)
    	{
    		System.out.println("Find Customer Error: " + io);
    	}
		
		return customer;
	}
	/////////////////////////////////////////////////////////
	
	public String insertOrderDetails(Connection con, OrderDetails od)
	{
		String id = null;
		try {

			Statement insertStmt = con.createStatement();


			Statement stmt = con.createStatement();
			String orderDetailsSelect = "select MAX(Details_ID) from Order_Details";
			ResultSet rs = stmt.executeQuery(orderDetailsSelect);

			while(rs.next()) 
			{ 
				id = rs.getString(1);
			}

			id = "d" + (Integer.parseInt(id.substring(1)) + 1);
			rs.close();
			stmt.close();

			String insertDetails = "insert into order_details values('"+ id +"','"+ od.getProduct().getProductID() +"',"+ od.getQuantity() +","+ od.getTotal() +");";
			System.out.println(insertDetails);
			insertStmt.executeUpdate(insertDetails);

			insertStmt.close();
		}
		catch (Exception io) 
		{
			System.out.println("error"+io);
			io.printStackTrace();
		}
		return id;
	}
	
	public void insertOrder(Connection con, Order o)
	{
		try {

			Statement insertStmt = con.createStatement();

			String id = null;

			Statement stmt = con.createStatement();
			String orderSelect = "select MAX(Order_ID) from Orders";
			ResultSet rs = stmt.executeQuery(orderSelect);

			while(rs.next()) 
			{ 
				id = rs.getString(1);
			}

			id = "o" + (Integer.parseInt(id.substring(1)) + 1);
			rs.close();
			stmt.close();

			for(int i = 0; i < o.getNoOfItems(); i++)
			{
				String detailsID = insertOrderDetails(con, o.getItem(i));
				String insertOrder = "insert into orders values('"+ id +"','"+ detailsID +"','"+ o.getOwner().getId() +"');";
				System.out.println(insertOrder);
				insertStmt.executeUpdate(insertOrder);
			}

			insertStmt.close();
		}

		catch (Exception io) 
		{
			System.out.println("error"+io);
			io.printStackTrace();
		};  
	}

}
