package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.firebasetest.View.MainActivity;
import com.google.firebase.auth.FirebaseAuth;


import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                   // "(?=\\S+$)" +           //no white spaces
                    ".{4,}"               //at least 4 characters
                    //"$"
            );

    Button btnSignIn;
    FirebaseAuth mFirebaseAuth;

    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        btnSignIn = findViewById(R.id.button2);



            emailInput = findViewById(R.id.editText);
            passwordInput = findViewById(R.id.editText2);
        }

        private boolean validateEmail () {
            String emailId = emailInput.getText().toString().trim();

            if (TextUtils.isEmpty(emailInput.getText().toString())){
                emailInput.setError("Field can't be empty");
                return false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
                emailInput.setError("Please enter a valid email address");
                return false;
            } else {
                emailInput.setError(null);
                return true;
            }
        }

       

        private boolean validatePassword () {
            String passwordId = passwordInput.getText().toString().trim();

            if (TextUtils.isEmpty(passwordInput.getText().toString())) {
              passwordInput.setError("Field can't be empty");
                return false;
            } else if (!PASSWORD_PATTERN.matcher(passwordId).matches()) {
                passwordInput.setError("Password too weak");
                return false;
            }
                return true;
            }




        public void confirmInput (View v){
            if (!validateEmail() | !validatePassword()) {
                return;
            }

            String input = "Email: " + emailInput.getText().toString();
            input += "\n";
            input += "Password: " + passwordInput.getText().toString();

            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

            Intent intToMain = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intToMain);
        }



    }
