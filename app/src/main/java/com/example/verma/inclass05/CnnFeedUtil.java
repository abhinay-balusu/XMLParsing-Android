package com.example.verma.inclass05;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by verma on 9/26/2016.
 */
public class CnnFeedUtil {

    static public class CnnFeedPullParser{


        static ArrayList<CnnFeed> parseCnnFeed(InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            ArrayList<CnnFeed> cnnFeedList=new ArrayList<CnnFeed>();
            CnnFeed cnnFeed=null;
            int event = parser.getEventType();
            while(event!= XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_TAG:

                        if(parser.getName().equals("item")){
                            cnnFeed = new CnnFeed();
                        }else if(parser.getName().equals("title")){
                            if(cnnFeed!=null){
                                cnnFeed.setTitle(parser.nextText().trim());
                            }
                        }else if(parser.getName().equals("description")){
                            if(cnnFeed!=null){
                                cnnFeed.setDescription(parser.nextText().trim());
                            }

                        }else if(parser.getName().equals("pubDate")){
                            if(cnnFeed!=null){
                                cnnFeed.setPublishDate(parser.nextText().trim());
                            }

                        }else if(parser.getName().equals("media:content")){
                            if(cnnFeed!=null){

                                if(parser.getAttributeValue(null,"height").equals("300"))
                                {
                                    cnnFeed.setImageURL(parser.getAttributeValue(null,"url"));
                                }
                                if(parser.getAttributeValue(null,"height").equals("619"))
                                {
                                    cnnFeed.setLargeImageUrl(parser.getAttributeValue(null,"url"));
                                }

                            }

                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            cnnFeedList.add(cnnFeed);
                            cnnFeed=null;
                        }
                        break;
                    default:
                        break;
                }
                event=parser.next();
            }
            return cnnFeedList;
        }
    }
}
