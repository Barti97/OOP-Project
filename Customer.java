package FinalProject;

import java.util.ArrayList;

public class Customer {
	
	private String id;
	private String name;
	private String address;
	private ArrayList<Order> orderList;
	private int noOfOrders;
	
	Customer(String nameIn, String addressIn)
	{
		this.name = nameIn;
		this.address = addressIn;
		orderList = new ArrayList<Order>();
	}
	
	Customer(String in, String nameIn, String addressIn)
	{
		this.id = in;
		this.name = nameIn;
		this.address = addressIn;
		orderList = new ArrayList<Order>();
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public ArrayList<Order> getOrderList() 
	{
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList)
	{
		this.orderList = orderList;
		this.noOfOrders = this.orderList.size();
	}

	public int getNoOfOrders() 
	{
		return noOfOrders;
	}

	public void setNoOfOrders(int noOfOrders) 
	{
		this.noOfOrders = noOfOrders;
	}
	
	public boolean addOrder(Order order)
	{
		if(!order.isEmpty())
		{
			orderList.add(order);
			noOfOrders++;
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public boolean removeOrder(Order order)
	{
		if(orderList.contains(order))
		{
			orderList.remove(order);
			noOfOrders--;
			return true;
		}
		else
		{
			System.out.println("Order not found");
			return false;
		}
	}
	
	public void printOrder(int index)
	{
		if(noOfOrders > 0)
		{
			orderList.get(index).printOrder();
		}
	}
	
	public void printAllOrders()
	{
		if(noOfOrders > 0)
		{
			for(int i = 0; i < noOfOrders; i++)
			{
				Order newOrder = orderList.get(i);
				System.out.println("Order " + (i + 1));
				newOrder.printOrder();
			}
		}
	}
	
	public String toString()
	{
		return this.name;
	}
		
}
