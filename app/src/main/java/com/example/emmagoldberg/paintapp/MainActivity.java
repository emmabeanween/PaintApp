package com.example.emmagoldberg.paintapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emmagoldberg.paintapp.customview.CustomView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Button mClearButton;
    private Button mEraseButton;
    private Button mPenButton;
    private Button mPencilButton;
    private Button mPaintBrushButton;
    private Button mBlueButton;
    private Button mGreenButton;
    private Button mPurpleButton;
    private Button mPinkButton;
    private Button mRedButton;
    private Button mYellowButton;
    private Button mOrangeButton;
    private Button mBrownButton;
    private Button mBlackButton;
    private Button mGreyButton;
    private Button mDownloadButton;
    private TextView mShowColorText;
    private TextView mShowUtilityText;
    private boolean canDownload;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1){
            // see if user allowed it

            for (int i = 0; i < grantResults.length; i++){

                if (grantResults[i]!= PackageManager.PERMISSION_GRANTED){

                    // if the permission has not been granted
                    canDownload = false;
                    Log.i("msg", "user didn't grant");

                }

                else {

                     canDownload = true;
                     Log.i("msg", "user did grant");
                }

            }


        }

         if (canDownload == true){

             final CustomView mView = (CustomView)findViewById(R.id.my_custom_view);
             mView.downloadCanvas();

         }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final CustomView mView = (CustomView)findViewById(R.id.my_custom_view);
        mView.setWillNotDraw(false);


        mShowColorText = (TextView)findViewById(R.id.current_color_text_view);
        mShowUtilityText = (TextView)findViewById(R.id.current_tool_text_view);



        mBlueButton = (Button)findViewById(R.id.blue_button);
        mBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mView.setColor(ContextCompat.getColor(getApplicationContext(), R.color.CornflowerBlue));
                mShowColorText.setText("blue");


            }
        });
        mGreenButton = (Button)findViewById(R.id.green_button);
        mGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setColor(ContextCompat.getColor(getApplicationContext(), R.color.SeaGreen));
                mShowColorText.setText("green");
            }
        });

        mPurpleButton= (Button)findViewById(R.id.purple_button);
        mPurpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mView.setColor(ContextCompat.getColor(getApplicationContext(), R.color.Lavender));
                mShowColorText.setText("purple");
            }
        });

        mPinkButton = (Button)findViewById(R.id.pink_button);
        mPinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setColor(ContextCompat.getColor(getApplicationContext(), R.color.LightPink));
                mShowColorText.setText("pink");

            }
        });

        mRedButton = (Button)findViewById(R.id.red_button);
        mRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setColor(ContextCompat.getColor(getApplicationContext(), R.color.IndianRed));
                mShowColorText.setText("red");
            }
        });

        mYellowButton = (Button)findViewById(R.id.yellow_button);
        mYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setColor(ContextCompat.getColor(getApplicationContext(), R.color.LightGoldenrodYellow));
                mShowColorText.setText("yellow");
            }
        });



        mOrangeButton  = (Button)findViewById(R.id.orange_button);
        mOrangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setColor(ContextCompat.getColor(getApplicationContext(), R.color.DarkOrange));
                mShowColorText.setText("orange");
            }
        });

        mBrownButton = (Button)findViewById(R.id.brown_button);
        mBrownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setColor(ContextCompat.getColor(getApplicationContext(), R.color.SaddleBrown));
                mShowColorText.setText("brown");
            }
        });



        mBlackButton = (Button)findViewById(R.id.black_button);
        mBlackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setColor(ContextCompat.getColor(getApplicationContext(), R.color.Black));
                mShowColorText.setText("black");
            }
        });

        mGreyButton = (Button)findViewById(R.id.gray_button);
        mGreyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setColor(ContextCompat.getColor(getApplicationContext(), R.color.LightGrey));
                mShowColorText.setText("gray");
            }
        });

        mPenButton = (Button)findViewById(R.id.pen_button);
        mPenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mView.setStrokeWidth(5f);
                mShowUtilityText.setText("pen");
            }
        });

        mPencilButton = (Button)findViewById(R.id.pencil_button);
        mPencilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setStrokeWidth(10f);
                mShowUtilityText.setText("pencil");

            }
        });

        mPaintBrushButton = (Button)findViewById(R.id.paint_button);
        mPaintBrushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.setStrokeWidth(20f);
                mShowUtilityText.setText("paint");
            }
        });

        mClearButton = (Button)findViewById(R.id.clear_button);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mView.clearCanvas();
            }
        });



        mEraseButton = (Button)findViewById(R.id.erase_button);
        mEraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mView.setErase();

            }
        });


        // result of requesting permission



        mDownloadButton = (Button)findViewById(R.id.download_button);
        mDownloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                if ( ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.
                        WRITE_EXTERNAL_STORAGE)
                        != PackageManager
                        .PERMISSION_GRANTED){
                    // request permission

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);



                }

                else {

                    mView.downloadCanvas();
                }




            }
        });












    }
}
