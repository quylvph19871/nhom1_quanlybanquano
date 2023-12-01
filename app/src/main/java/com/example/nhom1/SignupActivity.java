package com.example.nhom1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nhom1.DAOModel.DAOUser;
import com.example.nhom1.Model.User;
import com.example.nhom1.database.DbHelper;
import com.google.android.material.textfield.TextInputLayout;



public class SignupActivity extends AppCompatActivity {
    private TextInputLayout edtUser;
    private TextInputLayout edtsdt;
    private TextInputLayout edtpass;
    private Button btndangKy;
    private TextView btndangNhap;
    private DAOUser daoUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edtUser = findViewById(R.id.edtUser);
        edtsdt = findViewById(R.id.edtsdt);
        edtpass = findViewById(R.id.edtpass);
        btndangKy = findViewById(R.id.btndangKy);
        btndangNhap = findViewById(R.id.btndangNhap);




        btndangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUser.getEditText().getText().toString();
                String SDT = edtsdt.getEditText().getText().toString();
                String password = edtpass.getEditText().getText().toString();
                daoUser = new DAOUser(getApplicationContext());


                if (username.isEmpty()) {
                    edtUser.setError("hãy nhập tên !");
                    return;
                }
                if (SDT.isEmpty()) {
                    edtsdt.setError("hãy nhập số điện thoại 1 ");
                    return;
                }
                if (password.isEmpty()) {
                    edtpass.setError("hãy nhập mật khẩu !");
                    return;
                }
                if (!checkUser(edtUser.getEditText().getText().toString() , edtsdt.getEditText().getText().toString(), edtpass.getEditText().getText().toString())){

                    User user = new User(username , SDT,password);
                    user.setUsername(edtUser.getEditText().getText().toString());
                    user.setSDT(edtsdt.getEditText().getText().toString());
                    user.setPassword(edtpass.getEditText().getText().toString());
                    if (daoUser.insertUser(user) > 0){
                        Toast.makeText(SignupActivity.this,"thêm thành công " , Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btndangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

    }
    private boolean checkUser(String username , String SDT , String password){
        daoUser = new DAOUser(getApplicationContext());
        if (daoUser.checkUser(username , SDT , password)){
            Toast.makeText(this,"Tại khoản đã đăng kí " , Toast.LENGTH_SHORT).show();
            return  true;
        }
        return false;

    }
}




