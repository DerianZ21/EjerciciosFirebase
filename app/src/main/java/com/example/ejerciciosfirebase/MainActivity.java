package com.example.ejerciciosfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import com.google.firebase.database.*;


import butterknife.*;



public class MainActivity extends AppCompatActivity {

    private static final String PATH_START = "start";
    private static final String PATH_MESSAGE = "message";

    @BindView(R.id.etMessage)
    EditText etMessage;
    @BindView(R.id.btnSend)
    Button btnSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        final TextView tvMessage = findViewById(R.id.tvMessage);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference =
                database.getReference(PATH_START).child(PATH_MESSAGE);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvMessage.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error al ver los dat de firebase.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.btnSend)
    public void onViewClicked()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference =
                database.getReference(PATH_START).child(PATH_MESSAGE);

        reference.setValue(etMessage.getText().toString().trim());
    }
}