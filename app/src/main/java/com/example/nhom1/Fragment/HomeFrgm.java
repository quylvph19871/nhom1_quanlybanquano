package com.example.nhom1.Fragment;

import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom1.Adapter.AdapterHome;
import com.example.nhom1.DAOModel.DAOLuuHD;
import com.example.nhom1.DAOModel.DAOSanPham;
import com.example.nhom1.DAOModel.DAOUser;
import com.example.nhom1.Model.SanPham;
import com.example.nhom1.Model.TheLoai;
import com.example.nhom1.Model.User;
import com.example.nhom1.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFrgm extends Fragment {
    ViewFlipper viewFlipper;


    RecyclerView recycler_SPBanChay;
    private AdapterHome adapterHome;
    private ArrayList<SanPham> listSpTopOut = new ArrayList<>();
    DAOLuuHD daoLuuHD;
    DAOSanPham daoSanPham;
    LinearLayout layoutParent;
    DAOUser daoUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_frgm, container, false);
        viewFlipper=view.findViewById(R.id.viewFlipper);
        ImageView imgNotifi = view.findViewById(R.id.imgNotifi);
        layoutParent = view.findViewById(R.id.layoutParent);
        recycler_SPBanChay = view.findViewById(R.id.recycler_SPBanChay);
        TextView txtHello = view.findViewById(R.id.txtHello);
        daoLuuHD = new DAOLuuHD(getContext());
        daoSanPham = new DAOSanPham(getContext());
        daoUser = new DAOUser(getContext());
        ViewFlipper();
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", getActivity().MODE_PRIVATE);
        int maUserNow = pref.getInt("MA", 0);
        User user = daoUser.getUser(maUserNow);
//        String fullName = user.getFullName();
//
//        txtHello.setText("Xin chào, " + fullName + "!");

        ArrayList<SanPham> listSanPham = daoSanPham.getAllProduct(0);
        ArrayList<Integer> listMaSPTop = daoLuuHD.getTopSP();
        for (int i = 0; i < listMaSPTop.size(); i++) {
            for (int j = 0; j < listSanPham.size(); j++) {
                if (listMaSPTop.get(i) == listSanPham.get(j).getId()){
                    listSpTopOut.add(listSanPham.get(j));
                }
            }
        }

        ArrayList<TheLoai> listLoaiSP = daoSanPham.getDSLSP();
        for (int i = 0; i < listLoaiSP.size(); i++) {
            ArrayList<SanPham> listSP = daoSanPham.getSPofTL(listLoaiSP.get(i).getMaLoai());
            if (listSP.size() != 0){
                View addLayout = inflater.inflate(R.layout.list_san_pham, null);
                TextView tittle = addLayout.findViewById(R.id.txtSPHomeTittle);
                tittle.setText(listLoaiSP.get(i).getTenLoai());
                RecyclerView recyclerViewAdd = addLayout.findViewById(R.id.recycler_SPTheoLoai);
                AdapterHome adapterHome1 = new AdapterHome(listSP, getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewAdd.setLayoutManager(linearLayoutManager);
                recyclerViewAdd.setAdapter(adapterHome1);
                layoutParent.addView(addLayout);
            }
        }

        adapterHome = new AdapterHome(listSpTopOut ,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_SPBanChay.setLayoutManager(linearLayoutManager);
        recycler_SPBanChay.setAdapter(adapterHome);

//        Notifi
//        imgNotifi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog = new Dialog(getActivity());
//                dialog.setContentView(R.layout.dialog_notifi);
//                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                EditText btnDongNotifi = dialog.findViewById(R.id.btnDongNotifi);
//                btnDongNotifi.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();
//
//            }
//        });

        return view;
    }
    private void ViewFlipper(){
        List<String> banner = new ArrayList<>();
        banner.add("https://intphcm.com/data/upload/banner-thoi-trang-nam.jpg");
        banner.add("https://intphcm.com/data/upload/dung-luong-banner-thoi-trang.jpg");
        banner.add("https://tmluxury.vn/wp-content/uploads/ao-so-mi-nam-dep-tm-luxury.jpg");
        for (int i = 0; i < banner.size(); i++){
            ImageView imageView = new ImageView(getContext());
            Glide.with(getContext()).load(banner.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

}