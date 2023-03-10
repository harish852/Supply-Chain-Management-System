package com.example.supplychainhari17dec;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProductDetails {

    public TableView<Product> productTable;

    public Pane getAllProduct(){
        TableColumn id = new TableColumn("PRODUCT_ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn("PRODUCT_NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("PRODUCT_PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//
//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(1,"Lenovo",12899));
//        data.add(new Product(1,"HP",1456));

        ObservableList<Product> products = Product.getAllProducts();

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.width,SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane = new Pane();
//        tablePane.setStyle("-fx-background-color: #C0C0C0");
        tablePane.setMinSize(SupplyChain.width,SupplyChain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;
    }



    public Pane getProductsByName(String productName){
        TableColumn id = new TableColumn("PRODUCT_ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn("PRODUCT_NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("PRODUCT_PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setStyle("-fx-font-size: 50;");

//
//        ObservableList<Product> data = FXCollections.observableArrayList();
//        data.add(new Product(1,"Lenovo",12899));
//        data.add(new Product(1,"HP",1456));

        ObservableList<Product> products = Product.getProductsByName(productName);

        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(SupplyChain.width,SupplyChain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane = new Pane();
        tablePane.setStyle("-fx-background-color: #5F9EA0");
        tablePane.setMinSize(SupplyChain.width,SupplyChain.height);
        tablePane.getChildren().add(productTable);
        return tablePane;
    }

    public Product getSelectedProduct(){
        try{
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            return selectedProduct;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
