package com.greencoder.fuzzyapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.greencoder.fuzzyapp.com.greencoder.fuzzyapp.model.DataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by newcomputer on 10/5/15.
 */
public class DataAdapter extends ArrayAdapter<DataModel> implements Filterable{

    LayoutInflater inflater;
    int layout_resource;
    List<DataModel> dataStore;
    List<DataModel> allData;
    Context context;

    public DataAdapter(Context context, int resource, List<DataModel> objects) {

        super(context, resource, objects);

        inflater = ((Activity)context).getLayoutInflater();

        layout_resource=resource;

        allData=objects;

        dataStore=new ArrayList<DataModel>(allData);

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


        int viewType=DataFactory.getTypefromData(allData.get(position));

        return viewType;
    }

    @Override
    public int getViewTypeCount() {

        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        int view_type=getItemViewType(position);

        int layout=DataFactory.getLayoutfromData(allData.get(position));

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


        DataFactory.processViewHolder(holder,view_type,context,allData.get(position));


        return convertView;
    }

    Filter myFiler=new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            FilterResults filterResults=new FilterResults();


            if(charSequence==null || charSequence.length()==0 || charSequence.equals(DataFactory.DATA_TYPE_ALL))
            {
                filterResults.values=dataStore;
                filterResults.count=dataStore.size();
            }
            else
            {
                String filterString=charSequence.toString();
                List<DataModel> tempData=new ArrayList<DataModel>();

                for(DataModel data:dataStore)
                {
                    if(data.getType().equalsIgnoreCase(filterString))
                    {
                        tempData.add(data);
                    }
                }

                filterResults.values=tempData;
                filterResults.count=tempData.size();
            }

            return filterResults;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            if (filterResults.count == 0)
                notifyDataSetInvalidated();
            else
            {
                allData = (List<DataModel>) filterResults.values;
                notifyDataSetChanged();
            }

        }
    };


    @Override
    public Filter getFilter() {
        return myFiler;
    }

    @Override
    public DataModel getItem (int pos){

        return allData.get(pos);
    }

    @Override
    public int getCount()
    {
        return allData.size();
    }
}
