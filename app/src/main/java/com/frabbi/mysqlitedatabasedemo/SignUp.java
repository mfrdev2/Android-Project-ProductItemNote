package com.frabbi.mysqlitedatabasedemo;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class SignUp extends AppCompatActivity {
    private Controller controller;
    private EditText  username,first_name,last_name,address,city,country,
    email,phone,password1,password2;
    private RadioGroup gender;
    private RadioButton male,female;
    private Button regSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        controller = new Controller(SignUp.this);

        intit();
        regSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString().trim();
                String password = getValidPass(SignUp.this);
                String firstName = first_name.getText().toString().trim();
                String lastName = last_name.getText().toString().trim();
                String sGender = getGender();
                String e_mail = email.getText().toString().trim();
                String phone_no = phone.getText().toString().trim();
                String uAddress = address.getText().toString().trim();
                String uCity = city.getText().toString().trim();
                String uCountry = country.getText().toString().trim();

                if(!user_name.isEmpty() && password !=null){
                    if(!controller.isExistUsername(user_name)){
                        User user = new User(user_name, password, firstName, lastName,
                                sGender,e_mail,phone_no,uAddress,uCity,uCountry);

                        boolean status =  controller.insertDataToUser(user);

                        if (status) {
                            Toast.makeText(SignUp.this, "Data successfully inserted.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }else {
                        Toast.makeText(SignUp.this,"Username already existed.",Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(SignUp.this, "Data field problem", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void intit() {
        username = findViewById(R.id.usernameId);
        first_name = findViewById(R.id.firstNameId);
        last_name = findViewById(R.id.lastNameId);
        gender = findViewById(R.id.radioGroupId);
        address = findViewById(R.id.addressId);
        city = findViewById(R.id.cityId);
        country = findViewById(R.id.countryId);
        phone = findViewById(R.id.phoneNoId);
        email = findViewById(R.id.emailID);
        password1 = findViewById(R.id.pass1Id);
        password2 = findViewById(R.id.pass2Id);
        male = findViewById(R.id.maleId);
        female = findViewById(R.id.femaleId);

        regSubmit = findViewById(R.id.regButtonId);


    }

    private String getGender() {
        String selectedGender = null;
        int id = gender.getCheckedRadioButtonId();
        if(id == male.getId()){
            selectedGender = male.getText().toString();
        }else if(id == female.getId()){
            selectedGender = female.getText().toString();
        }


/*        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (i == male.getId()) {
                           selectedGender = male.getText().toString();
                            Log.d("Gender : ", selectedGender);

                        }
                        if(i == female.getId()){
                            selectedGender = female.getText().toString();
                            Log.d("Gender : ", selectedGender);
                        }
                    }
                });*/
        return selectedGender;
    }

    private String getValidPass(Context context) {
        String p1 = password1.getText().toString().trim();
        String p2 = password2.getText().toString().trim();
        if(!p1.isEmpty() && !p2.isEmpty()){
            if(p1.equals(p2)){
               return p2;
            }else {
                Toast.makeText(context,"Please check your password.",Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }
}