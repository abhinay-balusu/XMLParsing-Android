package com.example.verma.inclass05;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by verma on 9/26/2016.
 */
public class CnnFeed implements Parcelable,Comparable<CnnFeed>{

    String title;
    String publishDate;
    String description;
    String imageURL;
    String largeImageUrl;

    /*public CnnFeed(String title, String publishDate, String description, String imageURL) {
        this.title = title;
        this.publishDate = publishDate;
        this.description = description;
        this.imageURL = imageURL;
    }*/

    protected CnnFeed(Parcel in) {
        title = in.readString();
        publishDate = in.readString();
        description = in.readString();
        imageURL = in.readString();
        largeImageUrl = in.readString();
    }

    public static final Creator<CnnFeed> CREATOR = new Creator<CnnFeed>() {
        @Override
        public CnnFeed createFromParcel(Parcel in) {
            return new CnnFeed(in);
        }

        @Override
        public CnnFeed[] newArray(int size) {
            return new CnnFeed[size];
        }
    };

    public CnnFeed() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(publishDate);
        dest.writeString(description);
        dest.writeString(imageURL);
        dest.writeString(largeImageUrl);
    }


    @Override
    public int compareTo(CnnFeed another) {

        Date d1 = new Date(this.publishDate);
        Date d2 = new Date(another.publishDate);

        if(d1.compareTo(d2) >0)
        {
            return 1;
        }
        else if(d1.compareTo(d2) <0)
        {
            return -1;
        }
        return 0;
    }
}
