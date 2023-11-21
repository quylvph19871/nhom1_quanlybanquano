package com.example.nhom1;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom1.DAOmodel.DAOUser;

public class LoginActivity extends AppCompatActivity {

    private DAOUser daoUser;
    EditText edtUser, edtPassword;
    ImageView btnLogin, img_hidePassword;
    CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtTenDangNhap);
        edtPassword = findViewById(R.id.edtMatKhau);
        checkBox = findViewById(R.id.chkNhoMK);
        btnLogin = findViewById(R.id.btnDangNhap);
        img_hidePassword = findViewById(R.id.img_hidePassword);
        daoUser = new DAOUser(this);
        edtPassword.getInputType();
        //sự kiện hide pass
        img_hidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPassword.getInputType() != InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_hidePassword.setImageResource(R.drawable.ic_hide_on);
                } else {
                    edtPassword.setInputType(129);
                    img_hidePassword.setImageResource(R.drawable.ic_visibility_off);
                }
            }
        });

//        Get Data từ SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        boolean rem = pref.getBoolean("REMEMBER", false);

        edtUser.setText(user);
        edtPassword.setText(pass);
        checkBox.setChecked(rem);
    }

}