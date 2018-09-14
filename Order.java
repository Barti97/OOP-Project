package FinalProject;

import java.util.ArrayList;

public class Order extends ProductDB{

	private ArrayList<OrderDetails> itemList = new ArrayList<OrderDetails>();

	private String id;
	private Customer owner;
	private int totalCost;

	Order(String in, Customer inCust)
	{
		this.id = in;
		this.owner = inCust;
	}

	Order()
	{
		super();
	}
	

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public ArrayList<OrderDetails> getItemList() {
		return itemList;
	}

	public String getId() {
		return id;
	}

	public Customer getOwner() {
		return owner;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public boolean add(Product product, int quantity)
	{
		if(quantity > 0)
		{
			itemList.add(new OrderDetails(product, quantity));
			super.setNoOfItems(super.getNoOfItems() + 1);
			this.totalCost = calculateTotalCost();
			System.out.println("ITEM ADDED");
			return true;
		}
		else
		{
			System.out.println("Order not created");
			return false;
		}
	}

	public void printOrder()
	{
		for(int i = 0; i < itemList.size(); i++)
		{
			OrderDetails orderDetails = itemList.get(i);
			orderDetails.print();
		}
	}

	public int calculateTotalCost()
	{
		int total = 0;
		for(int i = 0; i < super.getNoOfItems(); i++)
		{
			total += itemList.get(i).getProduct().getPrice() * itemList.get(i).getQuantity();
		}
		return total;
	}
	
	public OrderDetails getItem(int index)
	{
		return itemList.get(index);
	}
}
