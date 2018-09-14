package FinalProject;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyMain extends Application{

	final static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
			launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws SQLException {
		
		primaryStage.setTitle("Store Simulator");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?user=root&useLegacyDatetimeCode=false&serverTimezone=GMT" );
		System.out.println ("Database connection established");

		Controller controller = new Controller(con);

		TabPane tabPane = new TabPane();
		CreateTab tab1 = new CreateTab("Create a Product", controller); 
		tab1.setClosable(false);
		ProductsTab tab2 = new ProductsTab("Display Products", controller);
		tab2.setClosable(false);
		CustomersTab tab3 = new CustomersTab("Customers", controller);
		tab3.setClosable(false);
		MakeOrderTab tab4 = new MakeOrderTab("Order Products", controller);
		tab4.setClosable(false);
		OrdersTab tab5 = new OrdersTab("Display Orders", controller);
		tab5.setClosable(false);
		
		tabPane.getTabs().addAll(tab1,tab2,tab3,tab4,tab5);
		BorderPane mainPane = new BorderPane(tabPane);
		primaryStage.setScene(new Scene(mainPane, 1200, 800));
		primaryStage.show();
	}
}
