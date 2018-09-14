package FinalProject;

public class Product {
	
	private String name;
	private String description;
	private int price;
	private String productID;
	
	Product(String nameIn, String descriptionIn, int priceIn)
	{
		this.name = nameIn;
		this.description = descriptionIn;
		this.price = priceIn;	
	}
	
	Product(String inID, String nameIn, String descriptionIn, int priceIn)
	{
		this.productID = inID;
		this.name = nameIn;
		this.description = descriptionIn;
		this.price = priceIn;	
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public int getPrice() 
	{
		return price;
	}

	public void setPrice(int price) 
	{
		this.price = price;
	}

	public String getProductID() 
	{
		return productID;
	}

	public void setProductID(String productID)
	{
		this.productID = productID;
	}
	
	public String toString()
	{
		return	"Product Details:"+
				"\n--------------------"+
				"\nProduct Name: "+this.name+
				"\nProduct ID: "+this.productID+
				"\nDescription: "+this.description+
				"\nPrice: €"+this.price;
	}
	
	public void print()
	{
		System.out.println(toString());
	}
}
