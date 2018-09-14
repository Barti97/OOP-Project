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

public class CustomersTab extends Tab{

	private Controller ctrl;
	private GridPane custGridPane;
	private VBox showCustomers;
	private VBox myMain;
	private TableView<Customer> table;
	private Button addBtm;
	private Button newCust;
	
	public CustomersTab(String name, Controller in)
	{
		super(name);
		this.ctrl = in;

		custGridPane = new GridPane();
		
		table = new TableView<Customer>();
		
		newCust = new Button("Add a Customer");
		newCust.setOnAction(e->{
			addCustomer();
		});
		
		showCustomers = new VBox(10);
		showCustomers.setAlignment(Pos.CENTER);
		
		display();

		myMain = new VBox(10);
		myMain.getChildren().addAll(showCustomers,newCust);
		
		custGridPane.add(myMain, 0, 0);


		custGridPane.setAlignment(Pos.CENTER);
		this.setContent(custGridPane);
	}
	
	@SuppressWarnings("unchecked")
	public void display() 
	{
		showCustomers.getChildren().clear();
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		customers.setAll(ctrl.findCustomers());

		table.setItems(customers);
		table.setPrefWidth(412);
		table.setPrefHeight(200);

		TableColumn<Customer, String> idCol = new TableColumn<Customer, String>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
		idCol.setPrefWidth(60);

		TableColumn<Customer, String> nameCol = new TableColumn<Customer, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		nameCol.setPrefWidth(150);

		TableColumn<Customer, String> addressCol = new TableColumn<Customer, String>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		addressCol.setPrefWidth(200);


		table.getColumns().clear();
		table.getColumns().addAll(idCol, nameCol, addressCol);

		showCustomers.getChildren().addAll(table);
	}
	
	public void addCustomer()
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
		
		HBox nameHBox = new HBox(10);
		Label name = new Label("Name: " );
		name.setPrefWidth(100);
		TextField newName = new TextField();
		newName.setPrefWidth(200);
		nameHBox.getChildren().addAll(name, newName);

		HBox addressHBox = new HBox(10);
		Label address = new Label("Address: " );
		address.setPrefWidth(100);
		TextField newAddress = new TextField();
		newAddress.setPrefWidth(200);
		addressHBox.getChildren().addAll(address, newAddress);

		VBox customerVBox = new VBox(5);
		customerVBox.setAlignment(Pos.CENTER);
		customerVBox.setPadding(new Insets(50,50,50,50));

		addBtm = new Button("ADD");
		addBtm.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
		addBtm.setPadding(new Insets(5,10,5,10));
		addBtm.setOnAction(e->{
			Customer newCustomer = new Customer(newName.getText(), newAddress.getText());
			ctrl.insertCustomer(newCustomer);
			display();
			productWindow.close();
		});

		customerVBox.getChildren().addAll(nameHBox, addressHBox, addBtm);

		layout.add(customerVBox, 0, 0);
		
		productWindow.setScene(new Scene(layout, 500, 200));
		productWindow.showAndWait();
	}
}
