package com.example.verma.inclass05;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by verma on 9/26/2016.
 */
public class LoadImage extends AsyncTask<String , Void, Bitmap> {

GetCNNFeedAsyncTask.GetContext activity;
    ImageView imgV;
    public LoadImage(GetCNNFeedAsyncTask.GetContext activity, ImageView imgV) {
        this.activity=activity;
        this.imgV = imgV;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imgV.setImageBitmap(bitmap);
        //activity.setupImage(bitmap);
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            Bitmap image = BitmapFactory.decodeStream(con.getInputStream());
            return image;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}

