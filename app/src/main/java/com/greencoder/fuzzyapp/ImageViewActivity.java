package com.greencoder.fuzzyapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ImageViewActivity extends Activity {

    public static String EXTRA_IMAGE_URL="image_url";

    @Bind(R.id.imageView)ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_view);

        ButterKnife.bind(this);

        String imageUrl=getIntent().getStringExtra(EXTRA_IMAGE_URL);

        Picasso.with(this).load(imageUrl).into(imageView);

    }


}
