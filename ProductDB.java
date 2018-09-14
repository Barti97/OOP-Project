package FinalProject;

import java.util.ArrayList;

public class ProductDB {

	private ArrayList<Product> list;
	private int noOfItems;

	ProductDB()
	{
		list = new ArrayList<Product>();
	}

	public boolean isEmpty()
	{
		if(noOfItems == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void add(Product object)
	{
		list.add(object);
		noOfItems++;
		System.out.println(object.toString() + "\nNew product has been added\n");
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public void remove(int index)
	{
		if(!isEmpty())
		{
			System.out.println(list.get(index).toString() + " has been removed");
			list.remove(index);
			noOfItems--;
		}
		else
		{
			System.out.println("The list is empty - unable to remove object");
		}
	}

	public int getTotal()
	{
		return noOfItems;
	}

	public Product getObject(int index)
	{
		return list.get(index);
	}

	public void clear()
	{
		if(!isEmpty())
		{
			list.clear();
			this.noOfItems = 0;	
		}
	}

	public Product searchByID(String id)
	{
		if(!isEmpty())
		{
			for(int i = 0; i < noOfItems; i++)
			{
				if(list.get(i).getProductID().equals(id))
				{
					return list.get(i);
				}
			}
		}
		System.out.println("Product not found");
		return null;
	}

	public void print()
	{
		for(int i = 0; i < noOfItems; i++)
		{
			System.out.println(list.get(i).toString());
		}
	}
}
