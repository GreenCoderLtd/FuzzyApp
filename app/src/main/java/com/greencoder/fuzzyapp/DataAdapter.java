package com.greencoder.fuzzyapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greencoder.fuzzyapp.com.greencoder.fuzzyapp.model.DataModel;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by newcomputer on 10/5/15.
 */
public class DataAdapter extends ArrayAdapter<DataModel> {

    LayoutInflater inflater;
    int layout_resource;
    DataModel []allData;
    Context context;

    public DataAdapter(Context context, int resource, DataModel[] objects) {
        super(context, resource, objects);

        inflater = ((Activity)context).getLayoutInflater();

        layout_resource=resource;

        allData=objects;

        this.context=context;
    }

    static class ViewHolder
    {
        TextView textView;
        ImageView imageView;
        TextView dateTextView;

    }

    @Override
    public int getItemViewType(int position) {


        return DataFactory.getTypefromData(allData[position]);
    }

    @Override
    public int getViewTypeCount() {

        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        int view_type=getItemViewType(position);

        int layout=DataFactory.getLayoutfromData(allData[position]);

        if(convertView==null)
        {
            convertView=inflater.inflate(layout,parent,false);

            holder=new ViewHolder();

            DataFactory.createViewHolder(holder, convertView, view_type);

            convertView.setTag(holder);

        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }


        DataFactory.processViewHolder(holder,view_type,context,allData[position]);


        return convertView;
    }
}
