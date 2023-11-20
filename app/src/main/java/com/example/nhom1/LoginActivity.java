package com.example.nhom1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.nhom1.DAO.KhachHangDAO;
import com.example.nhom1.Entity.KhachHang;
import com.example.nhom1.Support.ChangeType;
import com.example.nhom1.view.Main_Admin;
import com.example.nhom1.view.Main_KH;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    TextView gotoSignUpAct, textView_Forgot_Pass;
    String TAG = "LoginActivity_____";
    String roleUser = "";
    AppCompatButton loginButton;
    Context context = this;
    TextInputLayout tilEmail, tilPass;
    CheckBox checkBox;
    ChangeType changeType = new ChangeType();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gotoSignUpAct = findViewById(R.id.go_to_SignUpAct);
        loginButton = findViewById(R.id.button_Login);
        tilEmail = findViewById(R.id.textInput_Email);
        tilPass = findViewById(R.id.textInput_Password);
        checkBox = findViewById(R.id.checkBox_Remember_Me);
        //
        //
        //textView_Forgot_Pass = findViewById(R.id.textView_Forgot_Pass);

        getDataIntent();
        getRememberMe();
        loginTime();
        goToSignUp();

        if (roleUser.equals("admin")){
            LinearLayout linearLayout = findViewById(R.id.layout_Sign_Up);
            linearLayout.setVisibility(View.GONE);
        // textView_Forgot_Pass.setVisibility(View.GONE);
        }
        if (roleUser.equals("nhanVien")){
            LinearLayout linearLayout = findViewById(R.id.layout_Sign_Up);
            linearLayout.setVisibility(View.GONE);
        }
    }

    private void loginTime() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roleUser.equals("admin")) {
                    Log.d(TAG, "loginTime: U r admin");
                    String email = changeType.deleteSpaceText(tilEmail.getEditText().getText().toString());
                    String pass = changeType.deleteSpaceText(tilPass.getEditText().getText().toString());
                    if (checkLoginAdmin(email, pass)) {
                        setRememberMe("admin", email, pass, checkBox.isChecked());
                        Intent intent = new Intent(context, Main_Admin.class);
                        SharedPreferences pref = getSharedPreferences("Who_Login", MODE_PRIVATE);
                        if (pref != null) {
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("role", "ad");
                            editor.putString("who", "admin");
                            editor.putString("isLogin", "true");
                            Log.d(TAG, "checkFirstTime: isLogin " + pref.getString("isLogin", ""));
                            editor.apply();
                        }
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(context, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
//                if (roleUser.equals("nhanVien")) {
//                    Log.d(TAG, "loginTime: U r nhanVien");
//                    String email = changeType.deleteSpaceText(tilEmail.getEditText().getText().toString());
//                    String pass = changeType.deleteSpaceText(tilPass.getEditText().getText().toString());
//                    if (checkLoginNV(email, pass)) {
//                        setRememberMe("nv", email, pass, checkBox.isChecked());
//                        Intent intent = new Intent(context, Main_NV_Navi_Activity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(context, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
//                    }
//                }
                if (roleUser.equals("khachHang")) {
                    Log.d(TAG, "loginTime: U r khachHang");
                    String email = changeType.deleteSpaceText(tilEmail.getEditText().getText().toString());
                    String pass = changeType.deleteSpaceText(tilPass.getEditText().getText().toString());
                    if (checkLoginKH(email, pass)) {
                        setRememberMe("kh", email, pass, checkBox.isChecked());
                        Intent intent = new Intent(context, Main_KH.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(context, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void setRememberMe(String role, String email, String pass, boolean checked) {
        SharedPreferences preferences = getSharedPreferences("Remember_Me", MODE_PRIVATE);
        if (preferences != null){
            SharedPreferences.Editor editor = preferences.edit();
            if (!checked) {
                editor.clear();
            } else {
                editor.putString("Email_RM", email);
                editor.putString("Pass_RM", pass);
                editor.putString("Role_RM", role);
            }
            editor.apply();
        }
    }
    private void getRememberMe() {
        SharedPreferences preferences = getSharedPreferences("Remember_Me", MODE_PRIVATE);
        if (preferences != null) {
            String role = preferences.getString("Role_RM", "");
            if (role != null) {
                Log.d(TAG, "getRememberMe: role: " + role);
                Log.d(TAG, "getRememberMe: roleUser: " + roleUser);
                if (role.equals("nv") && roleUser.equals("nhanVien")) {
                    tilEmail.getEditText().setText(preferences.getString("Email_RM", ""));
                    tilPass.getEditText().setText(preferences.getString("Pass_RM", ""));
                    checkBox.setChecked(true);
                }
                if (role.equals("kh") && roleUser.equals("khachHang")) {
                    tilEmail.getEditText().setText(preferences.getString("Email_RM", ""));
                    tilPass.getEditText().setText(preferences.getString("Pass_RM", ""));
                    checkBox.setChecked(true);
                }
                if (role.equals("admin") && roleUser.equals("admin")) {
                    tilEmail.getEditText().setText(preferences.getString("Email_RM", ""));
                    tilPass.getEditText().setText(preferences.getString("Pass_RM", ""));
                    checkBox.setChecked(true);
                }
            }
        }
    }
    private boolean checkLoginAdmin(String email, String matKhau) {
        if (email.equals("admin@gmail.com")) {
            tilEmail.setError("");
            tilEmail.setErrorEnabled(false);
            if (matKhau.equals("admin")) {
                tilPass.setError("");
                tilEmail.setErrorEnabled(false);
                return true;
            } else {
                tilPass.setError("Mật khẩu admin khó hơn cơ!");
                tilEmail.setErrorEnabled(true);
            }
        } else {
            tilEmail.setError("Email admin không như này đâu!");
            tilEmail.setErrorEnabled(true);
            tilPass.setError("Mật khẩu admin khó hơn cơ!");
            tilEmail.setErrorEnabled(true);
        }
        return false;
    }
//    private boolean checkLoginNV(String email, String matKhau) {
//        NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
//        NhanVien nhanVien;
//        ArrayList<NhanVien> listNV = nhanVienDAO.selectNhanVien(null,
//                "email=?", new String[]{email}, null);
//        if (listNV != null) {
//            if (listNV.size() == 0) {
//                tilEmail.setError("Email không tồn tại!");
//                tilEmail.setErrorEnabled(true);
//            } else {
//                tilEmail.setError("");
//                tilEmail.setErrorEnabled(false);
//                nhanVien = listNV.get(0);
//                if (matKhau.equals(nhanVien.getMatKhau())) {
//                    tilPass.setError("");
//                    tilEmail.setErrorEnabled(false);
//                    SharedPreferences pref = getSharedPreferences("Who_Login", MODE_PRIVATE);
//                    if (pref != null) {
//                        SharedPreferences.Editor editor = pref.edit();
//                        editor.putString("role", "nv");
//                        editor.putString("who", nhanVien.getMaNV());
//                        editor.putString("isLogin", "true");
//                        Log.d(TAG, "checkFirstTime: isLogin " + pref.getString("isLogin", ""));
//                        editor.apply();
//                    }
//                    return true;
//                } else {
//                    tilPass.setError("Mật khẩu sai!");
//                    tilEmail.setErrorEnabled(true);
//                }
//            }
//        }
//        return false;
//    }
private boolean checkLoginKH(String email, String matKhau) {
    KhachHangDAO khachHangDAO = new KhachHangDAO(context);
    KhachHang khachHang;
    ArrayList<KhachHang> listKH = khachHangDAO.selectKhachHang(null,
            "email=?", new String[]{email}, null);
    if (listKH != null) {
        if (listKH.size() == 0) {
            tilEmail.setError("Email không tồn tại!");
            tilEmail.setErrorEnabled(true);
        } else {
            tilEmail.setError("");
            tilEmail.setErrorEnabled(false);
            khachHang = listKH.get(0);
            if (matKhau.equals(khachHang.getMatKhau())) {
                tilPass.setError("");
                tilEmail.setErrorEnabled(false);
                SharedPreferences pref = getSharedPreferences("Who_Login", MODE_PRIVATE);
                if (pref != null) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("role", "kh");
                    editor.putString("who", khachHang.getMaKH());
                    editor.putString("isLogin", "true");
                    Log.d(TAG, "checkFirstTime: isLogin " + pref.getString("isLogin", ""));
                    editor.apply();
                }
                return true;
            } else {
                tilPass.setError("Mật khẩu sai!");
                tilEmail.setErrorEnabled(true);
            }
        }
    }
    return false;
}
    private void getDataIntent() {
        Log.d(TAG, "getDataIntent: true");
        Intent intent = getIntent();
        if (intent != null) {
            Log.d(TAG, "getDataIntent: intent != null");
            roleUser = intent.getStringExtra("role");
        }
    }

    private void goToSignUp() {
        gotoSignUpAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SignupActivity.class);
                Log.d(TAG, "goToSignUp: roleUser: " + roleUser);
                intent.putExtra("role", roleUser);
                startActivity(intent);
                finish();
            }
        });
    }
}