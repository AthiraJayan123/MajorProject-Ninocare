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

public class GovProjectAdapter extends ArrayAdapter<vaccinationViewBean> {
    Activity context;
    ArrayList<vaccinationViewBean> rest_List;


    public GovProjectAdapter(@NonNull Context context, ArrayList<vaccinationViewBean> rest_List) {
        super(context, R.layout.custm_gov_projects, rest_List);

        this.context = (Activity) context;
        this.rest_List = rest_List;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.custm_gov_projects, null, true);

        TextView name = (TextView) view.findViewById(R.id.custom_govprojname);
        TextView target = (TextView) view.findViewById(R.id.custom_govprojuser);
        TextView date = (TextView) view.findViewById(R.id.custom_govprojdate);
        TextView description = (TextView) view.findViewById(R.id.custom_govprojdetails);


//        item_img.getLayoutParams().height=100;
//        item_img.getLayoutParams().width=100;
//        item_img.setScaleType(ImageView.ScaleType.FIT_XY);
//
//        ImageView fimage = (ImageView) view.findViewById(R.id.cust_dish_image);

        name.setText(rest_List.get(position).getPro_name());
        target.setText(rest_List.get(position).getPro_user());
        date.setText(rest_List.get(position).getPro_date());
        description.setText(rest_List.get(position).getPro_det());
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
