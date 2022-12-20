package com.example.supplychainhari17dec;

public class wishList {

    public static boolean placeWishList(String customerEmail,Product product){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = String.format("insert into wishlist (customer_id,product_id) values ((select customer_id from customer where email='%s'),'%s')",customerEmail,product.getId());
        int row = 0;
        try{
            row= databaseConnection.executeUpdateQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return row!=0;
    }
}
