package com.example.photolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;

import java.util.List;

public class GaleriaActivity extends AppCompatActivity {

    private ImageView imgFromStorage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria2);

        imgFromStorage = findViewById(R.id.imgFromStorage);

    }
}