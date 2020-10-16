package com.example.spoctoiotkrishinetwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView a,b,c,d;
    Button btn;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   //listView=findViewById()
        a=(TextView)findViewById(R.id.humidity);
        b=(TextView)findViewById(R.id.moist);
        c=(TextView)findViewById(R.id.temp);
        btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff= FirebaseDatabase.getInstance().getReference().child("DHT11SensorData");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String Humidity=dataSnapshot.child("Humidity").getValue().toString();
                        String Moisture=dataSnapshot.child("MoisturePercentage").getValue().toString();
                        String Temperature=dataSnapshot.child("Temperature").getValue().toString();
                        a.setText(Humidity);
                        b.setText(Moisture);
                        c.setText(Temperature);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}