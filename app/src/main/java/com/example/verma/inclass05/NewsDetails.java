package com.example.verma.inclass05;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsDetails extends AppCompatActivity implements GetCNNFeedAsyncTask.GetContext{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        CnnFeed cnn = (CnnFeed) getIntent().getExtras().get(MainActivity.STORY_KEY);

        if(cnn.getTitle() != null)
        {
            TextView titleTV = (TextView)findViewById(R.id.storyTitle);
            titleTV.setText(cnn.getTitle());
        }
        if(cnn.getPublishDate()!=null)
        {
            TextView pubdateTV = (TextView)findViewById(R.id.storyPubDate);

            Date date = new Date(cnn.getPublishDate());
            SimpleDateFormat formattedDate = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");

            String newDate = formattedDate.format(date);
            //assert pubdateTV != null;*/

            //android.text.format.DateFormat df = new android.text.format.DateFormat();
            //df.format("yyyy-MM-dd hh:mm:ss", cnn.getPublishDate());

            pubdateTV.setText(newDate.toString());

        }
        if(cnn.getDescription()!=null) {

            TextView desTV = (TextView)findViewById(R.id.storyDescription);
            desTV.setText(cnn.getDescription());
        }

        if(cnn.getImageURL()!=null) {
            ImageView iv = (ImageView)findViewById(R.id.storyImageView);
            new LoadImage(this,iv).execute(cnn.getLargeImageUrl());
        }




    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setupData(ArrayList<CnnFeed> cnnFeedArrayList) {

    }
}
