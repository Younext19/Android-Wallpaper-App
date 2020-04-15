package com.damso.bmw_wp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class img_extra extends AppCompatActivity {

    ImageView imgunderv5;
    int imageselected;
    private ProgressDialog waitDialog ;
    private AlertDialog.Builder donedialoge;
    int[] fi={R.drawable.fi1,R.drawable.fi2,R.drawable.fi3,R.drawable.fi4,R.drawable.fi5,R.drawable.fi6,
            R.drawable.fi7,R.drawable.fi8,R.drawable.fi9,R.drawable.fi10,R.drawable.fi11,R.drawable.fi12,R.drawable.fi13,
            R.drawable.fi14,R.drawable.fi15,R.drawable.fi16,R.drawable.fi17,R.drawable.fi18,R.drawable.fi19,R.drawable.fi20,
            R.drawable.fi21,R.drawable.fi22,R.drawable.fi23,R.drawable.fi24,R.drawable.fi25,R.drawable.fi26,R.drawable.fi27,
            R.drawable.fi28,R.drawable.fi29,R.drawable.fi30,R.drawable.fi31,R.drawable.fi32,R.drawable.fi33,R.drawable.fi34,
            R.drawable.fi35,R.drawable.fi36};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_extra);


        imgunderv5 = findViewById(R.id.imgunderv5);
        //initializing LoadingDialog
        waitDialog = new ProgressDialog(this);
        waitDialog.setTitle("Loading ...");
        //initializing DoneDialog
        donedialoge = new AlertDialog.Builder(this);
        donedialoge.setTitle("Done");
        donedialoge.setMessage("The operation ended successfully !");
        //Get Selected Image in hight quality
        Intent intent = getIntent();
        int receivedposition = intent.getIntExtra("image_position",0);
        //Store The image in this Value to work with it later
        imageselected = fi[receivedposition];
        //show the image full Screen
        imgunderv5.setImageResource(imageselected);
    }
    //THE Button to close current page and go back to home :
    public void backbtn(View view) {
        finish();
    }

    //Button To set Image as Home wallpaper  :
    public void homewallpaper(View view) {

        waitDialog.show();
        Thread mThread = new Thread() {
            @Override
            public void run() {
                setWallpaper(imageselected);
                waitDialog.cancel();
                img_extra.this.runOnUiThread(new Runnable() {
                    public void run() {
                        done();

                    }
                });

            }
        };
        mThread.start();

    }

    //___________________________________SET WALLPAPER THINGS_______________________________
    private  void setWallpaper(int image)
    {

        Bitmap bmap2 = BitmapFactory.decodeResource(getResources(),image);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        Bitmap bitmap = Bitmap.createScaledBitmap(bmap2, width, height, true);
        WallpaperManager mananger = WallpaperManager.getInstance(getApplicationContext());



        try{


            mananger.setBitmap(bitmap);




        } catch (IOException e){

        }



    }
    //----------------------------FINISHED SETWALLPAPER THINGS---------------------


    //__________________________After Complite Function Succesfully DoneDialoge :__________________
    public void done()
    {

        final AlertDialog.Builder donedialoge = new AlertDialog.Builder(this);
        donedialoge.setTitle("Done");
        donedialoge.setMessage("The operation ended successfully !");

        donedialoge.setPositiveButton("More Wallpapers", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();

            }

        });


        donedialoge.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }

        });

        donedialoge.show();
    }


}
