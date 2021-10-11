package com.frabbi.mysqlitedatabasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongPredicate;

public class MyDatabase {
   private SQLiteDatabase db;
   private MyHelper myHelper;

    public MyDatabase(Context context) {
        myHelper = new MyHelper(context);
        db = myHelper.getWritableDatabase();
    }
    public List<User> getAllDataFromUser(){
        String[] column = {MyHelper.ID,
                MyHelper.USER_NAME,
        MyHelper.PASSWORD,
        MyHelper.FIRST_NAME ,
        MyHelper.LAST_NAME,
        MyHelper.GENDER,
        MyHelper.EMAIL,
        MyHelper.PHONE_NO,
        MyHelper.ADDRESS,
        MyHelper.CITY,
        MyHelper.COUNTRY,};
        Cursor cursor;
        cursor = db.query(MyHelper.TABLE_NAME1,
                column,null,null,null,null,null);
        List<User> userList = new ArrayList<>();
        while (cursor.moveToNext()){
            User user = new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10)
            );
            userList.add(user);
        }




        return userList;
    }
    public List<User> getAllLoginData(){
        String[] column = {MyHelper.ID,
                MyHelper.USER_NAME,
        MyHelper.PASSWORD,
        MyHelper.FIRST_NAME ,
        MyHelper.LAST_NAME,
        MyHelper.GENDER,
        MyHelper.EMAIL,
        MyHelper.PHONE_NO,
        MyHelper.ADDRESS,
        MyHelper.CITY,
        MyHelper.COUNTRY,};
        Cursor cursor;
        cursor = db.query(MyHelper.TABLE_NAME1,
                column,null,null,null,null,null);
        List<User> userList = new ArrayList<>();
        while (cursor.moveToNext()){
            User user = new User(

                    cursor.getString(1),
                    cursor.getString(2)

            );
            userList.add(user);
        }




        return userList;
    }
    public List<Product> getAllDataFromProduct(){
        String[] column = {MyHelper.ID2,
                MyHelper.P_NAME,
                MyHelper.P_DESC,
                MyHelper.P_PRICE
               };
        Cursor cursor;
        cursor = db.query(MyHelper.TABLE_NAME2,
                column,null,null,null,null,null);
        List<Product> productList = new ArrayList<>();
        while (cursor.moveToNext()){
            Product product = new Product(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    Double.parseDouble(cursor.getString(3))
                 );
            productList.add(product);
        }

        return productList;
    }

    public boolean updateProduct(Product newProduct,Product oldProduct){
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyHelper.ID2,newProduct.get_id());
        contentValues.put(MyHelper.P_NAME,newProduct.getP_name());
        contentValues.put(MyHelper.P_DESC,newProduct.getP_desc());
        contentValues.put(MyHelper.P_PRICE,newProduct.getPrice());
        Log.d("UPDATE:",""+oldProduct.get_id());
        int s = db.update(MyHelper.TABLE_NAME2,contentValues,MyHelper.ID2+"=?",new String[]{
                String.valueOf(oldProduct.get_id())
        });

        return s>0;
    }
    public boolean deleteProduct(Product product){
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyHelper.P_NAME,product.getP_name());
        contentValues.put(MyHelper.P_DESC,product.getP_desc());
        contentValues.put(MyHelper.P_PRICE,product.getPrice());
        Log.d("DELETE:",""+product.get_id());
        int s = db.delete(MyHelper.TABLE_NAME2,MyHelper.ID2+"=?",new String[]{
                String.valueOf(product.get_id())
        });

        return s>0;
    }


    public boolean insertDataIntoUserTable(User user) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyHelper.USER_NAME, user.getUser_name());
        contentValues.put(MyHelper.PASSWORD,user.getPassword());
        contentValues.put(MyHelper.FIRST_NAME,user.getFirst_name());
        contentValues.put(MyHelper.LAST_NAME,user.getLast_name());
        contentValues.put(MyHelper.GENDER,user.getLast_name());
        contentValues.put(MyHelper.EMAIL,user.getLast_name());
        contentValues.put(MyHelper.PHONE_NO,user.getPhone_no());
        contentValues.put(MyHelper.ADDRESS,user.getAddress());
        contentValues.put(MyHelper.CITY,user.getCity());
        contentValues.put(MyHelper.COUNTRY,user.getCountry());

        long l = db.insert(MyHelper.TABLE_NAME1,null,contentValues);
        Log.d("Long-User ",""+l);

        return l>0;
    }

    public boolean insertDataIntoProductTable(Product product) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(MyHelper.P_NAME,product.getP_name());
        contentValues.put(MyHelper.P_DESC,product.getP_desc());
        contentValues.put(MyHelper.P_PRICE,product.getPrice());

        long l = db.insert(MyHelper.TABLE_NAME2,null,contentValues);

        Log.d("Long-Product",""+l);

        return l>0;
    }


    //inner class
  private static class MyHelper extends SQLiteOpenHelper {
        private Context context;
        static final String DATABASE_NAME = "MyDatabase.db";
        static final int VERSION = 2;
        static final String TABLE_NAME1 = "user";
        static final String TABLE_NAME2 = "product";

        //column name
        static final String ID = "_id";
        static final String USER_NAME = "user_name";
        static final String PASSWORD = "password";
        static final String FIRST_NAME = "first_name";
        static final String LAST_NAME = "last_name";
        static final String GENDER = "gender";
        static final String EMAIL = "e_mail";
        static final String PHONE_NO = "phone_no";
        static final String ADDRESS = "address";
        static final String CITY = "city";
        static final String COUNTRY = "country";

        static final String ID2 = "_id";
        static final String P_NAME = "p_name";
        static final String P_DESC = "p_desc";
        static final String P_PRICE = "p_price";

        static final String DROPTABLE1 = "DROP TABLE IF EXISTS "+TABLE_NAME1;
        static final String DROPTABLE2 = "DROP TABLE IF EXISTS "+TABLE_NAME2;


        private static final String CREATE_TABLE1 = "CREATE TABLE "+TABLE_NAME1+" (" +ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +USER_NAME+" VARCHAR(45) NOT NULL,"
                +PASSWORD+" VARCHAR(45) NOT NULL,"
                +FIRST_NAME+" VARCHAR(45),"
                +LAST_NAME+ " VARCHAR (45),"
                +GENDER+ " VARCHAR (45),"
                +EMAIL+ " VARCHAR (45),"
                +PHONE_NO+" VARCHAR(45),"
                +ADDRESS+ " VARCHAR(45),"
                +CITY+" VARCHAR(45),"
                +COUNTRY+" VARCHAR(45));";

        private static final String CREATE_TABLE2 = "CREATE TABLE "+TABLE_NAME2+" (" +ID2+
                " INTEGER  PRIMARY KEY AUTOINCREMENT, "+P_NAME+
                " VARCHAR(45) ,"+P_DESC+" VARCHAR(45) ,"+P_PRICE+
                " DOUBLE );";

        public MyHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATE_TABLE1);
                db.execSQL(CREATE_TABLE2);
            }catch (Exception e){
                Log.d("SQLiteException: ",e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            try {
                db.execSQL(DROPTABLE1);
                db.execSQL(DROPTABLE2);
                onCreate(db);
            }catch (Exception e){
                Log.d("SQLiteException: ",e.getMessage());
            }
        }

    }
}
