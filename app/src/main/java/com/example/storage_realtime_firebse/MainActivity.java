package com.example.storage_realtime_firebse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity<DatabseReference> extends AppCompatActivity {

    TextView text_string;
    Button button_read_data;

    FirebaseDatabase mfirebasedatabase;
    DatabaseReference mdatabseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_string=findViewById(R.id.textview);
        button_read_data=findViewById(R.id.button);

        mfirebasedatabase=FirebaseDatabase.getInstance();
    }
    @Override
    protected void onStart(){
        super.onStart();

        button_read_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mdatabseref=mfirebasedatabase.getReference().child("Name");
                ValueEventListener fetch_data=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Toast.makeText(MainActivity.this, "Listener Worked!!", Toast.LENGTH_SHORT).show();
                        String data = snapshot.getValue(String.class);
                        text_string.setText(data);
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                mdatabseref.addValueEventListener(fetch_data);

                mdatabseref=mfirebasedatabase.getReference().push();
              //  mdatabseref.child("Surname").setValue("Kamble");
              //  mdatabseref.push().child("Food").setValue("Omlet");

                mdatabseref.child("Car_Name").setValue("Tesla");
                mdatabseref.child("Model_Name").setValue("Model X");
                mdatabseref.child("Wheel_Type").setValue("Four_Wheeler");

            }
        });
    }
}