package com.syntax.ninocare.Commom;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.syntax.ninocare.R;

import java.util.ArrayList;

/**
 * Created by user on 10/31/2018.
 */

public class VacciFoodAdapter extends ArrayAdapter<vaccinationViewBean> {
    Activity context;
    ArrayList<vaccinationViewBean> rest_List;

    public VacciFoodAdapter(@NonNull Context context,  ArrayList<vaccinationViewBean> rest_List) {
        super(context, R.layout.custom_vacci_details, rest_List);
        this.context = (Activity) context;
        this.rest_List = rest_List;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_vacci_details, null, true);

        TextView id = (TextView) view.findViewById(R.id.custom_vacci_id);
        TextView vacciname = (TextView) view.findViewById(R.id.custom_vacci_name);
        TextView vaccidetail = (TextView) view.findViewById(R.id.custom_vacci_detail);



//        item_img.getLayoutParams().height=100;
//        item_img.getLayoutParams().width=100;
//        item_img.setScaleType(ImageView.ScaleType.FIT_XY);
//
//        ImageView fimage = (ImageView) view.findViewById(R.id.cust_dish_image);

        id.setText(rest_List.get(position).getVacci_id());
        vacciname.setText(rest_List.get(position).getVacci_name());
        vaccidetail.setText(rest_List.get(position).getVacci_details());

//        String base = rest_List.get(position).getImg();
        // String base = rest_List.get(position).getPicture();

//        try {
//            byte[] imageAsBytes = Base64.decode(base.getBytes());
//
//            item_img.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) );
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }


        return view;
    }


}


