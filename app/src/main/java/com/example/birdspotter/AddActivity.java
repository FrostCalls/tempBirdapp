package com.example.birdspotter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.ListPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    // Connection to the Viewmodel to communicate with SQL Database
    private BirdViewModel birdViewModel;

    private EditText editTextName;
    private EditText editTextDesc;
    private EditText editTextLoc;
    private EditText editTextDateD;
    private EditText editTextDateM;
    private EditText editTextDateY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        String birds[] = getResources().getStringArray(R.array.birds);
        setContentView(R.layout.activity_add);

        birdViewModel = ViewModelProviders.of(this).get(BirdViewModel.class);

        LinearLayout gallery = findViewById(R.id.gallery);

        LayoutInflater inflater = LayoutInflater.from(this);



        for (int i = 0; i < 6; i++) {

            View view = inflater.inflate(R.layout.bird_scroll, gallery, false);

            TextView birdname = view.findViewById(R.id.birdnametext);
            birdname.setText(birds[i]);


            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(R.drawable.birdpics + i);

            gallery.addView(view);
        }


        editTextDesc = findViewById(R.id.edit_text_desc);
        editTextLoc = findViewById(R.id.edit_text_loc);
        editTextDateD = findViewById(R.id.edit_text_DateD);
        editTextDateM = findViewById(R.id.edit_text_DateM);
        editTextDateY = findViewById(R.id.edit_text_DateY);

        // Sets the back symbol as a button to go to home (this change is program wide)
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add new bird");

    }

    // Adds a bird to the database and navigates to the landing page
    private void SaveBird(){
        String name = editTextName.getText().toString();
        String desc = editTextDesc.getText().toString();
        String loc = editTextLoc.getText().toString();
        String dateD = editTextDateD.getText().toString();
        String dateM = editTextDateM.getText().toString();
        String dateY = editTextDateY.getText().toString();
        String date = dateD + "/" + dateM + "/" + dateY;

        Intent intent = new Intent(AddActivity.this, LandingActivity.class);

        Bird bird = new Bird(name,desc,loc,date);
        birdViewModel.insert(bird);

        Toast.makeText(this,"Sighting added", Toast.LENGTH_LONG).show();

        startActivity(intent);

    }

    // OnClick function for the submit button
    public void submitBird(View view) {
        SaveBird();
    }
}
