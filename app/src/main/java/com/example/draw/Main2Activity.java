package com.example.draw;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//For Auto draw
public class Main2Activity extends AppCompatActivity {

    private  PaintView paintView;
    private TextView resultLabel, identifyResult;
    private ImageView image1, image2, image3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resultLabel = findViewById(R.id.resultLabel);
        identifyResult = findViewById(R.id.identifyResult);
        paintView = (PaintView) findViewById(R.id.paintView);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //開始做辨識的工作
                ArrayList<ArrayList<Float>> graphData = paintView.GetXYTimePoint();
                for(int i = 0; i < graphData.size(); i++) {
                    ArrayList<Float> temp = graphData.get(i);
                    for(int j = 0; j < temp.size(); j++) {
                        Log.d("Graph Data", i + "." + j + "  " + temp.get(j).toString());
                    }
                }
                //辨識完，圖片跟結果要顯示
                image1.setVisibility(View.VISIBLE);
                image2.setVisibility(View.VISIBLE);
                image3.setVisibility(View.VISIBLE);
                resultLabel.setVisibility(View.VISIBLE);
                identifyResult.setVisibility(View.VISIBLE);
            }
        });

        //在辨識之前先不用出現:
        image1.setVisibility(View.INVISIBLE);
        image2.setVisibility(View.INVISIBLE);
        image3.setVisibility(View.INVISIBLE);
        resultLabel.setVisibility(View.INVISIBLE);
        identifyResult.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                paintView.clear();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //當圖片被按下，要把此圖傳回MaubActivity
    public void getImage(View view) {
        switch (view.getId()){
            case R.id.image1:
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.image2:
                Snackbar.make(view, "Image 2 selected", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.image3:
                Snackbar.make(view, "Image 3 selected", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }
}
