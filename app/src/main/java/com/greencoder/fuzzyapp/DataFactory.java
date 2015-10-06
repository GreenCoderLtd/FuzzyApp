package com.greencoder.fuzzyapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.greencoder.fuzzyapp.com.greencoder.fuzzyapp.model.DataModel;
import com.squareup.picasso.Picasso;

/**
 * Created by newcomputer on 10/5/15.
 */
public class DataFactory {

    public static int TYPE_TEXT=0;
    public static int TYPE_IMAGE=1;

    public static Intent getIntentfromData(Context context, DataModel data)
    {
        if(data.getType().equals("text"))
        {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://fuzzproductions.com/"));
        }
        else
        {
            Intent intent=new Intent(context,ImageViewActivity.class);
            intent.putExtra(ImageViewActivity.EXTRA_IMAGE_URL,data.getData());

            return  intent;

        }
    }

    public static int getTypefromData(DataModel data)
    {

        return (data.getType().equals("text"))?TYPE_TEXT:TYPE_IMAGE;
    }

    public static int getLayoutfromData(DataModel data)
    {

        if(data.getType().equals("text"))
        {
            return R.layout.list_item_text;
        }
        else
        {
            return  R.layout.list_item_image;

        }

    }

    public static void createViewHolder(DataAdapter.ViewHolder viewHolder,View view,int view_type)
    {
        if(view_type==TYPE_TEXT)
        {
            viewHolder.textView=(TextView) view.findViewById(R.id.textView);
            viewHolder.dateTextView=(TextView)view.findViewById(R.id.date_textView);
        }
        else if(view_type==TYPE_IMAGE)
        {
            viewHolder.imageView=(ImageView)view.findViewById(R.id.imageView);
            viewHolder.dateTextView=(TextView)view.findViewById(R.id.date_textView);
        }
    }

    public static void processViewHolder(DataAdapter.ViewHolder viewHolder,int view_type, Context context,DataModel data)
    {
        if(view_type==TYPE_TEXT)
        {
            viewHolder.textView.setText(data.getSummary());
            viewHolder.dateTextView.setText(data.getDate());
        }
        else if(view_type==TYPE_IMAGE)
        {
            Picasso.with(context).load(data.getData()).into(viewHolder.imageView);
            viewHolder.dateTextView.setText(data.getDate());
        }
    }
}
