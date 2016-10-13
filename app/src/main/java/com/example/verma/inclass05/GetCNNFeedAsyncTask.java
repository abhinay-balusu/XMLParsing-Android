package com.example.verma.inclass05;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by verma on 9/26/2016.
 */


public class GetCNNFeedAsyncTask extends AsyncTask<String, Void, ArrayList<CnnFeed>> {


    GetContext activity;
    ProgressDialog progressDialog1;

    public GetCNNFeedAsyncTask(GetContext activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<CnnFeed> doInBackground(String... params) {
        InputStream in=null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status= con.getResponseCode();
            if(status==HttpURLConnection.HTTP_OK){
                in = con.getInputStream();
                return CnnFeedUtil.CnnFeedPullParser.parseCnnFeed(in);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        }catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        progressDialog1 = new ProgressDialog(activity.getContext());
        progressDialog1.setMessage("Loading News...");
        progressDialog1.setMax(100);
        progressDialog1.setCancelable(false);
        progressDialog1.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog1.show();
    }

    @Override
    protected void onPostExecute(ArrayList<CnnFeed> cnnFeeds) {
        super.onPostExecute(cnnFeeds);
        activity.setupData(cnnFeeds);
        //Log.d("arraylist",cnnFeeds.toString());
        progressDialog1.dismiss();
    }

    public static interface GetContext {
        public Context getContext();
        public void setupData(ArrayList<CnnFeed> cnnFeedArrayList);

        //void setupImage(Bitmap bitmap);
    }
}