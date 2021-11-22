package com.syntax.ninocare.AshaWorker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.ImageView;
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

public class asha_new_user extends AppCompatActivity {
    EditText name, phone, age, address, email, hnumber, husname;
    private int mYear, mMonth, mDay, mHour, mMinute;
    RadioGroup carryornot;
    RadioButton carry, carrynot, r1;
    EditText month;
    Button adduser;
    String NAME, PHONE, AGE, ADDRESS, EMAIL, MONTH, HNUMBER, HUSNAME;

    ImageView imgGal, imgCam, imgView;
    Uri imageUri;
    MediaPlayer mp = new MediaPlayer();

    private static final int PICK_IMAGE = 1;
    private static final int PICK_Camera_IMAGE = 2;
    private Bitmap bitmap;
    String bal;

    int stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        hnumber = (EditText) findViewById(R.id.reg_hnumber);
        name = (EditText) findViewById(R.id.reg_name);
        age = (EditText) findViewById(R.id.reg_dob);
        husname = (EditText) findViewById(R.id.reg_husname);
        carryornot = (RadioGroup) findViewById(R.id.reg_carryStatus);
        carry = (RadioButton) findViewById(R.id.reg_carry_yes);
        carrynot = (RadioButton) findViewById(R.id.reg_carry_no);
        phone = (EditText) findViewById(R.id.reg_phone);

        address = (EditText) findViewById(R.id.reg_address);
        email = (EditText) findViewById(R.id.reg_email);
        month = (EditText) findViewById(R.id.reg_spnr_month);
        adduser = (Button) findViewById(R.id.btn_register);


        imgGal = (ImageView) findViewById(R.id.img_newuser_imgGal);
        imgCam = (ImageView) findViewById(R.id.img_newuser_imgCam);
        imgView = (ImageView) findViewById(R.id.img_newuser_image);


        carryornot.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = carryornot.getCheckedRadioButtonId();
                r1 = (RadioButton) findViewById(id);
                String statuscarry = r1.getText().toString();
                if (statuscarry.equals("Carrying")) {
                    month.setVisibility(View.VISIBLE);
                    stat = 1;
                } else {
                    month.setVisibility(View.GONE);
                    MONTH = "";
                    stat = 0;
                }
            }
        });

        month.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    getStartDate();
                return false;
            }
        });
//        ArrayAdapter ar = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, status);
//        ar.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        final String status[] = {"Select a Month","0","1st","2nd"};
//        month.setAdapter(ar);
//
//        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                if(position==0){
//
//                    Toast.makeText(getApplicationContext(), "Please Select Status", Toast.LENGTH_SHORT).show();
//
//                }
//                else{
//                    MONTH=status[position].trim();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        imgCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                String fileName = "new-photo-name.jpg";
                //create parameters for Intent with filename
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, fileName);
                values.put(MediaStore.Images.Media.DESCRIPTION, "Image captured by camera");
                //imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
                imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                //create new Intent
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(intent, PICK_Camera_IMAGE);
            }
        });

        imgGal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                // TODO Auto-generated method stub
                try {
                    Intent gintent = new Intent();
                    gintent.setType("image/*");
                    gintent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(gintent, "Select Picture"), PICK_IMAGE);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e(e.getClass().getName(), e.getMessage(), e);
                }
            }
        });

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NAME = name.getText().toString().trim();
                AGE = age.getText().toString().trim();
                HNUMBER = hnumber.getText().toString().trim();
                PHONE = phone.getText().toString().trim();
                ADDRESS = address.getText().toString().trim();
                EMAIL = email.getText().toString().trim();
                MONTH = month.getText().toString();
                HUSNAME = husname.getText().toString();

                if (stat == 0) {
                    MONTH = "";
                }
                Toast.makeText(asha_new_user.this, "month=" + MONTH, Toast.LENGTH_SHORT).show();
                if (HNUMBER.isEmpty()) {
                    hnumber.requestFocus();
                    hnumber.setError("Enter House Number)");
                } else if (!NAME.matches("[A-Z a-z]+")) {
                    name.requestFocus();
                    name.setError("ENTER VALID NAME(Characters only)");
                } else if (AGE.isEmpty()) {
                    age.requestFocus();
                    age.setError("ENTER VALID Age");
                } else if (!HUSNAME.matches("[A-Z a-z]+")) {
                    husname.requestFocus();
                    husname.setError("ENTER VALID NAME(Characters only)");
//                } else if (AGE.isEmpty()) {
//                    month.requestFocus();
//                    Toast.makeText(getApplicationContext(), "Select Month", Toast.LENGTH_SHORT).show();
                } else if (PHONE.isEmpty()) {
                    phone.requestFocus();
                    phone.setError("ENTER VALID Number(Numbers only)");
                } else if (ADDRESS.isEmpty()) {
                    address.requestFocus();
                    address.setError("ENTER VALID Address");

                } else if (stat == 1 && MONTH.isEmpty()) {

                    month.requestFocus();
                    Toast.makeText(getApplicationContext(), "Select STATUS", Toast.LENGTH_SHORT).show();
                } else if (!EMAIL.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
                    email.requestFocus();
                    email.setError("ENTER VALID Email");

                } else {
                    Toast.makeText(asha_new_user.this, "quintin ", Toast.LENGTH_SHORT).show();
                    volly_Add_user();

                }

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        Uri selectedImageUri = null;
        String filePath = null;
        switch (requestCode) {
            case PICK_IMAGE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImageUri = data.getData();
                }
                break;
            case PICK_Camera_IMAGE:
                if (resultCode == RESULT_OK) {
                    //use imageUri here to access the image
                    selectedImageUri = imageUri;
		 		    	/*Bitmap mPic = (Bitmap) data.getExtras().get("data");
						selectedImageUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), mPic, getResources().getString(R.string.app_name), Long.toString(System.currentTimeMillis())));*/
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        if (selectedImageUri != null) {
            try {
                // OI FILE Manager
                String filemanagerstring = selectedImageUri.getPath();

                // MEDIA GALLERY
                String selectedImagePath = getPath(selectedImageUri);

                if (selectedImagePath != null) {
                    filePath = selectedImagePath;
                } else if (filemanagerstring != null) {
                    filePath = filemanagerstring;
                } else {
                    Toast.makeText(getApplicationContext(), "Unknown path",
                            Toast.LENGTH_LONG).show();
                    Log.e("Bitmap", "Unknown path");
                }

                if (filePath != null) {
                    decodeFile(filePath);
                } else {
                    bitmap = null;
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Internal error",
                        Toast.LENGTH_LONG).show();
                Log.e(e.getClass().getName(), e.getMessage(), e);
            }
        }
    }

    public void decodeFile(String filePath) {
        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        bitmap = BitmapFactory.decodeFile(filePath, o2);

        imgView.setImageBitmap(bitmap);

    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }


    public void volly_Add_user() {
        Base64.InputStream is;
        BitmapFactory.Options bfo;
        Bitmap bitmapOrg;
        ByteArrayOutputStream bao;

        bfo = new BitmapFactory.Options();
        bfo.inSampleSize = 2;
        //bitmapOrg = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/" + customImage, bfo);

        bao = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        byte[] ba = bao.toByteArray();
        bal = Base64.encodeBytes(ba);

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, Common.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (!response.trim().equals("failed")) {
                    Toast.makeText(getApplicationContext(), "Added Successfully !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), AshaHome.class));

                } else {
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
                final String uid = prefs.getString("logid", "No logid");//"No name defined" is the default value.
                map.put("key", "AshaAddUser");
                map.put("aid", uid);
                map.put("name", NAME);
                map.put("house", HNUMBER);
                map.put("husname", HUSNAME);
                map.put("phone", PHONE);
                map.put("address", ADDRESS);
                map.put("month", MONTH);
                map.put("dob", AGE);
                map.put("email", EMAIL);
                map.put("image", bal);

                return map;
            }
        };
        queue.add(request);
    }

    void getStartDate() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

//        if(mMonth<10){
//            String mn="0"+mMonth;
//            mMonth=Integer.parseInt(mn);
//        }
//        if(mDay<10){
//            String dy="0"+mDay;
//            mDay=Integer.parseInt(dy);
//        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {


                        String mn = monthOfYear + "";
                        String dy = dayOfMonth + "";
                        if (monthOfYear < 10) {
                            int m = monthOfYear + 1;
                            mn = "0" + m;
                        }
                        if (dayOfMonth < 10) {

                            dy = "0" + dayOfMonth;

                        }
//                        childdob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        // month.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        month.setText(year + "-" + mn + "-" + dy);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}
