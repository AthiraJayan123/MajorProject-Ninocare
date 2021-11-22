package com.syntax.ninocare.AshaWorker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class  Asha_VacciNotification extends AppCompatActivity {
    Spinner user_type,vacci_name;
    EditText vacci_date,vacci_location,vacci_time;
    Button btn_send;
    String VACC_ID,DATE,LOCATION,TIME,USER_TYPE,VACC_NAME;
    String name[]={"Select Vaccination name"},id[]={};
    String user_typearr[]={"Select User Type","Mother","Child"};
    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha__vacci_notification);

        btn_send=(Button)findViewById(R.id.asha_vaciinoti_btnsend) ;
        user_type=(Spinner)findViewById(R.id.asha_vaciinoti_usertype);
        vacci_name=(Spinner)findViewById(R.id.asha_vaciinoti_name);
        vacci_date=(EditText)findViewById(R.id.asha_vaciinoti_date);
        vacci_location=(EditText)findViewById(R.id.asha_vaciinoti_location);
        vacci_time=(EditText)findViewById(R.id.asha_vaciinoti_time);


        ArrayAdapter arr1 = new ArrayAdapter(Asha_VacciNotification.this, android.R.layout.simple_spinner_dropdown_item,user_typearr );
        arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user_type.setAdapter(arr1);

        String vaccname[]={"Vaccination name"};
        ArrayAdapter arr2 = new ArrayAdapter(Asha_VacciNotification.this, android.R.layout.simple_spinner_dropdown_item,vaccname );
        arr2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vacci_name.setAdapter(arr2);


        user_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position==0){

                    Toast.makeText(getApplicationContext(), "Please Select User", Toast.LENGTH_SHORT).show();

                }
                else{
                    USER_TYPE=user_typearr[position].trim();
                    getVacc_Details();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        vacci_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                   if(name[position].equals("Select Vaccination name")) {
                       Toast.makeText(Asha_VacciNotification.this, "Select Vaccination Name", Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       VACC_NAME = name[position].trim();
                       VACC_ID = id[position].trim();
                   }
                }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        vacci_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP)
                    getStartDate();
                return false;
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DATE=vacci_date.getText().toString();
                LOCATION=vacci_location.getText().toString();
                TIME=vacci_time.getText().toString();

                if(USER_TYPE.isEmpty())
                {
                    user_type.requestFocus();
//                    user_type.setError("Enter House Number");
                }
                else if(VACC_NAME.isEmpty()) {
                    vacci_name.requestFocus();
//                    vacci_name.setError("ENTER VALID NAME(Characters only)");
                } else if(DATE.isEmpty())
                {
                    vacci_date.requestFocus();
                    vacci_date.setError("Select a Date");
                }else if(LOCATION.isEmpty())
                {
                    vacci_location.requestFocus();
                    vacci_location.setError("Enter Location");
                }else if(TIME.isEmpty())
                {
                    vacci_time.requestFocus();

                }
                else{
                    addVaccNotifiaction();
                }

            }
        });



    }


    public void getVacc_Details()
    {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {
                    String ar[]=response.split("#");
                    id=ar[0].split(":");
                    name=ar[1].split(":");
                    VACC_NAME=name[0];
                    VACC_ID=id[0];
                    ArrayAdapter a=new ArrayAdapter(getApplicationContext(),R.layout.cust_lst,name);
                    a.setDropDownViewResource(R.layout.cust_lst);
                    vacci_name.setAdapter(a);
//                    Toast.makeText(getApplicationContext(), "Added Successfully !", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    String nullarr[]={"not available"};
                    ArrayAdapter a=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,nullarr);
                    vacci_name.setAdapter(a);
                    Toast.makeText(getApplicationContext(), " not available !", Toast.LENGTH_LONG).show();
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
                map.put("key","getVacc_Details");
                map.put("aid",uid);
                map.put("userType",USER_TYPE);

                return map;
            }
        };
        queue.add(request);
    }

    public void addVaccNotifiaction()
    {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {

                    Toast.makeText(getApplicationContext(), "Added Successfully !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),AshaHome.class));
                }
                else
                {

                    Toast.makeText(getApplicationContext(), " Error !", Toast.LENGTH_LONG).show();
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
                map.put("key","addVaccNotifiaction");
                map.put("aid",uid);
                map.put("vid",VACC_ID);
                map.put("date",DATE);
                map.put("loacation",LOCATION);
                map.put("time",TIME);




                return map;
            }
        };
        queue.add(request);
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
                        Date date=new Date();
//
//                        SimpleDateFormat f=new SimpleDateFormat("YYYY-MM-DD");
//                        f.format(date);
                        String pickdate=year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        if(pickdate.compareTo(date.toString())>=0){

                            vacci_date.setError("Pick Coming Date");
                        }

//                        dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        vacci_date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
