package com.frabbi.mysqlitedatabasedemo;


import android.content.Context;
import android.content.SharedPreferences;

public class SP {
   private SharedPreferences sp;
   private SharedPreferences.Editor spe;

    public SP(Context context) {
        sp = context.getSharedPreferences("MyRef",Context.MODE_PRIVATE);
        spe = sp.edit();
    }

    public void setUserNameSp(String username) {
        spe.putString("username",username);
        spe.commit();
    }
    public void setPassSp(String password) {
        spe.putString("password",password);
        spe.commit();
    }

    public String getUsernameSp(){
        return sp.getString("username",null);
    }
    public String getUserPassSp(){
        return sp.getString("password",null);
    }
}
