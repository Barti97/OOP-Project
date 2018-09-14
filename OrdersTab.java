package FinalProject;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OrdersTab extends Tab{

	private GridPane ordersGridPane;
	private Controller ctrl;
	private VBox layout;
	private TableView<Order> orderTable;

	public OrdersTab(String name, Controller in)
	{
		super(name);

		ordersGridPane = new GridPane();
		this.ctrl = in;
		
		ordersGridPane = new GridPane();
		ordersGridPane.setAlignment(Pos.CENTER);

		layout = new VBox(5);
		layout.setAlignment(Pos.CENTER);

		ordersGridPane.add(layout, 0, 0);

		showOrders();

		this.setContent(ordersGridPane);
		this.setClosable(false);
		this.setOnSelectionChanged(e->{
			showOrders();
		});
	}

	@SuppressWarnings("unchecked")
	public void showOrders()
	{

		layout.getChildren().clear();
		
		orderTable = new TableView<Order>();
		orderTable.setPrefWidth(500);
		orderTable.getSelectionModel().selectedItemProperty().addListener(e->{
			int rowIndex = -1;
			for(int i = 0; i < orderTable.getItems().size(); i++)
			{
				if(orderTable.getSelectionModel().getSelectedItem().equals(orderTable.getItems().get(i)))
				{
					rowIndex = i;
				}
			}
			if(orderTable.getSelectionModel().getSelectedItem() != null)
			{
				popup(orderTable.getItems().get(rowIndex));
			}
		});
		
		ObservableList<Order> orders = FXCollections.observableArrayList();
		orders.addAll(ctrl.findOrders());
		System.out.println(ctrl.findOrders().get(0).getTotalCost());
		orderTable.setItems(orders);

		TableColumn<Order, String> pIDColumn = new TableColumn<Order, String>("ID");
		pIDColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("id"));
		pIDColumn.setPrefWidth(60);

		TableColumn<Order, Customer> pCustomerColumn = new TableColumn<Order, Customer>("Customer");
		pCustomerColumn.setCellValueFactory(new PropertyValueFactory<Order, Customer>("owner"));
		pCustomerColumn.setPrefWidth(299);

		TableColumn<Order, Float> pCostColumn = new TableColumn<Order, Float>("Total (€)");
		pCostColumn.setCellValueFactory(new PropertyValueFactory<Order, Float>("totalCost"));
		pCostColumn.setPrefWidth(139);

		orderTable.getColumns().clear();
		orderTable.getColumns().addAll(pIDColumn, pCustomerColumn, pCostColumn);

		layout.getChildren().addAll(orderTable);
	}

	@SuppressWarnings("unchecked")
	public void popup(Order ord)
	{
		GridPane orderPane = new GridPane();
		orderPane.setAlignment(Pos.CENTER);

		VBox orderV = new VBox(5);
		orderV.setAlignment(Pos.CENTER);

		TableView<OrderDetails> itemsTable = new TableView<OrderDetails>();
		itemsTable.setPrefWidth(410);

		ObservableList<OrderDetails> itemsOrdered = FXCollections.observableArrayList();
		for(int i = 0; i < ord.getItemList().size(); i++)
		{
			itemsOrdered.add((OrderDetails) ord.getItemList().get(i));
			System.out.println(ord.getItemList().get(i));
		}
		itemsTable.setItems(itemsOrdered);

		TableColumn<OrderDetails, String> pIDCol = new TableColumn<OrderDetails, String>("Product");
		pIDCol.setCellValueFactory(new PropertyValueFactory<OrderDetails, String>("product"));
		pIDCol.setPrefWidth(269);

		TableColumn<OrderDetails, Float> pCustomerColumn = new TableColumn<OrderDetails, Float>("Price");
		pCustomerColumn.setCellValueFactory(new PropertyValueFactory<OrderDetails, Float>("price"));
		pCustomerColumn.setPrefWidth(69);

		TableColumn<OrderDetails, Integer> pCostColumn = new TableColumn<OrderDetails, Integer>("Quantity");
		pCostColumn.setCellValueFactory(new PropertyValueFactory<OrderDetails, Integer>("quantity"));
		pCostColumn.setPrefWidth(70);

		Stage orderWindow = new Stage();
		orderWindow.setTitle("Order: " + ord.getId());
		orderWindow.initModality(Modality.APPLICATION_MODAL);
		orderWindow.initStyle(StageStyle.UTILITY);
		orderWindow.setOnCloseRequest(e->{
			showOrders();
			orderWindow.close();
		});

		itemsTable.getColumns().clear();
		itemsTable.getColumns().addAll(pIDCol, pCustomerColumn, pCostColumn);

		orderV.getChildren().addAll(itemsTable);

		orderPane.add(orderV, 0, 0);

		Scene orderScene = new Scene(orderPane, 600, 500);

		orderWindow.setScene(orderScene);
		orderWindow.showAndWait();
	}

}
