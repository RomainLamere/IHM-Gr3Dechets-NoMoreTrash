package com.example.nomoretrash.signalements;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nomoretrash.R;

public class MesSignalementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_signalements);

        final Button buttonStat = findViewById(R.id.boutonRetour);
        buttonStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MesSignalementsActivity.this.finish();
            }
        });

    }


}
