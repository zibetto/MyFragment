package com.melec.myfragment.main;


import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity{

    public static final String INDEX = "INDEX";
    public static String[] mDetailsActivity;


    ImageView image;



    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mDetailsActivity = getResources().getStringArray(R.array.Details);

        /** Need to check if Activity has been switched to landscape mode
          If yes, finished and go back to the start Activity */

       /**
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        */

        /** hide the action bar */



        setContentView(R.layout.details_activity);

        Intent mIntent = getIntent();



        int pos = mIntent.getIntExtra(INDEX,0);

            if(pos != -1) {

                TextView view = (TextView) findViewById(R.id.detailsViewActivity);
                view.setText(mDetailsActivity[pos]);

                image = (ImageView) findViewById(R.id.imageViewActivity);
                image.setImageResource(Images.mImage[pos]);
          }
    }





}