package com.damso.bmw_wp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    GridView gridview;
    int i;
    int count=0;

    int[] pics={R.drawable.ci1,R.drawable.ci2,R.drawable.ci3,R.drawable.ci4,R.drawable.ci5,R.drawable.ci6,
            R.drawable.ci7,R.drawable.ci8,R.drawable.ci9,R.drawable.ci10,R.drawable.ci11,R.drawable.ci12,R.drawable.ci13,
            R.drawable.ci14,R.drawable.ci15,R.drawable.ci16,R.drawable.ci17,R.drawable.ci18,R.drawable.ci19,R.drawable.ci20,
            R.drawable.ci21,R.drawable.ci22,R.drawable.ci23,R.drawable.ci24,R.drawable.ci25,R.drawable.ci26,R.drawable.ci27,
            R.drawable.ci28,R.drawable.ci29,R.drawable.ci30,R.drawable.ci31,R.drawable.ci32,R.drawable.ci33,R.drawable.ci34,
            R.drawable.ci35,R.drawable.ci36};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //__________________ADS___________________
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        mInterstitialAd = new InterstitialAd(this);
        //my REAL AD ca-app-pub-3940256099942544/1033173712
        mInterstitialAd.setAdUnitId("ca-app-pub-3518572148386753/8590931885");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                //Intent intent = new Intent( firstpage.this, firstpage.class );
                //startActivity ( intent );
                //finish();
                //Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        });
        //Initialisation :
        gridview = findViewById(R.id.grid);

        //Loading Images :
        CustomAdapter customAdapter = new CustomAdapter();
        gridview.setAdapter(customAdapter);

        //When Click On Image :
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(count%3==0) {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                    else{
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        count++;
                        Intent intent = new Intent(getApplicationContext(), img_extra.class);
                        intent.putExtra("image_position", i);
                        startActivity(intent);
                    }
                }
                else {   //PUT EXTRA IMAGE
                    count++;
                    Intent intent = new Intent(getApplicationContext(), img_extra.class);
                    intent.putExtra("image_position", i);
                    startActivity(intent);
                }






            }
        });
    }


    //__________________________________START On Back Press BAck Button___________________________
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void closebtn(View view) {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    //-------------------------------------Finished ONBACKPRESS BUTTON----------------------




    //___________________________________START THE CLASS OF ADAPTER_______________________________
    private class CustomAdapter  extends BaseAdapter {


        @Override
        public int getCount() {
            return pics.length ;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            View view1 = getLayoutInflater().inflate(R.layout.row_item,null);
            ImageView image = view1.findViewById(R.id.img);
            image.setImageResource(pics[i]);

            return view1;
        }


    }
}
