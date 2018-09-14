package FinalProject;

public class Phone extends Product{
	
	private String make;
	private String model;
	private int storage;
	
	Phone(String nameIn, String descriptionIn, String makeIn, String modelIn, int storageIn, int priceIn)
	{	
		super(nameIn, descriptionIn, priceIn);
		this.make = makeIn;
		this.model = modelIn;
		this.storage = storageIn;
	}
	
	Phone(String in, String nameIn, String descriptionIn, String makeIn, String modelIn, int storageIn, int priceIn)
	{	
		super(in, nameIn, descriptionIn, priceIn);
		this.make = makeIn;
		this.model = modelIn;
		this.storage = storageIn;
	}
	
	public String getMake() 
	{
		return make;
	}
	
	public void setMake(String make) 
	{
		this.make = make;
	}
	
	public String getModel() 
	{
		return model;
	}
	
	public void setModel(String model) 
	{
		this.model = model;
	}
	
	public int getStorage()
	{
		return storage;
	}
	
	public void setStorage(int storage) 
	{
		this.storage = storage;
	}
	
	public String toString()
	{
		return	super.toString()+
				"\nPhone Make: "+this.make+
				"\nPhone Model: "+this.model+
				"\nStorage: "+this.storage;
	}
	
	public void print()
	{	
		super.print();
		System.out.println(toString());
	} 
}
