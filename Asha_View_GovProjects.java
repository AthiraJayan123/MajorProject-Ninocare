package com.syntax.ninocare.AshaWorker;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.syntax.ninocare.Commom.Common;
import com.syntax.ninocare.Commom.GovProjectAdapter;
import com.syntax.ninocare.Commom.VacciFoodAdapter;
import com.syntax.ninocare.Commom.vaccinationViewBean;
import com.syntax.ninocare.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Asha_View_GovProjects extends AppCompatActivity {
    ListView govProjectList;
    ArrayList<vaccinationViewBean> arrayList;
    GovProjectAdapter adpater;
    String listcontent[]={"Government Projects not available"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha__view__gov_projects);
        govProjectList=(ListView)findViewById(R.id.asha_view_govList);
        arrayList = new ArrayList<vaccinationViewBean>();
        adpater = new GovProjectAdapter(Asha_View_GovProjects.this, arrayList);


        volly_getgovProjects();
    }

    public void volly_getgovProjects()
    {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******",response);
                if(!response.trim().equals("failed"))
                {
//                    listcontent=response.trim().split("#");
//
//                    ArrayAdapter add1 = new ArrayAdapter(Asha_View_GovProjects.this, android.R.layout.simple_list_item_1, listcontent);
//                    govProjectList.setAdapter(add1);
                    Gson gson = new Gson();
                    vaccinationViewBean[] beans = gson.fromJson(response, vaccinationViewBean[].class);

                    for (vaccinationViewBean ListBean : beans) {
                        vaccinationViewBean bean = new vaccinationViewBean();
                        bean.setPro_name(ListBean.getPro_name());
                        bean.setPro_user(ListBean.getPro_user());
                        bean.setPro_date(ListBean.getPro_date());
                        bean.setPro_det(ListBean.getPro_det());

                        arrayList.add(bean);
                        govProjectList.setAdapter(adpater);
                    }
                }
                else
                {
//                    ArrayAdapter arr1=new ArrayAdapter(Asha_View_GovProjects.this, R.layout.support_simple_spinner_dropdown_item,listcontent);
//                    arr1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    govProjectList.setAdapter(arr1);

                    Toast.makeText(getApplicationContext(), " no data !", Toast.LENGTH_LONG).show();
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

                map.put("key","AshaViewgovPrograms");


                return map;
            }
        };
        queue.add(request);
    }

}
