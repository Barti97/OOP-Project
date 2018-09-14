package FinalProject;

public class TV extends Product{
	
	private String make;
	private String type;
	private int screenSize;
	private boolean capable3D;
	
	
	TV(String nameIn, String descriptionIn, String makeIn, String typeIn, int screenSizeIn, boolean capable3Din, int priceIn)
	{	
		super(nameIn, descriptionIn, priceIn);
		this.make = makeIn;
		this.type = typeIn;
		this.screenSize = screenSizeIn;
		this.capable3D = capable3Din;	
	}

	TV(String in, String nameIn, String descriptionIn, String makeIn, String typeIn, int screenSizeIn, boolean capable3Din, int priceIn)
	{	
		super(in, nameIn, descriptionIn, priceIn);
		this.make = makeIn;
		this.type = typeIn;
		this.screenSize = screenSizeIn;
		this.capable3D = capable3Din;	
	}

	
	public String getMake() 
	{
		return make;
	}

	public void setMake(String make) 
	{
		this.make = make;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public int getScreenSize()
	{
		return screenSize;
	}

	public void setScreenSize(int screenSize) 
	{
		this.screenSize = screenSize;
	}

	public boolean isCapable3D() 
	{
		return capable3D;
	}

	public void setCapable3D(boolean capable3d) 
	{
		capable3D = capable3d;
	}
	
	public String toString()
	{	
		return	super.toString()+
				"\nTV Make: "+this.make+
				"\nTV Type: "+this.type+
				"\nScreen Size: "+this.screenSize+
				"\n3D capable: "+this.capable3D;
	}
	
	public void print()
	{
		super.print();
		System.out.println(toString());
	} 
}
