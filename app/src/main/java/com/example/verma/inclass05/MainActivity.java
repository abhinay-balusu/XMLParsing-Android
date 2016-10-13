package com.example.verma.inclass05;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements GetCNNFeedAsyncTask.GetContext{

    Bitmap bitmap;
    LinearLayout layoutV;
    CnnFeed cnnFeed;
    public final static String STORY_KEY = "Story";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new GetCNNFeedAsyncTask(this).execute("http://rss.cnn.com/rss/cnn_tech.rss");

        layoutV = (LinearLayout)findViewById(R.id.layoutV);

    }

    @Override
    public Context getContext() {

        return this;
    }

    @Override
    public void setupData(final ArrayList<CnnFeed> cnnFeedArrayList) {

        Collections.sort(cnnFeedArrayList);
        for(int i = 0; i<cnnFeedArrayList.size(); i++){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(10, 0, 0, 0); //substitute parameters for left, top, right, bottom
            //textView.setLayoutParams(tvparams);

            final LinearLayout layoutH = new LinearLayout(this);
            layoutH.setOrientation(LinearLayout.HORIZONTAL);
            //layoutH.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
            layoutH.setLayoutParams(params);
            //layoutH.setPadding(2,2,2,2);
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(300,300));
            layoutH.setId(i+1);
            TextView textView = new TextView(this);

            textView.setLayoutParams(params);


            cnnFeed = cnnFeedArrayList.get(i);
            textView.setText(cnnFeed.getTitle());
            new LoadImage(this,imageView).execute(cnnFeed.getImageURL());

            //imageView.setImageBitmap(bitmap);

            layoutH.addView(imageView);
            layoutH.addView(textView);

            layoutH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,NewsDetails.class);
                    CnnFeed tempFeed = cnnFeedArrayList.get(layoutH.getId()-1);
                    intent.putExtra(STORY_KEY,tempFeed);
                    startActivity(intent);
                }
            });

            layoutV.addView(layoutH);

        }

    }

}
