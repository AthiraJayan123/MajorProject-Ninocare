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

public class Asha_ProgramNotification extends AppCompatActivity {

    EditText programName,pdate,plocation,ptime,pdescription;
    Button btnprogramSend;
    String PNAME,PDATE,PLOCATION,PTIME,PDESCRIPT;

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha__program_notification);

        programName=(EditText)findViewById(R.id.asha_programnoti_progname);
        pdate=(EditText)findViewById(R.id.asha_programnoti_date);
        plocation=(EditText)findViewById(R.id.asha_programnoti_location);
        ptime=(EditText)findViewById(R.id.asha_programnoti_time);
        pdescription=(EditText)findViewById(R.id.asha_programnoti_description);
        btnprogramSend=(Button) findViewById(R.id.asha_programnoti_send);

        btnprogramSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PNAME=programName.getText().toString();
                PDATE=pdate.getText().toString();
                PLOCATION=plocation.getText().toString();
                PTIME=ptime.getText().toString();
                PDESCRIPT=pdescription.getText().toString();
                if(PNAME.isEmpty())
                {
                    programName.requestFocus();
                    Toast.makeText(Asha_ProgramNotification.this, "Enter Program Name", Toast.LENGTH_SHORT).show();
                }else if(PDATE.isEmpty())
                {
                    pdate.requestFocus();
                    Toast.makeText(Asha_ProgramNotification.this, "Pick Date", Toast.LENGTH_SHORT).show();
                }else if(!PLOCATION.matches("[A-Z a-z]+"))
                {
                    plocation.requestFocus();
                    Toast.makeText(Asha_ProgramNotification.this, "Enter Program Location", Toast.LENGTH_SHORT).show();
                }else if(PTIME.isEmpty())
                {
                    ptime.requestFocus();
                    Toast.makeText(Asha_ProgramNotification.this, "Enter Program Time", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    volly_programnotification();
                }
            }
        });

        pdate.setOnTouchListener(new View.OnTouchListener() {
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
                        pdate.setText(year + "-" + mn + "-" + dy);




                       // pdate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void volly_programnotification()
    {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {


                    Toast.makeText(getApplicationContext(), "Program Notification Send !", Toast.LENGTH_SHORT).show();

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
                map.put("key","AshaProgramNotification");
                map.put("aid",uid);
                map.put("progname",PNAME);
                map.put("pdate",PDATE);
                map.put("plocat",PLOCATION);
                map.put("ptime",PTIME);
                map.put("pdescript",PDESCRIPT);
                return map;
            }
        };
        queue.add(request);
    }

}
