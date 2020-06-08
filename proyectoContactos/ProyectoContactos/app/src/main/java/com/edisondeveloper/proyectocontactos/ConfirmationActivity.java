package com.edisondeveloper.proyectocontactos;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    private String name;
    private String date;
    private String phone;
    private String email;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        TextView txtName        = findViewById(R.id.txtName);
        TextView txtDate        = findViewById(R.id.txtDate);
        TextView txtPhone       = findViewById(R.id.txtPhone);
        TextView txtEmail       = findViewById(R.id.txtEmail);
        TextView txtDescription = findViewById(R.id.txtDescription);
        Button btnEdit = findViewById(R.id.btnEditDates);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            name        = extras.getString(MainActivity.EXTRA_NAME);
            date        = extras.getString(MainActivity.EXTRA_DATE);
            phone       = extras.getString(MainActivity.EXTRA_PHONE);
            email       = extras.getString(MainActivity.EXTRA_EMAIL);
            description = extras.getString(MainActivity.EXTRA_DESCRIPTION);
        }

        String formatDate         =  getString(R.string.confirmDate) + " " + date;
        String formatPhone        = getString(R.string.confirmCel) + " " + phone;
        String formatEmail        = getString(R.string.confirmEmail) + " " + email;
        String formatDescription  = getString(R.string.confirmDescrip) + " " + description;

        txtName.setText(name);
        txtDate.setText(formatDate);
        txtPhone.setText(formatPhone);
        txtEmail.setText(formatEmail);
        txtDescription.setText(formatDescription);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.action_bar);
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editarDatos = new Intent(ConfirmationActivity.this, MainActivity.class);
                editarDatos.putExtra(MainActivity.EXTRA_NAME, name);
                editarDatos.putExtra(MainActivity.EXTRA_DATE, date);
                editarDatos.putExtra(MainActivity.EXTRA_PHONE, phone);
                editarDatos.putExtra(MainActivity.EXTRA_EMAIL, email);
                editarDatos.putExtra(MainActivity.EXTRA_DESCRIPTION, description);
                startActivity(editarDatos);
                finish();
            }
        });

    }
}