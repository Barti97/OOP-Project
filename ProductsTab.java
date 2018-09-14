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
import javafx.stage.StageStyle;

public class ProductsTab extends Tab{

	private Controller ctrl;
	private Button search;
	private VBox showProducts;
	private HBox find;
	private Label label;
	private TextField idInput;
	private TableView<Product> table;
	private GridPane mainPane;
	
	public ProductsTab(String name, Controller in)
	{
		super(name);
		this.ctrl = in;

		mainPane = new GridPane();
		mainPane.setAlignment(Pos.CENTER);
		
		idInput = new TextField();
		idInput.setPrefWidth(50);
		
		search = new Button("Search");
		search.setOnAction(e->{
			productByID(idInput.getText());
		});
		
		label = new Label("Search by ID: ");

		showProducts = new VBox(10);
		showProducts.setAlignment(Pos.CENTER);
		
		find = new HBox(20);
		find.setAlignment(Pos.CENTER);
		find.setPadding(new Insets(20,20,20,20));
		find.getChildren().addAll(label,idInput,search);
		
		products();
		
		mainPane.add(showProducts, 0, 0);
		mainPane.add(find, 0, 1);
		this.setContent(mainPane);


	}

	@SuppressWarnings("unchecked")
	private void products()
	{
		showProducts.getChildren().clear();

		ObservableList<Product> products = FXCollections.observableArrayList();
		products.clear();
		products.setAll(ctrl.displayProducts());

		table = new TableView<Product>();
		table.getSelectionModel().selectedItemProperty().addListener(e->{
			int rowIndex = 0;
			for(int i = 0; i < table.getItems().size(); i++)
			{
				if(table.getSelectionModel().getSelectedItem().equals(table.getItems().get(i)))
				{
					rowIndex = i;
				}
			}
			if(table.getSelectionModel().isSelected(rowIndex))
			{
				popup(table.getItems().get(rowIndex));
			}

		});

		table.setItems(products);
		table.setPrefWidth(500);
		table.setPrefHeight(200);

		TableColumn<Product, String> idColumn = new TableColumn<Product, String>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productID"));
		idColumn.setPrefWidth(60);

		TableColumn<Product, String> nameColumn = new TableColumn<Product, String>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		nameColumn.setPrefWidth(150);

		TableColumn<Product, String> descColumn = new TableColumn<Product, String>("Descrption");
		descColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
		descColumn.setPrefWidth(200);


		TableColumn<Product, Integer> priceColumn = new TableColumn<Product, Integer>("Price €");
		priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
		priceColumn.setPrefWidth(88);

		table.getColumns().clear();
		table.getColumns().setAll(idColumn, nameColumn, descColumn, priceColumn);

		showProducts.getChildren().addAll(table); // idInput, search
	}

	@SuppressWarnings("unchecked")
	private void productByID(String id)
	{
		Product p = ctrl.findProductByID(id);
		if(p != null)
		{

			showProducts.getChildren().clear();

			ObservableList<Product> products = FXCollections.observableArrayList();
			products.clear();
			products.add(p);
			
			table.setItems(products);
			table.setPrefWidth(500);
			table.setPrefHeight(200);

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

			table.getColumns().clear();
			table.getColumns().setAll(idColumn, nameColumn, descColumn, priceColumn);


			showProducts.getChildren().addAll(table); // idInput, search
		}
	}

	private void popup(Product p)
	{
		GridPane layout = new GridPane();
		layout.setAlignment(Pos.CENTER);
		Stage productWindow = new Stage();
		productWindow.setTitle("Product Data");
		productWindow.initModality(Modality.APPLICATION_MODAL);
		productWindow.initStyle(StageStyle.UTILITY);
		productWindow.setOnCloseRequest(e2->
		{
			productWindow.close();
		});

		Button ok = new Button("OK!");
		ok.setOnAction(e1->
		{
			idInput.setText("");
			products();
			productWindow.close();

		});

		VBox productV = new VBox(20);
		productV.setAlignment(Pos.CENTER);

		if(p instanceof Phone)
		{
			HBox labels = new HBox(5);
			Label make = new Label("Make");
			make.setMaxWidth(100);

			Label model = new Label("Model");
			model.setMaxWidth(100);

			Label storage = new Label("Storage");
			storage.setMaxWidth(100);

			Label price = new Label("Price");
			price.setMaxWidth(100);

			labels.getChildren().addAll(make, model, storage, price);

			Label makeData = new Label(((Phone) p).getMake());
			makeData.setMaxWidth(100);

			Label modelData = new Label(((Phone) p).getModel());
			modelData.setMaxWidth(100);

			Label storageData = new Label(((Phone) p).getStorage() + "GB");
			storageData.setMaxWidth(100);

			Label priceData = new Label("€" + p.getPrice());
			priceData.setMaxWidth(100);

			HBox data = new HBox(5);
			data.getChildren().addAll(makeData, modelData, storageData, priceData);

			productV.getChildren().addAll(labels, data, ok);
		}

		else if(p instanceof TV)
		{
			HBox labels = new HBox(5);
			Label make = new Label("Make");
			make.setMaxWidth(100);

			Label type = new Label("Type");
			type.setMaxWidth(100);

			Label size = new Label("Screen Size");
			size.setMaxWidth(100);

			Label capable3D = new Label("3D Capable");
			capable3D.setMaxWidth(100);

			Label price = new Label("Price");
			price.setMaxWidth(100);

			labels.getChildren().addAll(make, type, size, capable3D, price);

			Label makeData = new Label(((TV) p).getMake());
			makeData.setMaxWidth(100);

			Label typeData = new Label(((TV) p).getType());
			typeData.setMaxWidth(100);

			Label sizeData = new Label(((TV) p).getScreenSize() + " inch");
			sizeData.setMaxWidth(100);

			Label capable3dData = new Label(((TV) p).isCapable3D() + "");
			capable3dData.setMaxWidth(100);

			Label priceData = new Label("€" + p.getPrice());
			priceData.setMaxWidth(100);

			HBox data = new HBox(5);
			data.getChildren().addAll(makeData, typeData,sizeData,capable3dData , priceData);

			productV.getChildren().addAll(labels, data, ok);
		}

		layout.add(productV, 0, 0);

		Scene productScene = new Scene(layout, 500, 350);

		productWindow.setScene(productScene);
		productWindow.showAndWait();
	}
}
