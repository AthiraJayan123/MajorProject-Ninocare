package com.syntax.ninocare.AshaWorker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.Map;

public class Asha_Broad_EmergMsg extends AppCompatActivity {

    EditText subject,message;
    Button btnsendMessage;
    String SUB,MSG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha__broad__emerg_msg);

        subject=(EditText)findViewById(R.id.asha_broad_subject);
        message=(EditText)findViewById(R.id.asha_broad_Message);
        btnsendMessage=(Button)findViewById(R.id.btnasha_broad_Message);

        btnsendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SUB=subject.getText().toString();
                MSG=message.getText().toString();
                if (SUB.isEmpty()) {
                    subject.requestFocus();
                    subject.setError("ENTER  SUBJECT");
                }
                else if(MSG.isEmpty()) {
                    message.requestFocus();
                    message.setError("ENTER MESSAGE");
                }
                else{
                    volly_Broadcast_Msg();
                }

            }
        });
    }
    public void volly_Broadcast_Msg()
    {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {
                    String broadsub=SUB.toUpperCase();

                    String ar[]=response.trim().split("#");
                    for(String a:ar)
                     {
                         Toast.makeText(Asha_Broad_EmergMsg.this, a, Toast.LENGTH_SHORT).show();

                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(a, null, broadsub+"\n\n"+MSG, null, null);
                    }
                    Toast.makeText(getApplicationContext(), "Successfully broadcast messages!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(),AshaHome.class));


                }
                else
                {
                    Toast.makeText(getApplicationContext(), "broadcasting failed !", Toast.LENGTH_LONG).show();
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
                map.put("key","AshaBroadMsg");
                map.put("aid",uid);
                map.put("sub",SUB);
                map.put("msg",MSG);

                return map;
            }
        };
        queue.add(request);
    }


}
