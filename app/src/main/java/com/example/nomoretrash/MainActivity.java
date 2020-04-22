package com.example.nomoretrash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.nomoretrash.signalements.MesSignalementsActivity;
import com.example.nomoretrash.signalements.SignalementActivity;
import com.example.nomoretrash.statistiques.StatistiquesActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static final int ActivitySignalementRequestCode = 2;
    private ArrayList<String> signalementsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signalementsList = new ArrayList<>();

        final Button buttonStat = findViewById(R.id.boutonStatistiques);
        buttonStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StatistiquesActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonSignalement = findViewById(R.id.boutonSignalement);
        buttonSignalement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignalementActivity.class);
                startActivityForResult(intent, ActivitySignalementRequestCode);
                //startActivity(intent);
            }
        });

        final Button button = findViewById(R.id.boutonMesSignalements);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, MesSignalementsActivity.class);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("TAG", "Value is: " + value);
                        ArrayList<String> signalementsListOnServer = new ArrayList<>();
                        signalementsListOnServer.add(value);
                        intent.putExtra("ma_liste_de_signalements", signalementsListOnServer);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Failed to read value
                        Log.w("TAG", "Failed to read value.", databaseError.toException());
                        intent.putExtra("ma_liste_de_signalements", signalementsList);

                    }
                });


                startActivity(intent);
            }
        });

        final ImageButton buttonAccount = findViewById(R.id.account);
        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (ActivitySignalementRequestCode): {
                if (resultCode == Activity.RESULT_OK) {
                    String returnValue = data.getStringExtra("mon_signalement");
                    signalementsList.add(returnValue);
                    Log.d("recap_signalement : ", returnValue);
                }
                break;
            }
        }
    }
}
