package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.udacity.aenima.androidjokes.Constants;
import com.udacity.aenima.androidjokes.ShowJokeActivity;
import com.udacity.aenima.javajokes.JokeDispenser;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        //Step 1
        //Toast.makeText(this, JokeDispenser.getAJoke(), Toast.LENGTH_SHORT).show();
        //Step 2
        //Intent intent = new Intent(this, ShowJokeActivity.class);
        //intent.putExtra(Constants.JOKE_STRING_EXTRA, JokeDispenser.getAJoke());
        //startActivity(intent);
        //Step 3
        RetrieveJokeAsyncTask task = new RetrieveJokeAsyncTask();
        task.execute(this);

    }


}
