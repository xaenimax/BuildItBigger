package com.udacity.aenima.androidjokes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import static com.udacity.aenima.androidjokes.Constants.JOKE_STRING_EXTRA;

public class ShowJokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(JOKE_STRING_EXTRA)){
            String joke = intent.getStringExtra(JOKE_STRING_EXTRA);
            TextView jokeTextView = findViewById(R.id.joke_text_tv);
            jokeTextView.setText(joke);
        }
    }

}
