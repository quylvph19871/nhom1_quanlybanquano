package com.example.nhom1.Fragment;


import android.annotation.SuppressLint;
import android.app.Dialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.nhom1.Adapter.AdapterSanPham;
import com.example.nhom1.DAOModel.DAOSanPham;
import com.example.nhom1.Model.SanPham;
import com.example.nhom1.Model.TheLoai;
import com.example.nhom1.R;


import java.util.ArrayList;

public class SuaSPFrgm extends Fragment {

    EditText edUpdateTenSP, edUpdateGiaBan, edUpdateMoTa, btnUpdate,edUpdateAnh,edUpdateSoLuongSP;
    AutoCompleteTextView edtLoaiSP;
    SanPham sanPham;
    DAOSanPham daoSanPham;
    String strTenSP;
    String strMota;
    String strLoaiSP;
    String strAnhSP;
    String strSoLuongSP;
    double strGiaban;
    ArrayList<SanPham> arrayList;
    AdapterSanPham adapter = null;
    int maLoai, index;
    boolean checkTL;

    public SuaSPFrgm(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sua_s_p_frgm, container, false);
        // ánh xạ
        ImageView btnBackSuaSP = view.findViewById(R.id.btnBackSuaSP);
        EditText btnSuaSPHuy = view.findViewById(R.id.btnSuaSPHuy);
        EditText btnSuaSPXN = view.findViewById(R.id.btnUpdateSp);
        edUpdateTenSP = view.findViewById(R.id.update_tenSP);
        edUpdateGiaBan = view.findViewById(R.id.update_giaBan);
        edUpdateMoTa = view.findViewById(R.id.update_moTa);
        edUpdateAnh=view.findViewById(R.id.update_anh);
        edtLoaiSP = view.findViewById(R.id.edUpdateLSP);
        edUpdateSoLuongSP=view.findViewById(R.id.update_soLuongSP);
        btnUpdate = view.findViewById(R.id.btnUpdateSp);
        daoSanPham = new DAOSanPham(getContext());
        arrayList = new ArrayList<>();
        adapter = new AdapterSanPham(getActivity(), arrayList);

//        Settext cho Edittext
        edUpdateGiaBan.setText(sanPham.getPrice() + "");
        edUpdateTenSP.setText(sanPham.getTenSanPham());
        maLoai = sanPham.getMaLoai();
        String tenLoai = "";
        ArrayList<TheLoai> listTL = daoSanPham.getDSLSP();
        for (int i = 0; i < listTL.size(); i++) {
            if (listTL.get(i).getMaLoai() == maLoai){
                tenLoai = listTL.get(i).getTenLoai();
            }
        }
        edtLoaiSP.setText(tenLoai);
        edUpdateSoLuongSP.setText(sanPham.getSoLuongSP()+"");
        edUpdateMoTa.setText(sanPham.getMota());
        edUpdateAnh.setText(sanPham.getImage());
        // xử lý sự kiện thêm ảnh



        // set sự kiện
        btnBackSuaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ChiTietSPSuaFrgm(sanPham));
            }
        });

        btnSuaSPHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUpdateGiaBan.setText(sanPham.getPrice() + "");
                edUpdateTenSP.setText(sanPham.getTenSanPham());
                maLoai = sanPham.getMaLoai();
                String tenLoai = "";
                ArrayList<TheLoai> listTL = daoSanPham.getDSLSP();
                for (int i = 0; i < listTL.size(); i++) {
                    if (listTL.get(i).getMaLoai() == maLoai){
                        tenLoai = listTL.get(i).getTenLoai();
                    }
                }
                edtLoaiSP.setText(tenLoai);
                edUpdateMoTa.setText(sanPham.getMota());
                edUpdateSoLuongSP.setText(sanPham.getSoLuongSP()+"");
                edUpdateAnh.setText(sanPham.getImage());
                Toast.makeText(getContext(), "Hủy!", Toast.LENGTH_SHORT).show();
                loadFragment(new ChiTietSPSuaFrgm(sanPham));
            }
        });
//        Set Data cho spnLoaiSP
        ArrayList<TheLoai> listTheLoai = daoSanPham.getDSLSP();
        ArrayList<String> listTenTL = new ArrayList<>();
        ArrayList<Integer> listMaTL = new ArrayList<>();
        int listTheLoaiSize = listTheLoai.size();
        if (listTheLoaiSize != 0) {
            for (int i = 0; i < listTheLoaiSize; i++) {
                TheLoai theLoaiModel = listTheLoai.get(i);
                listTenTL.add(theLoaiModel.getTenLoai());
                listMaTL.add(theLoaiModel.getMaLoai());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(), android.R.layout.select_dialog_item, listTenTL);

        edtLoaiSP.setThreshold(1);
        edtLoaiSP.setAdapter(adapter);

        btnSuaSPXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strTenSP = edUpdateTenSP.getText().toString();
                strGiaban = Double.parseDouble(edUpdateGiaBan.getText().toString());
                strMota = edUpdateMoTa.getText().toString();
                strLoaiSP = edtLoaiSP.getText().toString();
                strAnhSP=edUpdateAnh.getText().toString();
                strSoLuongSP=edUpdateSoLuongSP.getText().toString()+"";

                checkTL = false;
                index = 0;
                for (int i = 0; i < listTheLoaiSize; i++) {
                    String mTenLoai = listTenTL.get(i);
                    if (mTenLoai.equals(strLoaiSP)){
                        index = i;
                        checkTL = true;
                        break;
                    }
                }

                if (checkTL){
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dialog_confirm);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    TextView dialog_confirm_content = dialog.findViewById(R.id.dialog_confirm_content);
                    EditText btnDialogHuy = dialog.findViewById(R.id.btnDialogHuy);
                    EditText btnUpdate = dialog.findViewById(R.id.btnDialogXN);

                    dialog_confirm_content.setText("Bạn chắc chắn muốn sửa thông tin sản phẩm đã chọn!");

                    btnDialogHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "Hủy!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    //Update
                    btnUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (checkEdt()) {
                                maLoai = listMaTL.get(index);
                                daoSanPham.updateSanPham(strAnhSP, strTenSP, strGiaban, maLoai, strMota, sanPham.getId(), Integer.parseInt(strSoLuongSP));
                                Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                                loadFragment(new FragmentProduct());
                                resetEdt();
                                dialog.dismiss();
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
                    dialog.show();
                }   else {
                    edtLoaiSP.setError("Loại sản phẩm không tồn tại!");
                    edtLoaiSP.setText("");
                }
            }
        });
        return view;
    }



    //replaceFragment
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //    Check Form
    private boolean checkEdt() {

        boolean checkAdd = true;
        if (strTenSP.isEmpty()) {
            edUpdateTenSP.setError("Vui lòng nhập!");
            edUpdateTenSP.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        if (edUpdateGiaBan.getText().toString().isEmpty()) {
            edUpdateGiaBan.setError("Vui lòng nhập!");
            edUpdateGiaBan.setHintTextColor(Color.RED);
            checkAdd = false;
        }
        if (strLoaiSP.isEmpty()) {
            edtLoaiSP.setError("Vui lòng nhập!");
            edtLoaiSP.setHintTextColor(Color.RED);
            checkAdd = false;
        }
        if (edUpdateSoLuongSP.getText().toString().isEmpty()) {
            edUpdateSoLuongSP.setError("Vui lòng nhập!");
            edUpdateSoLuongSP.setHintTextColor(Color.RED);
            checkAdd = false;
        }
        if (strMota.isEmpty()) {
            edUpdateMoTa.setError("Vui lòng nhập!");
            edUpdateMoTa.setHintTextColor(Color.RED);
            checkAdd = false;
        }
        if (strAnhSP.isEmpty()) {
            edUpdateAnh.setError("Vui lòng nhập!");
            edUpdateAnh.setHintTextColor(Color.RED);
            checkAdd = false;
        }
        return checkAdd;
    }

    //    Reset Edittext
    private void resetEdt() {
        edUpdateTenSP.setText("");
        edUpdateTenSP.setHintTextColor(Color.BLACK);
        edUpdateGiaBan.setText("");
        edUpdateGiaBan.setHintTextColor(Color.BLACK);
        edUpdateSoLuongSP.setText("");
        edUpdateSoLuongSP.setHintTextColor(Color.BLACK);
        edUpdateMoTa.setText("");
        edUpdateMoTa.setHintTextColor(Color.BLACK);
        edUpdateAnh.setText("");
        edUpdateAnh.setHintTextColor(Color.BLACK);
    }
}