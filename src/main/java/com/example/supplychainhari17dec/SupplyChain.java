package com.example.supplychainhari17dec;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SupplyChain extends Application {


    public static final int width = 1600,height  = 600,headerBar = 60;

    Pane bodyPane =  new Pane();
    public static int bodyWidth,bodyHeight;

    Login login = new Login();

    ProductDetails productDetails = new ProductDetails();

    Button globalLoginButton;

    Label customerEmailLbel = null;

    String customerEmail = null;


    private GridPane headerBar(){
        TextField searchText =  new TextField();
        Button searchButton  = new Button("Search");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productName = searchText.getText();

//                //clear body and put this new pane in the body
//                bodyPane.getChildren().clear();
                bodyPane.getChildren().add( productDetails.getProductsByName(productName));
                bodyPane.setStyle("-fx-background-color: #5F9EA0");
            }
        });



        globalLoginButton = new Button("Log In");
        globalLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());

                globalLoginButton.setDisable(true);

            }
        });

        customerEmailLbel = new Label("Welcome User");

        GridPane gridPane  = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.add(globalLoginButton,2,0);
        gridPane.add(customerEmailLbel,5,0);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);
        gridPane.setStyle("-fx-background-color: #5F9EA0");


        return gridPane;
    }

       private GridPane footerBar(){

        Button addToCart = new Button("Add to cart");
        Button buyNowButton = new Button("Buy Now");

        Label messageLabel = new Label("");
        Label cartLabel = new Label("");
        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product selectedProduct  = productDetails.getSelectedProduct();
                if(Order.placeOrder(customerEmail,selectedProduct)){
                      messageLabel.setText("Ordered");
                }
                else{
                    messageLabel.setText("Order Failed");
                }
            }
        });

        addToCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Product selectedProduct = productDetails.getSelectedProduct();
                if(wishList.placeWishList(customerEmail,selectedProduct)){
                    cartLabel.setText("Added to cart");
                }
                else{
                    cartLabel.setText("Product cannot be added to cart");
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
        gridPane.setVgap(10);
        gridPane.setHgap(50);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setTranslateY(headerBar+height+5);


        gridPane.add(addToCart,0,0);
        gridPane.add(buyNowButton,4,0);
        gridPane.add(messageLabel,5,0);
        gridPane.add(cartLabel,1,0);
        gridPane.setStyle("-fx-background-color: #5F9EA0");

        return gridPane;

       }




    private GridPane loginPage(){
        Label emailLabel = new Label("Email");
        Label passwordLabel  = new Label("Password");
        Label messageLabel = new Label("Existing User?");

        TextField emailTextField = new TextField();
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String email = emailTextField.getText();
                String password = passwordField.getText();
//                messageLabel.setText(email+" $$ "+password);
                if(login.customerLogin(email,password)){
                    messageLabel.setText("Login Successful");
                    customerEmail = email;
                    globalLoginButton.setDisable(true);
                    customerEmailLbel.setText("Account : "+ customerEmail);
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productDetails.getAllProduct());
                }
                else{
                    messageLabel.setText("Login failed");
                    globalLoginButton.setDisable(false);

                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setVgap(5);
        gridPane.setHgap(5);

//        gridPane.setStyle("-fx-background-color: #C0C0C0");

        gridPane.setAlignment(Pos.CENTER);


        emailLabel.setTextFill(Color.web("#0076a3"));
        emailLabel.setFont(new Font("Arial",20));
        passwordLabel.setTextFill(Color.web("#0076a3"));
        passwordLabel.setFont(new Font("Arial",15));
        gridPane.add(emailLabel,0,0);
        gridPane.add(emailTextField,1,0);
        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(loginButton,0,2);
        gridPane.add(messageLabel,1,2);

        return gridPane;
    }


    private Pane createContent(){

        Pane root = new Pane();

        root.setPrefSize(1500,height+2*headerBar+10);

        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerBar);

        bodyPane.getChildren().addAll(productDetails.getAllProduct());

        root.getChildren().addAll(headerBar(),bodyPane,footerBar());
//        root.setStyle("-fx-background-color: #5F9EA0");
        return root;
    }


    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene;
        scene = new Scene(createContent());
        stage.setTitle("FIPZON");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}