package com.syntax.ninocare.AshaWorker;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.syntax.ninocare.R;

public class Asha_Send_Notification extends AppCompatActivity {

    ImageView foodnotifi,programnotifi,vaccinotifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asha__send__notification);
        foodnotifi=(ImageView)findViewById(R.id.asha_send_notifood);
        programnotifi=(ImageView)findViewById(R.id.asha_send_notiprogram);
        vaccinotifi=(ImageView)findViewById(R.id.asha_send_notivaccination);

        foodnotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Asha_FoodNotification.class));

            }
        });
        programnotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Asha_ProgramNotification.class));

            }
        });
        vaccinotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Asha_VacciNotification.class));

            }
        });
    }
}
