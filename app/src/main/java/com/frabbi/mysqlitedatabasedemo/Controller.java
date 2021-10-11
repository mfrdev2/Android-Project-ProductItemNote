package com.frabbi.mysqlitedatabasedemo;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.List;

public class Controller {
    private Context context;



    private MyDatabase db;
    public Controller(Context context) {
        this.context = context;
        db = new MyDatabase(context);
    }

    public boolean isValidUser(String username,String password){
        List<User> loginData = getDb().getAllLoginData();
        if (!loginData.isEmpty()) {
            for (User user: loginData){
                if(user.getUser_name().equals(username) && user.getPassword().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isExistUsername(String username){
        List<User> loginData = getDb().getAllLoginData();
        if (!loginData.isEmpty()) {
            for (User user: loginData){
                if(user.getUser_name().equals(username)){
                    return true;
                }
            }
        }
        return false;
    }
    public MyDatabase getDb() {
        return db;
    }

    public boolean insertDataToUser(User user){
        return getDb().insertDataIntoUserTable(user);
    }
    public boolean insertDataToProduct(Product product){
        return getDb().insertDataIntoProductTable(product);
    }

    public List<Product> getProductData(){
        return getDb().getAllDataFromProduct();
    }
    public List<User> getUserData(){
        return getDb().getAllDataFromUser();
    }
    public boolean updateProductData(Product newProduct,Product oldProduct){
        return getDb().updateProduct(newProduct,oldProduct);
    }
    public boolean deleteProductData(Product product){
        return getDb().deleteProduct(product);
    }


}
