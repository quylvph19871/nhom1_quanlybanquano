package com.example.nhom1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nhom1.Fragment.DonHangFragment;
import com.example.nhom1.Fragment.GioHangFragment;
import com.example.nhom1.Fragment.HomeFragment;
import com.example.nhom1.Fragment.SanPhamFragment;
import com.example.nhom1.Fragment.TaiKhoanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.Trangchu);
        load(new HomeFragment());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        if (item.getItemId() == R.id.Trangchu ) {
            fragment = new HomeFragment();
            load(fragment);
            return true;
        }
        else if (item.getItemId() == R.id.sanPham) {
            fragment = new SanPhamFragment();
            load(fragment);
            return true;
        }else if (item.getItemId() == R.id.gioHang) {
            fragment = new GioHangFragment();
            load(fragment);
            return true;
        } else if (item.getItemId() == R.id.donhang) {
            fragment = new DonHangFragment();
            load(fragment);
            return true;
        } else if (item.getItemId() == R.id.taikhoan) {
            fragment = new TaiKhoanFragment();
            load(fragment);
            return true;
        } else {
            return false;
        }
    }

    public void load(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}