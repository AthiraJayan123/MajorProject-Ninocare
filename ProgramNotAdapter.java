package com.syntax.ninocare.Commom;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.syntax.ninocare.R;

import java.util.ArrayList;

public class ProgramNotAdapter extends ArrayAdapter<NotificationBean> {

    Activity context;
    ArrayList<NotificationBean> rest_List;

    public ProgramNotAdapter(Activity context, ArrayList<NotificationBean> rest_List) {
        super(context, R.layout.custm_program_notification, rest_List);
        this.context = context;
        this.rest_List = rest_List;
        // TODO Auto-generated constructor stub
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.custm_program_notification, null, true);

        TextView id = (TextView) view.findViewById(R.id.custom_Programnot_id);
        TextView foodname = (TextView) view.findViewById(R.id.custom_Programnot_name);
        TextView date = (TextView) view.findViewById(R.id.custom_Programnot_date);
        TextView location = (TextView) view.findViewById(R.id.custom_Programnot_location);
        TextView time = (TextView) view.findViewById(R.id.custom_Programnot_time);
        TextView description = (TextView) view.findViewById(R.id.custom_Programnot_description);


//        item_img.getLayoutParams().height=100;
//        item_img.getLayoutParams().width=100;
//        item_img.setScaleType(ImageView.ScaleType.FIT_XY);
//
//        ImageView fimage = (ImageView) view.findViewById(R.id.cust_dish_image);

        id.setText(rest_List.get(position).getProgram_id());
        foodname.setText(rest_List.get(position).getProgram_name());
        date.setText(rest_List.get(position).getProgram_date());
        location.setText(rest_List.get(position).getProgram_location());
        time.setText(rest_List.get(position).getProgram_time());
        description.setText(rest_List.get(position).getProgram_description());
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
