
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nhom1.R;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    ViewFlipper viewFlipper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_fragment, container, false);
        viewFlipper = view.findViewById(R.id.viewFlipper);
        ViewFlipper();


        return view;
    }

    private void ViewFlipper() {
        List<String> banner = new ArrayList<>();
        banner.add("https://intphcm.com/data/upload/banner-thoi-trang-nam.jpg");
        banner.add("https://intphcm.com/data/upload/dung-luong-banner-thoi-trang.jpg");
        banner.add("https://tmluxury.vn/wp-content/uploads/ao-so-mi-nam-dep-tm-luxury.jpg");
        for (int i = 0; i < banner.size(); i++) {
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
