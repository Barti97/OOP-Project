package FinalProject;

public class OrderDetails {
	
	private Product product;
	private int quantity;
	private int price;
	private int total;
	
	OrderDetails(Product inProduct, int inQuantity)
	{
		this.product = inProduct;
		this.quantity = inQuantity;
		this.price = product.getPrice();
		this.total = price * quantity;
	}
	
	public int getTotal() {
		return total;
	}

	public int getPrice() {
		return price;
	}

	public String toString()
	{
		return "\n\tProduct: " + product.getName()
				+"\n\tQuantity: " + quantity;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void print()
	{
		System.out.println(toString());
	}
}
