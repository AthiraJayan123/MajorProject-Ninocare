package com.syntax.ninocare.Commom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.syntax.ninocare.R;

import java.util.ArrayList;

public class FoodNotAdapter extends ArrayAdapter<NotificationBean> {

    Activity context;
    ArrayList<NotificationBean> rest_List;

    public FoodNotAdapter(Activity context, ArrayList<NotificationBean> rest_List) {
        super(context, R.layout.custm_food_notification, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.custm_food_notification, null, true);

        TextView id = (TextView) view.findViewById(R.id.custom_foodnot_id);
        TextView foodname = (TextView) view.findViewById(R.id.custom_foodnot_name);
        TextView date = (TextView) view.findViewById(R.id.custom_foodnot_date);
        TextView description = (TextView) view.findViewById(R.id.custom_foodnot_description);


//        item_img.getLayoutParams().height=100;
//        item_img.getLayoutParams().width=100;
//        item_img.setScaleType(ImageView.ScaleType.FIT_XY);
//
//        ImageView fimage = (ImageView) view.findViewById(R.id.cust_dish_image);

        id.setText(rest_List.get(position).getFoodnot_id());
        foodname.setText(rest_List.get(position).getFood_name());
        date.setText(rest_List.get(position).getFood_date());
        description.setText(rest_List.get(position).getFood_decription());
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
