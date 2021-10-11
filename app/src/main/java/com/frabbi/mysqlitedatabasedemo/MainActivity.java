package com.frabbi.mysqlitedatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private Controller controller;
    private Button regBtn,logBtn;
    private EditText uname,upass;
    private SP sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = new SP(this);
        initi();
        if(sp.getUsernameSp() != null && sp.getUserPassSp() != null){
            uname.setText(sp.getUsernameSp());
            upass.setText(sp.getUserPassSp());
        }
        controller = new Controller(MainActivity.this);
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = uname.getText().toString().trim();
                String userPass = upass.getText().toString().trim();
                if (!userName.isEmpty() && !userPass.isEmpty()) {
                    if(controller.isValidUser(userName,userPass)){
                        sp.setUserNameSp(userName);
                        sp.setPassSp(userPass);
                        Intent intent = new Intent(MainActivity.this,ProductView.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "Please completed registration process.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Blank Text Field!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void initi() {
        regBtn = findViewById(R.id.regBtnId);
        logBtn = findViewById(R.id.loginBtnId);
        uname = findViewById(R.id.unameId);
        upass = findViewById(R.id.uPasswordId);
    }
}