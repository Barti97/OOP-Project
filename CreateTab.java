package FinalProject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CreateTab extends Tab{

	private GridPane createGridPane;
	private HBox buttonsHBox;
	private Button createPhone;
	private Button createTV;
	private Button submitBtm;
	private Button cancelBtm;
	private Controller ctrl;


	public CreateTab(String name, Controller in)
	{
		super(name);

		this.ctrl = in;

		createGridPane = new GridPane();
		buttonsHBox = new HBox(20);
		buttonsHBox.setAlignment(Pos.CENTER);

		createPhone = new Button("Create a Phone");
		createPhone.setPrefSize(150, 100);
		createPhone.setAlignment(Pos.CENTER);
		createPhone.setOnAction(e->{
			phoneMenu();
		});

		createTV = new Button("Create a TV");
		createTV.setPrefSize(150, 100);
		createTV.setAlignment(Pos.CENTER);
		createTV.setOnAction(e->{
			tvMenu();
		});

		buttonsHBox.getChildren().addAll(createPhone, createTV);
		createGridPane.add(buttonsHBox, 0, 0);
		createGridPane.setAlignment(Pos.CENTER);
		this.setContent(createGridPane);
	}

	public void phoneMenu()
	{
		createGridPane.getChildren().clear();

		HBox nameHBox = new HBox(10);
		Label name = new Label("Name: " );
		name.setPrefWidth(100);
		TextField newName = new TextField();
		newName.setPrefWidth(200);
		nameHBox.getChildren().addAll(name, newName);

		HBox descriptionHBox = new HBox(10);
		Label description = new Label("Description: " );
		description.setPrefWidth(100);
		TextField newDescription = new TextField();
		newDescription.setPrefWidth(200);
		descriptionHBox.getChildren().addAll(description, newDescription);

		HBox makeHBox = new HBox(10);
		Label make = new Label("Make: " );
		make.setPrefWidth(100);
		TextField newMake = new TextField();
		newMake.setPrefWidth(200);
		makeHBox.getChildren().addAll(make, newMake);

		HBox modelHBox = new HBox(10);
		Label model = new Label("Model: " );
		model.setPrefWidth(100);
		TextField newModel = new TextField();
		newModel.setPrefWidth(200);
		modelHBox.getChildren().addAll(model, newModel);

		HBox storageHBox = new HBox(10);
		Label storage = new Label("Storage: " );
		storage.setPrefWidth(100);
		TextField newStorage = new TextField();
		newStorage.setPrefWidth(200);
		storageHBox.getChildren().addAll(storage, newStorage);

		HBox priceHBox = new HBox(10);
		Label price = new Label("Price: " );
		price.setPrefWidth(100);
		TextField newPrice = new TextField();
		newPrice.setPrefWidth(200);
		priceHBox.getChildren().addAll(price, newPrice);

		VBox phoneVBox = new VBox(5);
		phoneVBox.setAlignment(Pos.CENTER);


		submitBtm = new Button("ADD");
		submitBtm.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
		submitBtm.setPadding(new Insets(5,10,5,10));
		submitBtm.setOnAction(e->{
			Phone newPhone = new Phone(newName.getText(), newDescription.getText(), newMake.getText(), newModel.getText(), Integer.parseInt(newStorage.getText()), Integer.parseInt(newPrice.getText()));
			ctrl.createPhone(newPhone);
		});

		cancelBtm = new Button("Cancel");
		cancelBtm.setPadding(new Insets(5,10,5,10));
		cancelBtm.setOnAction(e->{
			createGridPane.getChildren().clear();
			createGridPane.add(buttonsHBox, 0, 0);
		});

		phoneVBox.getChildren().addAll(nameHBox, descriptionHBox, makeHBox, modelHBox, storageHBox, priceHBox, submitBtm, cancelBtm);

		createGridPane.add(phoneVBox, 0, 0);
	}

	public void tvMenu()
	{
		createGridPane.getChildren().clear();

		HBox nameHBox = new HBox(10);
		Label name = new Label("Name: " );
		name.setPrefWidth(100);
		TextField newName = new TextField();
		newName.setPrefWidth(200);
		nameHBox.getChildren().addAll(name, newName);

		HBox descriptionHBox = new HBox(10);
		Label description = new Label("Description: " );
		description.setPrefWidth(100);
		TextField newDescription = new TextField();
		newDescription.setPrefWidth(200);
		descriptionHBox.getChildren().addAll(description, newDescription);

		HBox makeHBox = new HBox(10);
		Label make = new Label("Make: " );
		make.setPrefWidth(100);
		TextField newMake = new TextField();
		newMake.setPrefWidth(200);
		makeHBox.getChildren().addAll(make, newMake);

		HBox typeHBox = new HBox(10);
		Label type = new Label("Type: " );
		type.setPrefWidth(100);
		ComboBox<String> newType = new ComboBox<String>();
		newType.getItems().addAll("LCD", "LED", "Plasma");
		newType.setPrefWidth(200);
		typeHBox.getChildren().addAll(type, newType);

		HBox screenHBox = new HBox(10);
		Label screen = new Label("Screen Size: " );
		screen.setPrefWidth(100);
		TextField newScreen = new TextField();
		newScreen.setPrefWidth(200);
		screenHBox.getChildren().addAll(screen, newScreen);

		HBox capable3dHBox = new HBox(10);
		Label capable3d = new Label("3D Capable: \t");
		capable3d.setPrefWidth(100);
		RadioButton newCapable3d = new RadioButton();
		capable3dHBox.getChildren().addAll(capable3d, newCapable3d);

		HBox priceHBox = new HBox(10);
		Label price = new Label("Price: " );
		price.setPrefWidth(100);
		TextField newPrice = new TextField();
		newPrice.setPrefWidth(200);
		priceHBox.getChildren().addAll(price, newPrice);

		VBox tvVBox = new VBox(5);
		tvVBox.setAlignment(Pos.CENTER);

		submitBtm = new Button("ADD");
		submitBtm.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
		submitBtm.setOnAction(e->{
			TV newTV = new TV(newName.getText(), newDescription.getText(), newMake.getText(), newType.getValue(), Integer.parseInt(newScreen.getText()), newCapable3d.isSelected(), Integer.parseInt(newPrice.getText()));
			ctrl.createTV(newTV);
		});

		cancelBtm = new Button("Cancel");
		cancelBtm.setOnAction(e->{
			createGridPane.getChildren().clear();
			createGridPane.add(buttonsHBox, 0, 0);
		});

		tvVBox.getChildren().addAll(nameHBox, descriptionHBox, makeHBox, typeHBox, screenHBox,capable3dHBox, priceHBox, submitBtm, cancelBtm);

		createGridPane.add(tvVBox, 0, 0);
	}
}