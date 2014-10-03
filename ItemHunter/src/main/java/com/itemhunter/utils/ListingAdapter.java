package com.itemhunter.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itemhunter.main.R;
import com.itemhunter.objects.Listing;
import com.itemhunter.sqlite.AppConstants;

import java.util.ArrayList;

/**
 * Created by Kyle on 2/10/2014.
 */
public class ListingAdapter extends BaseAdapter implements View.OnClickListener {
    private ArrayList data;
    private Activity activity;
    private static LayoutInflater inflater=null;
    public Resources res;
    Listing tempvalues = null;
    int i = 0;

    public ListingAdapter(Activity a, ArrayList d, Resources resLocal){
        activity = a;
        data = d;
        res = resLocal;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        if(data.size()<= 0){
            return 1;
        }
        return data.size();
    }

    public Object getItem(int position){
        return position;
    }

    public long getItemId(int position){
        return position;
    }

    public static class ViewHolder{
        public ImageView itemImg;
        public TextView titleText;
        public TextView priceText;
        public TextView locationText;
        public ImageView marketLogo;
        public Button buyBut;
        public ImageView closeBut;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View vi = convertView;
        ViewHolder holder;

        if(convertView == null){
            vi = inflater.inflate(R.layout.homepage_list_item, null);

            holder = new ViewHolder();
            holder.itemImg = (ImageView)vi.findViewById(R.id.itemImg);
            holder.titleText = (TextView)vi.findViewById(R.id.titleText);
            holder.priceText = (TextView)vi.findViewById(R.id.priceText);
            holder.locationText = (TextView)vi.findViewById(R.id.locationText);
            holder.marketLogo = (ImageView)vi.findViewById(R.id.marketLogo);
            holder.buyBut = (Button)vi.findViewById(R.id.buybut);
            holder.closeBut = (ImageView)vi.findViewById(R.id.closeBut);

            vi.setTag(holder);
            Log.v(AppConstants.TAG, "View created");
        }
        else{
            holder = (ViewHolder)vi.getTag();
            Log.v(AppConstants.TAG, "data found, get tag");
        }

        if(data.size()<=0){
            holder.titleText.setText("No Data");
        }
        else{
            tempvalues = null;
            tempvalues = (Listing)data.get(position);
            //TODO - Set listing image and set
            holder.titleText.setText(tempvalues.getTitle());
            holder.priceText.setText(Double.toString(tempvalues.getPrice()));
            holder.locationText.setText(tempvalues.getLocation());

            vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
    }
    @Override
    public void onClick(View v){
        Log.v(AppConstants.TAG, "Row button clicked");
    }

    private class OnItemClickListener implements View.OnClickListener{
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0){
            //TODO - Call method in home.java
        }
    }
}