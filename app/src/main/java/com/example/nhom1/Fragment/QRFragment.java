package com.example.nhom1.Fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nhom1.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class QRFragment extends Fragment {
    private String data;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         ImageView imageView = view.findViewById(R.id.qrCodeImageView);

        // Tạo đối tượng QRCodeWriter
        QRCodeWriter writer = new QRCodeWriter();

        // Tạo thông tin cho mã QR code
        String contents = "[https://example.com](https://example.com)";

        // Tạo mã QR code
        BitMatrix matrix = null;
        try {
            matrix = writer.encode(contents, BarcodeFormat.QR_CODE, 900, 900);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

        // Chuyển mã QR code thành hình ảnh
        Bitmap bitmap = Bitmap.createBitmap(matrix.getWidth(), matrix.getHeight(), Bitmap.Config.ARGB_8888);
        for (int x = 0; x < matrix.getWidth(); x++) {
            for (int y = 0; y < matrix.getHeight(); y++) {
                bitmap.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }

        // Hiển thị hình ảnh mã QR code
        imageView.setImageBitmap(bitmap);
    }

    private Bitmap generateQRCode(String paymentInfo) {
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            return barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, 400, 400);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.qr_fragment,container,false);
    }
}
