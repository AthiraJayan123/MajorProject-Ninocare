package com.syntax.ninocare.AshaWorker;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syntax.ninocare.Commom.Common;
import com.syntax.ninocare.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class Asha_FoodNotification extends AppCompatActivity {
    EditText foodname,fdescript,fdate;
    Spinner usertype;
    Button btnsend;
    String FNAME,FDESC,FDATE,USER;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha__food_notification);
        foodname=(EditText)findViewById(R.id.asha_foodnoti_foodname);
        fdescript=(EditText)findViewById(R.id.asha_foodnoti_description);
        fdate=(EditText)findViewById(R.id.asha_foodnoti_date);
        usertype=(Spinner)findViewById(R.id.asha_foodnoti_usertype);
        btnsend=(Button)findViewById(R.id.asha_foodnoti_send);

        final String user[]={"Select User Type","Mother","Child"};
        ArrayAdapter aruser=new ArrayAdapter(Asha_FoodNotification.this, android.R.layout.simple_spinner_dropdown_item,user);
        aruser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usertype.setAdapter(aruser);

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FNAME=foodname.getText().toString();
                FDESC=fdescript.getText().toString();
                FDATE=fdate.getText().toString();
                if(FNAME.isEmpty()){
                    foodname.requestFocus();
                    Toast.makeText(Asha_FoodNotification.this, "Enter Food Name", Toast.LENGTH_SHORT).show();
                }
                else if(FDESC.isEmpty()){
                    fdescript.requestFocus();
                    Toast.makeText(Asha_FoodNotification.this, "Enter food description", Toast.LENGTH_SHORT).show();
                }else if(FDATE.isEmpty())
                {
                    fdate.requestFocus();
                    Toast.makeText(Asha_FoodNotification.this, "Select the date", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    volly_foodnotification();

                }
            }
        });
        usertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Toast.makeText(Asha_FoodNotification.this, "Select User Type", Toast.LENGTH_SHORT).show();
                }
                else{
                    USER=user[i];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fdate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP)
                    getStartDate();
                return false;
            }
        });

    }


    void getStartDate() {

        final Calendar c = Calendar.getInstance();
        mYear= c.get(Calendar.YEAR);
        mMonth= c.get(Calendar.MONTH);
        mDay= c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String mn=monthOfYear+"";
                        String dy=dayOfMonth+"";
                        if(monthOfYear<10){
                            int m=monthOfYear+1;
                            mn="0"+m;
                        }
                        if(dayOfMonth<10){

                            dy="0"+dayOfMonth;

                        }
//                        childdob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        // month.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        fdate.setText(year + "-" + mn + "-" + dy);


                        //fdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);

        datePickerDialog.show();
    }



    public void volly_foodnotification()
    {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {


                    Toast.makeText(getApplicationContext(), "Food Notification Send !", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(),Asha_Send_Notification.class));

                }
                else
                {


                  Toast.makeText(getApplicationContext(), " notification not send !", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "my error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<String, String>();
                SharedPreferences prefs = getSharedPreferences("SharedData", MODE_PRIVATE);
                final   String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.
                map.put("key","AshaFoodNotification");
                map.put("aid",uid);
                map.put("usertype",USER);
                map.put("foodname",FNAME);
                map.put("descript",FDESC);
                map.put("fdate",FDATE);
                return map;
            }
        };
        queue.add(request);
    }

}
