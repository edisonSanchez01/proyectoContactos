package com.edisondeveloper.proyectocontactos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.security.ConfirmationAlreadyPresentingException;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDate;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private EditText editTextDescription;
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_DATE = "date";
    public static final String EXTRA_PHONE = "phone";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_DESCRIPTION = "description";
    private Button buttonNext;
    private String textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.edtxtName);
        editTextDate = findViewById(R.id.edtxtDate);
        editTextPhone = findViewById(R.id.edtxtPhone);
        editTextEmail = findViewById(R.id.edtxtEmail);
        editTextDescription = findViewById(R.id.edtxtDescription);
        buttonNext = findViewById(R.id.btnNext);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            editTextName.setText(extras.getString(EXTRA_NAME));
            editTextDate.setText(extras.getString(EXTRA_DATE));
            editTextPhone.setText(extras.getString(EXTRA_PHONE));
            editTextEmail.setText(extras.getString(EXTRA_EMAIL));
            editTextDescription.setText(extras.getString(EXTRA_DESCRIPTION));
        }

        editTextDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                View currentFocus = MainActivity.this.getCurrentFocus();
                if(currentFocus != null){
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(),0);
                }
                obtenerFecha();
                return false;
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar();
            }
        });

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.action_bar);
        }

    }

    public void validar(){
        String name = editTextName.getText().toString();
        String date = editTextDate.getText().toString();
        String phone = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();
        String description = editTextDescription.getText().toString();
        if(name.length() < 10){
            editTextName.setError(getString(R.string.errorName));
        }else if(TextUtils.isEmpty(date)){
            editTextDate.setError(getString(R.string.errorDate));
        }else if(phone.length() < 10){
            editTextPhone.setError(getString(R.string.errorPhone));
        }else if(email.length() < 14){
            editTextEmail.setError(getString(R.string.errorEmail));
        }else if(!email.contains("@")){
            editTextEmail.setError(getString(R.string.errorEmail));
        }else{
            Intent confirmationActivity = new Intent(MainActivity.this, ConfirmationActivity.class);
            confirmationActivity.putExtra(EXTRA_NAME, name);
            confirmationActivity.putExtra(EXTRA_DATE, date);
            confirmationActivity.putExtra(EXTRA_PHONE, phone);
            confirmationActivity.putExtra(EXTRA_EMAIL, email);
            confirmationActivity.putExtra(EXTRA_DESCRIPTION, description);
            startActivity(confirmationActivity);
            finish();
        }
    }

    public void obtenerFecha(){
        Calendar calendar = Calendar.getInstance();
        int año = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int mesElejido = i1 + 1;
                String diaConFormato = i2 < 10 ? 0 + String.valueOf(i2) : String.valueOf(i2);
                String mesConFormato = mesElejido < 10 ? 0 + String.valueOf(mesElejido) : String.valueOf(mesElejido);
                textDate = diaConFormato + "/" + mesConFormato + "/" + i;
                editTextDate.setText(textDate);
            }
        },año, mes, dia);
        datePickerDialog.show();
    }

}