package com.example.draw;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private  PaintView paintView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

        if(savedInstanceState!=null) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.eraser:
                paintView.changePaintColor(Color.WHITE);
            case R.id.normal:
                paintView.normal();
                return true;
            case R.id.emboss:
                paintView.emboss();
                return true;
            case R.id.blur:
                paintView.blur();
                return true;
            case R.id.clear:
                paintView.clear();
                return true;
            case R.id.save:
                Log.e("save", Environment.getDataDirectory().getPath());
                saveImage();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        saveImageForReturn();
        outState.putString("str", "use");
    }

    public void saveImage() {
        try {
            String filename = Environment.getExternalStorageDirectory().toString();

            File f = new File(filename ,"Draw"+new Date().toLocaleString()+".png");
            f.createNewFile();
            System.out.println("file created " + f.toString());
            FileOutputStream out = new FileOutputStream(f);
            Bitmap bitmap = paintView.getBitmap();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveImageForReturn() {
    //回到mainActivity後，直接抓這張圖出來繼續畫
        try {
            String filename = Environment.getExternalStorageDirectory().toString();

            File f = new File(filename ,"myImage.png");
            f.createNewFile();
            System.out.println("file created " + f.toString());
            FileOutputStream out = new FileOutputStream(f);
            Bitmap bitmap = paintView.getBitmap();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeColor(View view) {
        switch (view.getId()){
            case R.id.black:
                paintView.changePaintColor(Color.BLACK);
                break;
            case R.id.blue:
                paintView.changePaintColor(Color.BLUE);
                break;
            case R.id.red:
                paintView.changePaintColor(Color.RED);
                break;
            case R.id.yello:
                paintView.changePaintColor(Color.YELLOW);
                break;
            case R.id.green:
                paintView.changePaintColor(Color.GREEN);
                break;
        }
    }
}
