package com.syntax.ninocare.AshaWorker;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.syntax.ninocare.Commom.Base64;
import com.syntax.ninocare.Commom.Common;
import com.syntax.ninocare.R;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Asha_add_childuser extends AppCompatActivity {

    Spinner mothername;
    EditText hnumber,childname,childdob;
    RadioGroup gender;
    RadioButton genderFemale,genderMale,r1;
    String HOUSNUMBER,CHILDNAME,GENDER1,DOB,MNAME;
    int MStatus=0;
    String name[]={},id[]={};
    String motherName[]={"Select Mother Name"};
    Button btnaddchild;
    private int mYear, mMonth, mDay, mHour, mMinute;


        @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha_add_childuser);
        hnumber=(EditText)findViewById(R.id.asha_addchild_hnumber);
        mothername=(Spinner)findViewById(R.id.asha_addchild_mother);
        childname=(EditText)findViewById(R.id.asha_addchild_name);
        gender=(RadioGroup)findViewById(R.id.asha_addchild_gender);
        genderFemale=(RadioButton)findViewById(R.id.asha_addchild_female);
        genderMale=(RadioButton)findViewById(R.id.asha_addchild_male);
        childdob=(EditText)findViewById(R.id.asha_addchild_dob);
        btnaddchild=(Button)findViewById(R.id.asha_addchild_btn);


        ArrayAdapter arr1 = new ArrayAdapter(Asha_add_childuser.this, android.R.layout.simple_spinner_dropdown_item,motherName );
        arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mothername.setAdapter(arr1);

        childname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
            HOUSNUMBER=hnumber.getText().toString();
                volly_get_Childmother();
                                return false;
            }
        });

//        hnumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                HOUSNUMBER=hnumber.getText().toString();
//
//                volly_get_Childmother();
//
//
//            }
//
//        });

            childdob.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    if(motionEvent.getAction()==MotionEvent.ACTION_UP)
                        getStartDate();
                    return false;
                }
            });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id=gender.getCheckedRadioButtonId();
                r1=(RadioButton)findViewById(id);
                GENDER1=r1.getText().toString();

            }
        });
        mothername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(MStatus==1) {
                    MNAME = id[i].trim();
                    
//                    Toast.makeText(Asha_add_childuser.this, MNAME, Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnaddchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HOUSNUMBER=hnumber.getText().toString();
                CHILDNAME=childname.getText().toString();
                DOB=childdob.getText().toString();
                if(HOUSNUMBER.isEmpty())
                {
                    hnumber.requestFocus();
                    hnumber.setError("Enter House Number");
                }
                else if(!CHILDNAME.matches("[A-Z a-z]+")) {
                    childname.requestFocus();
                    childname.setError("ENTER VALID NAME(Characters only)");
                } else if(GENDER1.isEmpty())
                {
                    gender.requestFocus();

                }else if(DOB.isEmpty())
                {
                    childdob.requestFocus();
                    childdob.setError("Enter Age");
                }else if(MNAME.isEmpty())
                {
                    mothername.requestFocus();

                }
                else{
                    volly_add_Childuser();
                }

            }
        });

    }

    public void volly_get_Childmother()
    {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("NotFound"))
                {

                    String ar[]=response.split("#");
                     id=ar[0].split(":");
                     name=ar[1].split(":");

                    ArrayAdapter arr1 = new ArrayAdapter(Asha_add_childuser.this, android.R.layout.simple_spinner_dropdown_item,name );
                    arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mothername.setAdapter(arr1);

                    MStatus=1;

                }
                else
                {


                    MStatus=0;
                    ArrayAdapter arr1 = new ArrayAdapter(Asha_add_childuser.this, android.R.layout.simple_spinner_dropdown_item,motherName );
                    arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mothername.setAdapter(arr1);
                    Toast.makeText(getApplicationContext(), " failed !", Toast.LENGTH_LONG).show();
//                    MStatus=true;
//
//                    if(MStatus) {
//
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Asha_add_childuser.this);
                        alertDialogBuilder.setMessage("Mother not Registered!!!!! Do you want to register mother??");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        startActivity(new Intent(getApplicationContext(), asha_new_user.class));
                                    }
                                });
                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), AshaHome.class));

                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();


//                    }
//
//                    else{
//                        Toast.makeText(Asha_add_childuser.this, "haii="+MStatus, Toast.LENGTH_SHORT).show();
//
//                    }
//

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
                map.put("key","AshaGetMother");
                map.put("aid",uid);
                map.put("houseNo",HOUSNUMBER);
                return map;
            }
        };
        queue.add(request);
    }
    public void volly_add_Childuser()
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
                    Toast.makeText(getApplicationContext(), "registeration failed !", Toast.LENGTH_LONG).show();
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
                map.put("key","AshaAddChildUser");
                map.put("aid",uid);

                map.put("house",HOUSNUMBER);
                map.put("mid",MNAME);
                map.put("name",CHILDNAME);
                map.put("gender",GENDER1);
                map.put("dob",DOB);


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


//                        childdob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        childdob.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}
