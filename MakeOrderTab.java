package FinalProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MakeOrderTab extends Tab{

	private GridPane makeOrderGridPane;
	private Controller ctrl;
	private TableView<Customer> customerTable;
	private TableView<Product> productTable;
	private VBox showCustomers;
	private VBox myVBox;
	private VBox myMain;
	private HBox qtyHBox;
	private Button select;
	private Button addBtm;
	private Button submitBtm;
	private Label label;
	private Label custLabel;
	private Label productsLabel;
	private TextField qty;
	private Order order;
	
	public MakeOrderTab(String name, Controller in)
	{
		super(name);
		this.ctrl = in;
		
		makeOrderGridPane = new GridPane();
		makeOrderGridPane.setPadding(new Insets(20,20,20,20));
		
		showCustomers = new VBox(10);
		showCustomers.setAlignment(Pos.CENTER);
		showCustomers.setPadding(new Insets(0,0,30,0));
		
		select = new Button("Select a product");
		select.setAlignment(Pos.CENTER);
		select.setOnAction(e->{
			showProducts();
		});
		
		submitBtm = new Button("SUBMIT");
		submitBtm.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
		submitBtm.setAlignment(Pos.CENTER);
		submitBtm.setOnAction(e->{
			if(customerTable.getSelectionModel().getSelectedItem() != null)
			{
				order.setOwner(customerTable.getSelectionModel().getSelectedItem());
				ctrl.createOrder(order);
				order.printOrder();
				display();
			}
		});
		
		custLabel = new Label();
		productsLabel = new Label();
		
		display();
		
		myMain = new VBox(10);
		myMain.setAlignment(Pos.CENTER);
		myMain.getChildren().addAll(showCustomers, custLabel,select,productsLabel, submitBtm);
		
		makeOrderGridPane.add(myMain, 0, 0);
		
		makeOrderGridPane.setAlignment(Pos.TOP_CENTER);
		this.setContent(makeOrderGridPane);
		this.setOnSelectionChanged(e->{
			display();
		});
	}
	
	@SuppressWarnings("unchecked")
	public void display() 
	{
		showCustomers.getChildren().clear();
		
		custLabel.setText("");
		custLabel.setStyle("-fx-font: 20 arial;");
		productsLabel.setText("");
		productsLabel.setStyle("-fx-font: 20 arial;");
		
		order = new Order();
		
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		customers.setAll(ctrl.findCustomers());

		customerTable = new TableView<Customer>();
	
		customerTable.getSelectionModel().selectedItemProperty().addListener(e->{
			if(customerTable.getSelectionModel().getSelectedItem() != null)
			{
				custLabel.setText(customerTable.getSelectionModel().getSelectedItem().getName());
			}
		});
	
		customerTable.setItems(customers);
		customerTable.setPrefWidth(412);
		customerTable.setPrefHeight(200);

		TableColumn<Customer, String> idCol = new TableColumn<Customer, String>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
		idCol.setPrefWidth(60);

		TableColumn<Customer, String> nameCol = new TableColumn<Customer, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		nameCol.setPrefWidth(150);

		TableColumn<Customer, String> addressCol = new TableColumn<Customer, String>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		addressCol.setPrefWidth(200);

		customerTable.getColumns().clear();
		customerTable.getColumns().addAll(idCol, nameCol, addressCol);

		showCustomers.getChildren().addAll(customerTable);
	}
	
	@SuppressWarnings("unchecked")
	private void showProducts()
	{
		GridPane layout = new GridPane();
		layout.setAlignment(Pos.TOP_CENTER);
		Stage productWindow = new Stage();
		productWindow.setTitle("Products");
		productWindow.initModality(Modality.APPLICATION_MODAL);
		productWindow.setOnCloseRequest(e2->
		{
			productWindow.close();
		});

		ObservableList<Product> products = FXCollections.observableArrayList();
		products.clear();
		products.setAll(ctrl.displayProducts());

		productTable = new TableView<Product>();
		
		productTable.setItems(products);
		productTable.setPrefWidth(500);
		productTable.setPrefHeight(600);

		TableColumn<Product, String> idColumn = new TableColumn<Product, String>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productID"));
		idColumn.setPrefWidth(60);

		TableColumn<Product, String> nameColumn = new TableColumn<Product, String>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		nameColumn.setPrefWidth(150);

		TableColumn<Product, String> descColumn = new TableColumn<Product, String>("Descrption");
		descColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
		descColumn.setPrefWidth(200);

		TableColumn<Product, Integer> priceColumn = new TableColumn<Product, Integer>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
		priceColumn.setPrefWidth(88);

		productTable.getColumns().clear();
		productTable.getColumns().setAll(idColumn, nameColumn, descColumn, priceColumn);

		myVBox = new VBox(10);
		myVBox.setAlignment(Pos.CENTER);
		myVBox.setPadding(new Insets(20,20,20,20));
		
		qtyHBox = new HBox(10);
		qtyHBox.setAlignment(Pos.CENTER);
		qtyHBox.setPadding(new Insets(20,20,20,20));
		
		label = new Label("Quantity: ");
		
		qty = new TextField();
		
		addBtm = new Button("ADD");
		addBtm.setAlignment(Pos.CENTER);
		addBtm.setOnAction(e->{
			if(productTable.getSelectionModel().getSelectedItem() != null)
			{
				order.add(productTable.getSelectionModel().getSelectedItem(), Integer.parseInt(qty.getText()));
				productsLabel.setText(productsLabel.getText() + productTable.getSelectionModel().getSelectedItem().getName() + "\n");
				productWindow.close();
			}
		});
		
		qtyHBox.getChildren().addAll(label,qty);
		myVBox.getChildren().addAll(productTable,qtyHBox,addBtm);
		
		layout.add(myVBox, 0, 0);
		
		productWindow.setScene(new Scene(layout, 700, 400));
		productWindow.showAndWait();
	}
}
