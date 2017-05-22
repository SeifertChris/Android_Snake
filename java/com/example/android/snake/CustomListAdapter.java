/**package com.example.android.snake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


 * Created by chris on 3/1/2017.
 *
 * Custom list adapter
 *


public class CustomListAdapter extends ArrayAdapter{

    List mList = new ArrayList();

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);

    }

    static class LayoutHandler{

        TextView NAME,SCORE,DATE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if (row == null){

            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAME = (TextView)row.findViewById(R.id.username_txt);
            layoutHandler.SCORE= (TextView)row.findViewById(R.id.score_txt);
            layoutHandler.DATE= (TextView)row.findViewById(R.id.date_txt);
            row.setTag(layoutHandler);

        }else{

            layoutHandler = (LayoutHandler)row.getTag();

        }

        DataProvider dataProvider = (DataProvider) this.getItem(position);
        layoutHandler.NAME.setText(dataProvider.getUserName());
        layoutHandler.SCORE.setText(dataProvider.getScore());
        layoutHandler.DATE.setText(dataProvider.getDate());

        return row;

    }

    public CustomListAdapter(Context context, int resource) { // default constructor
        super(context, resource);
    }

    @Override
    public void add(Object object){
        super.add(object);
        mList.add(object);

    }


}
**/