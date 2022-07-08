package com.example.firebasetest.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebasetest.LoginActivity;
import com.example.firebasetest.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    Button button;
    Button button1;
    Button button2;

    MediaPlayer mp;

    Animation topAnim;
    ImageView image;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.logout);

        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intToMain = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intToMain);
        });



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        image = findViewById(R.id.imageView2);
        image.setAnimation(topAnim);

        mp = MediaPlayer.create(this, R.raw.blazer_rail);
        mp.setLooping(true);

        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);

        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(v -> openActivity2());
        button1.setOnClickListener(v -> openActivity3());
        button2.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        });
    }

    public void openActivity2() {
        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }
    public void openActivity3() {
        Intent intent = new Intent(this, com.example.firebasetest.View.Levels.class);
        startActivity(intent);
    }
    public void displayToast (View v) {
        Toast.makeText(MainActivity.this,"Maja Drama Queen", Toast.LENGTH_SHORT).show();
    }

}